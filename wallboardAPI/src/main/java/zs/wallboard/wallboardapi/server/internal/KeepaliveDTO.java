package zs.wallboard.wallboardapi.server.internal;

public class KeepaliveDTO {

    private final KeepAliveState state;
    private final long TimeStamp;

    public KeepaliveDTO(final KeepAliveState state, final long timeStamp) {
        this.state = state;
        TimeStamp = timeStamp;
    }

    public KeepAliveState getState() {
        return state;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

}
