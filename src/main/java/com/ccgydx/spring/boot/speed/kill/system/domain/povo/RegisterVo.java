package com.ccgydx.spring.boot.speed.kill.system.domain.povo;

import com.ccgydx.spring.boot.speed.kill.system.valicator.IsMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description :注册实体类
 * @Author 刘龙
 * @Date 2020/3/22 22:34
 * @Version 1.0
 **/
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = -7250299926975997852L;

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
