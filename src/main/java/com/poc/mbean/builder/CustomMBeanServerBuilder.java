package com.poc.mbean.builder;

import com.poc.mbean.decorator.BaseDecoratorMBeanServer;
import com.poc.mbean.decorator.GetAttributeMBeanServer;
import com.poc.mbean.decorator.InvocationMBeanServer;

import javax.management.*;

public class CustomMBeanServerBuilder extends MBeanServerBuilder {

    public CustomMBeanServerBuilder() {
        System.out.println("init CustomMBeanServerBuilder...");
    }

    @Override
    public MBeanServer newMBeanServer(String defaultDomain, MBeanServer outer, MBeanServerDelegate delegate) {
        MBeanServer mBeanServer = new GetAttributeMBeanServer(super.newMBeanServer(defaultDomain, outer, delegate));
        mBeanServer = new InvocationMBeanServer(mBeanServer);
        return mBeanServer;
    }
}
