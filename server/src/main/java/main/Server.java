package main;

import serverModules.connection.ConnectionModule;

public class Server {
    private final static int PORT = 9999;

    public static void main(String[] args) {
        try {
            ConnectionModule module = new ConnectionModule(PORT);

            while (true) {

            }
        } catch (Exception e) {

        }
    }
}
