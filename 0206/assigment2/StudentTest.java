package assigment2;

public class StudentTest {

    public static void main(String[] args) {
        Student studentArray [] = new Student[3];
        studentArray[0] = new Student("홍길동", 15, 171, 81, "201101", "영문");
        studentArray[1] = new Student("한사람", 13, 183, 72, "201102", "건축");
        studentArray[2] = new Student("임걱정", 16, 175, 65, "201103", "무영");

        for (Student student : studentArray) {
            System.out.println(student.printInformation());
        }
    }
}
