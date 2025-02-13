package example;

import example.dao.BookDAO;
import example.mybatis.MyBatisSessionFactory;
import example.vo.BookVO;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        BookDAO dao = new BookDAO(factory);

//        HashMap<String, Object> map =
//                dao.selectByISBNHashMap("89-7914-063-0");
//
//        for (Object key: map.keySet()){
//            System.out.println(key + " : " + map.get(key));
//        }
//        BookVO bookVO = dao.selectByISBNBookVO("89-7914-063-0");
//        System.out.println(bookVO.getBtitle());

        List<BookVO> list = dao.selectByTitleBookVO("자바");
        for(BookVO b : list){
            System.out.println(b.getBtitle());
        }

    }
}
