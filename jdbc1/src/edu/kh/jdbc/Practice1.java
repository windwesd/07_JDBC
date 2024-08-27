package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		
		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			// 2. DriverManager를 이용해서 Connection 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; // 드라이버의 종류
			
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소 
									   // localhost == 현재 컴퓨터
			
			String port = ":1521"; // 프로그램 연결을 위한 구분 번호
			
			String dbName = ":XE"; // DBMS 이름 (XE == eXpress Edition)
			
			String userName = "KH_KHJ"; // 사용자 계정명
			
			String password = "KH1234"; // 계정 비밀번호
			
			conn = DriverManager.getConnection(type + host + port + dbName, userName, password);
			
			// 3. SQL 작성
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("성별 (M,F) : ");
			String gender = sc.nextLine().toUpperCase();
			
			System.out.print("급여 범위(최소, 최대 순서로 작성) : ");
			int min = sc.nextInt();
			int max = sc.nextInt();
			
			System.out.print("급여 정렬 (1.ASC, 2.DESC) : ");
			int sort = sc.nextInt();
			
			String sql = """
					SELECT 
						EMP_ID, 
						EMP_NAME,
						DECODE( SUBSTR(EMP_NO,8,1), '1', 'M', '2' ,'F') GENDER,
						SALARY,
						JOB_NAME,
						NVL(DEPT_TITLE, '없음') DEPT_TITLE
					FROM EMPLOYEE
					JOIN JOB USING(JOB_CODE)
					LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
					WHERE 
						DECODE( SUBSTR(EMP_NO,8,1), '1', 'M', '2' ,'F') = ?
					AND 
						SALARY BETWEEN ? AND ?
					ORDER BY SALARY 
					""";
			
					// 입력받은 정렬(sort) 값에 따라서 sql에 오름/내림 차순 SQL 추가하기
					if(sort == 1) sql += " ASC";
					else 		  sql += " DESC";
			
			// PreparedStatement 생성
					
			pstmt = conn.prepareStatement(sql);
			
			// 5. ?(placeholder)에 알맞은 값 세팅
			pstmt.setString(1, gender);
			pstmt.setInt(2, min);
			pstmt.setInt(3, max);
			
			// 6. SQL 수행 후 결과 반환 받는
			rs = pstmt.executeQuery();
			
			// 7. 커서를 이용해서 한 행씩 접근하여
			//   컬럼 값 얻어오기
			System.out.println("사번 | 이름   | 성별 | 급여    | 직급명 | 부서명");
			System.out.println("--------------------------------------------------------");
			
			boolean flag = true;
			// true : 조회 결과 없음 / false : 조회 결과 존재함!!
			
			while(rs.next()) {
				flag = false; // while이 1회 이상 반복함 == 조회 결과가 있음
				
				String empId     = rs.getString("EMP_ID");
				String empName   = rs.getString("EMP_NAME");
				String gen   	 = rs.getString("GENDER");
				int    salary 	 = rs.getInt("SALARY");
				String jobName   = rs.getString("JOB_NAME");
				String deptTitle = rs.getString("DEPT_TITLE");
				
				System.out.printf(
						"%-4s | %3s | %-4s | %7d | %-3s  | %s \n",
						empId, empName, gen, salary, jobName, deptTitle);
			}
			
			if(flag) { // flag == true -> 조회 결과 없음
				System.out.println("조회 결과 없음");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 8. 사용한 JDBC 객체 자원 반환(close)
				if(rs != null) rs.close();
				if(pstmt != null) rs.close();
				if(conn != null) rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
