package com.duxiutianlang.dtpmodel.xa;

public class DistributedTransactionTest {

    public static void main(String[] args) throws Exception {
        TMCoordinator tmCoordinator=new TMCoordinator();
        tmCoordinator.distributedTransaction(ConnectionCreateFactory.getIntegralConnection(),ConnectionCreateFactory.getRedPacketConnection());
    }
}
