package uk.tommyt.zkatomic;

import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(Main.class.getName());

        if (args.length != 1) {
            log.fatal("Config properties file should be passed as argument");
            System.exit(101);
        }

        Config config = null;
        try {
            config = new Config(args[0]);
        } catch (java.io.IOException e) {
            log.fatal(String.format("Opening config file: %s", e.toString()));
            System.exit(101);
        }

        Cluster cluster = new Cluster(config.get("zkConnString"));
        Counter counter = new Counter(cluster, config.get("counterPath"));

        long t1, t2;
        int iterations = Integer.parseInt(config.get("counterIterations"));

        t1 = java.lang.System.currentTimeMillis();
        for (int i=0; i<iterations ; i++){
            log.debug(String.format("Counter: %d", counter.inc()));
            counter.inc();
        }
        t2 = java.lang.System.currentTimeMillis();

        double rate = (double) iterations / (t2 - t1) * 1000;

        System.out.printf("%dms for %d iterations, (%.1f/s)", (t2 - t1), iterations, rate);
    }
}
