package io.github.evanspendlove.todojavaappuri.database;

import io.github.evanspendlove.todojavaappuri.model.Task;
import io.github.evanspendlove.todojavaappuri.model.User;

import java.sql.*;

//File Completed so far

public class DatabaseHandler extends Configs
{
    Connection dbConnection;

    // Get Database Connection
    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }


    // User Methods

    // Sign up a new user
    public void signUpUser(User user)
    {
        // Create a new SQL Insert Query statement
        String insert = "INSERT INTO " + Constants.USERS_TABLE + "("
                + Constants.USERS_FIRSTNAME + "," + Constants.USERS_SURNAME + "," + Constants.USERS_USERNAME + ","
                + Constants.USERS_PASSWORD + "," + Constants.USERS_GENDER + ") " + "VALUES(?,?,?,?,?)";

        try
        {
            // Create a new preparedStatement
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            // Fill the VALUES(...) section of the string with the values passed from the user
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender());

            preparedStatement.executeUpdate();
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    // Get the user's details
    public ResultSet getUser(User user)
    {
        ResultSet result = null;

        if(!user.getUsername().equals("") && !user.getPassword().equals("")) // If both fields are not empty
        {
            // Create new SQL SELECT Query
            String query = "SELECT * FROM " + Constants.USERS_TABLE + " WHERE "
                    + Constants.USERS_USERNAME + "=?" + " AND "
                    + Constants.USERS_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());

                result = preparedStatement.executeQuery();
            }
            catch(SQLException | ClassNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            // Throw an exception for now, need to add UI element to prompt user of this issue
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return result;
    }

    // Get Number of tasks open for this user
    public int getAllTasks(int userID)
    {
        // Create new select query
        String selectAllQuery = "SELECT * FROM " + Constants.TASKS_TABLE + " WHERE "
                + Constants.USERS_USERID + " =?";

        try
        {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(selectAllQuery);
            preparedStatement.setInt(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.last()) // If there are more than 0 rows
            {
                return resultSet.getRow(); // Return the number of rows in the table, i.e. number of tasks
            }
            else // Otherwise, empty resultSet
            {
                return 0; // Return 0 tasks
            }
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(SQLException ex2)
        {
            ex2.printStackTrace();
        }

        return -1; // Error code

    }

    // Get tasks per user
    public ResultSet getTasksByUser(int userId)
    {
        // Create new SQL Select Query
        String selectUsersTasksQuery = "SELECT * FROM " + Constants.TASKS_TABLE
                + " WHERE " + Constants.USERS_USERID + " =?";

        ResultSet result = null;

        try
        {
            // Create new Prepared Statement and fill values
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(selectUsersTasksQuery);
            preparedStatement.setInt(1, userId);

            // Store result of executed query
            result = preparedStatement.executeQuery();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex2)
        {
            ex2.printStackTrace();
        }

        // Return ResultSet of tasks
        return result;
    }


    // Task Methods

    // Insert task
    public void insertTask(Task task)
    {
        // Create new insert query
        String insertTaskQuery = "INSERT INTO " + Constants.TASKS_TABLE + " ("
                + Constants.USERS_USERID + "," + Constants.TASKS_TASK + ","
                + Constants.TASKS_DATECREATED + "," + Constants.TASKS_DESCRIPTION + ")"
                + "VALUES(?,?,?,?)";

        try
        {
            // Create new prepared statement
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertTaskQuery);

            // Fill the VALUES(...) with values
            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setTimestamp(3, task.getDateCreated());
            preparedStatement.setString(4, task.getDescription());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();;
        }
        catch (ClassNotFoundException ex2)
        {
            ex2.printStackTrace();
        }
    }

    // Update task
    public void updateTask(Task task)
    {
        // Create new SQL Update Query
        String taskUpdateQuery = "UPDATE " + Constants.TASKS_TABLE + " SET "
                + Constants.TASKS_TASK + " =? ," + Constants.TASKS_DATECREATED + " =? ,"
                + Constants.TASKS_DESCRIPTION + " =? " + "WHERE "
                + Constants.TASKS_TASKID + " =? " + "AND " + Constants.USERS_USERID + " =?";

        try
        {

            // Create new Prepared Statement
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(taskUpdateQuery);

            // Fill values to be set
            preparedStatement.setString(1, task.getTask());
            preparedStatement.setTimestamp(2, task.getDateCreated());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setInt(4, task.getTaskId());
            preparedStatement.setInt(5, task.getUserId());

            // Execute and close
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex2)
        {
            ex2.printStackTrace();
        }
    }

    // Delete task
    public void deleteTask(int userId, int taskId)
    {
        String deleteTaskQuery = "DELETE FROM " + Constants.TASKS_TABLE + " WHERE "
                + Constants.USERS_USERID + " =? " + " AND "
                + Constants.TASKS_TASKID + " =?";

        try
        {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(deleteTaskQuery);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, taskId);

            preparedStatement.execute();
            preparedStatement.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex2)
        {
            ex2.printStackTrace();
        }
    }
}
