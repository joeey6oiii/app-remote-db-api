package clientModules.response.receivers;

import clientModules.connection.DatagramConnectionModule;
import commands.CommandDescription;

import java.io.IOException;

public interface CommandReceiver {

    void receiveCommand(CommandDescription cmd, String[] args, DatagramConnectionModule module);

}
