package shop;


import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ShopDAO {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ShopMember selectMember(connection conn, String memberId) {
		ShopMember sm = null;
		
		try {
			String sql = "SELECT * FROM SHOP_MEMBER WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			
			if(rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String pw = rs.getString("MEMBER_PW");
				String phone = rs.getString("PHONE");
				String gender = rs.getString("GENDER");
				
				sm = new ShopMember(id, pw, phone, gender);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		return sm;
	}
}
