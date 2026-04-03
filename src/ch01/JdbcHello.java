package ch01;

import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHello {

    public static void main(String[] args) {
        // 연결 정보
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = "xxxxxx";
        String password = "xxxxx";
        // 실무에서는 비밀번호를 코드로 직접 쓰지않는다.
        // 환경 변수나 설정 파일을 이용한다. / (.env , application.properties, application.ymal)

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            System.out.println("연결 성공 됨");
            System.out.println("DB 제품명 : " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB 버전 : " + connection.getMetaData().getDatabaseProductVersion());

        } // 매개변수로 url주소 , userID, password
        catch (SQLException e) {
            System.out.println("db 연결 오류 발생");
            e.printStackTrace();
           // throw new RuntimeException(e);
        } // end of try/catch


    } // end of main
} // end of class
