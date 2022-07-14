package member.service;

import JDBC.ConnectionProvider;
import member.dao.MemerDao;
import member.dto.JoinRequest;
import member.dto.Member;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class JoinService implements Service{
    private MemerDao memberDao = new MemerDao();

    public void join(JoinRequest joinReq){
        Connection conn =null;
        try{
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false); // 자동 커밋모드 OFF

            Member member = memberDao.selectById(conn, joinReq.getId());
            if(member !=null){ // param으로 받은 ID값으로 쿼리한 결과가 있으면 가입할수 없다.
                JdbcUtil.rollback(conn);
                throw new DuplicateIdException();
            }

            memberDao.insert(conn,new Member(joinReq.getId(), joinReq.getName(), joinReq.getPassword(), new Date()));
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn);
        }
    }
}
