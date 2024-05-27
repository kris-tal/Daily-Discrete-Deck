package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.viewmodels.BoardViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dailydescretedeck.set.services.SetCollector;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

import static java.lang.Double.min;

public class BoardView extends Pane {
    private BoardViewModel viewModel;
    private double gap;
    private ObservableList<Card> selectedCards = FXCollections.observableArrayList();
    private Map<Card, CardView> cardViews = new HashMap<>();
    private SetCollector setCollector;
    private boolean xorSelected = false;
    private Rectangle xorRectangle;
    private double buttonWidth;
    private double buttonHeight;
    private double square;
    private double cardWidth;
    private double cardHeight;
    private StackPane xorButtonPane;

    public BoardView(BoardViewModel bViewModel) {
        this.viewModel = bViewModel;
        this.setCollector = SetCollector.getInstance();

        initializeComponents();

        Platform.runLater(this::redrawBoard);

        widthProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
        heightProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
    }

    private void initializeComponents() {
        xorRectangle = new Rectangle();
        xorRectangle.setFill(Color.LIGHTGRAY);
        xorRectangle.setArcWidth(10);
        xorRectangle.setArcHeight(10);
        xorRectangle.setStroke(Color.BLACK);
        xorRectangle.setStrokeWidth(2);

        Label xorLabel = new Label("XOR");
        xorLabel.setFont(Font.font("System", 16));
        xorLabel.setTextFill(Color.BLACK);
        xorButtonPane = new StackPane(xorRectangle, xorLabel);

        xorButtonPane.setOnMouseEntered(event -> xorRectangle.setFill(darkenColor((Color) xorRectangle.getFill(), 0.1)));
        xorButtonPane.setOnMouseExited(event -> {
            if (!xorSelected) {
                xorRectangle.setFill(Color.LIGHTGRAY);
            }
        });

        xorButtonPane.setOnMouseClicked(event -> selectXor());

        getChildren().add(xorButtonPane);
    }

    public void redrawBoard() {
        getChildren().clear();

        double paneWidth = getWidth();
        double paneHeight = getHeight();

        if (paneWidth == 0 || paneHeight == 0) {
            return;
        }

        square = min(paneWidth, paneHeight) * 0.09;
        double bigRectWidth = square * 9;
        double bigRectHeight = square * 7;

        double bigRectX = (paneWidth - bigRectWidth) / 2;
        double bigRectY = (paneHeight - bigRectHeight) / 2;

        Rectangle bigRect = new Rectangle(bigRectX, bigRectY, bigRectWidth, bigRectHeight);
        bigRect.setFill(Color.THISTLE);
        getChildren().add(bigRect);

        gap = square / 5;

        cardWidth = square * 2;
        cardHeight = square * 3;

        double startX = bigRectX + gap;
        double startY = bigRectY + gap / 2;

        int numberCards = viewModel.getBoard().getDeck().size() + viewModel.getBoard().getCards().size();
        Font font = new Font("System", gap * 1.8);

        Label cardsLeftLabel = new Label("Cards left: " + numberCards + "/63");
        cardsLeftLabel.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        cardsLeftLabel.setFont(font);
        cardsLeftLabel.setLayoutX(gap);
        cardsLeftLabel.setLayoutY(gap);
        getChildren().add(cardsLeftLabel);

        Label collectedSetsLabel = new Label("Collected SETs: " + viewModel.getBoard().getNumberSets());
        collectedSetsLabel.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        collectedSetsLabel.setFont(font);
        collectedSetsLabel.setLayoutX(gap);
        collectedSetsLabel.setLayoutY(gap * 4);
        getChildren().add(collectedSetsLabel);

        int cardIndex = 0;

        for (int row = 0; row < 2; row++) {
            double rowY = startY + row * (cardHeight + gap);
            startX += row * square;

            for (int col = 0; col < 4; col++) {
                if (cardIndex >= viewModel.cardsProperty().size()) {
                    break;
                }

                Card card = viewModel.cardsProperty().get(cardIndex++);

                CardView cardView = new CardView(card, 0, 0, square / 60);
                cardViews.put(card, cardView);
                cardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (selectedCards.contains(card)) {
                        selectedCards.remove(card);
                    } else {
                        selectedCards.add(card);
                    }
                });
                StackPane cardPane = new StackPane();
                cardPane.getChildren().add(cardView);

                cardPane.setLayoutX(startX + col * (cardWidth + gap));
                cardPane.setLayoutY(rowY);

                getChildren().add(cardPane);
            }
        }

        buttonWidth = (bigRectWidth - 40) / 4;
        buttonHeight = bigRectHeight / 10;

        Button surrenderButton = new Button("surrender");
        Button confirmButton = new Button("confirm");
        Button cancelButton = new Button("cancel");

        Pane buttonsPane = new Pane();
        buttonsPane.getChildren().addAll(surrenderButton, confirmButton, cancelButton);
        buttonsPane.setPrefWidth(bigRectWidth);
        buttonsPane.setLayoutX(bigRectX);
        buttonsPane.setLayoutY(bigRectY + bigRectHeight + gap);

        xorRectangle.setWidth(buttonWidth / 2);
        xorRectangle.setHeight(buttonHeight);

        xorButtonPane.setLayoutX(paneWidth - buttonWidth / 2 - gap);
        xorButtonPane.setLayoutY(gap);

        surrenderButton.setLayoutX(10);
        surrenderButton.setLayoutY(0);
        surrenderButton.setPrefWidth(buttonWidth);
        surrenderButton.setPrefHeight(buttonHeight);
        surrenderButton.setFont(Font.font("System", gap * 1.8));
        surrenderButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        confirmButton.setLayoutX(20 + buttonWidth);
        confirmButton.setLayoutY(0);
        confirmButton.setPrefWidth(buttonWidth);
        confirmButton.setPrefHeight(buttonHeight);
        confirmButton.setFont(Font.font("System", gap * 1.8));
        confirmButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        cancelButton.setLayoutX(30 + 2 * buttonWidth);
        cancelButton.setLayoutY(0);
        cancelButton.setPrefWidth(buttonWidth);
        cancelButton.setPrefHeight(buttonHeight);
        cancelButton.setFont(Font.font("System", gap * 1.8));
        cancelButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        surrenderButton.setOnAction(event -> {
            selectedCards.clear();
            selectedCards.addAll(viewModel.getNotSet());

            for (Card card : selectedCards) {
                CardView cardView = cardViews.get(card);
                cardView.selectNotSelected();
            }
            CardView.disableCards();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("You lost!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        });

        confirmButton.setOnAction(event -> {
            if (viewModel.isSetOk(selectedCards)) {
                SetCollector setCollector = SetCollector.getInstance();
                setCollector.addSets(1);
                System.out.println("Zapisano ilość zebranych SETów: " + setCollector.getSets());

                try {
                    java.time.LocalDate currentDate = java.time.LocalDate.now();
                    String setsCollected = String.valueOf(setCollector.getSets());
                    String dataToWrite = "Date: " + currentDate + ", Sets Collected: " + setsCollected;

                    String fileName = "setsCollected.txt";
                    java.nio.file.Path path = java.nio.file.Paths.get(fileName);

                    List<String> lines = new ArrayList<>();
                    lines.add(dataToWrite);

                    java.nio.file.Files.write(path, lines, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                viewModel.removeCards(selectedCards);
                System.out.println("Znaleziono SET");
                BoardView newBoardView = new BoardView(viewModel);
                StackPane parent = (StackPane) getParent();
                parent.getChildren().remove(this);
                parent.getChildren().add(newBoardView);
                CardView.enableCards();
                selectedCards.clear();
            } else {
                System.out.println("No SET found");
            }
        });

        cancelButton.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Cancel");
            for (Card card : viewModel.cardsProperty()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        getChildren().add(buttonsPane);
        getChildren().add(xorButtonPane);

        System.out.println("XOR button pane added at layoutX: " + xorButtonPane.getLayoutX() + " layoutY: " + xorButtonPane.getLayoutY());
    }

    private void selectXor() {
        System.out.println("selectXor called");
        xorRectangle.setFill(darkenColor((Color) xorRectangle.getFill(), 0.5));
        xorSelected = true;
        xorRectangle.setFill(Color.BLACK);

        System.out.println("Kliknięto w prostokąt XOR");
        Card card = viewModel.getXor(selectedCards);
        System.out.println("Card created: " + card);
        CardView cardView = new CardView(card, 0, 0, min(getWidth(), getHeight()) * 0.09 / 60);
        System.out.println("CardView created: " + cardView);
        cardView.disableThisCard();
        StackPane cardPane = new StackPane();
        cardPane.getChildren().add(cardView);
        cardPane.setLayoutX(getWidth() - gap - cardView.getWidth());
        cardPane.setLayoutY(gap + xorRectangle.getHeight() + gap);
        getChildren().add(cardPane);
        System.out.println("CardView added to the pane");
    }

    private Color darkenColor(Color color, double factor) {
        return new Color(
                Math.max(0, color.getRed() - factor),
                Math.max(0, color.getGreen() - factor),
                Math.max(0, color.getBlue() - factor),
                color.getOpacity()
        );
    }
}
