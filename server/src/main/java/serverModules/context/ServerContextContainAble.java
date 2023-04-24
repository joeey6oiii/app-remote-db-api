package serverModules.context;

import requests.Request;
import serverModules.callerBack.CallerBack;
import serverModules.connection.ConnectionModule;

public interface ServerContextContainAble {

    ConnectionModule getConnectionModule();

    CallerBack getCallerBack();

    Request getRequest();

}
