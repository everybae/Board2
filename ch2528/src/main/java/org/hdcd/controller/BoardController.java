package org.hdcd.controller;

import java.util.ArrayList;
import java.util.List;

import org.hdcd.common.security.domain.CustomUser;
import org.hdcd.domain.Board;
import org.hdcd.domain.Member;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.dto.MessageDTO;
import org.hdcd.dto.PaginationDTO;
import org.hdcd.service.BoardService;
import org.hdcd.service.NoticeService;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService service;
	private final NoticeService noticeService;
//	private final ReplyServiceImpl replyService;
	

	@GetMapping("/register")
	@PreAuthorize("hasRole('MEMBER')")
	public void registerForm(Model model, Authentication authentication) throws Exception {
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();

		Board board = new Board();

		board.setWriter(member.getUserId());

		model.addAttribute(board);
	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('MEMBER')")
	public String register(@Validated Board board, BindingResult result, RedirectAttributes rttr) throws Exception {
		if(result.hasErrors()) {
			return "board/register";
		}	
		
		service.register(board);

		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}

	@GetMapping("/list")
	public void list(@ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception {
		Page<Board> page = service.list(pageRequestVO);
	
		//PaginationDTO<Board> pagination = new PaginationDTO<>(page);
		//pagination.setPageRequest(pageRequestVO);
		
		model.addAttribute("pgntn", new PaginationDTO<>(page));
		
		List<CodeLabelValue> searchTypeCodeValueList = new ArrayList<CodeLabelValue>();
		searchTypeCodeValueList.add(new CodeLabelValue("n", "---"));
		searchTypeCodeValueList.add(new CodeLabelValue("t", "Title"));
		searchTypeCodeValueList.add(new CodeLabelValue("c", "Content"));
		searchTypeCodeValueList.add(new CodeLabelValue("w", "Writer"));
		searchTypeCodeValueList.add(new CodeLabelValue("tc", "Title OR Content"));
		searchTypeCodeValueList.add(new CodeLabelValue("cw", "Content OR Writer"));
		searchTypeCodeValueList.add(new CodeLabelValue("tcw", "Title OR Content OR Writer"));

		model.addAttribute("searchTypeCodeValueList", searchTypeCodeValueList);
		model.addAttribute("list", noticeService.list());
	}

	@GetMapping("/read")
	public void read(Long boardNo, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model, Authentication authentication) throws Exception
	{
		try
		{
			if(authentication.getPrincipal().equals(null))
			{
				model.addAttribute("RW", "손님");
			}
			else
			{
				CustomUser customUser = (CustomUser) authentication.getPrincipal();
				Member member = customUser.getMember();
				String replyWriter = member.getUserId();
				
				System.out.println(replyWriter);
				
				model.addAttribute("RW", replyWriter);
			}
		}
		catch(Exception e)
		{
			
		}
		
		
		model.addAttribute(service.read(boardNo));
	}

	@PostMapping("/remove")
	@PreAuthorize("(hasRole('MEMBER') and principal.username == #writer) or hasRole('ADMIN')")
	public String remove(Long boardNo, PageRequestVO pageRequestVO, RedirectAttributes rttr, String writer) throws Exception {
		service.remove(boardNo);

		rttr.addAttribute("page", pageRequestVO.getPage());
		rttr.addAttribute("sizePerPage", pageRequestVO.getSizePerPage());
		rttr.addAttribute("searchType", pageRequestVO.getSearchType());
		rttr.addAttribute("keyword", pageRequestVO.getKeyword());
	   
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

	@GetMapping("/modify")
	@PreAuthorize("hasRole('MEMBER')")
	public void modifyForm(Long boardNo, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	@PostMapping("/modify")
	@PreAuthorize("hasRole('MEMBER') and principal.username == #board.writer")
	public String modify(@RequestParam("boardNo") int idx, @Validated Board board, BindingResult result, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, RedirectAttributes rttr) throws Exception {
		if(result.hasErrors())
		{
			return "board/modify";
		}		
		
		service.modify(board);

		rttr.addAttribute("page", pageRequestVO.getPage());
		rttr.addAttribute("sizePerPage", pageRequestVO.getSizePerPage());
		rttr.addAttribute("searchType", pageRequestVO.getSearchType());
		rttr.addAttribute("keyword", pageRequestVO.getKeyword());
	    
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/read?boardNo=" + idx;
	}
	
//	@PostMapping("/reply/write")
//	@PreAuthorize("hasRole('MEMBER')")
//	public String writeReply(@RequestParam("boardNo") int idx, @RequestParam("content") String content, Authentication authentication) throws Exception
//	{
//		CustomUser customUser = (CustomUser) authentication.getPrincipal();
//		Member member = customUser.getMember();
//		
//		ReplyDto replyDto = new ReplyDto();
//		
//		replyDto.setContent(content);
//		replyDto.setWriter(member.getUserId());
//		
//		Reply reply = new Reply();
//		
//		replyService.register(reply);
//		
//		return "redirect:/board/read?boardNo=" + idx;
//	}
	
	@ResponseBody
	@PostMapping(path = "/dataSend", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> dataSend(Model model, Authentication authentication)
	{
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();
		
		String test = "댓글을 입력해주세요";
		String replyWriter = member.getUserId();
	
		System.out.println(replyWriter);
		
		return new ResponseEntity<String>(test, HttpStatus.OK);
	}
	
	@GetMapping("/ajaxtest")
	public String test(Model model, MessageDTO dto)
	{
		model.addAttribute("댓글을 입력해주세요");
		return "board/ajaxtest";
	}
}
