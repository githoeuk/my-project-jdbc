package ch03;

import java.sql.*;

public class ProductSelectAll {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        String sql = """
                SELECT * FROM product2
                """;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            System.out.println("== 전체 상품 목록 조회 ==");
            while (rs.next()) {
                String output = """
                        ID : %3d | %-20s | %,7d원 | 재고 : %3d개
                        """.formatted(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt(("price")),
                        rs.getInt("stock")
                );
                // 참고로 텍스트 블록 문법은 자동으로 \n 기능이 들어가 있다.
                System.out.print(output);
            }

        } catch (SQLException e) {
            System.out.println("오류 :" + e.getMessage());
        } // end of try

    } // end of main
}
