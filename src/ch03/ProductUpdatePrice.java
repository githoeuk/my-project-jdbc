package ch03;

import java.sql.*;

public class ProductUpdatePrice {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        String sql = """
                update product2
             set price = ?
             where name = ?
             """;

        int newPrice = 5000000;
        String targetProductName = "애플 에어팟 프로";


        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newPrice);
            pstmt.setString(2, targetProductName);

            System.out.println("상품 가격 수정 ");
            int rows = pstmt.executeUpdate();

            if(rows > 0 ){
                System.out.println(rows + "개의 상품 가격이 수정되었습니다.");
            }else {
                System.out.println("수정할 상품을 찾지못하였습니다.");
            }


        } catch (SQLException e) {
            System.out.println("오류 :" + e.getMessage());
        } // end of try

    } // end of main
}
