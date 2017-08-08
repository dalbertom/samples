package org.gradle;

import org.gradle.Person; // a main class
import org.junit.Test; // a unit test dependency
import org.h2.jdbc.JdbcConnection; // an integration test dependency

public class DatabaseTest {
    @Test
    public void dbTest() throws Exception {
        // work with DB
    }
}