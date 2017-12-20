package com.taotao.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传图片服务
 * @author 樊钰丰 2017/12/20
 */
@Service
public interface PictureService {

    public Map oploadPicture(MultipartFile uploadFile);

}
