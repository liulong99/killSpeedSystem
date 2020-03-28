package com.ccgydx.spring.boot.speed.kill.system.domain.povo;

import com.ccgydx.spring.boot.speed.kill.system.valicator.IsMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description :登录实体类
 * @Author 刘龙
 * @Date 2020/3/16 17:39
 * @Version 1.0
 **/
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    @ApiModelProperty(value = "用户账号")
    private String mobile;

    @NotNull
    @Length(min = 20,max = 40)
    @ApiModelProperty(value = "用户密码")
    private String password;

}
