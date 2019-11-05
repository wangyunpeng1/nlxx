package com.xx.controller;

import com.sun.javafx.collections.MappingChange;
import com.xx.service.LeaveAMessService;
import com.xx.vo.Mess;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("pages")
@RestController
public class LeaveAMessController {
    @Autowired
    private LeaveAMessService leaveAMessService;

    /**
     * 获取留言
     * @return
     */
    @GetMapping("getMess")
    public Result getMess ()
    {
        List<Mess> messes = leaveAMessService.getMess();
        Map map = new HashMap();
        map.put("mess",messes);
        map.put("size",messes.size());
        return new Result(true, StatusCode.OK,"查询成功",map);
    }

    @PostMapping("insertMess")
    public Result insertMess(@RequestBody Mess mess, HttpSession session)
    {
        try {
            mess.setUserId(session.getAttribute("userId").toString());
            mess.setUserName(session.getAttribute("userName").toString());
            leaveAMessService.insertMess(mess);
            return new Result(true, StatusCode.OK,"留言成功");
        }catch (NullPointerException e) {
            return new Result(false, StatusCode.noLogin, "未登陆");
        }
    }
}
