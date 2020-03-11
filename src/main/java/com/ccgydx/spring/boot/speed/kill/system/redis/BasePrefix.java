package com.ccgydx.spring.boot.speed.kill.system.redis;

/**
 * @Description : redis 的key前缀，防止 key 冲突
 * @Author 刘龙
 * @Date 2020/3/11 14:25
 * @Version 1.0
 **/
public abstract class BasePrefix implements KeyPrefix {

    private String prefix;
    private int expireSeconds;

    public BasePrefix(int expireSeconds,String prefix) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }

    /**
     * 0 表示永不过期
     * @param prefix
     */
    public BasePrefix(String prefix){
        this(0,prefix);
    }

    /**
     * 过期时间
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    /**
     * 添加前缀
     * @return
     */
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
