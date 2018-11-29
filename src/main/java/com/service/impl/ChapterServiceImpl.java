package com.service.impl;

import com.dao.ChapterMapper;
import com.entity.Chapter;
import com.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public void insert(Chapter chapter) {
        chapterMapper.insert(chapter);
    }
}
