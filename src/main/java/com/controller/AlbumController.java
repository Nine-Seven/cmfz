package com.controller;

import com.entity.Album;
import com.service.AlbumService;
import com.util.RenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("selectAlbums")
    public @ResponseBody
    Map selectCarousels(int page, int rows) {
        return albumService.selectAll(page, rows);
    }




    @RequestMapping("addAlbum")
    public @ResponseBody
    boolean addAlbum(Album album, MultipartFile pic, HttpServletRequest request) {

        String path = request.getServletContext().getRealPath("img");

        //获取文件名
        String picName = pic.getOriginalFilename();
        //获取images绝对路径
        //文件重命名
        String newFileName = RenameUtil.rename(picName);
        //创建File对象
        File destFile = new File(path + "/" + newFileName);
        try {
            pic.transferTo(destFile);
            //最后添加,防止拷贝文件出错
            album.setCoverImg(newFileName);
            albumService.insert(album);
        } catch (Exception e) {
            //添加出错后删除拷贝的文件
            destFile.delete();
            e.printStackTrace();
            return false;
        }
        return true;
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
