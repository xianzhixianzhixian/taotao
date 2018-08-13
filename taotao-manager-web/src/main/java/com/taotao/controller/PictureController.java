package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传的controller
 * @author 樊钰丰 2017/12/20
 */
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(@Autowired MultipartFile uploadFile){
        Map result=pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把result转换为JSON格式的字符串
        String json=JsonUtils.objectToJson(result);
        return json;
    }
}
