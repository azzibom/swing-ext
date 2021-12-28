package by.azzi.gui.swing.entitytablemodel;

import javax.swing.*;
import java.awt.*;

public class Example {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("entityTableModelExample");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        final EntityTableModel<Todo> tableModel = new EntityTableModel<>(Todo.class);
        tableModel.add(new Todo("qwe"));
        tableModel.add(new Todo("asd"));
        tableModel.add(new Todo("zxc"));

        frame.add(new JScrollPane(new JTable(tableModel)));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class Todo {

        @JTableColumn(cellEditable = true)
        private String title;
        private boolean done;

        public Todo(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @JTableColumn(cellEditable = true)
        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }
    }
}


