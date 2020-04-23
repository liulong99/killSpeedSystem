package com.ccgydx.spring.boot.speed.kill.system.service.Impl;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.LoginVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.povo.RegisterVo;
import com.ccgydx.spring.boot.speed.kill.system.exception.GlobalException;
import com.ccgydx.spring.boot.speed.kill.system.redis.MiaoshaUserKey;
import com.ccgydx.spring.boot.speed.kill.system.redis.RedisService;
import com.ccgydx.spring.boot.speed.kill.system.util.CodeMsg;
import com.ccgydx.spring.boot.speed.kill.system.util.Md5Util;
import com.ccgydx.spring.boot.speed.kill.system.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.ccgydx.spring.boot.speed.kill.system.mapper.MiaoshaUserMapper;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService{

    public static final String  COOKIE_NAME_TOKEN="token";

    @Resource
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 根据id 查询判断用户是否存在
     * @param id
     * @return
     */
    @Override
    public MiaoshaUser getById(Long id) {
        //取缓存
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if(miaoshaUser!=null){
            return miaoshaUser;
        }

        //取数据库
        Example example=new Example(MiaoshaUser.class);
        example.createCriteria().andEqualTo("id",id);
        miaoshaUser = miaoshaUserMapper.selectOneByExample(example);
        if(miaoshaUser!=null){
            redisService.set(MiaoshaUserKey.getById, "" + id, miaoshaUser);
        }
        return miaoshaUser;
    }

    /**
     * 修改密码
     * @param token
     * @param id
     * @param formPass
     * @return
     */
    @Override
    public boolean updatePassword(String token,long id, String formPass) {
        //取user
        MiaoshaUser miaoshaUser=getById(id);
        if(miaoshaUser==null){
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        //更新数据库
        miaoshaUser.setPassword(Md5Util.formPassToDbPass(formPass,miaoshaUser.getSalt()));
        Example example=new Example(MiaoshaUser.class);
        example.createCriteria().andEqualTo("id",miaoshaUser.getId());
        miaoshaUserMapper.updateByExample(miaoshaUser,example);

        //处理缓存
        redisService.delete(MiaoshaUserKey.getById,""+id);
        redisService.set(MiaoshaUserKey.token,token,miaoshaUser);

        return true;
    }

    /**
     * 登录
     * @param loginVo
     * @return
     */
    @Override
    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if(loginVo==null){
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser miaoshaUser = getById(Long.parseLong(mobile));
        if(miaoshaUser==null){
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        String password=miaoshaUser.getPassword();
        String salt=miaoshaUser.getSalt();
        String calcPass= Md5Util.formPassToDbPass(formPass,salt);
        if(!password.equals(calcPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token=UUIDUtils.uuid();
        //将cookie放入到Response中
        addCookie(response,miaoshaUser,token);
        return true;
    }

    /**
     * 根据token的值从redis中取value
     * @param token
     * @return
     */
    @Override
    public MiaoshaUser getUserByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if(miaoshaUser!=null){
            addCookie(response,miaoshaUser,token);
        }
        return miaoshaUser;
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @Override
    public boolean register(RegisterVo registerVo) {
        MiaoshaUser miaoshaUser=new MiaoshaUser();
        Long id=Long.parseLong(registerVo.getMobile());
        miaoshaUser.setId(id);
        String password=Md5Util.formPassToDbPass(registerVo.getPassword(),Md5Util.salt);
        miaoshaUser.setPassword(password);
        miaoshaUser.setNickname(registerVo.getNickname());
        miaoshaUser.setSalt(Md5Util.salt);
        miaoshaUser.setLoginCount(1);
        miaoshaUser.setRegisterDate(new Date());
        miaoshaUser.setLastLoginDate(new Date());
        int insert = miaoshaUserMapper.insert(miaoshaUser);
        return insert>0;
    }

    /**
     * 将cookie放入到Response中
     * @param response
     * @param miaoshaUser
     */
    private void addCookie(HttpServletResponse response,MiaoshaUser miaoshaUser,String token){
        redisService.set(MiaoshaUserKey.token,token,miaoshaUser);
        //将token放入到cookie中，可以通过Response获取
        Cookie cookie=new Cookie(COOKIE_NAME_TOKEN,token);
        //设置超时时间
        cookie.setMaxAge(MiaoshaUserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        //将cookie放入到Response中
        response.addCookie(cookie);
    }
}
