package com.controller;

import com.entity.Chapter;
import com.service.ChapterService;
import com.util.AudioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    private String rename(String picFileName) {
        String uuid = UUID.randomUUID().toString();
        String subFile = picFileName.substring(picFileName.lastIndexOf('.'));
        return uuid + subFile;
    }


    @RequestMapping("addChapter")
    public boolean addChapter(String title, MultipartFile file, HttpServletRequest request, Integer pid) {
        Chapter chapter = new Chapter();
        chapter.setTitle(title);
        //获取文件名
        String picFileName = file.getOriginalFilename();
        //获取images绝对路径
        String path = request.getServletContext().getRealPath("upload");
        File file1 = new File(path);
        if (!file1.exists()) file1.mkdirs();
        //文件重命名
        String newFileName = rename(picFileName);
        //创建File对象
        File destFile = new File(path + "/" + newFileName);
        try {

            file.transferTo(destFile);
            //获取时长
            String time = AudioUtil.getAudioPlayTime(destFile);
            chapter.setSize(file.getSize() / 1024.0 / 1024.0);
            chapter.setPid(pid);
            chapter.setId(UUID.randomUUID().toString());
            chapter.setDuration(time);
            //最后添加,防止拷贝文件出错
            chapter.setDownPath(newFileName);
            chapterService.insert(chapter);
        } catch (Exception e) {
            //添加出错后删除拷贝的文件
            destFile.delete();
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
