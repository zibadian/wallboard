package zs.wallboard.wallboardapi.server;

import zs.wallboard.wallboardapi.server.internal.KeepAliveState;

public interface ApiServer {

    void shutdown();

    void pause();

    void resume();

    KeepAliveState getState();

}
