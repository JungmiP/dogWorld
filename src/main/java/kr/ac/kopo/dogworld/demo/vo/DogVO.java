package kr.ac.kopo.dogworld.demo.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="dog")
public class DogVO {
	@Id
	private int no;
	@Column(name="member_id")
	private String memberId;
	private String name;
	@Column(name="birth_date")
	private String birthDate;
	@Column(name="breed_code")
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
