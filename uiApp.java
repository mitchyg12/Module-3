package com.example.uiapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;

public class uiApp extends Application {
    private static final String TEXT_FILE_NAME = "log.txt";
    private TextArea textArea;
    private BorderPane borderPane;
    private double hue;
    private MenuItem item3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        textArea = new TextArea();
        textArea.setWrapText(true);
        borderPane = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);

        primaryStage.setTitle("User Interface App");
        primaryStage.setScene(new Scene(borderPane, 500, 300));
        primaryStage.show();

        Random random = new Random();
        hue = random.nextDouble() * (120 - 85) + 85;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Options");

        MenuItem item1 = new MenuItem("Print Date and Time");
        item1.setOnAction(e -> printDateTime());

        MenuItem item2 = new MenuItem("Write to Log File");
        item2.setOnAction(e -> writeToLogFile());

        item3 = new MenuItem("Change Background Color");
        item3.setOnAction(e -> changeBackgroundColor());

        MenuItem item4 = new MenuItem("Exit");
        item4.setOnAction(e -> System.exit(0));

        menu.getItems().addAll(item1, item2, item3, item4);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void printDateTime() {
        LocalDateTime now = LocalDateTime.now();
        textArea.appendText(now + "\n");
    }

    private void writeToLogFile() {
        try (PrintWriter out = new PrintWriter(TEXT_FILE_NAME)) {
            out.println(textArea.getText());
        } catch (IOException e) {
            textArea.appendText("error: " + e.getMessage() + "\n");
        }
    }

    private void changeBackgroundColor() {
        textArea.setStyle("-fx-control-inner-background: hsb(" + hue + ", 100%, 100%);");
        item3.setText("Change Background Color (Current Hue: " + hue + ")");
    }
}
