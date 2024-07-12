package kr.ac.kopo.dogworld.demo.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.ac.kopo.dogworld.demo.vo.MemberVO;

@Repository
public interface JpaMemberRepository extends JpaRepository<MemberVO, String>{
	@Query(value="insert into member(id, password, name, birth_date, phone, post, basic_addr, detail_addr, email)"
			+ "values()", nativeQuery=true)
	public void join(MemberVO member);
	
	public Optional<MemberVO> findById(String id);
	
	@Transactional
    @Modifying
    @Query(value = "UPDATE member SET name = ?2, birth_date = TO_DATE(?3, 'YYYY-MM-DD'), phone = ?4, post = ?5, basic_addr = ?6, detail_addr = ?7, email = ?8 WHERE id = ?1", nativeQuery = true)
    void updateMember(String id, String name, String birthDate, String phone, String post, String basicAddr, String detailAddr, String email);

}








