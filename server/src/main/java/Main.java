import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        int port = 9999;

        try {
            try {
                ds = new DatagramSocket(port, InetAddress.getLocalHost());
                System.out.println("SERVER STARTED");

                byte[] buffer = new byte[4096];
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

                String data = "Package received";
                byte[] buffer2 = data.getBytes();

                while (true) {
                    ds.receive(dp);
                    InetAddress clientAddress = dp.getAddress();
                    int clientPort = dp.getPort();
                    ds.send(new DatagramPacket(buffer2, buffer2.length, clientAddress, clientPort));
                    System.out.println("received: " + new String(dp.getData(), 0, dp.getLength()));
                }
            } catch (IOException ignored) {}
        } finally {
            assert ds != null;
            ds.close();
        }
    }
}
