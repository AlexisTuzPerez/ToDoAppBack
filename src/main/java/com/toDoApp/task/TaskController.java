package com.toDoApp.task;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;





    @GetMapping
    public ResponseEntity<List<Task>>  getTasks(){
        List<Task> tasks = taskService.getUserTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId) {

        Task task = taskService.findUserTaskById(taskId);
        return ResponseEntity.ok(task);


    }


    @PostMapping
    public ResponseEntity<Task> addTask( @Valid @RequestBody Task task){

        Task savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);



    }


    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {

        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);

    }



    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }









}
