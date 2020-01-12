package io.github.evanspendlove.todojavaappuri.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configs
{
    // Instance variables
    protected static String dbHost = null;
    protected static String dbPort = null;
    protected static String dbUser = null;
    protected static String dbPass = null;
    protected static String dbName = null;

    private static Properties propertiesFile = null;

    public static void initialiseConfigs()
    {
        readConfigFile();
        loadConfigs();
    }

    private static void readConfigFile()
    {
        // Instance variables
        InputStream readFile = null;
        Properties dbProperties = null;

        try
        {
            dbProperties = new Properties(); // Initialise dbProperties
            readFile = Configs.class.getResourceAsStream("/config.properties"); // Read from config.properties

            if (readFile != null) // If the read was successful
            {
                // Load properties into dbProperties
                dbProperties.load(readFile);
            }
            else // Otherwise it couldn't be read
            {
                throw new FileNotFoundException("Property file for configuring Database connection " + " not found in the classpath"); // Throw appropriate exception
            }

        }
        // Catch Exceptions
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex2)
        {
            ex2.printStackTrace();
        }
        finally // Close the file stream
        {
            try
            {
                readFile.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }

        setPropertiesFile(dbProperties);  // Set the Properties file with loaded Properties object
    }

    private static void loadConfigs()
    {
        if(propertiesFile != null)
        {
            // Set the class variables as per the Properties object loaded from the config file
            dbHost = propertiesFile.getProperty("host");
            dbUser = propertiesFile.getProperty("username");
            dbPass = propertiesFile.getProperty("password");
            dbPort = propertiesFile.getProperty("port");
            dbName = propertiesFile.getProperty("databaseName");
        }
        else
        {
            System.out.println("Error occurred!");
        }
    }

    public static void setPropertiesFile(Properties propFile)
    {
        propertiesFile = propFile;
    }
}
