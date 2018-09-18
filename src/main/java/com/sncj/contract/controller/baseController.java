package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.SecurityUserUtils;
import com.sncj.contract.entity.RoleEntity;
import com.sncj.contract.entity.UserEntity;
import com.sncj.contract.service.IUploadService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public String home(@RequestParam(value = "page", required = false) String page, Model model, HttpServletRequest request, Principal principal, Integer contractDetailId, Integer contractEditId) {
        if (page != null) {
            model.addAttribute("main", page);
        } else {
            model.addAttribute("main", "main");
        }
        if (null != contractDetailId) {
            model.addAttribute("detailId", contractDetailId);
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
    private static final String PROJECT_PATH = "C:/testImg";
    /*
     * 上传图片文件夹
     */
    private static final String UPLOAD_PATH = "/uploadImg/";

    @RequestMapping("/uploadImg")//用于ckeditor中上传图片？
    @ResponseBody
    public Map uploadImg(@RequestParam("upload") MultipartFile file,
                         HttpServletResponse response,
                         HttpServletRequest request)
            throws IllegalStateException, IOException {
        Map m = iUploadService.uploadImg(file);
        return m;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile file,
                      HttpServletResponse response,
                      HttpServletRequest request)
            throws IllegalStateException, IOException {
        Map m = iUploadService.uploadImg(file);
        return m;
    }

    @RequestMapping("file/download/{fileName}")
    public void download(HttpServletResponse response, @PathVariable(name = "fileName") String fileName) throws FileNotFoundException {
        File file = new File("F:\\upload\\" + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        // 设置被下载而不是被打开
        response.setContentType("application/gorce-download");
        // 设置被第三方工具打开,设置下载的文件名
        response.addHeader("Content-disposition", "attachment;fileName=" + fileName);
        try {
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
