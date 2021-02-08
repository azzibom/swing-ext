package by.azzibom.gui.swing.entitytablemodel;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


/**
 * table model for entities on annotations.<br>
 * supports inserting, updating and deleting objects/rows.<br>
 * does not observe the change of a specific entity.<br>
 *
 * @author Ihar Misevich
 * @version 1.0
 */
public class EntityTableModel<E> extends AbstractTableModel implements TableModel, List<E> {

    /**
     * map of primitive types to wrapper types
     * */
    public static final Map<Class<?>, Class<?>> PRIMITIVE_MAP;
    static {
        Map<Class<?>, Class<?>> map = new HashMap<>(8);
        map.put(byte.class, Byte.class);
        map.put(short.class, Short.class);
        map.put(int.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(float.class, Float.class);
        map.put(double.class, Double.class);
        map.put(boolean.class, Boolean.class);
        map.put(char.class, Character.class);
        PRIMITIVE_MAP = Collections.unmodifiableMap(map);
    }
    /**
     * entity meta class for getting metadata
     * */
    private final Class<E> clazz;

    /**
     * data/rows list
     * */
    private final List<E> data;

    /**
     * cols list
     * */
    private final List<Col<E>> cols = new ArrayList<>();

    /**
     * col factory
     * */
    private final ColFactory factory = new ColFactory();

    public EntityTableModel(Class<E> clazz) {
        this(clazz, new ArrayList<>());
    }

    public EntityTableModel(Class<E> clazz, E[] data) {
        this(clazz, new ArrayList<>(Arrays.asList(data)));
    }

    public EntityTableModel(Class<E> clazz, List<E> data) {
        this.clazz = clazz;
        this.data = data;

        setFields(clazz);
    }

    protected void setFields(Class<E> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            setCol(f);
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            setCol(m);
        }
    }

    protected void setCol(AccessibleObject ao) {
        if (ao.isAnnotationPresent(JTableColumn.class)) {
            JTableColumn tableColumn = ao.getAnnotation(JTableColumn.class);
            int index = tableColumn.index();

            if (index != -1) {
                cols.add(index, factory.getA(ao));
            } else {
                cols.add(factory.getA(ao));
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return cols.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        Col<E> col = cols.get(columnIndex);
        return col.name();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Col<E> col = cols.get(columnIndex);
        return col.columnClass();
    }

    protected static Class<?> getPrimitiveWrapperClass(Class<?> type) {
        if (type.isPrimitive()) {
            return PRIMITIVE_MAP.get(type);
        }
        throw new IllegalArgumentException("type '" + type + "' is not primitive");
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        Col<E> col = cols.get(columnIndex);
        return col.isColEditable();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        E row = data.get(rowIndex);
        Col<E> col = cols.get(columnIndex);
        return col.get(row);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        E row = data.get(rowIndex);
        Col<E> col = cols.get(columnIndex);
        col.set(row, value);
        fireTableCellUpdated(rowIndex, columnIndex); // вызвать в методе set ??
    }


    // classes representing columns from fields and methods

    /**
     * col interface, describes table columns
     *
     * @author Ihar Misevich
     * @version 1.0
     * @since 1.0
     */
    interface Col<E> {

        /**
         * column name
         * @return column name
         * */
        String name();

        /**
         * get column value
         * @param row object for extraction value
         * @return column value
         * */
        Object get(E row);

        /**
         * set column value
         * @param row object to insert the value
         * @param value value to insert
         * */
        void set(E row, Object value);

        /**
         * column editable or not editable
         * @return true if column editable
         * */
        boolean isColEditable();

        /**
         * column value type
         * @return column value type
         * */
        Class<?> columnClass();
    }

    /**
     * implementation of a {@link Col} interface based on a class field
     *
     * @author Ihar Misevich
     * @version 1.0
     * @since 1.0
     */
    private class FieldCol<EE> implements Col<EE> {

        private final Field field;
        private final JTableColumn tableColumn;

        private String name;
        private Method read;
        private Method write;
        private Class<?> columnClass;
        private DataConverter converter;

        public FieldCol(Field field) {
            this.field = field;
            tableColumn = field.getAnnotation(JTableColumn.class);

            init();
        }

        private void init() {
            initName();
            initColumnClass();
            initReadMethod();
            initWriteMethod();
            initConverter();
        }

        private void initConverter() {
            Class<?> converterClass = tableColumn.converter();
            if (converterClass != void.class) {
                try {
                    converter = (DataConverter<?>) converterClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void initName() {
            name = tableColumn.name().equals("") ? field.getName() : tableColumn.name();
        }

        private void initColumnClass() {
            columnClass = tableColumn.columnClass() == void.class ? field.getType() : tableColumn.columnClass();

            if (columnClass.isPrimitive()) {
                columnClass = getPrimitiveWrapperClass(columnClass);
            }
        }

        private void initReadMethod() {
            try {
                read = EntityTableModel.this.clazz.getDeclaredMethod(tableColumn.readMethod());
            } catch (NoSuchMethodException e) {
                read = null;
            }
        }

        private void initWriteMethod() {
            try {
                Class<?> returnType = read != null ? read.getReturnType() : field.getType();
                write = EntityTableModel.this.clazz.getDeclaredMethod(tableColumn.writeMethod(), returnType);
            } catch (NoSuchMethodException e) {
                write = null;
            }
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public Object get(EE row) {
            try {
                Object res;
                if (read != null) {
                    read.setAccessible(true);
                    res = read.invoke(row);
                } else {
                    field.setAccessible(true);

                    res = field.get(row);
                }

                if (converter != null) {
                    res = converter.convert(res);
                }

                return res;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } finally {
                field.setAccessible(false);
            }
        }

        @Override
        public void set(EE row, Object value) {
            try {
                if (write != null) {
                    write.setAccessible(true);
                    write.invoke(row, value);
                } else {
                    field.setAccessible(true);
                    field.set(row, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } finally {
                field.setAccessible(false);
            }
        }

        @Override
        public boolean isColEditable() {
            return tableColumn.cellEditable();
        }

        @Override
        public Class<?> columnClass() {
            return columnClass;
        }
    }

    /**
     * implementation of a {@link Col} interface based on a method
     *
     * @author Ihar Misevich
     * @version 1.0
     * @since 1.0
     */
    private class MethodCol<EE> implements Col<EE> {

        private final Method method;
        private final JTableColumn tableColumn;

        private String name;
        private Method read;
        private Method write;
        private Class<?> columnClass;
        private DataConverter converter;

        public MethodCol(Method method) {
            this.method = method;
            tableColumn = method.getAnnotation(JTableColumn.class);

            init();
        }

        private void init() {
            initName();
            initColumnClass();
            initReadMethod();
            initWriteMethod();
            initConverter();
        }

        private void initConverter() {
            Class<?> clazzConverter = tableColumn.converter();
            if (clazzConverter != void.class) {
                try {
                    converter = (DataConverter<?>) clazzConverter.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void initName() {
            name = tableColumn.name().equals("") ? method.getName() : tableColumn.name();
        }

        private void initReadMethod() {
            read = method;
        }

        private void initWriteMethod() {
            try {
                write = EntityTableModel.this.clazz.getDeclaredMethod(tableColumn.writeMethod(), method.getReturnType());
            } catch (NoSuchMethodException e) {
                write = null;
            }
        }

        private void initColumnClass() {
            columnClass = tableColumn.columnClass() == void.class ?
                    method.getReturnType() : tableColumn.columnClass();

            if (columnClass.isPrimitive()) {
                columnClass = getPrimitiveWrapperClass(columnClass);
            }
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public Object get(EE row) {
            try {
                read.setAccessible(true);

                Object res = read.invoke(row);
                if (converter != null) {
                    res = converter.convert(res);
                }
                return res;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } finally {
                read.setAccessible(false);
            }
        }

        @Override
        public void set(EE row, Object value) {
            if (write != null) {
                try {
                    write.setAccessible(true);
                    write.invoke(row, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                } finally {
                    write.setAccessible(false);
                }
            } else {
                throw new RuntimeException("no such set value strategy");
            }
        }

        @Override
        public boolean isColEditable() {
            return write != null;
        }

        @Override
        public Class<?> columnClass() {
            return columnClass;
        }
    }

    /**
     * {@link Col} factory
     *
     * @author Ihar Misevich
     * @version 1.0
     * @since 1.0
     * */
    private class ColFactory {

        public Col<E> getA(AccessibleObject ao) {
            if (ao instanceof Field) {
                return new FieldCol<E>((Field) ao);
            } else if (ao instanceof Method) {
                return new MethodCol<E>((Method) ao);
            } else {
                throw new IllegalArgumentException("accessible object is not field or method");
            }
        }

    }


    // List methods

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return data.toArray(a);
    }

    @Override
    public boolean add(E e) {
        boolean res = data.add(e);
       if (res) fireTableDataChanged();
        return res;
    }

    @Override
    public boolean remove(Object o) {
        boolean res = data.remove(o);
        if (res) fireTableDataChanged();
        return res;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return data.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res =  data.addAll(c);
        if (res) fireTableDataChanged();
        return res;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean res = data.addAll(index, c);
        if(res) fireTableDataChanged();
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = data.removeAll(c);
        if (res) fireTableDataChanged();
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = data.retainAll(c);
        if (res) fireTableDataChanged();
        return res;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        data.replaceAll(operator);
        fireTableDataChanged();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        data.sort(c);
    }

    @Override
    public void clear() {
        data.clear();
        fireTableDataChanged();
    }

    @Override
    public boolean equals(Object o) {
        return data.equals(o);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public E get(int index) {
        return data.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = data.set(index, element);
        fireTableDataChanged();
        return e;
    }

    @Override
    public void add(int index, E element) {
        data.add(index, element);
        fireTableDataChanged();
    }

    @Override
    public E remove(int index) {
        E e =  data.remove(index);
        fireTableDataChanged();
        return e;
    }

    @Override
    public int indexOf(Object o) {
        return data.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return data.lastIndexOf(o);
    }

    /**
     * @version 1.0
     * @since 1.0
     * */
    protected class EntityTableListIterator<EE> implements ListIterator<EE>{

        private final ListIterator<EE> origin;

        public EntityTableListIterator(ListIterator<EE> origin) {
            this.origin = origin;
        }

        public EntityTableListIterator(List<EE> data) {
            this(data.listIterator());
        }

        public EntityTableListIterator(List<EE> data, int index) {
            this(data.listIterator(index));
        }

        @Override
        public boolean hasNext() {
            return origin.hasNext();
        }

        @Override
        public EE next() {
            return origin.next();
        }

        @Override
        public boolean hasPrevious() {
            return origin.hasPrevious();
        }

        @Override
        public EE previous() {
            return origin.previous();
        }

        @Override
        public int nextIndex() {
            return origin.nextIndex();
        }

        @Override
        public int previousIndex() {
            return origin.previousIndex();
        }

        @Override
        public void remove() {
            origin.remove();
            fireTableDataChanged();
        }

        @Override
        public void set(EE EE) {
            origin.set(EE);
            fireTableDataChanged();
        }

        @Override
        public void add(EE EE) {
            origin.add(EE);
            fireTableDataChanged();
        }

        @Override
        public void forEachRemaining(Consumer<? super EE> action) {
            origin.forEachRemaining(action);
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new EntityTableListIterator<>(data);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new EntityTableListIterator<E>(data, index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return data.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<E> spliterator() {
        return data.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        boolean res = data.removeIf(filter);
        if (res) fireTableDataChanged();
        return res;
    }

    @Override
    public Stream<E> stream() {
        return data.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return data.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        data.forEach(action);
    }
}
