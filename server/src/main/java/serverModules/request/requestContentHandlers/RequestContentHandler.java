package serverModules.request.requestContentHandlers;

import requests.Request;

public interface RequestContentHandler {
    Object handleRequestContent(Request request);
}
