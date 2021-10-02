package com.ez.swm.book.vo;

public class BookTime {

	private int studycafe_no;
	private String studycafe_room;
	private String book_date;
	private String book_time;
	
	public int getStudycafe_no() {
		return studycafe_no;
	}
	public String getStudycafe_room() {
		return studycafe_room;
	}
	public String getBook_date() {
		return book_date;
	}
	public String getBook_time() {
		return book_time;
	}
	public void setStudycafe_no(int studycafe_no) {
		this.studycafe_no = studycafe_no;
	}
	public void setStudycafe_room(String studycafe_room) {
		this.studycafe_room = studycafe_room;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public void setBook_time(String book_time) {
		this.book_time = book_time;
	}
	@Override
	public String toString() {
		return "BookTime [studycafe_no=" + studycafe_no + ", studycafe_room=" + studycafe_room + ", book_date="
				+ book_date + ", book_time=" + book_time + "]";
	}
	
	

}
