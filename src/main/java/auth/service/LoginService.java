package auth.service;

import JDBC.ConnectionProvider;
import auth.dto.User;
import member.dao.MemberDao;
import member.dto.Member;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginService {

    private MemberDao memberDao = new MemberDao();

    // try-with-resource문 예외발생여부와 상관없이 사용했던 리소스 객체의 close()메소드를 호출해서 안전하게 리소스를 닫아준다.
    // try 블록이 정상적으로 실행을 완료했거나 도중에 예외가 발생하게 되면 자동으로 close()메소드 호출.
    // < Java 1권 p.438 >
    public User login(String id, String pw){
        try(Connection conn = ConnectionProvider.getConnection()){
            Member member = memberDao.selectById(conn, id);
            if(member==null){
                throw new LoginFailException();
            }
            if(!member.matchPassword(pw)){
                throw new LoginFailException();
            }
            return new User(member.getId(), member.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
