package zs.wallboard.wallboardapi.providers.impl;

import zs.wallboard.wallboardapi.providers.TimeProvider;

import java.time.LocalDateTime;

public class TimeProviderImpl implements TimeProvider {

    @Override
    public long getNowAsMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }

}
