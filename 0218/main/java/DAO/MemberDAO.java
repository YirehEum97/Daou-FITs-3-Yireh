package DAO;

import VO.MemberVO;
import javafx.collections.FXCollections;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;

public class MemberDAO {
    private SqlSessionFactory sqlSessionFactory;

    public MemberDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public MemberVO logIn(String id, String pw){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberVO memberVO = null;

        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("id", id);
            param.put("pw", pw);

            String name = sqlSession.selectOne("Member.tryLogIn", param);
            memberVO = new MemberVO(id,pw,name);


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return memberVO;
    }

}
