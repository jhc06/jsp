package member.handler;

import handler.CommandHandler;
import member.dto.JoinRequest;
import member.service.DuplicateIdException;
import member.service.JoinService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JoinHandler implements CommandHandler {
    public static final String FORM_VIEW = "/view/joinForm.jsp";
    private JoinService joinService = new JoinService();

    // getMethod()
    //  - Returns the name of the HTTP method with which this request was made, for example, GET, POST, or PUT.
    // setStatus(int statusCode)
    //  - Sets the status code for this response.
    // Status code (405) indicating that the method specified in the Request-Line
    // is not allowed for the resource identified by the Request-URI.
    // specified 명시된, identified 식별된
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if(req.getMethod().equals("GET")){
            return processForm(req,res);
        } else if (req.getMethod().equals("POST")) {
            return processSubmit(req,res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }
    private String processForm(HttpServletRequest req,HttpServletResponse res){
        return FORM_VIEW;
    }

    // get.Parameter(String ParameterName)
    //  - Returns the value of a request parameter as a String, or null if the parameter does not exist.
    // cf) getParameterValues(String ParameterName)
    // set.Attribute(String attributeName, Object objectToBeStored)
    // - Stores an attribute in this request. Attributes are reset between requests.
    // - If the object passed in is null, the effect is the same as calling removeAttribute(java.lang.String).
    // isEmpty()
    // - Returns true if this map contains no key-value mappings.
    private String processSubmit(HttpServletRequest req, HttpServletResponse res){
        JoinRequest joinReq = new JoinRequest();
        joinReq.setId(req.getParameter("id"));
        joinReq.setName(req.getParameter("name"));
        joinReq.setPassword(req.getParameter("password"));
        joinReq.setConfirmPassword(req.getParameter("confirmPassword"));

        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors); // JSP코드에서 에러종류별 메세지 작성용.

        joinReq.validate(errors);

        if(!errors.isEmpty()){ // errors에 에러객체가 있다면 Joinform으로 되돌린다.
            return FORM_VIEW;
        }
        try{
            joinService.join(joinReq);
            return "/web/view/joinSuccess.jsp";
        } catch (DuplicateIdException e){
            errors.put("dulplicateId", Boolean.TRUE);
            return FORM_VIEW;
        }
    }
}
