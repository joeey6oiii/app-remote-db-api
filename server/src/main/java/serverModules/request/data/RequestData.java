package serverModules.request.data;

import serverModules.callerBack.CallerBack;

public class RequestData {
    private byte[] data;
    private CallerBack callerBack;
    private final boolean nullStatus;

    public RequestData() {
        nullStatus = true;
    }

    public RequestData(byte[] data, CallerBack callerBack) {
        this.data = data;
        this.callerBack = callerBack;
        nullStatus = false;
    }

    public byte[] getByteArray() {
        return data;
    }

    public void setByteArray(byte[] data) {
        this.data = data;
    }

    public CallerBack getCallerBack() {
        return callerBack;
    }

    public void setCallerBack(CallerBack callerBack) {
        this.callerBack = callerBack;
    }

    public boolean hasNullStatus() {
        return this.nullStatus;
    }
}