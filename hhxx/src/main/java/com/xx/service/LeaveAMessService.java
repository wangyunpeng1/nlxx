package com.xx.service;

import com.xx.dao.LeaveAMessDao;
import com.xx.vo.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LeaveAMessService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LeaveAMessDao leaveAMessDao;

    //获取留言
    public List<Mess> getMess()
    {
        List<Mess> messes = leaveAMessDao.getMess();
        return messes;
    }

    //提交留言
    public void insertMess(Mess mess)
    {
        mess.setMessId(getId());
        mess.setCreateDate(getTime());
        leaveAMessDao.insertMess(mess);
    }


    //获取id
    public String getId()
    {
        boolean flag = redisTemplate.hasKey("messId");
        if (flag)
        {
            String a =redisTemplate.opsForValue().get("messId").toString();
            int b = Integer.parseInt(a)+1;
            redisTemplate.opsForValue().set("messId",b);
        }else{
            redisTemplate.opsForValue().set("messId",1);
        }
        String messId =redisTemplate.opsForValue().get("messId").toString();
        System.out.println(messId);
        return messId;
    }

    //获取当前时间
    public String getTime()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return dateFormat.format(date);
    }
}
