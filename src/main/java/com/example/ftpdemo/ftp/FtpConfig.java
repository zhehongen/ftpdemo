package com.example.ftpdemo.ftp;

import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.DbUserManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huyapeng
 * @date 2020/5/26
 * Email: yapeng.hu@things-matrix.com
 */
@Configuration
public class FtpConfig {

    private static final Logger logger = LoggerFactory.getLogger(FtpService.class);

    @Resource
    private DataSource dataSource;

    @Value("${ftp.server.port}")
    private Integer port;
    @Value("${ftp.server.passive-ports}")
    private String passivePorts;

    @Resource
    private MyDownloadFtplet myDownloadFtplet;

    @SuppressWarnings("SpellCheckingInspection")
    @Bean
    public FtpServer ftpServer() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();
        //1、设置服务端口
        listenerFactory.setPort(port);
        //2、设置被动模式数据上传的接口范围,云服务器需要开放对应区间的端口给客户端
        DataConnectionConfigurationFactory dataConnectionConfFactory = new DataConnectionConfigurationFactory();
        dataConnectionConfFactory.setPassivePorts(passivePorts);
        listenerFactory.setDataConnectionConfiguration(dataConnectionConfFactory.createDataConnectionConfiguration());

        //4、替换默认的监听器
        Listener listener = listenerFactory.createListener();
        serverFactory.addListener("default", listener);

        //5、配置自定义用户事件
        Map<String, Ftplet> ftpLets = new HashMap(1);
        ftpLets.put("ftpService", myDownloadFtplet);
        serverFactory.setFtplets(ftpLets);

        //6.2、基于数据库来存储用户实例
        DbUserManagerFactory dbUserManagerFactory = new DbUserManagerFactory();
        dbUserManagerFactory.setDataSource(dataSource);
        dbUserManagerFactory.setAdminName("admin");
        dbUserManagerFactory.setSqlUserAdmin("SELECT userid FROM ftp_user WHERE userid='{userid}' AND userid='admin'");
        dbUserManagerFactory.setSqlUserInsert("INSERT INTO ftp_user (userid, userpassword, homedirectory, " +
                "enableflag, writepermission, idletime, uploadrate, downloadrate) VALUES " +
                "('{userid}', '{userpassword}', '{homedirectory}', {enableflag}, " +
                "{writepermission}, {idletime}, uploadrate}, {downloadrate})");
        dbUserManagerFactory.setSqlUserDelete("DELETE FROM ftp_user WHERE userid = '{userid}'");
        dbUserManagerFactory.setSqlUserUpdate("UPDATE ftp_user SET userpassword='{userpassword}',homedirectory='{homedirectory}',enableflag={enableflag},writepermission={writepermission},idletime={idletime},uploadrate={uploadrate},downloadrate={downloadrate},maxloginnumber={maxloginnumber}, maxloginperip={maxloginperip} WHERE userid='{userid}'");
        dbUserManagerFactory.setSqlUserSelect("SELECT * FROM ftp_user WHERE userid = '{userid}'");
        dbUserManagerFactory.setSqlUserSelectAll("SELECT userid FROM ftp_user ORDER BY userid");
        dbUserManagerFactory.setSqlUserAuthenticate("SELECT userid, userpassword FROM ftp_user WHERE userid='{userid}'");
        dbUserManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(dbUserManagerFactory.createUserManager());
        //7、实例化FTP Server
        return serverFactory.createServer();
    }

}
