package member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Member {
    //DTO 는 data trasfer object.
    private String id;
    private String name;
    private String password;
    private Date regDate;

    public Member(String id, String name, String password, Date regDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.regDate = regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public boolean matchPassword(String pwd){
        return password.equals(pwd);
    }
}
