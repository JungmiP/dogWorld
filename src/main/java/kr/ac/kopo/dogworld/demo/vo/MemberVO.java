package kr.ac.kopo.dogworld.demo.vo;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity(name = "member")
@DynamicInsert
public class MemberVO {
	@Id
	private String id;
	private String password;
	private String name;
	@Column(name = "birth_date")
	private String birthDate;
	private String phone;
	private String post;
	@Column(name = "basic_addr")
	private String basicAddr;
	@Column(name = "detail_addr")
	private String detailAddr;
	private String email;
	@Column(name = "reg_date")
	@ColumnDefault("sysdate")
	private String regDate;
	@ColumnDefault("B")
	private String grade;
	private String oauth;
	private String admin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getBasicAddr() {
		return basicAddr;
	}

	public void setBasicAddr(String basicAddr) {
		this.basicAddr = basicAddr;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", birthDate=" + birthDate
				+ ", phone=" + phone + ", post=" + post + ", basicAddr=" + basicAddr + ", detailAddr=" + detailAddr
				+ ", email=" + email + ", regDate=" + regDate + ", grade=" + grade + ", oauth=" + oauth + ", admin="
				+ admin + "]";
	}

	@PrePersist
	public void prePersist() {
		this.grade = (this.grade == null) ? "B" : this.grade;
	}
}
