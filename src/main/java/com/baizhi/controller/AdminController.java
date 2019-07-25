package com.baizhi.controller;

        import com.baizhi.entity.Admin;
        import com.baizhi.service.AdminService;
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
    public Map<String,Object> adminLogin(String clientCode, String username, String password , HttpSession session){
         Map<String, Object> login = adminService.login(clientCode, username, password, session);
        return login;
    }

    @RequestMapping("/logout")
    public String adminLogout(HttpSession session){
          session.invalidate();
        return "forward:/jsp/login.jsp";
    }
}
