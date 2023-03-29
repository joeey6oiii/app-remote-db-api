import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Objects;
import java.util.Scanner;

/**
 * Client entry point class. Contains <code>main()</code> method.
 */

public class Main {

    /**
     * Program entry point.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        DatagramChannel cl = null;

        try {
            cl = DatagramChannel.open();
            cl.bind(null);

            InetSocketAddress se = new InetSocketAddress(InetAddress.getLocalHost(), 9999);
            cl.connect(se);

            String msg = "";
            while (!msg.equals("exit")) {
                System.out.print("Type anything or exit: ");
                msg = new Scanner(System.in).nextLine();
                if (!Objects.equals(msg, "")) {
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    cl.send(buffer, se);
                    buffer.clear();
                }

                ByteBuffer bbf = ByteBuffer.allocate(1024);
                cl.receive(bbf);
                bbf.flip();
                byte[] bytes = new byte[bbf.remaining()];
                bbf.get(bytes);
                System.out.println(new String(bytes));
            }
            cl.close();
        } catch (IOException ignored) {}
    }
}