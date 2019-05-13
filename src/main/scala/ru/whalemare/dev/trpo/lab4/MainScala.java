package ru.whalemare.dev.trpo.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class MainScala extends Application {

    public MainScala() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scene-scala.fxml"));
        primaryStage.setTitle("BinaryTreeViewScala");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
