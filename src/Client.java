import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 25111;

    public static void main(String[] args) {
        String name = "[CLIENT] ";
        Socket socket;
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + "Started");

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            System.out.println(name + "Connected with Server.");
            System.out.println(name + "Enter Fibonacci number ('end' for exit): ");

            while (true) {
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) {
                    break;
                }
                System.out.println(name + "From Server: " + in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
