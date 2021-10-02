package com.deft.crud.admin.board.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminBoardDTO {
	private int boardNo;
	private int writeNo;
	private String boardName;
	private String empName;
	private java.util.Date writeDate;
	private int views;
	private String contents;
	private String type;
	private int empNo;
	private int boardAttatchNo;
	

}
