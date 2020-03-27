package com.ccgydx.spring.boot.speed.kill.system;

import com.ccgydx.spring.boot.speed.kill.system.util.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootSpeedKillSystemApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    public void TestMd5(){
       System.out.println(Md5Util.inputPassToFormPass("123456")); //80fb3d54c5f0778777b64fcc11552faa
//        System.out.println(Md5Util.formPassToDbPass(Md5Util.inputPassToFormPass("123456"),"1a2b3c4d"));//1d6d87a18832748f2c32780cd1958ef3
        System.out.println(Md5Util.inputPassToDbPass("123456", "1a2b3c4d"));//88e904f9891adbd082e63292850dad52
    }

}
