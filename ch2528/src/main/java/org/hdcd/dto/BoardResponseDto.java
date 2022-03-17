package org.hdcd.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hdcd.domain.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto
{
	private Long boardNo;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regdate;
	private LocalDateTime updDate;
	private List<ReplyResponseDto> replys;
	
	public BoardResponseDto(Board board)
	{
		this.boardNo = board.getBoardNo();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.regdate = board.getRegDate();
		this.updDate = board.getUpdDate();
		this.replys = board.getReplyList().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
	}
}