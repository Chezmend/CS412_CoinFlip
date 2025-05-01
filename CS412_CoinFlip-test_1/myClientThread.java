import java.io.BufferedReader;
import java.io.IOException;

class myClientThread implements Runnable {
    private BufferedReader in;

    public myClientThread(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);
                System.out.print("Enter message: ");
            }
        } catch (IOException e) {
            System.out.println("Disconnected from server.");
        } finally {
            System.out.println("Connection closed. Exiting chat.");
        }
    }
}
