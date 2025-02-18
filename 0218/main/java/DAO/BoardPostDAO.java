package DAO;

import VO.BoardPostVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BoardPostDAO {
    private SqlSessionFactory sqlSessionFactory;

    public BoardPostDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 게시글 등록 메서드
    public void insertBoardPost(BoardPostVO post) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("Board.insertPost", post);
            session.commit();
        } catch(Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    public List<BoardPostVO> selectAllPosts() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List<BoardPostVO> posts = session.selectList("Board.selectAllPosts");
            for (BoardPostVO post : posts) {
                System.out.println(post);
            }
            return posts;
        } catch (Exception e) {
            System.out.println("No posts found");
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}