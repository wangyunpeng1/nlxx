package com.xx.controller;

import com.xx.service.BlogService;
import com.xx.service.UserService;
import com.xx.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    /**
     * 关注用户
     * @param userFans
     * @return
     */
    @PostMapping("flow")
    public Result userFlow(@RequestBody UserFans userFans)
    {
        boolean flag = userService.userFlow(userFans);
        if (flag)
        {
            return new Result(true, StatusCode.OK,"关注成功");
        }else {
            return new Result(true, StatusCode.OK,"取消关注成功");
        }
    }

    /**
     * 查看用户博客
     * @param userBlog
     * @return
     */
    @PostMapping("userBlogs")
    public Result userBlogs(@RequestBody UserBlog userBlog)
    {
        List<UserBlog> userBlogs = userService.userBlogs(userBlog);
        return new Result(true, StatusCode.OK,"查询成功",userBlogs);
    }

    /**
     * 用户收藏博客
     * @param userBlog
     * @return
     */
    @PostMapping
    public Result userCollectionBlogs(@RequestBody UserBlog userBlog)
    {
        userService.userCollectionBlogs(userBlog);
        return new Result(true, StatusCode.OK,"收藏成功");
    }

    @PostMapping
    public Result userCancelCollectionBlogs(@RequestBody UserBlog userBlog)
    {
        userService.userCancelCollectionBlogs(userBlog);
        return new Result(true, StatusCode.OK,"取消成功");
    }

    /**
     * 查看用户收藏博客（我的收藏）
     * @param userBlog
     * @return
     */
    @PostMapping("lookUserCollectionBlogs")
    public Result lookUserCollectionBlogs(@RequestBody UserBlog userBlog)
    {
        List<UserBlog> userBlogs = userService.lookUserCollectionBlogs(userBlog);
        return new Result(true, StatusCode.OK,"查看收藏成功",userBlogs);
    }

    /**
     * 查看用户各种数
     * @param userId
     * @return
     */
    @PostMapping("userSum")
    public Result userSum(@PathVariable String userId)
    {
        List<UserSum> userSums = userService.userSum(userId);
        return new Result(true, StatusCode.OK,"查看各种数成功",userSums);
    }

    /**
     * 上传博客
     * @param blog
     * @return
     */
    @PostMapping("uploadBlog")
    public Result uploadBlog(@RequestBody Blog blog)
    {
        blogService.uploadBlog(blog);
        return new Result(true, StatusCode.OK,"上传成功");

    }

    /**
     * 修改博客
     * @param blog
     * @return
     */
    @PostMapping("updateBlog")
    public Result updateBlog(@RequestBody Blog blog)
    {
        blogService.updateBlog(blog);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     * 点赞博客
     * @param userBlog
     * @return
     */
    @PostMapping("fabulousBlog")
    public Result fabulousBlog(@RequestBody UserBlog userBlog)
    {
        boolean flag = userService.fabulousBlog(userBlog);
        if (flag){
            return new Result(true, StatusCode.OK,"点赞成功");
        }else {
            return new Result(true, StatusCode.OK,"取消成功");
        }
    }

    /**
     * 查询博客点赞数（测试上述功能）
     * @param blogId
     * @return
     */
    @PostMapping("selectFabulous/{blogId}")
    public Result selectFabulous(@PathVariable String blogId)
    {
        long a = userService.selectBulous(blogId);
        return new Result(true, StatusCode.OK,"查看成功",a);
    }
}
