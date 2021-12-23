# StringCellRenderer

Swing `*CellRenderer` implementation for convert entity to string for `JTable` and `JList` and/or `JComboBox`.

## Using

- include library in you project
    - as [maven dependency](https://github.com/azzibom/swing-ext/packages)
    - or other way
- create `CellRendererStringConverter` for convert your entity to string
- create `StringListCellRenderer` (for `JList` or `JComboBox`) and/or `StringTableCellRenderer` (
  for `JTable`)
- set your `CellRendererStringConverter` to your `StringListCellRenderer` and/or `StringTableCellRenderer`
- set your `StringListCellRenderer` and/or `StringTableCellRenderer` to `JList` and/or `JComboBox`
  and/or `JTable`

## Compile and packaging

Using `Maven` in project root directory execute `mvn clean package`.
