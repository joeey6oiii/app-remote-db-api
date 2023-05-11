package main;

import database.LoadService;
import defaultClasses.Person;
import fileService.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.Request;
import serverModules.connection.ConnectionModule;
import serverModules.connection.ConnectionModuleFactory;
import serverModules.connection.DatagramConnectionModuleFactory;
import serverModules.context.ServerContext;
import serverModules.request.data.RequestData;
import serverModules.request.handlers.RequestHandlerManager;
import serverModules.request.reader.RequestReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Program entry point class. Contains <code>main()</code> method.
 */

public class Server {

    private static final Logger logger = LogManager.getLogger("logger.Server");

    private static final int PORT = 64999;

    /**
     * Program entry point.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        File file = new File(".jar\\src\\main\\resources\\Person.yaml");
        FileService fileService = new FileService();
        Class<Person> type = Person.class;

        List<Person> list = new ArrayList<>();
        try {
            if (file.exists()) {
                logger.info("Reading file...");
                list = fileService.readFile(file, type);
                logger.info("Completed reading file");
            } else {
                fileService.createFile(file);
                logger.warn("\"Person.yaml\" file not found. Created new eponymous file");
            }
        } catch (IOException e) {
            logger.fatal("Failed to proceed file", e);
        }

        if (!list.isEmpty()) {
            logger.info("Uploading data from file to database...");
            new LoadService().loadToDatabase(list);
            logger.info("Data uploaded");
        } else {
            logger.info("Upload data not found. Continuing execution with an empty database");
        }

        ConnectionModuleFactory factory = new DatagramConnectionModuleFactory();
        ConnectionModule module = factory.createConnectionModule(PORT);
        logger.info("Server started");
        while (true) {
            try {
                RequestData requestData = module.receiveData();
                if (requestData.hasNullStatus()) {
                    logger.debug("Empty request received");
                    continue;
                }

                Request request = new RequestReader().readRequest(requestData.getByteArray());
                ServerContext context = new ServerContext(module, requestData.getCallerBack(), request);
                new RequestHandlerManager().manageRequest(context);
            } catch (IOException e) {
                logger.error("Something went wrong during I/O operations", e);
            } catch (ClassNotFoundException e) {
                logger.error("Unexpected error: Could not find request class", e);
            } catch (Exception e) {
                logger.error("Unexpected error happened during server operations", e);
            }
        }
    }
}