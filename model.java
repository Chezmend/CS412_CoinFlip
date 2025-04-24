public class Model {
    private double operand1;
    private double operand2;
    private String operator;

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperator() {
        return operator;
    }

    public boolean parseInput(String input) {
        input = input.trim();

        if (input.contains("+")) {
            operator = "+";
        } else if (input.contains("-")) {
            operator = "-";
        } else if (input.contains("*")) {
            operator = "*";
        } else if (input.contains("/")) {
            operator = "/";
        } else if (input.contains("%")) {
            operator = "%";
        } else if (input.contains("^")) {
            operator = "^";
        } else {
            return false; 
        }

        String[] parts = input.split("\\" + operator); 
        try {
            operand1 = Double.parseDouble(parts[0].trim());
            operand2 = Double.parseDouble(parts[1].trim());
            return true; 
        } catch (NumberFormatException e) {
            return false; 
        }
    }
}
