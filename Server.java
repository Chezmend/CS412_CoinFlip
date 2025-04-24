
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            while (true) {
                System.out.println("Waiting for client connection...");

                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected: " + socket.getPort());

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    String input = in.readLine();
                    System.out.println("Received: " + input);

                    Model model = new Model();
                    if (model.parseInput(input)) {

                        double result = evaluate(model.getOperand1(), model.getOperand2(), model.getOperator());
                        out.println(Double.toString(result));
                    } else {
                        out.println("Error: Invalid input");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double evaluate(double operand1, double operand2, String operator) {
        if (operator.equals("+")) {
            return operand1 + operand2;
        } else if (operator.equals("-")) {
            return operand1 - operand2;
        } else if (operator.equals("*")) {
            return operand1 * operand2;
        } else if (operator.equals("/")) {
            if (operand2 == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return operand1 / operand2;
        } else if (operator.equals("%")) {
            if (operand2 == 0) {
                throw new ArithmeticException("Modulo by zero");
            }
            return operand1 % operand2;
        } else if (operator.equals("^")) {
            return Math.pow(operand1, operand2);
        } else {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
