package io.github.evanspendlove.todojavaappuri.model;

public class User
{
    // TODO: Handle SQL Injection by parsing values here.

    // Instance variables
    private String firstName;
    private String surname;
    private String username;
    private String password;
    private String gender;

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Overloaded Constructor

    public User()
    {

    }

    public User(String fname, String sname, String uname, String pwd, String gender)
    {
        this.firstName = fname;
        this.surname = sname;
        this.username = uname;
        this.password = pwd;
        this.gender = gender;
    }
}
