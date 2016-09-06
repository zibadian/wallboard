package zs.wallboard.wallboardapi.providers;

import java.time.LocalDateTime;

public interface TimeProvider {

    long getNowAsMillis();

    LocalDateTime getNow();

}
