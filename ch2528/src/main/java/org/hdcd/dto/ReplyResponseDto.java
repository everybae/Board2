package org.hdcd.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hdcd.domain.Reply;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReplyResponseDto
{
	private Long replyNo;
	private String writer;
	private String content;
	private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	private String updDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	private Long boardNo;
	
	public ReplyResponseDto(Reply reply)
	{
		this.replyNo = reply.getReplyNo();
		this.content = reply.getContent();
		this.writer = reply.getWriter();
		this.regDate = reply.getRegDate();
		this.updDate = reply.getUpdDate();
		this.boardNo = reply.getBoardNo().getBoardNo();
	}
}