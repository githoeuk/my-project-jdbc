package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDelete {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop2?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        String sql = """
                 delete from product2 where id = ?
                """;

        int targetId = 2;

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,targetId);

            System.out.println("상품 가격 수정 ");
            int rows = pstmt.executeUpdate();

            System.out.println("== 상품 각격 수정 : 타겟 상품 ID : " + targetId);
            if (rows > 0) {
                System.out.println(rows + "개의 상품 가격이 삭제되었습니다.");
            } else {
                System.out.println("삭제할 상품을 찾지못하였습니다.");
            }


        } catch (SQLException e) {
            System.out.println("오류 :" + e.getMessage());
        } // end of try

    } // end of main
}
