package com.xx.controller;

import com.xx.service.BlogService;
import com.xx.vo.*;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("pages")
public class BlogController
{
    @Autowired
    private BlogService blogService;

    /**
     * 查看博客
     * @param blogId
     * @return
     */
    @PostMapping("lookBlogId/{blogId}")
    public Result lookBlogId(@PathVariable("blogId") String blogId)
    {
        return new Result(true, StatusCode.OK,"查看成功",blogService.lookBlog(blogId));
    }

    /**
     * 获取前10点赞的博客
     * @return
     */
    @PostMapping("getTopTenFabulousBlogs")
    public Result getTopTenFabulousBlogs()
    {
        Set<String> blogFabulous = blogService.getTopTenFabulousBlogs();
        //id转博客
        Map<String,String> set = blogService.getblogIdName(blogFabulous);
        return new Result(true,StatusCode.OK,"查询成功",set);
    }

    /**
     * 获取前10浏览的博客
     * @return
     */
    @PostMapping("getTopTenVisitBlogs")
    public Result getTopTenVisitBlogs()
    {
        Set<String> blogVisit = blogService.getTopTenVisitBlogs();
        //id转博客
        Map<String,String> set = blogService.getblogIdName(blogVisit);
        return new Result(true,StatusCode.OK,"查询成功",set);
    }

    /**
     * 获取前10收藏的博客
     * @return
     */
    @PostMapping("getTopTenCollectionsBlogs")
    public Result getTopTenCollectionsBlogs()
    {
        Set<String> blogCollections = blogService.getTopTenCollectionsBlogs();
        //id转博客
        Map<String,String> set = blogService.getblogIdName(blogCollections);
        return new Result(true,StatusCode.OK,"查询成功",set);
    }

    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("uploadImg")
    public String uploadImg(@Param("file") MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        Date date = new Date();
        //format() 字符串格式化
        String res = sdf.format(date);
//        本地使用
        File f=new File("E:\\idea\\nlxx\\hhxx\\src\\main\\resources\\static\\uploadImg");
        if(!f.exists())
        {
            try {
                f.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String rootPath = "E:\\idea\\nlxx\\hhxx\\src\\main\\resources\\static\\uploadImg\\";
//        String rootPath = request.getServletContext().getRealPath("D:/3333/uploads");
        //原文件名称
        String originalFilename = file.getOriginalFilename();
        //定义新名称
        String newFileName = res+originalFilename.substring(originalFilename.lastIndexOf("."));
        //创建年月文件夹
//        Calendar dateTwo = Calendar.getInstance();
//        File dateDirs = new File(dateTwo.get(Calendar.YEAR)
//                + File.separator + (dateTwo.get(Calendar.MONTH)+1));
        //新文件
        File newFile = new File(rootPath+newFileName);
//        if(!newFile.getParentFile().exists()) {
//            //如果目标文件所在的目录不存在，则创建父目录
//            newFile.getParentFile().mkdirs();
//        }
        //内存写入磁盘
        file.transferTo(newFile);
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("code",1);
//            String result = new JSONObject(map).toString();
//            System.out.println("bbbb");
//            return result;

        String fileUrl =  "../uploadImg/"+newFileName;
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        map.put("code",0);
        map.put("msg","上传成功");//提示消息
        map2.put("src",fileUrl);//图片url
        map2.put("title",newFileName);//图片名称，这个会显示在输入框里
        map.put("data",map2);
        String result = new JSONObject(map).toString();
        return result;
    }
}
