package com.deft.crud.board.model.dto;

import java.time.LocalDate;

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
public class BoardDTO implements java.io.Serializable{

	private int boardNo;
	private int writeNo;
	private String boardName;
	private String empName;
	private LocalDate writeDate;
	private int views;
	private String contents;
	private String type;
	private int empNo;
	private String empId;
	
}
