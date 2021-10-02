package com.ez.swm.studycafe.vo;

import java.sql.Date;

public class StudycafeReview {
	@Override
	public String toString() {
		return "StudycafeReview [studycafe_no=" + studycafe_no + ", review_no=" + review_no + ", review_content="
				+ review_content + ", review_grade=" + review_grade + ", userno=" + userno + "]";
	}
	private int studycafe_no;
	private int review_no;
	private String review_content;
	private int review_grade;
	private int userno;
	private String nickname;
	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	private Date review_date;
	
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getStudycafe_no() {
		return studycafe_no;
	}
	public int getReview_no() {
		return review_no;
	}
	public String getReview_content() {
		return review_content;
	}
	public int getReview_grade() {
		return review_grade;
	}
	public void setStudycafe_no(int studycafe_no) {
		this.studycafe_no = studycafe_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public void setReview_grade(int review_grade) {
		this.review_grade = review_grade;
	}
	
	
	
}
