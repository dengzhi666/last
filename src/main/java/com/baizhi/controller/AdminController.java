package com.baizhi.controller;

        import com.baizhi.entity.Admin;
        import com.baizhi.service.AdminService;
        import org.apache.shiro.SecurityUtils;
        import org.apache.shiro.authc.IncorrectCredentialsException;
        import org.apache.shiro.authc.UnknownAccountException;
        import org.apache.shiro.authc.UsernamePasswordToken;
        import org.apache.shiro.subject.Subject;
        import org.hibernate.validator.constraints.EAN;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpSession;
        import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @RequestMapping("/login")
    @ResponseBody
    public String login(Admin admin, Subject subject1){
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try{
            subject1.login(token);
            return "jsp/main.jsp";
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
            return "jsp/login.jsp";
        }catch (IncorrectCredentialsException i){
            System.out.println("密码错误");
            return "jsp/login.jsp";
        }

    }
//    public Map<String,Object> adminLogin(String clientCode, String username, String password , HttpSession session){
//         Map<String, Object> login = adminService.login(clientCode, username, password, session);
//        return login;
//    }
    @RequestMapping("/logout")
    public String adminLogout(HttpSession session){
          session.invalidate();
        return "forward:/jsp/login.jsp";
    }
}
