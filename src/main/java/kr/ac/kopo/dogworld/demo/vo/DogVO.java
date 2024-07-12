package kr.ac.kopo.dogworld.demo.vo;

import java.text.SimpleDateFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name="dog")
public class DogVO {
	@Id
	//기본키를 자동으로 생성하는 JPA어노테이션, stratgey 키 생성 옵션, 시퀀스를 사용해 키를 생성
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dog_seq")
	//시퀀스를 정의(name은 generator값과 일치, 시퀀스이름, 시퀀스 번호 증가)
	@SequenceGenerator(name = "dog_seq", sequenceName = "seq_dog_no", allocationSize = 1)
	//기본키 자동생성
	private int no;
	private String memberId;
	private String name;
	private String birthDate;
	private int breedCode;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public int getBreedCode() {
		return breedCode;
	}
	public void setBreedCode(int breedCode) {
		this.breedCode = breedCode;
	}
	
	@Override
	public String toString() {
		return "DogVO [no=" + no + ", memberId=" + memberId + ", name=" + name + ", birthDate=" + birthDate
				+ ", breedCode=" + breedCode + "]";
	}
	
}
