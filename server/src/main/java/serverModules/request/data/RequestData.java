package serverModules.request.data;

import requests.Request;
import serverModules.callerBack.CallerBack;

public class RequestData {
    private final CallerBack callerBack;
    private final Request request;

    public RequestData(CallerBack callerBack, Request request) {
        this.callerBack = callerBack;
        this.request = request;
    }

    public CallerBack getCallerBack() {
        return callerBack;
    }

    public Request getRequest() {
        return request;
    }
}