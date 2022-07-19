package member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@AllArgsConstructor
public class Member {
    //DTO 는 data trasfer object.
    private String id;
    private String name;
    private String password;
    private Date regDate;


    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public boolean matchPassword(String pwd){
        return password.equals(pwd);
    }
}
