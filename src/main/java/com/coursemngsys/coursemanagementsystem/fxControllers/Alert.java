package com.coursemngsys.coursemanagementsystem.fxControllers;

public class Alert {
    static javafx.scene.control.Alert alert;

    public static void alertMessage(String s) {
        alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("");
        alert.setContentText(s);
        alert.showAndWait();
    }
    public static void alertError(String s) {
        alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(s);
        alert.showAndWait();
    }
}