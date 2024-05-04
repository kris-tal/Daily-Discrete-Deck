module dailydescretedeck.set {
    requires javafx.controls;
    requires javafx.fxml;


    opens dailydescretedeck.set to javafx.fxml;
    exports dailydescretedeck.set;
}