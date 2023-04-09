package serverModules.request;

import commands.BaseCommand;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class CommandRequestReader implements RequestReadAble<BaseCommand> {

    @Override
    public BaseCommand readRequest(DatagramPacket packet) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (BaseCommand) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}