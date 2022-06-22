import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static final int PORT = 25111;

    public static void main(String[] args) {
        String name = "[SERVER] ";
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    int number;
                    System.out.println(name + "Connected with Client.");

                    while ((line = in.readLine()) != null) {
                        if (line.equals("end")) {
                            break;
                        }
                        if (line.chars().allMatch(Character::isDigit)) {
                            number = Integer.parseInt(line);
                            System.out.println(name + "Fibonacci number [" + number + "]");

                            int[] f = fibonacci(number);
                            out.println("Fibonacci numbers: " + Arrays.toString(f));
                            System.out.println(name + "Fibonacci number [" + Arrays.toString(f) + "]");
                        } else {
                            out.println("Error. Please, enter the number.");
                        }
                    }
                    System.out.println(name + "Started.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static int[] fibonacci(int number) {
        int[] f = new int[number];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < number; ++i) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}