module dailydescretedeck.set {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens dailydescretedeck.set to javafx.fxml;
    opens dailydescretedeck.set.models to javafx.fxml;
    exports dailydescretedeck.set;
    exports dailydescretedeck.set.models;
    exports dailydescretedeck.set.viewmodels;
    opens dailydescretedeck.set.viewmodels to javafx.fxml;
    exports dailydescretedeck.set.views;
    opens dailydescretedeck.set.views to javafx.fxml;
}