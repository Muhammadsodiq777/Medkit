package com.example.medkit.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;

@Component
public class MyComponentLineRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void run(String... args) throws Exception {
        serverMaster();
    }

    public void serverMaster() throws Exception {
        String masterServerPort = "localhost";
        String hostAddress = "";
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                for (InterfaceAddress inetAddress : networkInterface.getInterfaceAddresses()) {
                    if (inetAddress.getNetworkPrefixLength() != 8 && inetAddress.getBroadcast() != null) {
                        hostAddress = inetAddress.getAddress().getHostAddress();
                        logger.info("Current IP address : " + hostAddress);
                    }
                }
            }
        }
        logger.info("Master Server Port : " + masterServerPort);
        if (masterServerPort.trim().equals(hostAddress)) {
            logger.info("Master Server");
        } else {
            logger.info("Another Server");
        }
    }
}
