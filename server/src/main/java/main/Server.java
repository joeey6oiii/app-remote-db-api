package main;

import database.LoadService;
import defaultClasses.Person;
import fileService.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.Request;
import serverModules.connection.ConnectionModuleConfigurator;
import serverModules.connection.ConnectionModule;
import serverModules.context.ServerContext;
import serverModules.request.data.RequestData;
import serverModules.request.handlers.RequestHandlerManager;
import serverModules.request.reader.RequestReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final Logger logger = LogManager.getLogger("logger.Server");

    private static final int PORT = 64999;

    public static void main(String[] args) {

        File file = new File("server\\src\\main\\resources\\Person.yaml");
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
            logger.fatal("Failed to read file: " + e.getMessage());
        }

        if (!list.isEmpty()) {
            logger.info("Uploading data from file to database...");
            new LoadService().loadToDatabase(list);
            logger.info("Data uploaded");
        } else {
            logger.info("Upload data not found. Continuing execution with an empty database");
        }

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
            logger.fatal("Unpredicted error " + e.getMessage());
        }
        logger.info("Server shut down");
    }
}