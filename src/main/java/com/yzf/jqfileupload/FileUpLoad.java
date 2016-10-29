package com.yzf.jqfileupload;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        final String FIle_PATH=request.getRealPath("");
        File upload_file=new File(FIle_PATH + "/UserFiles/" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        if(!upload_file.exists()){
            upload_file.mkdirs();
        }
        response.getWriter().print(upload_file.getPath());
    }
}
