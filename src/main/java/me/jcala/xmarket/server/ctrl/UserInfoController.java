package me.jcala.xmarket.server.ctrl;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import me.jcala.xmarket.server.entity.dto.Result;
import me.jcala.xmarket.server.service.inter.StaticService;
import me.jcala.xmarket.server.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Api("跟用户信息有关的api")
@RestController
@RequestMapping("/users")
public class UserInfoController {

    private UserService userService;

    private StaticService staticService;


    @Autowired
    public UserInfoController(UserService userService, StaticService staticService) {
        this.userService = userService;
        this.staticService = staticService;
    }

    @ApiOperation(value = "用户登录",response = Result.class,produces = "application/json;charset=UTF-8",code = 200)
    @ApiResponses({
            @ApiResponse(code=200,message="登录成功"),
            @ApiResponse(code=401,message="用户名错误"),
            @ApiResponse(code=401,message="密码错误"),
            @ApiResponse(code=500,message="服务器异常"),
            @ApiResponse(code=400,message="请求参数不合法")
    })
    @PostMapping(value = "/login",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(String username,String password){
        return userService.login(username,password);
    }

    @ApiOperation(value = "用户注册",response = Result.class,produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/register",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> register(String username,String password,String phone){
     return userService.register(username,password,phone);
   }
    @ApiOperation(value = "设置用户学校信息",response = Result.class,produces = "application/json;charset=UTF-8")
    @PutMapping(value = "/{user_id}/update_school",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
   public ResponseEntity<?> updateUserSchool(@PathVariable("user_id") String id,String school){
       return userService.updateSchool(id,school);
   }
    @ApiOperation(value = "修改用户密码",response = Result.class,produces = "application/json;charset=UTF-8")
    @PutMapping(value = "/{user_id}/pass", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateUserPassword(@PathVariable("user_id")String id,String oldPass,String newPass)
            throws RuntimeException{
        return userService.updatePassword(id,oldPass,newPass);
    }
    @ApiOperation(value = "修改用户头像",response = Result.class,produces = "application/json;charset=UTF-8")
    @PutMapping(value = "/{user_id}/avatar",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateUserAvatar(@PathVariable("user_id")String id, HttpServletRequest request)
            throws Exception{
      return userService.updateAvatar(id,request);
    }
    @ApiOperation(value = "获取用户头像",response = byte[].class,produces = "application/json;charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code=404,message="没有找到该图片")
    })
    @GetMapping(value = "/pic/{dir}/{pic_name:.+}",produces = "image/jpeg;image/png;image/gif")
    public ResponseEntity<byte[]> gainUserAvatar(@PathVariable("dir")String dir,@PathVariable("pic_name") String picName)
            throws RuntimeException {
        return staticService.gainPic(dir,picName);
    }
    @ApiOperation(value = "获取学校名称列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/school_list",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getSchoolList() throws RuntimeException{
        return userService.gainSchoolList();
    }

}