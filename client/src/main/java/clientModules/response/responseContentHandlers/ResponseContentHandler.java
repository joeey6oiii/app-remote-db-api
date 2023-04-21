package clientModules.response.responseContentHandlers;

import responses.Response;

public interface ResponseContentHandler {
    void handleResponseContent(Response response); // стоит ли методу что-то возвращать
}
