# Todo Full Stack App
> Full stack Java Todo App with JavaFX front-end and MySQL back-end

Key Features:
- User account support with login and sign up options
- Tasks are stored per User
- Task fields are: Name, Description, Date Created
- Tasks can be update through UI
- All data is stored in a MySQL Database.

## Development Setup / Installation

OS X, Linux & Windows:

```sh
Prerequisites:
- Install JDK 11
- Install JavaFX for JDK 11
- Install JFoenix 9.0.0 (optional, as included through Maven)
- Install MySQL Connector for Java 8.0.18

Frameworks:
- This project uses Maven.

Backend:
- Please update the config.properties file with your database credentials.
- Please update the Constants.java file with your database details for accessing it, if necessary.

Note: This project was designed in IntelliJ IDEA.
```

## Release History

* 1.0.1
    * CHANGE: Added full JavaFX UI support.
    * CHANGE: Added logout feature and back buttons
    * CHANGE: Update documentation
    * CHANGE: Changed database credentials to be stored in config.properties file
* 1.0.0
    * Initial release

## Meta

Evan Spendlove

Distributed under the MIT license. See ``LICENSE`` for more information.

[https://github.com/evanSpendlove]

## Contributing

1. Fork it (<https://github.com/evanSpendlove/TodoJavaApp/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
