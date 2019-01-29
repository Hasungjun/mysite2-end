package com.douzon.mysite.vo;

import java.util.Date;

public class BoardVo {
	
private int	no;
private String	title;
private String	contents;
private Date write_date;
private int	hit;
private int	g_no;
private int	o_no;
private int	depth;
private int	user_no;
private String	user_name;


public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContents() {
	return contents;
}
public void setContents(String contents) {
	this.contents = contents;
}
public Date getWrite_date() {
	return write_date;
}
public void setWrite_date(Date write_date) {
	this.write_date = write_date;
}
public int getHit() {
	return hit;
}
public void setHit(int hit) {
	this.hit = hit;
}
public int getG_no() {
	return g_no;
}
public void setG_no(int g_no) {
	this.g_no = g_no;
}
public int getO_no() {
	return o_no;
}
public void setO_no(int o_no) {
	this.o_no = o_no;
}
public int getDepth() {
	return depth;
}
public void setDepth(int depth) {
	this.depth = depth;
}
public int getUser_no() {
	return user_no;
}
public void setUser_no(int user_no) {
	this.user_no = user_no;
}
@Override
public String toString() {
	return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", write_date=" + write_date + ", hit="
			+ hit + ", g_no=" + g_no + ", o_no=" + o_no + ", depth=" + depth + ", user_no=" + user_no + ", user_name="
			+ user_name + "]";
}




}
