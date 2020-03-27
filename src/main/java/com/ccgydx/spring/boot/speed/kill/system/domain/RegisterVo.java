package com.ccgydx.spring.boot.speed.kill.system.domain;

import com.ccgydx.spring.boot.speed.kill.system.valicator.IsMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description :注册实体类
 * @Author 刘龙
 * @Date 2020/3/22 22:34
 * @Version 1.0
 **/
@Data
public class RegisterVo {
    @NotNull
    @IsMobile
    @ApiModelProperty(value = "用户账号")
    private String mobile;

    @NotNull
    @ApiModelProperty(value = "用户密码")
    private String password;

    @NotNull
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
}
