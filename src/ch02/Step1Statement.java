package ch02;

import java.sql.*;

public class Step1Statement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=Asia/Seoul";
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // 1. Connection 객체 필요 - 생성 - 세션 생성(논리적으로 연결된 상태를 의미함)
            // 2. Statement 객체 필요 - 문자열을 쿼리 객체로 변경 해줌
            // 3. ResultSet 객체 필요 - 쿼리가 실행되면 결과집합을 담고 있는 녀석
            Statement stmt = connection.createStatement();
            //stmt.executeQuery("SELECT * FROM product"); //괄호안에 쿼리문 작성
            ResultSet rs =  stmt.executeQuery("SELECT * FROM product"); // Set자료구조로 쿼리에서 받은걸 담음
            System.out.println("======= 상품 목록 출력 =======");
            // rs.next() -> 다음 행이 존재하는가? --> true/false로 표현
            while(rs.next()){
                int id = rs.getInt("id");
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                //String price = rs.getString("price");
                int stock = rs.getInt("stock");
                String description = rs.getString("description");
                // String created_at = rs.getString("created_at");
                Timestamp created_at = rs.getTimestamp("created_at");

                System.out.println("상품 ID :" + id + " | " + "카테고리ID :" + category_id    + " | " + "상품명 : " + name
                                    + " | " + "가격 : " + price + " | " + "재고 : " + stock + " | "
                                    + "\n" + "상세설명 : " + description + " | " + "생성 일자 : " + created_at);
            } // end of while


        } catch (SQLException e) {
            System.out.println("오류 발생 : " + e.getMessage());
            System.out.println("sqlstate : " + e.getSQLState());
            // throw new RuntimeException(e);
        } // end of try/catch

    } // end of main
}
