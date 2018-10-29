package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: xianzhixianzhixian on 2018/10/28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${RESID_USER_SESSION}")
    private String RESID_USER_SESSION;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public TaotaoResult checkData(String content, Integer type) {
        //创建查询条件
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //对数据进行校验，1、2、3分别代表username、phone、email
        if( 1 == type){
            criteria.andUsernameEqualTo(content);
        }else if( 2 == type ){
            criteria.andPhoneEqualTo(content);
        }else if( 3 == type ){
            criteria.andEmailEqualTo(content);
        }
        List<TbUser> userList = tbUserMapper.selectByExample(example);
        if(userList==null || userList.size()==0){
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        Date date = new Date();
        user.setUpdated(date);
        user.setCreated(date);
        //md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        tbUserMapper.insert(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult userLogin(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (StrUtil.listNull(list)) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        TbUser user = list.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        //生成token，使用UUID
        String token = UUID.randomUUID().toString();
        user.setPassword(null); //清除密码
        //将用户信息写入redis，RESID_USER_SESSION:token
        try {
            jedisClient.set(RESID_USER_SESSION+":"+token, JsonUtils.objectToJson(user));
            jedisClient.expire(RESID_USER_SESSION+":"+token, SSO_SESSION_EXPIRE);
        }catch (Exception e){
            jedisClient.del(RESID_USER_SESSION+":"+token);
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        //返回token
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jedisClient.get(token);
        if (StrUtil.testTrimEmpty(json)){
            return TaotaoResult.build(400, "本次登录已过期，请重新登录");
        }
        //更新过期时间
        jedisClient.expire(RESID_USER_SESSION+":"+token, SSO_SESSION_EXPIRE);
        //返回用户信息
        return TaotaoResult.ok(json);
    }
}
