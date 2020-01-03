package com.duxiutianlang.dtpmodel.xa;

import java.sql.Connection;

//连接创建工厂
public class ConnectionCreateFactory {

    private ConnectionCreateFactory(){

    }

    public static Connection getIntegralConnection() throws Exception {
        IConnection integralConnection = new IntegralConnection();
        return integralConnection.createConnection();
    }

    public static Connection getRedPacketConnection() throws Exception {
        IConnection redPacketConnection = new RedPacketConnection();
        return redPacketConnection.createConnection();
    }
}
