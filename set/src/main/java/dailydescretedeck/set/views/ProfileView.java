package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.CalendarViewModel;
import dailydescretedeck.set.viewmodels.MenuViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import dailydescretedeck.set.viewmodels.ProfileViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileView extends StackPane {
    private final ProfileViewModel profileViewModel;
    private CalendarView calendarView;
    Stage stage;


    public ProfileView(Stage stage) {
        this.profileViewModel = new ProfileViewModel();
        this.calendarView = new CalendarView();
        this.stage = stage;
        Label headerLabel = new Label("Profile");
        VBox layout = new VBox(headerLabel, calendarView);
        headerLabel.setFont(Font.font("System Bold", 20));
        VBox.setMargin(headerLabel, new javafx.geometry.Insets(10, 0, 10, 0));
        layout.setPadding(new Insets(30));

         Button menuButton = new Button("Menu");
        double buttonWidth = 100; 
        double buttonHeight = 50; 
        double gap = 10; 
        menuButton.setLayoutX(getWidth() - buttonWidth + 50);
        menuButton.setLayoutY(getHeight() - buttonHeight);
        menuButton.setPrefWidth(buttonWidth * 0.5);
        menuButton.setPrefHeight(buttonHeight * 0.5);
        menuButton.setFont(Font.font("System", gap * 1));
        menuButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        menuButton.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Menu");
            MenuViewModel menuViewModel = new MenuViewModel();
            MenuView menuView = new MenuView(menuViewModel, stage);
            Scene scene = new Scene(menuView, 1000, 800);
            scene.getRoot().setStyle("-fx-background-color: thistle;");
            stage.setScene(scene);
            stage.setTitle("Set");
            stage.show();

            menuView.display();
        });

        getChildren().add(layout);
        getChildren().add(menuButton);
    }

    public ProfileView(Stage stage, ProfileViewModel pvm) {
        this.profileViewModel = pvm;
        this.calendarView = new CalendarView();
        this.stage = stage;
        Label headerLabel = new Label("Profile");
        headerLabel.setFont(Font.font("System Bold", 20));
        VBox layout = new VBox(headerLabel, calendarView);
        VBox.setMargin(headerLabel, new javafx.geometry.Insets(10, 0, 10, 0));
        layout.setPadding(new Insets(30));
        getChildren().add(layout);
    }

    public void display() {      //jak to zrobic
        Scene scene = new Scene(this, stage.getWidth(),stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
