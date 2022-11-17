module com.coursemngsys.coursemanagementsystem {
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires com.google.gson;
    requires mysql.connector.java;

    opens com.coursemngsys.coursemanagementsystem;
    exports com.coursemngsys.coursemanagementsystem;
    exports com.coursemngsys.coursemanagementsystem.Model to org.hibernate.orm.core, java.persistence;
    opens com.coursemngsys.coursemanagementsystem.Model to org.hibernate.orm.core;
    exports com.coursemngsys.coursemanagementsystem.fxControllers;
    opens com.coursemngsys.coursemanagementsystem.fxControllers to javafx.fxml;
    exports com.coursemngsys.coursemanagementsystem.temp;
    opens com.coursemngsys.coursemanagementsystem.temp to javafx.fxml;
}