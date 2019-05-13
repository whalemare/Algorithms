package ru.whalemare.dev.trpo.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author Anton Vlasov - whalemare
 * @since 2019
 */
public class BinaryTreeViewScala {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonRemove;

    @FXML
    private TextField editInput;

    @FXML
    private TextArea textOutput;

    private Controller controller = new Controller();

    private int getValue() {
        return Integer.valueOf(editInput.getCharacters().toString());
    }

    @FXML
    public void initialize() {
        textOutput.setPrefSize(1080, 300);
        buttonAdd.setOnMouseClicked(event -> {
            controller.onClickAdd(getValue(), value -> textOutput.setText(value));
        });

        buttonRemove.setOnMouseClicked(event -> {
            controller.onClickRemove(getValue(), value -> textOutput.setText(value));
        });
    }
}
