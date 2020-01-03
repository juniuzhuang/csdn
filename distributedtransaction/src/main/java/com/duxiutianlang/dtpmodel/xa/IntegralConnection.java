package com.duxiutianlang.dtpmodel.xa;

import java.sql.Connection;
import java.sql.DriverManager;

public class IntegralConnection implements IConnection {
    @Override
    public Connection createConnection() throws Exception {
        Connection connection = DriverManager.getConnection(INTEGRAL_URL,"meiling","meiling#78");
        return connection;
    }
}
