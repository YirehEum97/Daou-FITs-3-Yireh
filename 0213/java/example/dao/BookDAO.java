package example.dao;

import example.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BookDAO {

    private SqlSessionFactory sqlSessionFactory;

    public BookDAO(){}

    public BookDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void deleteBookVO(BookVO book){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("example.MyBook.deleteBookVO", book);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public void insertBookVO(BookVO book){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert("example.MyBook.insertBookVO", book);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    public ObservableList<BookVO> selectByTitleBookVO(String btitle){
        ObservableList<BookVO> list = null;
        List tlist = null;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            tlist = sqlSession.selectList(
                    "example.MyBook.selectByTitleBookVO",
                    btitle);
            list = FXCollections.observableArrayList(tlist);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }
}
