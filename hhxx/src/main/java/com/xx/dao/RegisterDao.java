package com.xx.dao;

import com.xx.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegisterDao
{
    public boolean isExistPhone(@Param("phone") String phone);
    public boolean isExistAccount(@Param("account") String account);
    public void registerUser(User user);

}
