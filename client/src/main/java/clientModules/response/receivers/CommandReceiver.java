package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import commands.CommandDescription;

public interface CommandReceiver {

    void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module);

}
