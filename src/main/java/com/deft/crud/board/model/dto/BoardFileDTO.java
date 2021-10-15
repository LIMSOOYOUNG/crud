package com.deft.crud.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BoardFileDTO {

	private int boardNo;
	private int writeNo;
	private int boardAttatchNo;
	private String originalName;
	private String savedName;
	private String savedPath;
	
}
