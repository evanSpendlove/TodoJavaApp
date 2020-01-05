module TodoJavaApp
{
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires com.jfoenix;

    opens io.github.evanspendlove.todojavaappuri;
    opens io.github.evanspendlove.todojavaappuri.controller to javafx.fxml;
}