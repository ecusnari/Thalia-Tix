package thaliatix;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueDIDGenerator {
	static AtomicInteger atomicInteger = new AtomicInteger();
    public static int getUniqueID() {
        return atomicInteger.incrementAndGet();
    }
}
