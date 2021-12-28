package by.azzi.gui.swing.entitytablemodel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * аннотация для пометки полей/методов класса как колонок для {@link javax.swing.JTable}
 * при использовании модели таблицы {@link EntityTableModel}
 *
 * если поле помечено аннотацией, то считывание и установка значения происходит непосредственно через поле,
 * если не указаны методы акцесоры
 *
 * если метод помечен аннотацией, то вызывается непосредственно метод
 *
 * метод не должен принимать параметров
 *
 * @author Ihar Misevich
 * @version 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JTableColumn {

    /**
     * Имя колонки, по умолчанию имя поля/метода
     */
    String name() default "";

    /**
     * индекс колонки
     * если заданы одинаковые индексы на нескольких "колонках" последнее слово за последней по порядку
     */
    int index() default -1;

    /**
     * класс колонки, по умолчанию тип поля / возвращаемое значение метода,
     * что бы прикинуться другим типом
     *
     * ВНИМАНИЕ: не все типы могут быть безболезненно переведены в другие типы,
     * в этом случае вам может понадобится {@link JTableColumn#converter()}
     */
    Class<?> columnClass() default void.class;

    /**
     * конвертер исходного типа поля/метода в указанный
     *
     * можт быть полезно если указанный тип колонки
     */
    Class converter() default void.class;

    /**
     * редактируемое или не редактируемая колонка
     * если поле помечено как final этот аттрибут не имеет эффекта
     */
    boolean cellEditable() default false;

    /**
     * метод чтения значения
     * - если аннотация над методом, то аннотированный метод и является методом чтения,
     * а установка любого значения не дает эффекта
     */
    String readMethod() default "";

    /**
     * метод установки значения
     * если колонка помечена как редактируемая то при установке значения вызовется этот метод
     *
     * если аннотация установлена над методом по умолчанию поведение следующее:
     * - в случае произвольного имени метода: ищется метод с таким же именем, который принимает 1 параметр с типом
     * возвращаемого значения метода над которым находится аннотация (Например: int age() -> void age(int age))
     * - в случае имен методов с приставками get или is производится поиск метода c приставкой set, котоый принимает 1
     * параметр с типом возвращаемого значения метода над которым находится аннотация
     * (Например: int getAge() -> void setAge(int age))
     */
    String writeMethod() default "";
}
