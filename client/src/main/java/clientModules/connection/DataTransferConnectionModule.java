package clientModules.connection;

import exceptions.ServerUnavailableException;

import java.io.IOException;

/**
 * An interface for all connection module-implementers with the ability to transfer data.
 */

public interface DataTransferConnectionModule extends ConnectionModule {

    /**
     * A method that receives data.
     *
     * @return data byte array
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     */

    byte[] receiveData() throws IOException, ServerUnavailableException;

    /**
     * A method that sends the specified data.
     *
     * @param data data to send
     * @throws IOException if failed during I/O operations
     * @throws ServerUnavailableException if the server was unavailable during sending and receiving operations
     */

    void sendData(byte[] data) throws IOException, ServerUnavailableException;

}
