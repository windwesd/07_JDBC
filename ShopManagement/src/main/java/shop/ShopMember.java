package shop;

public class ShopMember {

	private String memberId;
	private String memberPw;
	private String phone;
	private String gender;
	
	public ShopMember() {}
	
	public ShopMember(String memberId, String memberPw, String phone, String gender) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.phone = phone;
		this.gender = gender;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "ShopMember [memberId=" + memberId + ", memberPw=" + memberPw + ", phone=" + phone + ", gender=" + gender
				+ "]";
	}
}
	
	
	
	
	
