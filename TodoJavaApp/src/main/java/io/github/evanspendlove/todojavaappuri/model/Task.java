package io.github.evanspendlove.todojavaappuri.model;

import java.sql.Timestamp;

// File completed so far.

public class Task
{
    // Instance variables
    private int userId;
    private int taskId;
    private String task;
    private Timestamp dateCreated;
    private String description;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Overloaded Constructor

    public Task()
    {

    }

    public Task(String task, Timestamp dateCreated, String description, int userId)
    {
        this.task = task;
        this.dateCreated = dateCreated;
        this.description = description;
        this.userId = userId;
    }
}
