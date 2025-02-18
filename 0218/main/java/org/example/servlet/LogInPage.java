package org.example.servlet;

import DAO.MemberDAO;
import VO.MemberVO;
import mybatis.MyBatisSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LogInPage extends HttpServlet {

    private SqlSessionFactory sqlSessionFactory;
    private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        try {
            sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
            memberDAO = new MemberDAO(sqlSessionFactory);
        } catch (Exception e) {
            throw new ServletException("MyBatis 초기화 실패", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // DAO를 통해 로그인 처리
        MemberVO member = memberDAO.logIn(id, pw);

        if (member != null && member.getName() != null) {
            // 로그인 성공: 결과를 request에 담고 JSP로 포워딩
            request.setAttribute("member", member);
            request.getRequestDispatcher("/WEB-INF/jsp/bulletin.jsp").forward(request, response);
        } else {
            // 로그인 실패: 오류 메시지 처리 등
            request.setAttribute("error", "로그인 실패");
            request.getRequestDispatcher("/WEB-INF/jsp/loginfail.jsp").forward(request, response);
        }
    }
}
