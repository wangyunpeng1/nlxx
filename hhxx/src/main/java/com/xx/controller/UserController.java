package com.xx.controller;

import com.xx.service.BlogService;
import com.xx.service.UserService;
import com.xx.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("pages")
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
     * 加载个人信息中的我的博客
     * @param session
     * @return
     */
    @GetMapping("userBlogs")
    public Result userBlogs(HttpSession session)
    {
        String userId = session.getAttribute("userId").toString();
        List<Blog> userBlogs = userService.userBlogs(userId);
        return new Result(true, StatusCode.OK,"查询成功",userBlogs);
    }

    /**
     * 用户收藏博客
     * @param userBlog
     * @return
     */
    @PostMapping("userCollectionBlogs")
    public Result userCollectionBlogs(@RequestBody UserBlog userBlog,HttpSession session)
    {
        userBlog.setUserId(session.getAttribute("userId").toString());
        boolean flag = userService.userCollectionBlogs(userBlog);
        if (flag){
            return new Result(false, StatusCode.OK,"取消成功");
        }else{
            return new Result(true, StatusCode.OK,"收藏成功");
        }
    }

    /**
     * 获取博客的收藏总数
     * @param userBlog
     * @return
     */
    @PostMapping("selectCollections")
    public Result selectCollections(@RequestBody UserBlog userBlog)
    {
        long a = blogService.selectCollections(userBlog.getBlogId());
        return new Result(true, StatusCode.OK,"查看成功",a);
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
     * @param session
     * @return
     */
    @GetMapping("userSum")
    public Result userSum(HttpSession session)
    {
        String userId = session.getAttribute("userId").toString();
        UserSumPlus userSums = userService.userSum(userId);
        return new Result(true, StatusCode.OK,"查看各种数成功",userSums);
    }

    /**
     * 上传博客
     * @param blog
     * @return
     */
    @PostMapping("uploadBlog")
    public Result uploadBlog(@RequestBody Blog blog, HttpSession session)
    {
        blog.setUserId(session.getAttribute("userId").toString());
        blogService.uploadBlog(blog);
        return new Result(true, StatusCode.OK,"上传成功");
    }

    /**
     * 修改博客
     * @param blog
     * @return
     */
    @PostMapping("updateBlog")
    public Result updateBlog(@RequestBody Blog blog,HttpSession session)
    {
        String userId = session.getAttribute("userId").toString();
        blog.setUserId(userId);
        blogService.updateBlog(blog);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     * 点赞博客
     * @param userBlog
     * @return
     */
    @PostMapping("fabulousBlog")
    public Result fabulousBlog(@RequestBody UserBlog userBlog,HttpSession session)
    {
        userBlog.setUserId(session.getAttribute("userId").toString());
        boolean flag = userService.fabulousBlog(userBlog);
        if (flag){
            return new Result(true, StatusCode.OK,"点赞成功");
        }else {
            return new Result(false, StatusCode.OK,"取消成功");
        }
    }

    /**
     * 获取博客点赞数
     * @param userBlog
     * @return
     */
    @PostMapping("selectFabulous")
    public Result selectFabulous(@RequestBody UserBlog userBlog)
    {
        long a = userService.selectBulous(userBlog.getBlogId());
        return new Result(true, StatusCode.OK,"查看成功",a);
    }
}
