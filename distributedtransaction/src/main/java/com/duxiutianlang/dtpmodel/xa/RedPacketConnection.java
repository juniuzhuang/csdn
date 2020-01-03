package com.duxiutianlang.dtpmodel.xa;

import java.sql.Connection;
import java.sql.DriverManager;

public class RedPacketConnection implements IConnection {
    @Override
    public Connection createConnection() throws Exception {
        Connection connection = DriverManager.getConnection(REDPACKET_URL,"meiling","meiling#78");
        return connection;
    }
}