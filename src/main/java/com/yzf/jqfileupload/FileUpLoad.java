package com.yzf.jqfileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YZF on 2016/10/28.
 */
public class FileUpLoad extends HttpServlet{

    /**
     * 处理上传方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            final String FIle_PATH=request.getRealPath("")+ "/UserFiles/" + new SimpleDateFormat("yyyyMMdd").format(new Date())+"/tmp";
            File tmpFile=new File(FIle_PATH );
            if(!tmpFile.exists()){
                tmpFile.mkdirs();
            }
            final DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //缓冲区8MB
            diskFileItemFactory.setSizeThreshold(8 * 1024);
            //设置目录
            diskFileItemFactory.setRepository(tmpFile);
            final ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setHeaderEncoding("UTF-8");

            servletFileUpload.setSizeMax(10*1024*1024);
            //获得文件
            List<FileItem> fileItems=servletFileUpload.parseRequest(request);
            String userFilePath="";
            final Iterator<FileItem> fileItemIterator=fileItems.iterator();
            while (fileItemIterator.hasNext()){
                final FileItem item = fileItemIterator.next();

                String temp=item.getName();
                String fileName= UUID.randomUUID().toString().replaceAll("-","")
                        +item.getName().substring(item.getName().lastIndexOf("."));
                final File userFile=new File(FIle_PATH,fileName);
                item.write(userFile);
                userFilePath=FIle_PATH+"/"+fileName;
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("{\"info\":\"ok\",\"path\":\""+userFilePath+"\"}");
        }catch (Exception e){
            response.getWriter().print("{\"info\":\"error\"}");
            e.printStackTrace();
        }finally {
            response.getWriter().close();
        }
    }
}
