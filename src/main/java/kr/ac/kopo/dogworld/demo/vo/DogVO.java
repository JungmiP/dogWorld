package kr.ac.kopo.dogworld.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="dog")
public class DogVO {
	@Id
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
