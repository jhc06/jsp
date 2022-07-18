package member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter@Setter
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

    private void checkEmpty(Map<String, Boolean> errors,
                            String value, String fieldName) {
        if (value == null || value.isEmpty())
            errors.put(fieldName, Boolean.TRUE);
    }

}
