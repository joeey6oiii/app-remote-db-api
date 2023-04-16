package serverModules.connection;

import serverModules.callerBack.CallerBack;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
//import java.util.concurrent.ConcurrentHashMap;

public class ConnectionModule implements ReceiveConnectionAble, ReceiveDataAble<byte[]> {
    private final int BYTE_SIZE = 1024;
    private final DatagramSocket socket;
    private CallerBack callerBack;
//    private ConcurrentHashMap<Integer, CallerBack> callerBackMap;

//    {
//        callerBackMap = new ConcurrentHashMap<>();
//    }

    public ConnectionModule(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public CallerBack getCallerBack() {
        return this.callerBack;
    }

    @Override
    public byte[] receiveData() {
        byte[] bytes = new byte[BYTE_SIZE];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            socket.receive(packet);
//            callerBackMap.put(packet.getPort(), new CallerBack(packet.getAddress(), packet.getPort()));
            callerBack = new CallerBack(packet.getAddress(), packet.getPort()); // все равно однопоточный режим по тз...
            return packet.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}