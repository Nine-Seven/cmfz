package com.controller;

import com.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("selectAlbums")
    public @ResponseBody
    Map selectCarousels(int page, int rows) {
        return albumService.selectAll(page, rows);
    }


    private String rename(String picFileName) {
        String uuid = UUID.randomUUID().toString();
        String subFile = picFileName.substring(picFileName.lastIndexOf('.'));
        return uuid + subFile;
    }


    @RequestMapping("addAlbum")
    public @ResponseBody
    boolean addAlbum() {
        return false;
    }


    @RequestMapping("/download")
    public String download(String title, String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取文件
        if (filePath != null) {
            ServletContext context = request.getSession().getServletContext();

            String path = request.getServletContext().getRealPath("upload");

            File file = new File(path + "/" + filePath);
            if (file.exists()) {

                String mimeType = context.getMimeType(path + "/" + filePath);
                response.setContentType(mimeType);// 设置下载文件格式

                //获取文件后缀
                String subFile = filePath.substring(filePath.lastIndexOf('.'));

                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + title + subFile);// 设置文件名

                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;

                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
