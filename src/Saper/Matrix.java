package Saper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Matrix<E extends Event> {

    Text gameMessage;
    private FieldObj[][] matrix;
    private int numberOfBombs;
    private int numberOfFields;
    private boolean[] bombFields;
    private int xFirst;
    private int yFirst;
    private MouseEvent mouseEvent;

    private GridPane gridPlaneGame;

//    public Matrix(int n) {
//        this.event = event;
//        this.xFirst = 10;
//        this.yFirst = 10;
//        this.numberOfBombs = 20;
//        this.numberOfFields = n * n;
//        this.matrix = new GrindObject[n][];
//        for(int i = 0; i < n ; i++){
//            this.matrix = new GrindObject[i][n-1];
//        }
//        bombFieldsArr();
//        generateMatrix();
//        setNeighours();
//    }


    public Matrix(int xFirst, int yFirst, int n, int numberOfBombs, GridPane gridPlaneGame, Text gameMessage) throws CustomException {
        this.xFirst = xFirst;
        this.yFirst = yFirst;
        this.gameMessage = gameMessage;
        this.gridPlaneGame = gridPlaneGame;
        this.numberOfBombs = numberOfBombs;
        this.numberOfFields = n * n;
            this.matrix = new FieldObj[n][];
            for(int i = 0; i < n ; i++){
                this.matrix = new FieldObj[i][n-1];
            }
            bombFieldsArr();
            generateMatrix();
            setNeighours();
            addEventHandlerToAllButtons();
    }

    private void handle (int x, int y){
        EventHandler leftClick = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                 openField(x,y);
            }
        };

        EventHandler<MouseEvent>  rightClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                matrix[x][y].setChecked(true);
                matrix[x][y].setText();
            }
        };

        matrix[x][y].getButton().addEventHandler(ActionEvent.ACTION, leftClick);

    }

    private void addEventHandlerToAllButtons(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].getTextField().setText(matrix[i][j].toString());
                handle(i,j);
            }
        }
    }

    public int getNumberOfClosedFields(){
        int temp = 0;
        for(int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(!matrix[i][j].isOpen()) temp ++;
            }
        }
        return temp;
    }

    private boolean[] bombFieldsArr() {
        this.bombFields = new boolean[numberOfFields];
        for(boolean el: this.bombFields) el = false;
        int temp;
        for (int i = 0; i < numberOfBombs; i++) {
            temp = (int) (Math.random() * numberOfFields-1);

            if (!this.bombFields[temp] || temp != getFieldId(xFirst,yFirst)) {
                this.bombFields[temp] = true;
            } else if(this.bombFields[temp] || temp == getFieldId(xFirst,yFirst)){
                i -= 1;
            }
        }
        return this.bombFields;
    }


    public int getFieldId (int x , int y){
        int iDCounter = 0;
        for(int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                iDCounter ++;
            }
        }
        return iDCounter;
    }

    public FieldObj[][] getMatrix() {
        return matrix;
    }



    private void setNeighours(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int numberOfBombsInSurround = 0;
                try {
                    if (matrix[i-1][j - 1].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i-1][j].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i-1][j+1].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i][j-1].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i][j+1].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i+1][j-1].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i+1][j].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
                }
                try {
                    if (matrix[i+1][j+1].isBomb()) numberOfBombsInSurround++;
                }catch(ArrayIndexOutOfBoundsException ignored){
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

        openFieldInner(x,y);

        if(getNumberOfClosedFields() == numberOfBombs) {
            openAll();
            gameMessage.setText("CONGRATULATIONS !!!");
        }
    }

    public void removeAllText(){
        for( int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                matrix[i][j].getTextField().setText("");

            }
        }
        openAll();
    }


    public void openFieldInner(int x, int y){
        if(x>=0 && x < matrix.length && y>=0 && y < matrix[x].length && !matrix[x][y].isBomb() && !matrix[x][y].isOpen()){
            matrix[x][y].setAllSizesToZero();
            matrix[x][y].setOpen(true);
                if (x > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x-1,y);
                if (x > 0 && y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x-1,y-1);
                if (x > 0 && y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x-1,y+1);
                if (y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x,y-1);
                if (y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x, y+1);
                if (x < matrix.length && y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x+1,y-1);
                if (x < matrix.length && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x+1,y);
                if (x < matrix.length && y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0 )openFieldInner(x+1,y+1);
        }
    }

    public void openAll(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].setAllSizesToZero();

                matrix[i][j].setOpen(true);
            }
        }
    }

    private void generateMatrix(){
        int counter = 0;

        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(i==0) {
                    if(j == 0){
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"", 30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }else if(j == matrix[i].length-1){
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }else{
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }
                }else if(i>0 && i<matrix.length-1) {
                    if(j == 0){
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }else if(j == matrix[i].length-1){
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }else{
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }
                }else if(i == matrix.length-1){
                    if(j == 0){
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }else if(j == matrix[i].length-1){
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }else{
                        matrix[i][j] = new FieldObj(this.bombFields[counter],false,false,30,30,30,30,30,30,1,10,"",30,toString(),0);
                        String value = "ButtonX" + i + "Y" + j;
                        matrix[i][j].getButton().setId(value);
                    }
                }
                counter ++;
            }
        }
    }

    public void print(){
        System.out.print("   ");
        for(int j = 0; j < matrix.length; j++){
            for(int f = 1; f <= 3-String.valueOf(j).length(); f++) System.out.print(" ");
            System.out.print(j);
        }
        System.out.println("");
        for(int j = 0; j < matrix.length*4; j++){
            System.out.print("-");}
        System.out.println();
        for(int i = 0 ; i < matrix.length; i++){
            System.out.print(i);
            for(int f = 1; f <= 3-String.valueOf(i).length()-1; f++) System.out.print(" ");
            System.out.print(" |");
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(" "+matrix[i][j].toString()+" ");
            }
            System.out.println("");
        }
    }

    public static void printStaticMatrix(int numberOfFields){
        System.out.print("   ");
        for(int j = 0; j < numberOfFields-1; j++){
            for(int f = 1; f <= 3-String.valueOf(j).length(); f++) System.out.print(" ");
            System.out.print(j);
        }
        System.out.println("");
        for(int j = 0; j < numberOfFields*4; j++){
            System.out.print("-");}
        System.out.println();
        for(int i = 0 ; i < numberOfFields-1; i++){
            System.out.print(i);
            for(int f = 1; f <= 3-String.valueOf(i).length()-1; f++) System.out.print(" ");
            System.out.print(" |");
            for(int j = 0; j < numberOfFields-1; j++){
                System.out.print(" \u25A1 ");
            }
            System.out.println("");
        }
    }
}
