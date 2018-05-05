package src;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    public static final Logger log = LogManager.getLogger("A1");

    public static void main(String[] args) throws InterruptedException {
        String log4jConfigPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfigPath);

        Thread serverThread = new Thread(new Server());
        serverThread.start();

        try {
            // Juntamos ambos threads
            serverThread.join();
        } catch(InterruptedException ie) {
            Main.log.warn(ie.getMessage());
            throw ie;
        }
    }
}
