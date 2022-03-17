package org.hdcd.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hdcd.domain.Board;
import org.hdcd.domain.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto
{
	private Long replyNo;
	private String writer;
	private String content;
	private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	private String updDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	private Board boardNo;
	
	public Reply toEntity()
	{
		Reply replys = Reply.builder()
				.replyNo(replyNo)
				.content(content)
				.writer(writer)
				.regDate(regDate)
				.updDate(updDate)
				.boardNo(boardNo)
				.build();
		
		return replys;
	}
}