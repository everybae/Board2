package org.hdcd.service;

import org.hdcd.domain.Reply;
import org.hdcd.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl
{
	private final ReplyRepository replyRepository;
	
	public void register(Reply reply) throws Exception
	{
		replyRepository.save(reply);
	}
	
	public Reply read(Long boardNo) throws Exception
	{
		return replyRepository.getOne(boardNo);
	}
}