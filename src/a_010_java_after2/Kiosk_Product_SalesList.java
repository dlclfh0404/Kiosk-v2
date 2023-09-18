package a_010_java_after2;

import java.sql.*;
import java.util.*;

class Product_SakesList{
    public String     tot_system_date;          //판매일자
    public int        ord_buying_count;         //수량
    public int        ord_price;                //금액
    public int        pdt_id;                   //상품코드
    public String     pdt_id_name;              //상품명
    
    public int     cnt;                         //매출 조회 순번
    
    void printScore() {
        System.out.printf("%3d   %6s    %2d     %6d    %5d  %2s\n",
                cnt,  tot_system_date, ord_buying_count, ord_price, pdt_id, pdt_id_name);
    }
}
public class Kiosk_Product_SalesList {

    public static void main(String[] args) {
        Scanner kpbl=new Scanner(System.in);
        int List=0;        // 번호를 입력 받을 변수
        
        int num_count =0;  // 등록된 코드 건수, 순번을 출력할 때 사용
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
	            
	            // 매출조회 하기 전에 코드에 등록된 건수 조회
	            sql="select count(*) num from tbl_order_total";
	           
	            pstmt = conn.prepareStatement(sql);
	            ResultSet rs = pstmt.executeQuery();
	            rs.next();
	            num_count = rs.getInt("num");
	            System.out.println("등록된코드:"+num_count+"건");
	           
	            System.out.println("상품코드를 입력하세요. 상품 리스트를 출력합니다. 전체:1 종료:9");
	            List=kpbl.nextInt();
	            
	            // 1: 전체 매뉴 조회  9: Main_menu
	            // 그 외 코드번호를 입력했을때 : 상품코드를 입력했을때 해당 번호의 매출을 조회
	            if(List==1) {
	                sql = "select to_char(a.tot_system_date, 'yyyy-mm-dd') tot_system_date, b.ord_buying_count, b.ord_price, "
	                	+ "       c.pdt_id, c.pdt_id_name "
	                	+ "	 from tbl_order_total a, tbl_order_list b, tbl_product_master c "
	                	+ " 	  where a.tot_ord_no = b.ord_no and b.ord_pdt_id = c.pdt_id "
	                	+ " 	  order by c.pdt_id, a.tot_system_date asc";
	            }else if(List==9) { 
	                Kiosk_MainMenu.main(args);
	                break;
	            }else if(List!=1||List!=9){
	                sql= "select to_char(a.tot_system_date, 'yyyy-mm-dd') tot_system_date, b.ord_buying_count, b.ord_price, "
	                    + "      c.pdt_id, c.pdt_id_name "
	                    + "	from tbl_order_total a, tbl_order_list b, tbl_product_master c "
	                    + " 	 where a.tot_ord_no = b.ord_no and b.ord_pdt_id = c.pdt_id and c.pdt_id = "+List
	                    + " 	 order by c.pdt_id, a.tot_system_date asc";
	            }
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            System.out.println("======================================================");
	            System.out.println(" 순번    판매일자       수량       금액      상품코드/상품명");
	            System.out.println("======================================================");
	
	            num_count = 0;      // 초기화로 순번을 증가할떄 이용                                
	            int total=0;        // 총 매출 총액
	            int buying_count=0; // 총 매출 합계
	            Product_SakesList p = new Product_SakesList(); // 구조체
	            while(rs.next()) {
	            	p.cnt = num_count+1;
	                num_count++;
	                p.tot_system_date = rs.getString("tot_system_date");
	                p.ord_buying_count = rs.getInt("ord_buying_count");
	                p.ord_price = rs.getInt("ord_price");
	                p.pdt_id = rs.getInt("pdt_id");
	                p.pdt_id_name = rs.getString("pdt_id_name");
	                    
	                p.printScore();
	                    
	                total = total + p.ord_price;                        // 총 매출 합계
	                buying_count = buying_count + p.ord_buying_count;   // 총 매출 총액
	            }
	              
	            System.out.println("======================================================");
	            System.out.println("*전체판매출합계: "+buying_count+"  "+ total);
	                                                                                                                //else 끝
		    }catch(Exception e) {                                                                                   //try 끝
		        e.printStackTrace();
		    }       
	        continue;
	      }while(true);                                                                                            //do 끝
	   }
	}