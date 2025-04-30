package com.toDoApp.task;


import com.toDoApp.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @Column(name= "title", nullable = false)
    private String title;


    @Column(name ="description", nullable = false)
    private String description;


    @Enumerated(EnumType.STRING)
    @Column(name="priority", nullable = false)
    private Priority priority;




    @Column(name ="date_added")
    private LocalDate dateAdded;


    @Future(message ="Future")
    @Column(name="due_date", nullable = false)
    private LocalDate dueDate;


    @Column(name="status", nullable = false)
    private Boolean status;







}
