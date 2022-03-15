package org.hdcd;

import org.hdcd.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPAtest
{
	@Autowired
	ReplyRepository replyRepository;
	
	@Test
	public void replytest()
	{
		replyRepository.getOne((long) 2);
	}
}