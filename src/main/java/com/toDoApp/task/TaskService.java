package com.toDoApp.task;


import com.toDoApp.exceptions.customExceptions.BadRequestException;
import com.toDoApp.exceptions.customExceptions.ForbiddenException;
import com.toDoApp.exceptions.customExceptions.NotFoundException;
import com.toDoApp.user.User;
import com.toDoApp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;



    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public List<Task> getUserTasks(){



        User user = userRepository.findByEmail(getUsername()).orElseThrow(
                () -> new NotFoundException("User not found with id:" + getUsername())
        );

        List<Task> tasks = taskRepository.findByUser(user).orElseThrow(
                () -> new NotFoundException("User with no tasks")
        );



        return tasks;

    }




    public Task findUserTaskById(Long taskId) {




        Task taskFinded = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException("Task not found")
        );


        if(!getUsername().equals(taskFinded.getUser().getEmail())) {
            throw new ForbiddenException("You are not allowed to access this task");
        }

        return taskFinded;


    }



    public Task createTask(Task task) {

        User user = userRepository.findByEmail(getUsername()).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        task.setUser(user);
        task.setStatus(false);
        task.setDateAdded(LocalDate.now());
        return taskRepository.save(task);
    }


    public Task updateTask(Long id,Task task) {




        Task existingTask = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Task not found with id: " + id)
        );


        if(!getUsername().equals(existingTask.getUser().getEmail())) {
            throw new ForbiddenException("You are not allowed to access this task");
        }


        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);




    }


    public void deleteTask(Long taskId) {


        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException(("Task not found with id" + taskId))
        );


        if(!getUsername().equals(task.getUser().getEmail())) {
             throw new ForbiddenException("You are not allowed");

        }

        taskRepository.deleteById(taskId);
    }




}
