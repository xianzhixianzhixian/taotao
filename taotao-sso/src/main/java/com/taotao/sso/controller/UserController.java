package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.StrUtil;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理controller
 * @author: xianzhixianzhixian on 2018/10/28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable("param") String param, @PathVariable("type") Integer type, String callback){
        //需要支持jsonp
        TaotaoResult result = null;
        if (StrUtil.testTrimEmpty(param)){
            result = TaotaoResult.build(400, "校验内容不能为空");
        }
        if (type == null){
            result = TaotaoResult.build(400, "校验类型不能为空");
        }
        if (type != 1 && type!=2 && type!=3){
            result = TaotaoResult.build(400, "数据类型错误");
        }
        //校验出错
        if(null != result){
            if(null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else {
                return result;
            }
        }
        try {
            result = userService.checkData(param, type);
        }catch (Exception e){
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if(null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else {
            return result;
        }
    }
}
