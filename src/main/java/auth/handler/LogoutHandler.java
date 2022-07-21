package auth.handler;

import handler.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false);
        if(session != null){ // session이 존재하면 세션을 종료함.
            session.invalidate();
        }
        res.sendRedirect(req.getContextPath()+"/index.jsp"); // 세션 종료 후 index.page로 이동.
        return null;
    }
}
