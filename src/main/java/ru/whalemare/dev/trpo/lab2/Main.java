package ru.whalemare.dev.trpo.lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.whalemare.dev.trpo.lab4.JavaController;

/**
 * @author Mikhail Medvedev
 * @since 2019
 */
public class Main extends Application {

    public Main() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LinkedDequeView.controller = new JavaController();
        Parent root = FXMLLoader.load(getClass().getResource("/scene.fxml"));
        primaryStage.setTitle("LinkedDequeView");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
