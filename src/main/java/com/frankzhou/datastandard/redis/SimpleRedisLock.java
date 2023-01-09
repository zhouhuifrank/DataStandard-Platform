package com.frankzhou.datastandard.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleRedisLock implements DistributedLock {

    @Override
    public boolean tryLock(long timeSec) {
        return false;
    }

    @Override
    public void unLock() {

    }
}
