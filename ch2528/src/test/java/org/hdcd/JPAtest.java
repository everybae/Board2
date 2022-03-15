package org.hdcd;

import java.util.List;

import org.hdcd.domain.Reply;
import org.hdcd.repository.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPAtest
{
	@Autowired
	ReplyRepository replyRepository;
	
	@Test
	@DisplayName("@Query를 이용한 테스트")
	public void replytest()
	{
		
	}
}