package serverModules.response.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.data.FragmentHeader;
import response.responses.Response;
import serializer.ObjectSerializer;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;
import utility.UdpDataTransferUtilities;

import java.net.InetAddress;

/**
 * A class that represents the base response sender.
 */

public class ResponseSender implements ResponseAble<Response> {
    private static final Logger logger = LogManager.getLogger("logger.ResponseSender");

    /**
     * A method that serializes the received response, divides it by chunks and sends them to the client.
     * Sends <code>byte[]</code>, where header length placed on the first position, then from second position to length
     * of the header placed serialized header, and after placed the chunk (part of the data)
     *
     * @param module server core
     * @param callerBack client
     * @param response answer to the client
     */

    @Override
    public void sendResponse(ConnectionModule module, CallerBack callerBack, Response response) throws IllegalArgumentException {
        if (response != null) {
            ObjectSerializer os = new ObjectSerializer();
            final InetAddress address = callerBack.getAddress();
            final int port = callerBack.getPort();
            try {
                byte[] data = os.serialize(response);
                int maxPacketSize = UdpDataTransferUtilities.PACKET_SIZE.getPacketSizeValue();
                int packetIndex = 0;
                int offset = 0;

                while (offset < data.length) {
                    FragmentHeader header = new FragmentHeader(packetIndex);
                    byte[] headerData = os.serialize(header);
                    int headerDataLength = headerData.length;

                    if (headerDataLength >= maxPacketSize - 1) {
                        throw new IllegalArgumentException("Header data size is larger than max packet size");
                    }

                    int freeSpace = maxPacketSize - headerDataLength - 1;
                    int remainingData = data.length - offset;
                    int length = Math.min(freeSpace, remainingData);

                    byte[] packet = new byte[length + headerDataLength + 1];
                    packet[0] = (byte) headerDataLength;
                    System.arraycopy(headerData, 0, packet, 1, headerDataLength);
                    System.arraycopy(data, offset, packet, headerDataLength + 1, length);

                    module.sendData(packet, address, port);

                    packetIndex++;
                    offset += length;
                }

                FragmentHeader header = new FragmentHeader(-1);
                byte[] headerData = os.serialize(header);
                int headerDataLength = headerData.length;

                if (headerDataLength >= maxPacketSize - 1) {
                    throw new IllegalArgumentException("Header data size is larger than max packet size");
                }

                byte[] noDataNotification = new byte[headerDataLength + 1];
                noDataNotification[0] = (byte) headerDataLength;
                System.arraycopy(headerData, 0, noDataNotification, 1, headerDataLength);

                module.sendData(noDataNotification, address, port);
            } catch (Exception e) {
                logger.fatal("Something went wrong during I/O operations", e);
            }
        }
    }

}
