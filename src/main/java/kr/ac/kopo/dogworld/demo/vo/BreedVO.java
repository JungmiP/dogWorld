package kr.ac.kopo.dogworld.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="Breed")
public class BreedVO {
	@Id
	private int no;
	private String dogSize;
	private String breedName;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getDogSize() {
		return dogSize;
	}
	public void setDogSize(String dogSize) {
		this.dogSize = dogSize;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	
	@Override
	public String toString() {
		return "BreedVO [no=" + no + ", dogSize=" + dogSize + ", breedName=" + breedName + "]";
	}
	
}
