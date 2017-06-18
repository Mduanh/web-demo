package info.duanlang.user.controller;

import info.duanlang.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 * Author: dh
 * Time: 2017/5/31
 * Description:user
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CacheService cacheService;
    @RequestMapping("/loginPage")
    public String loginPage(){
        cacheService.set("name1","duanlang",30, TimeUnit.DAYS);
        return "loginPage";
    }
}
