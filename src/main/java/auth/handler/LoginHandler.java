package auth.handler;

import auth.service.LoginService;
import handler.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandler implements CommandHandler {

    private static final String FORM_VIEW = "/view/loginForm.jsp";
    private LoginService loginService = new LoginService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        return null;
    }
}
