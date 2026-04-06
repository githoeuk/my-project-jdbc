package ch03;

import java.sql.*;

public class ProductSelectByPrice {

    public static void main(String[] args) {

        // 상품이 만원에서 십만원 사이에 상품만을 출력하시오
        // shopDB 사용 , product 테이블 사용
        // 가격 오름차순 정렬

        int minPrice = 10000;
        int maxPrice = 100000;

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        String sql = """
                SELECT * FROM product where price between ? and ?	
                order by price asc
                """;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, minPrice);
            pstmt.setInt(2, maxPrice);
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
