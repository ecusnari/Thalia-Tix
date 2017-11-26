package thaliatix;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueTIDGenerator {
	static AtomicInteger atomicInteger = new AtomicInteger();
    public static int getUniqueID() {
        return atomicInteger.incrementAndGet();
}
}
