package Saper;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FieldObj {

    private boolean isBomb;
    private boolean isChecked;
    private boolean isOpen;
    private int numberOfBombsInSurround;
    private boolean isBlownBomb;

    private Text textField;
    private double wrappingWidth;
    private String textFieldText;
    private double strokeWidth;

    private Button button;
    private int minHeight;
    private int prefHeight;
    private int maxHeight;
    private int minWidth;
    private int prefWidth;
    private int maxWidth;
    private int columnSpan;
    private int fontSize;
    private Font font;
    private String text;
    private ActionEvent action;
    private double opacity;



    public FieldObj(boolean isBomb, boolean isChecked, boolean isOpen, int minHeight,
                    int prefHeight, int maxHeight, int minWidth, int prefWidth, int maxWidth,
                    int columnSpan, int fontSize, String text, double wrappingWidth,
                    String textFieldText, double strokeWidth)
    {
        this.isBomb = isBomb;
        this.isChecked = isChecked;
        this.isOpen = isOpen;
        this.button = new Button();
        this.minHeight = minHeight;
        this.prefHeight = prefHeight;
        this.maxHeight = maxHeight;
        this.minWidth = minWidth;
        this.prefWidth = prefWidth;
        this.maxWidth = maxWidth;
        this.columnSpan = columnSpan;
        this.font = new Font("Arial Bold", fontSize);
        this.text = text;
        this.opacity = 1;
        this.textField = new Text();
        this.strokeWidth = strokeWidth;
        this.wrappingWidth = wrappingWidth;
        this.textFieldText = toString();
        this.fontSize = fontSize;

        textField.setText(this.textFieldText);
        textField.setWrappingWidth(this.wrappingWidth);
        textField.setTextAlignment(TextAlignment.CENTER);
        textField.setStrokeWidth(this.strokeWidth);
        textField.setStrokeType(StrokeType.OUTSIDE);
        textField.setFont(new Font("Arial Bold", this.fontSize));

        button.setOpacity(this.opacity);
        button.setMinHeight(this.minHeight);
        button.setPrefHeight(this.prefHeight);
        button.setMaxHeight(this.maxHeight);
        button.setMinWidth(this.minWidth);
        button.setPrefWidth(this.prefWidth);
        button.setMaxWidth(this.maxWidth);
        button.setFont(new Font("Arial Bold", this.fontSize));
        button.setText(this.text);
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public void setAllSizesToZero(){
        button.setOpacity(0);
        button.setMinHeight(0);
        button.setPrefHeight(0);
        button.setMaxHeight(0);
        button.setMinWidth(0);
        button.setPrefWidth(0);
        button.setMaxWidth(0);
    }

    public String toString(){

            if(this.isBomb && !this.isChecked && !this.isBlownBomb) return "\u26AB";
            else if(this.isBomb && this.isChecked && !this.isBlownBomb) return "\u26AA";
            else if(this.isBomb && !this.isChecked && this.isBlownBomb) return "@";
            else{
                if(numberOfBombsInSurround==0) return ".";
                else
                    return String.valueOf(getNumberOfBombsInSurround());
            }

    }

    public void setText() {
        toString();
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
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

    public boolean isBlownBomb() {
        return isBlownBomb;
    }

    public double getWrappingWidth() {
        return wrappingWidth;
    }

    public String getTextFieldText() {
        return textFieldText;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getPrefHeight() {
        return prefHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public int getPrefWidth() {
        return prefWidth;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Font getFont() {
        return font;
    }

    public String getText() {
        return text;
    }

    public ActionEvent getAction() {
        return action;
    }

    public double getOpacity() {
        return opacity;
    }
}
