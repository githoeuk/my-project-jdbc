package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductInsert {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/shop2?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        // insert 쿼리 구문을 미리 String값으로 만들어 두자
        // jdk 8버전

        // 테스트 블록 문법 : JDK 13버전 이후 사용가능
//        String sql = """
//                insert into product2(name,price,stock,description) values
//                ('테스트1', 1000, 5, '테스트 값');
//                """;
        String sql = """
                insert into product2(name,price,stock,description) values
                (?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "애플 에어팟 프로");
            pstmt.setInt(2, 329000);
            pstmt.setInt(3, 10);
            pstmt.setString(4, "최신 무선 이어폰");

            // 쿼리 실행
            //pstmt.executeQuery();
            int rows = pstmt.executeUpdate(); //?
            System.out.println(rows + "개의 상품이 추가되었습니다.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } // end of try

    } // end of main

} // end of class
