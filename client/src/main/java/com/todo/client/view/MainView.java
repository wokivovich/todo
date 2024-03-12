package com.todo.client.view;

import com.todo.client.Task;
import com.todo.client.request.TaskRequest;
import com.todo.client.service.RequestSender;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private final RequestSender requestSender;

    public MainView(RequestSender requestSender) {
        this.requestSender = requestSender;

     VerticalLayout verticalLayout = new VerticalLayout();
     TextField taskField = new TextField();
     List<Task> tasks = requestSender.getUserActiveTasks(1L);
     Button addTask = new Button("Add task");

     addTask.addClickListener(click -> {
         if (!taskField.getValue().isEmpty()) {
             requestSender.createTask(1L, new TaskRequest(taskField.getValue()));

             UI.getCurrent().getPage().reload();
         }
     });
     addTask.addClickShortcut(Key.ENTER);

     tasks.forEach(task -> {
         Checkbox checkbox = new Checkbox(task.getDescription());

         checkbox.addClickListener(click -> {
             requestSender.completeTask(task.getId());
             checkbox.setVisible(false);
         });
         verticalLayout.add(checkbox);
     });
        HorizontalLayout addTaskBlock = new HorizontalLayout();
        addTaskBlock.add(taskField);
        addTaskBlock.add(addTask);

     add(
             new H1("Tasks"),

             verticalLayout,
             addTaskBlock
     );
    }
}
