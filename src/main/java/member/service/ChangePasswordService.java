package member.service;

import JDBC.ConnectionProvider;
import member.dao.MemberDao;
import member.dto.Member;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class ChangePasswordService {
    private MemberDao memberDao = new MemberDao();

    public void changePassword(String userId, String curPwd, String newPwd){
        Connection conn = null;
        try{
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            Member member = memberDao.selectById(conn, userId);
            if(member == null){
                throw new MemberNotException();
            }
            if(!member.matchPassword(curPwd)){
                throw new InvalidPasswordException();
            }
            member.changePassword(newPwd);
            memberDao.update(conn, member);
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}
