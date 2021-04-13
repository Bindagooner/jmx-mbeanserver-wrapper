package com.poc.mbean.decorator;

import com.poc.Tracker;

import javax.management.*;

public class GetAttributeMBeanServer extends BaseDecoratorMBeanServer {

    private MBeanServer inner;

    public GetAttributeMBeanServer(MBeanServer mBeanServer) {
        super(mBeanServer);
        inner = mBeanServer;
    }

    @Override
    public Object getAttribute(ObjectName name, String attribute) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException {
        System.out.println("getAttribute call");
        // TODO sleep 3s
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object rs = inner.getAttribute(name, attribute);
        Tracker.latencyList.add(System.currentTimeMillis() - start);
        return rs;
    }
}
