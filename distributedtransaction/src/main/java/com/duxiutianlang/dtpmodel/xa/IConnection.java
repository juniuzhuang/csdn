package com.duxiutianlang.dtpmodel.xa;

import java.sql.Connection;

public interface IConnection {
    String INTEGRAL_URL="jdbc:mysql://118.178.88.114:32502/xa_integral?characterEncoding=utf8&useSSL=false&autoReconnect=true";  //积分系统的URL
    String REDPACKET_URL="jdbc:mysql://118.178.88.114:32502/xa_user?characterEncoding=utf8&useSSL=false&autoReconnect=true";  //红包系统的URL
    //创建一个连接
    Connection createConnection() throws Exception;
}
