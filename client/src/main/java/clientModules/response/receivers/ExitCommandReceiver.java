package clientModules.response.receivers;

import clientModules.connection.ConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.contentHandlers.ExecutionResultHandler;
import clientModules.response.contentHandlers.ExitCommandHandler;
import commands.CommandDescription;
import requests.CommandExecutionRequest;
import responses.CommandExecutionResultResponse;

import java.io.PrintWriter;
import java.util.Scanner;

public class ExitCommandReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] arr, ConnectionModule module) {
        PrintWriter out = new PrintWriter(System.out);
        out.println("Are you sure you want to exit? [Y/N]");
        out.flush();
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (!str.equalsIgnoreCase("Y")) {
            out.print("$ ");
            out.flush();
            str = scanner.nextLine();
            if (str.equalsIgnoreCase("N")) {
                out.print("Return to console input\n");
                out.flush();
                return;
            }
        }
        out.close();
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, arr);
        CommandExecutionResultResponse resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);
        new ExitCommandHandler().handleResponse(resultResponse);
    }
}
