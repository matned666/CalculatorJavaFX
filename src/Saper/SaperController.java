package Saper;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SaperController {

    private Matrix matrix;
    private boolean isFirst;
    private FieldObj[][] firstArray;
    private int numberOfFields;
    private int numberOfBombs;

    @FXML
    GridPane gridPlaneGame;

    public SaperController() {
    }

    @FXML
    void gamePlayButtonPress() {
    }

    @FXML
    public void startGame() throws CustomException {
        matrix = new Matrix(10,10,18,40);
        for( int i = 0; i < 17; i++){
            for(int j = 0; j < 17; j++){
                gridPlaneGame.add(matrix.getMatrix()[i][j].getTextField(), i+2,j+2);
                gridPlaneGame.add(matrix.getMatrix()[i][j].getButton() ,i+2, j+2);
            }

        }
    }
}
