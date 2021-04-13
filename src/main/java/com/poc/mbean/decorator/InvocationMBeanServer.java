package com.poc.mbean.decorator;

import com.poc.Tracker;

import javax.management.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InvocationMBeanServer extends BaseDecoratorMBeanServer {

    private MBeanServer inner;

    public InvocationMBeanServer(MBeanServer mBeanServer) {
        super(mBeanServer);
        this.inner = mBeanServer;
    }

    private String buildInvocationString(String operatorName, Object[] params) {
        String collect = Arrays.stream(params).map(String::valueOf).collect(Collectors.joining(", "));
        return String.format("%s: [%s]", operatorName, collect);
    }

    @Override
    public Object invoke(ObjectName name, String operationName, Object[] params, String[] signature) throws InstanceNotFoundException, MBeanException, ReflectionException {
        System.out.println("invoke call");
        String item = buildInvocationString(operationName, params);
        System.out.println("invocationList add: " + item);
        Tracker.invocationList.add(item);
        return inner.invoke(name, operationName, params, signature);
    }
}
