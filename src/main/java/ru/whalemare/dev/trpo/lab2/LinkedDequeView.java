package ru.whalemare.dev.trpo.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author Mikhail Medvedev
 * @since 2019
 */
public class LinkedDequeView implements Controller.Renderable {

    @FXML
    private Button buttonAddHead;

    @FXML
    private Button buttonAddLast;

    @FXML
    private Button buttonRemoveHead;

    @FXML
    private Button buttonRemoveLast;

    @FXML
    private TextField editField;

    @FXML
    private TextArea textOutput;

    @FXML
    private TextArea textOutputInfo;

    public static Controller controller;

    private int getValue() {
        return Integer.valueOf(editField.getCharacters().toString());
    }

    @FXML
    public void initialize() {
        controller.onRender(this);

        buttonAddHead.setOnMouseClicked(event -> {
            controller.onClickAddHead(getValue());
        });
        buttonAddLast.setOnMouseClicked(event -> {
            controller.onClickAddLast(getValue());
        });

        buttonRemoveHead.setOnMouseClicked(event -> {
            controller.onClickRemoveHead(getValue());
        });
        buttonRemoveLast.setOnMouseClicked(event -> {
            controller.onClickRemoveLast(getValue());
        });
    }

    @Override
    public void render(String main, String additional) {
        textOutput.setText(main);
        textOutputInfo.setText(additional);
    }
}
