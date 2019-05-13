package ru.whalemare.dev.trpo.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.whalemare.dev.trpo.lab1.BinaryTree;

/**
 * @since 2019
 */
public class BinaryTreeView {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonRemove;

    @FXML
    private TextField editInput;

    @FXML
    private TextArea textOutput;

    private BinaryTree<Integer> tree = new BinaryTree<>();

    private int getValue() {
        return Integer.valueOf(editInput.getCharacters().toString());
    }

    @FXML
    public void initialize() {
        textOutput.setPrefSize(1080, 300);
        buttonAdd.setOnMouseClicked(event -> {
            tree.add(getValue());
            render();
        });

        buttonRemove.setOnMouseClicked(event -> {
            tree.remove(getValue());
            render();
        });

    }

    public void render() {
        textOutput.setText(tree.toString());
    }
}
