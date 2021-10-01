package com.deft.crud.admin.board.model.dto;

import java.util.Date;

public class AdminBoardDTO {
	private int boardNo;
	private int writeNo;
	private String boardName;
	private String writerName;
	private java.util.Date writeDate;
	private int views;
	private String contents;
	private String type;
	private int empNo;
	private int boardAttatchNo;
	
	public AdminBoardDTO() {}

	public AdminBoardDTO(int boardNo, int writeNo, String boardName, String writerName, Date writeDate, int views,
			String contents, String type, int empNo, int boardAttatchNo) {
		super();
		this.boardNo = boardNo;
		this.writeNo = writeNo;
		this.boardName = boardName;
		this.writerName = writerName;
		this.writeDate = writeDate;
		this.views = views;
		this.contents = contents;
		this.type = type;
		this.empNo = empNo;
		this.boardAttatchNo = boardAttatchNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getWriteNo() {
		return writeNo;
	}

	public void setWriteNo(int writeNo) {
		this.writeNo = writeNo;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public java.util.Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(java.util.Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getBoardAttatchNo() {
		return boardAttatchNo;
	}

	public void setBoardAttatchNo(int boardAttatchNo) {
		this.boardAttatchNo = boardAttatchNo;
	}

	@Override
	public String toString() {
		return "AdminBoardDTO [boardNo=" + boardNo + ", writeNo=" + writeNo + ", boardName=" + boardName
				+ ", writerName=" + writerName + ", writeDate=" + writeDate + ", views=" + views + ", contents="
				+ contents + ", type=" + type + ", empNo=" + empNo + ", boardAttatchNo=" + boardAttatchNo + "]";
	}
}
