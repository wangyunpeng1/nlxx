package com.xx.dao;

import com.xx.vo.Mess;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LeaveAMessDao {
    //获取留言
    public List<Mess> getMess();
    //添加留言
    public void insertMess(Mess mess);
}
