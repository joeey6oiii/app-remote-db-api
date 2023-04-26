package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import commands.CommandDescription;

public interface CommandReceiver {

    void temp(CommandDescription cmd, String[] arr, ConnectionModule module);

}
