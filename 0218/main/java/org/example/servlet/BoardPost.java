package org.example.servlet;


import DAO.BoardPostDAO;
import VO.BoardPostVO;
import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/submitBoard")
public class BoardPost extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory;
    private BoardPostDAO boardDAO;

    @Override
    public void init() throws ServletException {
        sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
        boardDAO = new BoardPostDAO(sqlSessionFactory);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        HttpSession session = request.getSession(false);
        String author = "익명";
        if (session != null && session.getAttribute("member") != null) {
            author = ((VO.MemberVO) session.getAttribute("member")).getId();
        }

        Timestamp regDate = new Timestamp(System.currentTimeMillis());

        BoardPostVO post = new BoardPostVO();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setRegDate(regDate);

        boardDAO.insertBoardPost(post);

        response.sendRedirect(request.getContextPath() + "/bulletin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        List<BoardPostVO> posts = boardDAO.selectAllPosts();
        for (BoardPostVO post : posts) {
            System.out.println(post);
        }
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/WEB-INF/jsp/bulletin.jsp").forward(request, response);
    }
}
