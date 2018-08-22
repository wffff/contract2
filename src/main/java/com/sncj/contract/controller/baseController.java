package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.SecurityUserUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Danny on 2018/8/21.
 */
@Controller
public class baseController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "home";
    }


    @RequestMapping("/main")
    public String home(@RequestParam(value = "page", required = false) String page, Model model, HttpServletRequest request, Principal principal) {
        if (page != null) {
            model.addAttribute("main", page);
        } else {
            model.addAttribute("main", "main");
        }
        model.addAttribute("username", SecurityUserUtils.getSecurityUser().getFullname());
        return "index";
    }

    @RequestMapping("/principal")
    @ResponseBody
    public Principal principal(Principal principal) {
        return principal;
    }

    @RequestMapping("/admin")
    @ResponseBody
    @Secured(value = "ROLE_ADMIN")
    public String admin(Principal principal) {
        return principal.getName();
    }

    @RequestMapping("/user")
    @ResponseBody
    @Secured(value = "ROLE_USER")
    public String user(Principal principal) {
        return principal.getName();
    }

    private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMddHHmmss";

    /*
     * 项目绝对路径
     */
    private static  final String PROJECT_PATH="C:/testImg";
    /*
     * 上传图片文件夹
     */
    private static final String UPLOAD_PATH="/uploadImg/";

    @RequestMapping("/uploadImg")
    public void uploadImg(@RequestParam("upload") MultipartFile file,
                          HttpServletResponse response,
                          String CKEditorFuncNum)
            throws IllegalStateException, IOException {

        PrintWriter out =response.getWriter();
        String fileName=file.getOriginalFilename();
        String uploadContentType =file.getContentType();
        String expandedName ="";
        if (uploadContentType.equals("image/pjpeg")
                || uploadContentType.equals("image/jpeg")) {
// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
            expandedName = ".jpg";
        } else if (uploadContentType.equals("image/png")
                || uploadContentType.equals("image/x-png")) {
// IE6上传的png图片的headimageContentType是"image/x-png"
            expandedName = ".png";
        } else if (uploadContentType.equals("image/gif")) {
            expandedName = ".gif";
        } else if (uploadContentType.equals("image/bmp")) {
            expandedName = ".bmp";
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
            out.println("</script>");
            return ;
        }
        if (file.getSize()> 600 * 1024) {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
                    + ",''," + "'文件大小不得大于600k');");
            out.println("</script>");
            return ;
        }
        DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
        fileName = df.format(new Date())+expandedName;
        file.transferTo(new File(PROJECT_PATH+UPLOAD_PATH +fileName));
// 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + 0
                + ",'" + "uploadImg/" + fileName + "','')");
        out.println("</script>");
        return ;
    }

}
