package Saper;

import javafx.scene.control.Button;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FieldObj {

    private boolean isBomb;
    private boolean isChecked;
    private boolean isOpen;
    private boolean isQuestionMark;
    private int numberOfBombsInSurround;
    private boolean isBlownBomb;
    private Text textField;
    private Button button;

    public FieldObj(boolean isBomb, boolean isChecked, boolean isOpen) {
        this.isBomb = isBomb;
        this.isChecked = isChecked;
        this.isOpen = isOpen;
        this.textField = new Text();
        this.button = new Button();
        this.isQuestionMark = false;

        textField.setText(toString());
        textField.setWrappingWidth(30);
        textField.setTextAlignment(TextAlignment.CENTER);
        textField.setStrokeWidth(0);
        textField.setStrokeType(StrokeType.OUTSIDE);
        textField.setFont(new Font("Arial Bold", 10));

        button.setOpacity(1);
        button.setMinHeight(30);
        button.setPrefHeight(30);
        button.setMaxHeight(30);
        button.setMinWidth(30);
        button.setPrefWidth(30);
        button.setMaxWidth(30);
        button.setFont(new Font("Arial Bold", 10));
        button.setText("");
    }

    public void setAllSizes(int i){
        button.setMinHeight(i);
        button.setPrefHeight(i);
        button.setMaxHeight(i);
        button.setMinWidth(i);
        button.setPrefWidth(i);
        button.setMaxWidth(i);
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public String toString(){
            if(this.isBomb && !this.isChecked && !this.isBlownBomb) return "\u26AB";
            else if(this.isBomb && !this.isChecked && this.isBlownBomb) return "@";
            else{
                if(numberOfBombsInSurround==0) return ".";
                else
                    return String.valueOf(getNumberOfBombsInSurround());
            }
    }

    public String buttonToString(){
        if(isChecked && !isQuestionMark) return "X";
        else if((isChecked && isQuestionMark) || (isChecked && isQuestionMark) ) return "?";
        else return "";
    }

    public Button getButton() {
        return button;
    }

    public Text getTextField() {
        return textField;
    }

    public void setBlownBomb(boolean blownBomb) {
        isBlownBomb = blownBomb;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setNumberOfBombsInSurround(int numberOfBombsInSurround) {
        this.numberOfBombsInSurround = numberOfBombsInSurround;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getNumberOfBombsInSurround() {
        return numberOfBombsInSurround;
    }

    public boolean isQuestionMark() {
        return isQuestionMark;
    }

    public void setQuestionMark(boolean questionMark) {
        isQuestionMark = questionMark;
    }
}
