package Saper;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SaperController {

    public Text gameMessage;
    private Matrix matrix;

    @FXML
    GridPane gridPlaneGame;

    public SaperController() {
    }

    public void initialize() {
        gameMessage.setText("Good luck...");
        if (this.matrix == null) {
            this.matrix = new Matrix(10, 10, 18, 40, gameMessage);
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                    gridPlaneGame.add(matrix.getMatrix()[i][j].getTextField(), i + 2, j + 2);
                    gridPlaneGame.add(matrix.getMatrix()[i][j].getButton(), i + 2, j + 2);
                }
            }
        }
    }

    @FXML
    void gamePlayButtonPress() {
    }

    @FXML
    public void startGame() {
      matrix.generateNextMatrix();
      gameMessage.setText("Good luck...");


    }
}
