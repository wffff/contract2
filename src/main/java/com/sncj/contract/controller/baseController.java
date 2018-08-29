package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.SecurityUserUtils;
import com.sncj.contract.service.IUploadService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Danny on 2018/8/21.
 */
@Controller
public class baseController {
    @Resource
    private IUploadService iUploadService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "home";
    }


    @RequestMapping("/main")
    public String home(@RequestParam(value = "page", required = false) String page, Model model, HttpServletRequest request, Principal principal,Integer contractDetailId, Integer contractEditId) {
        if (page != null) {
            model.addAttribute("main", page);
        } else {
            model.addAttribute("main", "main");
        }
        if (null!=contractDetailId){
            model.addAttribute("detailId",contractDetailId);
        }
        //if (null!=contractEditId){
         //   model.addAttribute("editId",contractEditId);
        //}
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
    private static final String PROJECT_PATH = "C:/testImg";
    /*
     * 上传图片文件夹
     */
    private static final String UPLOAD_PATH = "/uploadImg/";

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Map uploadImg(@RequestParam("upload") MultipartFile file,
                                HttpServletResponse response,
                                HttpServletRequest request)
            throws IllegalStateException, IOException {
//        if (uploadContentType.equals("image/pjpeg")
//                || uploadContentType.equals("image/jpeg")) {
//// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
//            expandedName = ".jpg";
//        } else if (uploadContentType.equals("image/png")
//                || uploadContentType.equals("image/x-png")) {
//// IE6上传的png图片的headimageContentType是"image/x-png"
//            expandedName = ".png";
//        } else if (uploadContentType.equals("image/gif")) {
//            expandedName = ".gif";
//        } else if (uploadContentType.equals("image/bmp")) {
//            expandedName = ".bmp";
//        } else {
//            out.println("<script type=\"text/javascript\">");
//            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
//                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
//            out.println("</script>");
//            return ;
//        }
//        if (file.getSize()> 600 * 1024) {
//            out.println("<script type=\"text/javascript\">");
//            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum
//                    + ",''," + "'文件大小不得大于600k');");
//            out.println("</script>");
//            return ;
//        }

//        DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
// 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名
        URL u = iUploadService.upload(file);
//        PrintWriter out = response.getWriter();
//        out.println("<script type=\"text/javascript\">");
//        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + u.getHost()+u.getPath() + "',''" + ")");
//        out.println("</script>");
//        out.flush();
//        out.close();

        Map m = new HashMap();
        m.put("uploaded", 1);

        m.put("fileName", u.getFile());

        m.put("url", "https://"+u.getHost() + u.getPath());

        return m;
//        return ;
    }

}
