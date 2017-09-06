package uk.tommyt.zkatomic;

import org.apache.log4j.Logger;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;

public class Counter {
    private DistributedAtomicLong counter;
    private Logger log = Logger.getLogger(Counter.class.getName());

    public Counter(Cluster cluster, String path) {
        counter = new DistributedAtomicLong(cluster.client, path, cluster.retryPolicy);
    }

    public Long inc() {
        try {
            AtomicValue c = counter.increment();
            if (c.succeeded()) {
                return (Long) c.postValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(String.format("Incrementing: %s", e));
            return null;
        }
    }
}
