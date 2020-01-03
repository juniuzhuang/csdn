package com.duxiutianlang.dtpmodel.xa;

import com.mysql.jdbc.jdbc2.optional.MysqlXAConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class TMCoordinator {

    public void distributedTransaction(Connection integralCon, Connection redpacketCon) throws XAException, SQLException {
        boolean logXaCommands = true;

        //创建一个积分XA连接
        XAConnection integralConnetion = new MysqlXAConnection((com.mysql.jdbc.ConnectionImpl) integralCon, logXaCommands);
        //创建积分的资源对象
        XAResource integralXAResource = integralConnetion.getXAResource();
        //创建一个红包XA连接
        XAConnection redpacketConnetion = new MysqlXAConnection((com.mysql.jdbc.ConnectionImpl) redpacketCon, logXaCommands);
        //创建红包的资源对象
        XAResource redpacketrXAResource = redpacketConnetion.getXAResource();

        Random random = new Random();
        //生成一个全局事务ID
        byte[] globalId = UUID.randomUUID().toString().getBytes();
        int formatId = 1;
        //TMCoordinator 把integralXAResource的事务分支id，注册到全局事务ID
        byte[] integralBqual = ("integral" + random.nextInt(10000)).getBytes();
        Xid integralXid = new MysqlXid(globalId, integralBqual, formatId);
        //TMCoordinator 把redpacketrXAResource的事务分支id，注册到全局事务ID
        byte[] redpacketBqual = ("redpacket" + random.nextInt(10000)).getBytes();
        Xid redpacketXid = new MysqlXid(globalId, redpacketBqual, formatId);

        try {
            //业务逻辑是 100积分等于1块红包

            //开始
            integralXAResource.start(integralXid, XAResource.TMNOFLAGS);
            //模拟扣除100积分
            String sql = "update user_integral set user_integral=user_integral-100 where user_id='duxiutianlang'";
            PreparedStatement integralPS = integralCon.prepareStatement(sql);
            //预执行
            integralPS.execute();
            //预执行完成
            integralXAResource.end(integralXid, XAResource.TMSUCCESS);

            //开始
            redpacketrXAResource.start(redpacketXid, XAResource.TMNOFLAGS);
            //用于新增红包一块
            sql = "update user_redpacket set balance_redpacket=balance_redpacket+1 where user_id='duxiutianlang'";
            PreparedStatement redpacketPS = redpacketCon.prepareStatement(sql);
            redpacketPS.execute();
            redpacketrXAResource.end(redpacketXid, XAResource.TMSUCCESS);

            //2阶段提交中得第一个阶段：准备提交
            int integralXAResource_prepare = integralXAResource.prepare(integralXid);
            int redpacketrXAResource_prepare = redpacketrXAResource.prepare(redpacketXid);

            //2阶段提交中得第二个阶段：真正提交
            if (integralXAResource_prepare == XAResource.XA_OK && redpacketrXAResource_prepare == XAResource.XA_OK) {
                boolean onePhase = false;
                integralXAResource.commit(integralXid, onePhase);//提交事务
                redpacketrXAResource.commit(redpacketXid, onePhase);//提交事务
            } else {//全部回滚
                integralXAResource.rollback(integralXid);
                redpacketrXAResource.rollback(redpacketXid);
            }
        } catch (Exception e) {
            integralXAResource.rollback(integralXid);
            redpacketrXAResource.rollback(redpacketXid);
        }

    }
}
