package com.todo.client.view;

import com.todo.client.Task;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    List<Task> tasks = new ArrayList<>();

    public MainView() {
     VerticalLayout verticalLayout = new VerticalLayout();
     TextField taskField = new TextField();
     Button createTask = new Button("Add task");

     createTask.addClickListener(click -> {
         Task task = Task.builder()
                 .task(taskField.getValue())
                 .done(false)
                 .build();
         Checkbox checkbox = new Checkbox(task.getTask());
         checkbox.addClickListener(checkboxClickEvent -> {
             if (checkbox.getValue()) {
                 task.setDone(true);
                 checkbox.setVisible(false);
                 System.out.println(task);
             }

         });

         System.out.println(task);
         verticalLayout.add(checkbox);
     });

     createTask.addClickShortcut(Key.ENTER);


     add(
             new H1("Tasks"),

             verticalLayout,
             taskField,
             createTask);
    }
}
