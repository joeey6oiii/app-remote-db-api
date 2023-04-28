package clientModules.response.contentHandlers;

import responses.Response;

import java.io.PrintWriter;

public class ExitCommandHandler implements HandleResponseAble {

    @Override
    public void handleResponse(Response response) {
        PrintWriter pw = new PrintWriter(System.out);
        pw.println("Shutting down program...");
        pw.flush();
        System.exit(0);
    }
}
