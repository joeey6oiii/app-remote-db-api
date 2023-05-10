package clientModules.response.receivers;

import clientModules.connection.DataTransferConnectionModule;
import clientModules.request.sender.CommandExecutionRequestSender;
import clientModules.response.handlers.ExitCommandHandler;
import commands.CommandDescription;
import requests.CommandExecutionRequest;
import responses.ExecutionResultResponse;

import java.io.PrintWriter;
import java.util.Scanner;

public class ExitCommandReceiver implements CommandReceiver {

    @Override
    public void receiveCommand(CommandDescription cmd, String[] args, DataTransferConnectionModule module) {
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
                out.print("Returning to the console input\n");
                out.flush();
                return;
            }
        }
        out.close();
        CommandExecutionRequest request = new CommandExecutionRequest(cmd, args);
        ExecutionResultResponse resultResponse = new CommandExecutionRequestSender().sendRequest(module, request);
        new ExitCommandHandler().handleResponse(resultResponse);
    }
}
