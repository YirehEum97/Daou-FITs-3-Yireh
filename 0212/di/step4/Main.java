package di.step4;


public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        UserVO userVO = new UserVO("HGD", "홍길동", "1234");

        userDAO.insert(userVO);
        System.out.println("New Account Created");

        userVO = userDAO.select("HGD");
        System.out.println("검색된 이름 : " + userVO.getName());
    }
}
