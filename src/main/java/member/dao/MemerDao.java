package member.dao;

import member.dto.Member;
import util.JdbcUtil;

import java.sql.*;

public class MemerDao {
    public Member selectById(Connection conn, String id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement(
                    "select * from MEMBER where ID = ?"
            );
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            Member member = null;
            if(rs.next()){
                member = new Member(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        toDate(rs.getTimestamp("regdate")));
            }
            return member;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }
    private Date toDate(Timestamp date){
        return date == null ? null : new Date(date.getTime());
    }


    public void insert(Connection conn,Member member) throws SQLException {
        try(PreparedStatement pstmt = conn.prepareStatement("insert into member values(?,?,?,?)")){
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getPassword());
            pstmt.setTimestamp(4, new Timestamp((member.getRegDate().getTime())));
            pstmt.executeUpdate();
        }
    }
}

