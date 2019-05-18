package ru.whalemare.dev.trpo.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.whalemare.dev.trpo.lab1.LinkedDeque;

import java.util.concurrent.Callable;

/**
 * @since 2019
 * @author Mikhail Medvedev
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
    private TextField editField;

    @FXML
    private TextArea textOutput;

    @FXML
    private TextArea textOutputInfo;

    private LinkedDeque<Integer> linkedDeque = new LinkedDeque<>();

    private int getValue() {
        return Integer.valueOf(editField.getCharacters().toString());
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

        int value = getValue();
        textOutputInfo.setText(
                "Список пуст: " + safe(() -> linkedDeque.isEmpty()) + "\n" +
                "Начальный элемент: " + safe(() -> linkedDeque.peekHead()) + "\n" +
                "Конечный элемент: " + safe(() -> linkedDeque.peekLast()) + "\n" +
                "Индекс элемента [" + value + "]: " + safe(() -> linkedDeque.indexOf(value))
        );
    }

    public <V> V safe(Callable<V> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
