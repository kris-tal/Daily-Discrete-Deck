package dailydescretedeck.set.views;

import dailydescretedeck.set.services.*;
import dailydescretedeck.set.viewmodels.BoardViewModel;
import dailydescretedeck.set.viewmodels.CardViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.min;

public class BoardView extends Pane {
    private BoardViewModel boardViewModel;
    private double gap;
    private ObservableList<CardViewModel> selectedCards = FXCollections.observableArrayList();
    private static long startTime = System.currentTimeMillis();
    private static Timeline timeline;
    private static boolean bylo = false;
    private Scenes scenes;
    private Money money;
    private boolean showSet;
    private ObservableList<CardViewModel> cardViewModels;

    public BoardView(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        this.scenes = new Scenes();
        this.money = new Money();
        this.showSet = false;
        this.cardViewModels = boardViewModel.cardsProperty();
        display();

        widthProperty().addListener((observable, oldValue, newValue) -> display());
        heightProperty().addListener((observable, oldValue, newValue) -> display());
    }

    private void display() {
        getChildren().clear();

        setStyle("-fx-background-color: thistle;");

        if (bylo) {
            startTime = System.currentTimeMillis();
            bylo = false;
        }

        double paneWidth = getWidth();
        double paneHeight = getHeight();

        if (paneWidth == 0 || paneHeight == 0) {
            return;
        }

        double square = min(paneWidth, paneHeight) * 0.09;
        double bigRectWidth = square * 9;
        double bigRectHeight = square * 7;

        double bigRectX = (paneWidth - bigRectWidth) / 2;
        double bigRectY = (paneHeight - bigRectHeight) / 2;

        Rectangle bigRect = new Rectangle(bigRectX, bigRectY, bigRectWidth, bigRectHeight);
        bigRect.setFill(Color.THISTLE);
        getChildren().add(bigRect);

        gap = square / 5;

        double cardWidth = square * 2;
        double cardHeight = square * 3;

        double startX = bigRectX + gap;
        double startY = bigRectY + gap / 2;

        int numberCards = boardViewModel.leftCards();
        Font font = new Font("Comic Sans MS", gap * 2);

        Label timeLabel = new Label();
        timeLabel.setFont(font);
        timeLabel.setLayoutX(gap);
        timeLabel.setLayoutY(gap * 7);
        getChildren().add(timeLabel);

        Runnable updateTime = () -> {
            long time = System.currentTimeMillis() - startTime;
            String timeString = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(time),
                    TimeUnit.MILLISECONDS.toSeconds(time) % 60);
            timeLabel.setText("time: " + timeString);
        };

        updateTime.run();

        Platform.runLater(() -> {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime.run()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });

        Label label1 = new Label("Cards left: " + numberCards + "/63");
        label1.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label1.setFont(font);
        label1.setLayoutX(gap);
        label1.setLayoutY(gap);
        getChildren().add(label1);

        Label label2 = new Label("Collected SETs: " + boardViewModel.getNumberSets());
        label2.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label2.setFont(font);
        label2.setLayoutX(gap);
        label2.setLayoutY(gap * 4);
        getChildren().add(label2);

        int cardIndex = 0;

        for (int row = 0; row < 2; row++) {
            double rowY = startY + row * (cardHeight + gap);
            startX += row * square;

            for (int col = 0; col < 4; col++) {
                if (cardIndex >= cardViewModels.size()) {
                    break;
                }

                CardViewModel cardViewModel = cardViewModels.get(cardIndex++);

                CardView cardView = new CardView(cardViewModel, 0, 0, square / 60);
                cardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (selectedCards.contains(cardViewModel)) {
                        selectedCards.remove(cardViewModel);
                    } else {
                        selectedCards.add(cardViewModel);
                    }
                });
                StackPane cardPane = new StackPane();
                cardPane.getChildren().add(cardView);

                cardPane.setLayoutX(startX + col * (cardWidth + gap));
                cardPane.setLayoutY(rowY);

                getChildren().add(cardPane);
            }
        }

        Button surrenderButton = new MyButton("Surrender");
        Button confirmButton = new MyButton("Confirm");
        Button cancelButton = new MyButton("Cancel");
        Button xorButton = new MyButton("XOR");
        Button backButton = new MyButton("Back to Menu");
        Button shuffleButton = new MyButton("Shuffle Cards");
        Button showButton = new MyButton("Show SET");

        Pane buttonsPane = new Pane();
        buttonsPane.getChildren().addAll(surrenderButton, confirmButton, cancelButton, shuffleButton, showButton, xorButton, backButton);
        buttonsPane.setPrefWidth(bigRectWidth);
        buttonsPane.setLayoutX(bigRectX);
        buttonsPane.setLayoutY(bigRectY + bigRectHeight + gap);

        double buttonWidth = (bigRectWidth - 50) / 5;
        double buttonHeight = bigRectHeight / 10;

        surrenderButton.setLayoutX(10);
        surrenderButton.setLayoutY(0);
        surrenderButton.setPrefWidth(buttonWidth);
        surrenderButton.setPrefHeight(buttonHeight);
        surrenderButton.setFont(Font.font("System", gap * 1.8));

        confirmButton.setLayoutX(20 + buttonWidth);
        confirmButton.setLayoutY(0);
        confirmButton.setPrefWidth(buttonWidth);
        confirmButton.setPrefHeight(buttonHeight);
        confirmButton.setFont(Font.font("System", gap * 1.8));

        cancelButton.setLayoutX(30 + 2 * buttonWidth);
        cancelButton.setLayoutY(0);
        cancelButton.setPrefWidth(buttonWidth);
        cancelButton.setPrefHeight(buttonHeight);
        cancelButton.setFont(Font.font("System", gap * 1.8));

        shuffleButton.setLayoutX(40 + 3 * buttonWidth);
        shuffleButton.setLayoutY(0);
        shuffleButton.setPrefWidth(buttonWidth);
        shuffleButton.setPrefHeight(buttonHeight);
        shuffleButton.setFont(Font.font("System", gap * 1.8));
        shuffleButton.setOnAction(event -> {
            boardViewModel.shuffleCards();
            BoardView newBoardView = new BoardView(boardViewModel);
            StackPane parent = (StackPane) getParent();
            parent.getChildren().remove(this);
            parent.getChildren().add(newBoardView);
        });

        showButton.setLayoutX(50 + 4 * buttonWidth);
        showButton.setLayoutY(0);
        showButton.setPrefWidth(buttonWidth);
        showButton.setPrefHeight(buttonHeight);
        showButton.setFont(Font.font("System", gap * 1.8));
        showButton.setOnAction(event -> {
            selectedCards.clear();
            selectedCards.addAll(boardViewModel.getSet());
            showSet = true;
            for (CardViewModel cardViewModel : selectedCards) {
                cardViewModel.getView().clicked();
                cardViewModel.getView().selectNotSelected();
            }
        });

        xorButton.setLayoutX(paneWidth - buttonWidth / 2 - gap);
        xorButton.setLayoutY(gap);
        xorButton.setPrefWidth(buttonWidth / 2);
        xorButton.setPrefHeight(buttonHeight);
        xorButton.setFont(Font.font("System", gap * 1.6));

        backButton.setLayoutX(paneWidth - buttonWidth - gap);
        backButton.setLayoutY(paneHeight - buttonHeight - gap);
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);
        backButton.setFont(Font.font("System", gap * 1.8));
        backButton.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }
            CardView.enableCards();
            bylo = true;
            scenes.showMenuView();
        });

        surrenderButton.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }

            selectedCards.clear();
            selectedCards.addAll(boardViewModel.getNotSet());

            for (CardViewModel cardViewModel : selectedCards) {
                CardView cv = cardViewModel.getView();
                cardViewModel.getView().selectNotSelected();
            }
            CardView.disableCards();
            confirmButton.setDisable(true);
            showButton.setDisable(true);
            surrenderButton.setDisable(true);
            cancelButton.setDisable(true);
            xorButton.setDisable(true);
            shuffleButton.setDisable(true);

            AestheticAlert.showAlert("Game Over", "You lost!");
        });

        confirmButton.setOnAction(event -> {
            if (boardViewModel.isSetOk(selectedCards, true)) {
                SetCollector setCollector = SetCollector.getInstance();
                End end = End.getInstance();
                TheBestTime theBestTime = TheBestTime.getInstance();
                if (SavingService.loadDateFromFile("saves/Date.txt") == null) {
                    SavingService.saveDateToFile("saves/Date.txt", LocalDate.now());
                    setCollector.resetsSets();
                    end.resetEnds();
                    theBestTime.resetTime();
                } else if (!SavingService.loadDateFromFile("saves/Date.txt").equals(LocalDate.now())) {
                    SavingService.saveDateToFile("saves/Date.txt", LocalDate.now());
                    setCollector.resetsSets();
                    end.resetEnds();
                    theBestTime.resetTime();
                    money.addMoney(5);
                }

                boolean ok = boardViewModel.removeCards(selectedCards);
                if (!showSet) {
                    money.addMoney(1);
                    setCollector.addSets(1);
                } else showSet = false;

                Map<LocalDate, Long> setsMap = SavingService.loadMapFromFile("saves/setsMap.txt");
                setsMap.put(LocalDate.now(), setCollector.getSets());
                SavingService.saveMapToFile("saves/setsMap.txt", setsMap);

                if (!ok) {
                    if (timeline != null) {
                        timeline.stop();
                    }
                    long t = SavingService.loadNumberFromFile("saves/theBestTime.txt");
                    long timeNow = System.currentTimeMillis() - startTime;
                    if (t == 0 || t > timeNow) {
                        theBestTime.newTime(timeNow);
                        SavingService.saveNumberToFile("saves/theBestTime.txt", timeNow);
                        Map<LocalDate, Long> timeMap = SavingService.loadMapFromFile("saves/timeMap.txt");
                        timeMap.put(LocalDate.now(), timeNow);
                        SavingService.saveMapToFile("saves/timeMap.txt", timeMap);
                        money.addMoney(10);
                    }
                    end.addEnds(1);
                    Map<LocalDate, Long> endsMap = SavingService.loadMapFromFile("saves/endsMap.txt");
                    endsMap.put(LocalDate.now(), end.getEnds());
                    SavingService.saveMapToFile("saves/endsMap.txt", endsMap);

                    confirmButton.setDisable(true);
                    showButton.setDisable(true);
                    surrenderButton.setDisable(true);
                    cancelButton.setDisable(true);
                    xorButton.setDisable(true);
                    shuffleButton.setDisable(true);

                    AestheticAlert.showAlert("Game over", "You won!");
                    return;
                }

                BoardView newBoardView = new BoardView(boardViewModel);
                StackPane parent = (StackPane) getParent();
                parent.getChildren().remove(this);
                parent.getChildren().add(newBoardView);
                CardView.enableCards();
                selectedCards.clear();
            } else {
                for (CardViewModel cardViewModel : cardViewModels) {
                    cardViewModel.getView().unclick();
                }
                CardView.enableCards();
                selectedCards.clear();
            }
        });

        cancelButton.setOnAction(event -> {
            for (CardViewModel cardViewModel : cardViewModels) {
                cardViewModel.getView().unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        xorButton.setOnAction(event -> {
            CardViewModel cardViewModel = boardViewModel.getXor(selectedCards);
            CardView cardView = new CardView(cardViewModel, 0, 0, square / (2 * 60));
            cardView.disableThisCard();
            StackPane cardPane = new StackPane();
            cardPane.getChildren().add(cardView);
            cardPane.setLayoutX(paneWidth - buttonWidth / 2 - gap);
            cardPane.setLayoutY(gap + buttonHeight + gap);
            getChildren().add(cardPane);
        });

        getChildren().add(buttonsPane);
        getChildren().add(xorButton);
        getChildren().add(backButton);
    }
}
