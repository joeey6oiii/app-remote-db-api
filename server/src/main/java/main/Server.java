package main;

import serverModules.connection.ConnectionModuleConfigurator;
import serverModules.connection.ConnectionModule;
import serverModules.context.ServerContext;
import serverModules.request.data.RequestData;
import serverModules.request.manager.RequestHandlerManager;

public class Server {
    private final static int PORT = 9999;

    public static void main(String[] args) {

        ConnectionModule module = new ConnectionModuleConfigurator().init(PORT);

        try {
            while (true) {
                RequestData requestData = module.receiveData();
                ServerContext context = new ServerContext(module, requestData.getCallerBack(), requestData.getRequest());
                new RequestHandlerManager().manageRequest(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
