# EntityTableModel

`JTable` Swing table model on annotations, for mapping entity classes to a table.

## Using

- include library in your project
    - as [maven dependency](https://github.com/azzibom/swing-ext/packages)
    - or other way
- annotated your data class fields and methods `@JTableColumn` annotation
- create instance `EntityTableModel` and set your table

## Compile and packaging

Using `Maven`
in project root directory execute `mvn clean package`.
