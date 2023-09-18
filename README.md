# KIOSK 상품 등록 및 매장 운영 프로그램
***JAVA 구조체를 활용한 KIOSK 운영 프로그램*** : 햄버거 가게<br>

> 정리한 노션 사이트 : <a href="https://dlclfh.notion.site/JAVA-Kiosk-17b8152490194dca8b2a09da419401ad?pvs=4">Notion</a>

## 제작 순서 🔨
#### Kiosk_Mainmenu 부분
- 1 상품코드 등록 : ***Kiosk_Product_Insert***
- 2 등록된 상품코드 조회 : ***Kiosk_Product_InquiryChoice***
- 3 KIOSK 매장 운영 프로그램(주문입력) : ***Kiosk_Product_BuyChoice***
- 4 매장 매출현황(주문서별 합계) : ***Kiosk_Product_BuyTotal***
- 5 매장 주문서별 LIST : ***Kiosk_Product_BuyList***
- 6 상품별 매출 수량 및 금액 총합 : ***Kiosk_Product_SalesListTotal***
- 7 상품별/일자별 매출 LIST : ***Kiosk_Product_SalesList***
#### DB(Data Base) 부분
- 상품마스터 테이블 명세서 : ***Tbl_Product_Master***
  - 상품코드
  - 상품명
  - 단가
  - 주문방법
- 주문서 주문리스트 테이블 명세서 : ***Tbl_Order_List***
  - 주문번호
  - count
  - 상품코드
  - 주문수량
  - 상품단가
  - 금액 
- 주문서 주문번호 테이블 명세서 : ***Tbl_Order_Total***
  - 주문번호
  - 금액
  - 지급방법
  - 받은 돈
  - 거스름 돈
  - system_date
---
#### 작성 순서
1. Kiosk_MainMenu를 작성한다. 번호를 입력받으면 해당하는 class 메인으로 넘어간다.
2. 기능부분 페이지를 작성하고, 조회해야하는 sql문을 작성한다.
3. Kiosk 프로그램을 실행한다.
---
### ***Language*** : Java, SQL(Oracle)



