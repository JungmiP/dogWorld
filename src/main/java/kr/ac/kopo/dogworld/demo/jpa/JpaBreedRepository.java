package kr.ac.kopo.dogworld.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.kopo.dogworld.demo.vo.BreedVO;

import java.util.List;

public interface JpaBreedRepository extends JpaRepository<BreedVO, Integer> {
    List<BreedVO> findAllByOrderByBreedNameAsc();
}
