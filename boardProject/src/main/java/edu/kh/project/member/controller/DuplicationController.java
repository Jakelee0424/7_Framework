package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.service.DuplicationCheckService;

@Controller
@RequestMapping("/dupCheck")
public class DuplicationController {

	@Autowired
	private DuplicationCheckService service;
	
	@GetMapping("/email")
	@ResponseBody
	public int duplicationCheck(String email) {
		
		int result = service.duplicationCheck(email);
		
		return result;
	}
	
	@GetMapping("/nickname")
	@ResponseBody
	public int duplicationCheckNickname(String nickname) {
		
		int result = service.duplicationCheckNickname(nickname);
		
		return result;
	}
	
}
