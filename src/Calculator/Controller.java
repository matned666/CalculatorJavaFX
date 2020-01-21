package Calculator;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    CalculatorWork calculatorWork;

    @FXML
    private Button nine;
    @FXML
    private Button eight;
    @FXML
    private Button seven;
    @FXML
    private Button six;
    @FXML
    private Button five;
    @FXML
    private Button four;
    @FXML
    private Button three;
    @FXML
    private Button two;
    @FXML
    private Button one;
    @FXML
    private Button equaling;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button divide;
    @FXML
    private Button modulo;
    @FXML
    private Button times;
    @FXML
    private TextField equalsNumber;

    private boolean isNextNumber;
    private boolean isResulted;

    public Controller() {
        calculatorWork = new CalculatorWork();
        isNextNumber = false;
        isResulted = false;
    }

    @FXML
    void initialize() {
    }

    private void inputNumber(String str){
        if(isResulted){
            equalsNumber.setPromptText("");
            calculatorWork.setFirstNumber("");
            calculatorWork.setSeccondNumber("");
            isNextNumber = false;
            isResulted = false;
        }else {
            if (!isNextNumber) {
                calculatorWork.setFirstNumber(calculatorWork.getFirstNumber() + str);
                equalsNumber.setPromptText(calculatorWork.getFirstNumber());
            } else {
                calculatorWork.setSeccondNumber(calculatorWork.getSeccondNumber() + str);
                equalsNumber.setPromptText(calculatorWork.getFirstNumber()+" "+calculatorWork.getSetter()+" "+calculatorWork.getSeccondNumber());
            }
        }
    }

    private void inputSetter(String str){
        if(isResulted){
            equalsNumber.setPromptText("");
            calculatorWork.setFirstNumber("");
            calculatorWork.setSeccondNumber("");
            isNextNumber = false;
            isResulted = false;
        }else {
            calculatorWork.setSetter(str);
            this.isNextNumber = true;
            if(calculatorWork.getFirstNumber().equals("")) calculatorWork.setFirstNumber("0");
            equalsNumber.setPromptText(calculatorWork.getFirstNumber()+" "+calculatorWork.getSetter());
        }
    }

    private void inputResult(){
        if(!isResulted && isNextNumber){
            equalsNumber.setPromptText(equalsNumber.getPromptText()+" = "+calculatorWork.getResult());
            isResulted = true;
        }else if(!isResulted && !isNextNumber){
            equalsNumber.setPromptText(equalsNumber.getPromptText());
            isResulted = true;
        }else {
            equalsNumber.setPromptText("");

            calculatorWork.setFirstNumber("");
            calculatorWork.setSeccondNumber("");
            isNextNumber = false;
            isResulted = false;
        }
    }

    @FXML
    public void sevenClick(){
        inputNumber("7");
    }

    @FXML
    public void eightClick(){
        inputNumber("8");
    }

    @FXML
    public void nineClick(){
        inputNumber("9");
    }

    @FXML
    public void sixClick(){
        inputNumber("6");
    }

    @FXML
    public void fiveClick(){
        inputNumber("5");
    }

    @FXML
    public void fourClick(){
        inputNumber("4");
    }

    @FXML
    public void threeClick(){
        inputNumber("3");
    }

    @FXML
    public void twoClick(){
        inputNumber("2");
    }

    @FXML
    public void oneClick(){
        inputNumber("1");
    }

    @FXML
    public void zeroClick(){
        inputNumber("0");
    }

    @FXML
    public void plusClick(){
        inputSetter("+");
    }

    @FXML
    public void minusClick(){
        inputSetter("-");
    }

    @FXML
    public void divideClick(){
        inputSetter("/");
    }

    @FXML
    public void timesClick(){
        inputSetter("x");
    }

    @FXML
    public void equalingClick(){
        inputResult();
          }

    @FXML
    public void moduloClick(){
        inputSetter("%");
    }

}
