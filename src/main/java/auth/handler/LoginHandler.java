package auth.handler;

import auth.dto.User;
import auth.service.LoginFailException;
import auth.service.LoginService;
import handler.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginHandler implements CommandHandler {

    private static final String FORM_VIEW = "/view/loginForm.jsp";
    private LoginService loginService = new LoginService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if(req.getMethod().equalsIgnoreCase("GET")){
            return processForm(req,res); //get방식 즉, login을 눌렀을 때는  view page로
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req,res); //login에 가서
        } else{
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        return null;
    }
    private String processForm(HttpServletRequest req, HttpServletResponse res){
        return FORM_VIEW;
    }
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = trim(req.getParameter("id"));
        String password = trim(req.getParameter("password"));

        Map<String, Boolean> errors = new HashMap(){};
        req.setAttribute("errors", errors);

        if(id == null || id.isEmpty())
            errors.put("id", Boolean.TRUE);
        if(password == null || password.isEmpty())
            errors.put("password", Boolean.TRUE);
        if(!errors.isEmpty()){
            return FORM_VIEW;
        }

        try{
            User user = loginService.login(id, password);
            req.getSession().setAttribute("authUser", user);
            res.sendRedirect(req.getContextPath()+"/index.jsp");
            return null;
        } catch (LoginFailException e) {
            errors.put("idOrPwNotMatch", Boolean.TRUE);
            return FORM_VIEW;
        }
    }
    //***** string.trim()
    // - 선행 또는 후행 공백이 없는 문자열을 리턴.
    private String trim(String str){
        return str == null ? null : str.trim();
    }
}
