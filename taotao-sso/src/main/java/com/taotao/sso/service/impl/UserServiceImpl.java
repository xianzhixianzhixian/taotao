package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xianzhixianzhixian on 2018/10/28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

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
}
