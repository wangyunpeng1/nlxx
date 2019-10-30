package com.xx.esRepository;

import com.xx.vo.ESBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ESRepository extends ElasticsearchRepository<ESBlog,String>
{
    Page<ESBlog> findByBlogidOrBlognameLikeOrBloglabel(String blogId,String blogName,String blogLabel,Pageable pageable);
}
