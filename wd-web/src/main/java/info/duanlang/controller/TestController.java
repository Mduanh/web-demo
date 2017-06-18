package info.duanlang.controller;

import info.duanlang.TestService;
import info.duanlang.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Author: dh
 * Time: 2017/5/31
 * Description: test
 **/
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        TestService testService =new TestService();
        testService.sysout("duanlang");
        return "test";
    }
}
