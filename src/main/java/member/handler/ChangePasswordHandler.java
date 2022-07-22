package member.handler;

import auth.dto.User;
import handler.CommandHandler;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordHandler implements CommandHandler {
    private static final String FORM_VIEW = "/view/changePwdForm.jsp";
    private ChangePasswordService changePasswordService = new ChangePasswordService();
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if(req.getMethod().equalsIgnoreCase("GET")){
            return processForm(req,res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req,res);
        } else {
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res){
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User user = (User)req.getSession().getAttribute("authUser");

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);

        String curPwd = req.getParameter("curPwd");
        String newPwd = req.getParameter("newPwd");

        if(curPwd==null || curPwd.isEmpty()){
            errors.put("curPwd", Boolean.TRUE);
        }
        if(newPwd==null|| newPwd.isEmpty()){
            errors.put("newPwd", Boolean.TRUE);
        }
        if(!errors.isEmpty()){
            return FORM_VIEW;
        }
        // 성공 / 실패 / 불가능.
        try{
            changePasswordService.changePassword(user.getId(),curPwd,newPwd);
            return "/view/changePwdSuccess.jsp";
        } catch (InvalidPasswordException e){
            errors.put("badCurPwd", Boolean.TRUE);
            return FORM_VIEW;
        } catch (MemberNotException e){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
