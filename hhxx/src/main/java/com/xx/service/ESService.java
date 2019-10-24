package com.xx.service;

import com.xx.esRepository.ESRepository;
import com.xx.vo.ESBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ESService
{
    @Autowired
    private ESRepository esRepository;

    public Page<ESBlog> findByBlogIdAndBlogNameLikeAndBlogLabel(String words, int start, int pageSize )
    {
        PageRequest request = PageRequest.of(start, pageSize);
        return esRepository.findByBlogidOrBlognameLikeOrBloglabel(words,words,words,request);
    }
}
