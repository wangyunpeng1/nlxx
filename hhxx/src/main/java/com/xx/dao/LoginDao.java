package com.xx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginDao
{
    //根据账号找密码
    public String findPassword(@Param("account") String account);

    //根据账号找Id
    public String findAccountUserId(@Param("account") String account);
    //根据手机号找Id

    public String findPhoneUserId(@Param("phone") String phone);
}
