package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.StrUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createUser(TbUser user){
        TaotaoResult result = null;
        try {
            result = userService.createUser(user);
        }catch (Exception e){
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username,String password){
        TaotaoResult result = null;
        try{
            result = userService.userLogin(username,password);
        }catch (Exception e){
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable("token") String token, String callback){
        TaotaoResult result = null;
        try{
            result = userService.getUserByToken(token);
        }catch (Exception e){
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        //判断是否为jsonp调用
        if (null != callback){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else {
            return result;
        }
    }
}
