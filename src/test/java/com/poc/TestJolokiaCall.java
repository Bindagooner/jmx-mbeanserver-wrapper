package com.poc;

import org.jolokia.client.J4pClient;
import org.jolokia.client.request.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJolokiaCall {

    POCMain main;
    J4pClient j4pClient;

    @BeforeAll
    public void setUp() throws Exception {
        main = new POCMain();
        main.initServer();
        main.startServer();
        j4pClient = new J4pClient("http://localhost:8080/jolokia/");
    }

    @AfterAll
    public void tearDown() {
        main.stopServer();
    }

    @Test
    public void testInvoke() throws Exception {
        // http://localhost:8080/jolokia/exec/com.poc.mbean:type=Sample/setAttribute/key1/value1
        J4pExecRequest req = new J4pExecRequest("com.poc.mbean:type=Sample",
                "setAttribute", "key1", "value1");
        J4pExecResponse resp = j4pClient.execute(req);
        System.out.println(resp.asJSONObject());
        // build toString template
        String expect = "setAttribute: [key1, value1]";
        assertTrue(Tracker.invocationList.contains(expect));
    }

//    @Test
//    public void testAddLatency() throws Exception {
//        // http://localhost:8080/jolokia/exec/com.poc.mbean:type=Sample/getAttribute/key1
//        J4pExecRequest req = new J4pExecRequest("com.poc.mbean:type=Sample",
//                "getAttribute", "key1");
//        J4pExecResponse resp = j4pClient.execute(req);
//        // try to sleep 3s
//        System.out.println(resp.asJSONObject());
//        assertTrue(Tracker.latencyList.stream().anyMatch(l -> l >= 3000));
//    }

    @Test
    public void testAddLatency() throws Exception {
        // http://localhost:8080/jolokia/read/com.poc.mbean:type=Sample/Name/vannhi

        J4pReadRequest req = new J4pReadRequest("com.poc.mbean:type=Sample", "Name");
        J4pReadResponse resp = j4pClient.execute(req);
        // try to sleep 3s
        System.out.println(resp.asJSONObject());
        assertTrue(Tracker.latencyList.stream().anyMatch(l -> l >= 3000));
    }
}
