package org.hdcd.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hdcd.domain.Board;
import org.hdcd.domain.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyRequestDto
{
	private Long replyNo;
	private String content;
	private String writer;
	private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	private String updDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

	private Board board;
	
	public Reply toEntity()
	{
		Reply reply = Reply.builder()
				.replyNo(replyNo)
				.content(content)
				.writer(writer)
				.regDate(regDate)
				.updDate(updDate)
				.board(board)
				.build();
		
		return reply;
	}
}