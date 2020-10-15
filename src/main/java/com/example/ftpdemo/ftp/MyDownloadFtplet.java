package com.example.ftpdemo.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xinfan.zhou@things-matrix.com
 * @date 2020/06/01 11:44
 */
@Component
@Slf4j
public class MyDownloadFtplet extends DefaultFtplet {


    @Override
    public FtpletResult onDownloadStart(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        log.info("ftp-cli >>>>>>>>>> {}", request.getRequestLine());

        //获取下载文件的下载路径
        String path = session.getUser().getHomeDirectory();
        //获取上传用户
        String name = session.getUser().getName();
        //获取上传文件名
        String filename = request.getArgument();
        String absolutePath = session.getFileSystemView().getWorkingDirectory().getAbsolutePath();
        log.info("absolutePath:  " + absolutePath);
        log.info("状态：开始下载~，用户:'{}'，从目录下载文件：'{}'，文件名称为：'{}'", name, path + absolutePath, filename);

        return super.onDownloadStart(session, request);
    }

    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        //获取下载文件的下载路径
        String path = session.getUser().getHomeDirectory();
        //获取上传用户
        String name = session.getUser().getName();
        //获取上传文件名
        String filename = request.getArgument();
        String absolutePath = session.getFileSystemView().getWorkingDirectory().getAbsolutePath();
        log.info("absolutePath:  " + absolutePath);
        log.info("状态：成功~，用户:'{}'，从目录下载文件：'{}'，文件名称为：'{}'", name, path + absolutePath, filename);
        return super.onDownloadEnd(session, request);
    }

}

