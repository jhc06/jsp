package member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Getter
public class JoinRequest {
    private String id;
    private String name;
    private String password;
    private String confirmPassword;

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isPasswordEqualToConfirm(){
        return password != null && password.equals(confirmPassword);
        // return boolean value;
    }

    public void validate(Map<String, Boolean> errors) {
        checkEmpty(errors, id, "id");
        checkEmpty(errors, name, "name");
        checkEmpty(errors, password, "password");
        checkEmpty(errors, confirmPassword, "confirmPassword");
        if (!errors.containsKey("confirmPassword")) {
            if (!isPasswordEqualToConfirm()) {
                errors.put("notMatch", Boolean.TRUE);
            }
        }
    }
    public void setParam(HttpServletRequest req){
        this.id=req.getParameter("id");
        this.name=req.getParameter("name");
        this.password=req.getParameter("password");
        this.confirmPassword=req.getParameter("confirmPassword");
    }

    private void checkEmpty(Map<String, Boolean> errors,
                            String value, String fieldName) {
        if (value == null || value.isEmpty())
            errors.put(fieldName, Boolean.TRUE);
    }

}
