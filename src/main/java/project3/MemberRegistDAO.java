package project3;

import common.JDBConnect;
import jakarta.servlet.ServletContext;


public class MemberRegistDAO extends JDBConnect{
	//DB연결을 위한 인수생성자 정의
	public MemberRegistDAO(ServletContext app) {
		super(app);
	}

	//회원정보 입력을 위한 메서드 정의
	public int registInsert(MemberRegistDTO dto) {
	
		int result = 0;
		String query = " INSERT INTO regist_member VALUES ("
				+ "    ?,?,?,?,?,?,?,"
				+ "    ?,?,?)";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getTel());
			psmt.setString(5, dto.getMobile());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, dto.getEmail_check());
			psmt.setString(8, dto.getZipcode());
			psmt.setString(9, dto.getAddr1());
			psmt.setString(10, dto.getAddr2());
	
			//실행
			result= psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("뭐라고 말할까 뭐라고 말할까~");
			e.printStackTrace();
		}
		//반환값 0이면 false
		return result;
	}
	
	//아이디 중복확인을 위한 메서드 정의
	public boolean idOverlap(String id) {
		//초기값은 true로 설정. 중복된 아이디가 없는 경우
		boolean retValue = true; //0이면 중복된 아이디가 없는거니까 true
		//중복된 아이디가 있는지 확인하는 쿼리문 (인파라미터가 있는 쿼리문 작성)
		String query = "SELECT COUNT(*) FROM regist_member WHERE id=? ";
		
		try {
			//prepare객체 생성 및 인파리미터 설정
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			//rs= ResultSet 멤버변수
			//SELECT계열의 쿼리문이므로 반환타입은 ResultSet 
			rs = psmt.executeQuery();
			//커서 이동시켜서 코드 읽어줌 count()를 사용하기 때문에 결과값은 무조건 있음 0 아니면 1임
			//따라서 if()문을 사용할 필요없이 next()를 호출
			rs.next();
			int result = rs.getInt(1);
			//중복된 아이디가 있을때 1이 반환되면서 false을 반환함
			if(result == 1) {
				retValue = false;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		//중복된 아이디가 없다면 0이므로 true를 반환한다.
		return retValue;
		
	}
	public MemberRegistDTO getMemberDTO(String uid, String upass) {
		
		//회원인증을 위한 쿼리문을 실행한 후 회원정보를 저장하기 위해 생성
		MemberRegistDTO dto = new MemberRegistDTO();
		//?(인파라미타)
		//로그인 폼에서 입력한 아이디, 비번을 통해 인파라미터를 설정할 수 있도록 쿼리문을 작성
		String query = "SELECT * FROM member WHERE id= ? AND pass=?";
		
		try {
			//쿼리문 실행을 위한 prepared 객체 생성
			psmt =con.prepareStatement(query);
			//인파라미터 설정
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			//쿼리문 실행한 후 ResultSet 객체를 통해 결과 반환
			rs=psmt.executeQuery();
			
			//반환된 ResultSet 객체에 정보가 있는지 확인한다.
			if (rs.next()) {
				//회원정보가 있다면 DTO객체에 저장한다.
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
