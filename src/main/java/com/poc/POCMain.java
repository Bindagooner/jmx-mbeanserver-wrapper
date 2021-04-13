package com.poc;

import com.poc.mbean.Sample;
import org.jolokia.jvmagent.JolokiaServer;
import org.jolokia.jvmagent.JvmAgentConfig;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class POCMain {


    private JolokiaServer jolokiaServer;
    private boolean isRunning;

    public static void main(String[] args) throws Exception {

    }

    public void initServer() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.poc.mbean:type=Sample");
        Sample mbean = new Sample();
        mbs.registerMBean(mbean, name);

        jolokiaServer = new JolokiaServer(new JvmAgentConfig("host=*,port=8080"), false);
    }

    public void startServer() {
        jolokiaServer.start();
        isRunning = true;
    }

    public void stopServer() {
        jolokiaServer.stop();
        isRunning = false;
    }
}
