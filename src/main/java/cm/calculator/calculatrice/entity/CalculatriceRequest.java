package cm.calculator.calculatrice.entity;

public class CalculatriceRequest {
    private String type;
    private double operand1;
    private double operand2;

    // Getters et setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }
}
