package serverModules.processors;

import requests.Request;

public interface Processor {
    void proceed(Request request);
}
