package a_010_java_after2;

import java.sql.*;
import java.util.Date;

class Product_BuyTotal{
    public int     tot_ord_no;					//주문번호
    public int     tot_ord_price;				//금액
    public int     tot_buying_method;			//지급방법 카드1 현금2
    public int     tot_in_money;				//받은 돈
    public int     tot_out_money;				//거스름 돈
    public Date    tot_system_date;				//system_date

    public int     cnt;						//순서
    public String  method;

    void printScore() {
        System.out.printf("%3d   %5d    %6d    %1d   %7d     %2d \n",
                cnt,tot_ord_no,tot_ord_price,tot_buying_method,tot_in_money,tot_out_money);
    }
}
public class Kiosk_Product_BuyTotal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub	
        
        int num_count =0;
        do {

        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql;
       
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "system";
        String pw = "1234";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("클래스 로딩 성공");
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 접속");
            sql="select count(*) num from tbl_order_total";
           
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            num_count = rs.getInt("num");
            System.out.println("등록된코드:"+num_count+"건");
           
            System.out.println("================================================");
            System.out.println(" NO   주문번호      금액  지급방법  받은 돈  거스름 돈");
            System.out.println("================================================");
           
            sql= "select * from tbl_order_total order by tot_ord_no ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
           
            num_count = 0;
            int total=0;
            Product_BuyTotal p =new Product_BuyTotal();
            while(rs.next()) {
                p.cnt = num_count+1;
                num_count++;
                p.tot_ord_no = rs.getInt("tot_ord_no");
                p.tot_ord_price = rs.getInt("tot_ord_price");
                p.tot_buying_method = rs.getInt("tot_buying_method");
                if(rs.getInt("tot_buying_method")==1) {
                    p.method="카드";
                } else if(rs.getInt("tot_buying_method")==2) {
                    p.method="현금";
                }
                p.tot_in_money = rs.getInt("tot_in_money");
                p.tot_out_money = rs.getInt("tot_out_money");
               
                p.printScore();
                
                total = total + p.tot_ord_price;
            }     
            System.out.println("================================================");
            System.out.println("***매출합계: " + total);
    }catch(Exception e) {
        e.printStackTrace();
    }    
        break;
        }while(true);
   }
}