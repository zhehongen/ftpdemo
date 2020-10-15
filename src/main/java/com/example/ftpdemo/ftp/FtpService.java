package com.example.ftpdemo.ftp;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author huyapeng
 * @date 2020/5/26
 * Email: yapeng.hu@things-matrix.com
 */
@Service
public class FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpService.class);

    @Resource
    private FtpServer server;

    @PostConstruct
    public void startServer() {
        try {
            server.start();
            logger.info("Apache Ftp server is starting!");
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }


    @PreDestroy
    public void stopServer() {
        server.stop();
        logger.info("Apache Ftp server is stopping!");

    }
}
