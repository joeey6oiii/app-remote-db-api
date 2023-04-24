package main;

import database.Database;
import requests.Request;
import serverModules.connection.ConnectionModuleConfigurator;
import serverModules.connection.ConnectionModule;
import serverModules.context.ServerContext;
import serverModules.request.data.RequestData;
import serverModules.request.manager.RequestHandlerManager;
import serverModules.request.reader.RequestReader;

public class Server {

    private final static int PORT = 9999;

    public static void main(String[] args) {

        ConnectionModule module = new ConnectionModuleConfigurator().newInstance(PORT);
        Database database = new Database();

        try {
            while (true) {
                RequestData requestData = module.receiveData();
                Request request = new RequestReader().readRequest(requestData.getByteArray());
                ServerContext context = new ServerContext(module, requestData.getCallerBack(), request);
                database.setServerContext(context);
                new RequestHandlerManager().manageRequest(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
