package kr.ac.kopo.dogworld.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.dogworld.demo.vo.MemberVO;

@Repository
public interface JpaMemberRepository extends JpaRepository<MemberVO, String>{
	@Query(value="insert into member(id, password, name, birth_date, phone, post, basic_addr, detail_addr, email)"
			+ "values()", nativeQuery=true)
	public void join(MemberVO member);
	
	public void login(MemberVO member);
}








