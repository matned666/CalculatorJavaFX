package Calculator;

public class CalculatorWork {

    private String nine;
    private String eight;
    private String seven;
    private String six;
    private String five;
    private String four;
    private String three;
    private String two;
    private String one;
    private String zero;

    private int result;
    private String firstNumber;
    private String seccondNumber;
    private String setter;



    public CalculatorWork() {
        this.result = 0;
        this.firstNumber = "";
        this. seccondNumber = "";
    }

    public String getSetter() {
        return setter;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public String getSeccondNumber() {
        return seccondNumber;
    }

    public String getResult(){
        return Integer.toString(resultInner());
    }

    public int resultInner() {
        Integer firstNumber = Integer.parseInt(this.firstNumber);
        Integer seccondNumber = Integer.parseInt(this.seccondNumber);

        if(setter.equals("+")) return firstNumber + seccondNumber;
        else if(setter.equals("-")) return firstNumber - seccondNumber;
        else if(setter.equals("x")) return firstNumber * seccondNumber;
        else if(setter.equals("%")) return firstNumber % seccondNumber;
        else if(setter.equals("/")) {
            if(seccondNumber == 0 ) return 0;
            else return firstNumber / seccondNumber;
        }
        else return 0;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSeccondNumber(String seccondNumber) {
        this.seccondNumber = seccondNumber;
    }
}
