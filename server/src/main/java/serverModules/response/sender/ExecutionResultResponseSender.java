package serverModules.response.sender;

import serverModules.response.responses.ExecutionResultResponse;

import java.net.DatagramSocket;

public class ExecutionResultResponseSender implements ResponseAble<ExecutionResultResponse> {

    @Override
    public void sendResponse(DatagramSocket socket, String address, int port, ExecutionResultResponse result) {

    }
}
