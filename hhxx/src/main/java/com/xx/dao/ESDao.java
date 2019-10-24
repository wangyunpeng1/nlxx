package com.xx.dao;

import com.xx.vo.ESBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESDao extends ElasticsearchRepository<ESBlog,String>
{
//    public Page<ESBlog> findByBlogIdOrBlogNameLikeOrBlogLabel(String blogId, String blogName, String blogLabel, Pageable pageable);
}
