package main;

import commandsModule.handler.CommandHandler;
import database.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.Request;
import serverModules.connection.ConnectionModuleConfigurator;
import serverModules.connection.ConnectionModule;
import serverModules.context.ServerContext;
import serverModules.request.data.RequestData;
import serverModules.request.manager.RequestHandlerManager;
import serverModules.request.reader.RequestReader;

public class Server {

    private static final Logger logger = LogManager.getLogger("logger.Server");

    private final static int PORT = 64999;

    public static void main(String[] args) {

        ConnectionModule module = new ConnectionModuleConfigurator().newInstance(PORT);
        logger.info("Server started");

        try {
            while (true) {
                RequestData requestData = module.receiveData();
                Request request = new RequestReader().readRequest(requestData.getByteArray());
                ServerContext context = new ServerContext(module, requestData.getCallerBack(), request);
                new RequestHandlerManager().manageRequest(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Server shut down");
    }
}
