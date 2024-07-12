package kr.ac.kopo.dogworld.demo.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.dogworld.demo.vo.DogVO;

@Repository
public interface JpaDogRepository extends JpaRepository<DogVO, String>{
	List<DogVO> findByMemberId(String memberId);
	Optional<DogVO> findByNo(int no);
}

