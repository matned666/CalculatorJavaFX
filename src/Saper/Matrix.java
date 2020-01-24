package Saper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class Matrix{

    private Text gameMessage;
    private FieldObj[][] matrix;
    private int numberOfBombs;
    private int numberOfFields;
    private boolean[] bombFields;
    private int xFirst;
    private int yFirst;

    Matrix(int xFirst, int yFirst, int n, int numberOfBombs, Text gameMessage){
        this.xFirst = xFirst;
        this.yFirst = yFirst;
        this.gameMessage = gameMessage;
        this.numberOfBombs = numberOfBombs;
        this.numberOfFields = n * n;
        this.matrix = new FieldObj[n][];
        for (int i = 0; i < n; i++) {
            this.matrix = new FieldObj[i][n - 1];
        }
        bombFieldsArr();
        generateMatrix();
    }


    private void handle(int x, int y) {
        EventHandler leftClick = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openField(x, y);
            }
        };
        matrix[x][y].getButton().addEventHandler(ActionEvent.ACTION, leftClick);
    }

    private void addEventHandlerToAllButtons() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].getTextField().setText(matrix[i][j].toString());
                handle(i, j);
            }
        }
    }

    public int getNumberOfClosedFields() {
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!matrix[i][j].isOpen()) temp++;
            }
        }
        return temp;
    }

    private boolean[] bombFieldsArr() {
        this.bombFields = new boolean[numberOfFields];
        for (boolean el : this.bombFields) el = false;
        int temp;
        for (int i = 0; i < numberOfBombs; i++) {
            temp = (int) (Math.random() * numberOfFields - 1);
            if (!this.bombFields[temp] || temp != getFieldId(xFirst, yFirst)) {
                this.bombFields[temp] = true;
            } else if (this.bombFields[temp] || temp == getFieldId(xFirst, yFirst)) {
                i -= 1;
            }
        }
        return this.bombFields;
    }


    public int getFieldId(int x, int y) {
        int iDCounter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                iDCounter++;
            }
        }
        return iDCounter;
    }

    public FieldObj[][] getMatrix() {
        return matrix;
    }


    private void setNeighbors() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int numberOfBombsInSurround = 0;
                try {
                    if (matrix[i - 1][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i - 1][j].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i - 1][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i + 1][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i + 1][j].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i + 1][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                matrix[i][j].setNumberOfBombsInSurround(numberOfBombsInSurround);
            }
        }
    }


    public void openField(int x, int y) {
        if (matrix[x][y].isBomb()) {
            openAll();
            matrix[x][y].setBlownBomb(true);
            matrix[x][y].getTextField().setText(matrix[x][y].toString());
            gameMessage.setText("GAME OVER !!!");
        }
        openFieldInner(x, y);
        if (getNumberOfClosedFields() == numberOfBombs) {
            openAll();
            gameMessage.setText("CONGRATULATIONS !!!");
        }
    }


    public void openFieldInner(int x, int y) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[x].length && !matrix[x][y].isBomb() && !matrix[x][y].isOpen()) {
            matrix[x][y].setAllSizes(0);
            matrix[x][y].setOpen(true);
            if (x > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x - 1, y);
            if (x > 0 && y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x - 1, y - 1);
            if (x > 0 && y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0)
                openFieldInner(x - 1, y + 1);
            if (y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x, y - 1);
            if (y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x, y + 1);
            if (x < matrix.length && y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0)
                openFieldInner(x + 1, y - 1);
            if (x < matrix.length && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x + 1, y);
            if (x < matrix.length && y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0)
                openFieldInner(x + 1, y + 1);
        }
    }

    public void openAll() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].setAllSizes(0);
                matrix[i][j].setOpen(true);
            }
        }
    }

    public void closeAll() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].setAllSizes(30);
                matrix[i][j].setOpen(false);
            }
        }
    }

    public void generateMatrix() {
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new FieldObj(this.bombFields[counter], false, false, 30, 30, 30, 30, 30, 30, 1, 10, 30, 0);
                String value = "ButtonX" + i + "Y" + j;
                matrix[i][j].getButton().setId(value);
                counter++;
            }
        }
        setNeighbors();
        addEventHandlerToAllButtons();
    }

    public void generateNextMatrix() {
        int counter = 0;
        closeAll();
        this.bombFields = bombFieldsArr();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].setBomb(this.bombFields[counter]);
                matrix[i][j].setChecked(false);
                matrix[i][j].setBlownBomb(false);
                matrix[i][j].getTextField().setText(matrix[i][j].toString());
                counter++;
            }
        }
        setNeighbors();
        addEventHandlerToAllButtons();
    }
}
