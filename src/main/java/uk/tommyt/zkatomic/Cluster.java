package uk.tommyt.zkatomic;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Cluster {
    RetryPolicy retryPolicy;
    CuratorFramework client;

    public Cluster(String zkConnString) {
        retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zkConnString, retryPolicy);
        client.start();
    }
}
