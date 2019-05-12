package ru.whalemare.dev.trpo.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.whalemare.dev.trpo.lab3.LinkedDequeScala;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class LinkedDequeView {

    @FXML
    private Button buttonAddHead;

    @FXML
    private Button buttonAddLast;

    @FXML
    private Button buttonRemoveHead;

    @FXML
    private Button buttonRemoveLast;

    @FXML
    private TextField editInput;

    @FXML
    private TextArea textOutput;

    private LinkedDequeScala<Integer> linkedDeque = new LinkedDequeScala<>();

    private int getValue() {
        return Integer.valueOf(editInput.getCharacters().toString());
    }

    @FXML
    public void initialize() {
        buttonAddHead.setOnMouseClicked(event -> {
            linkedDeque.addHead(getValue());
            render();
        });
        buttonAddLast.setOnMouseClicked(event -> {
            linkedDeque.addLast(getValue());
            render();
        });

        buttonRemoveHead.setOnMouseClicked(event -> {
            linkedDeque.removeHead();
            render();
        });
        buttonRemoveLast.setOnMouseClicked(event -> {
            linkedDeque.removeLast();
            render();
        });
    }

    public void render() {
        textOutput.setText(linkedDeque.toString());
    }
}
