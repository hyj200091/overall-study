package com.soft1851.org.n3r.idworker;

/**
 * @author mqxu
 */
public interface WorkerIdStrategy {
    void initialize();

    long availableWorkerId();

    void release();
}
