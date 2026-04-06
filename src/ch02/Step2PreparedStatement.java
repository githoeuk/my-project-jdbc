package ch02;

import java.sql.*;

public class Step2PreparedStatement {


    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PASSWORD");

        int maxPrice = 50000; //5만원 이하 상품 검색 예정

        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {


            PreparedStatement pstmt = conn.prepareStatement("select * from product where price <= ?"); // ? <- 이 녀석에다가 값을 넣어야 햄
            pstmt.setInt(1, maxPrice);

            // 쿼리 실행
            // select - executeQuery() --> ResultSet
            // insert,update,delete - executeUpdate --> int
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    System.out.printf("%-20s %,d원 (재고: %d개)%n",
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("stock"));
                }

            }  // 2차원 표형태로 되어있음 // - close시켜줘야함

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    } // emd of main

}
