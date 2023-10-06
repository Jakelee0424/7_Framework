package edu.kh.project.common;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// 예외 처리용 컨트롤러(프로젝트 전역)
@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(SQLException.class)
	public String exceptionHandler(SQLException e, Model model) {

		model.addAttribute("e", "SQLExceoption 발생"); 
		
		return "common/error";
	}

	@ExceptionHandler(IOException.class)
	public String exceptionHandler2(IOException e, Model model) {

		model.addAttribute("e", "IOExceoption 발생"); 
	 	
		return "common/error";
	}

	@ExceptionHandler(NullPointerException.class)
	public String exceptionHandler3(NullPointerException e, Model model) {

		model.addAttribute("e", "NullPointerException 발생"); 
		 	
		return "common/error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler4(Exception e, Model model) {

		model.addAttribute("e", "Exception 발생"); 
		 	
		return "common/error";
	}
	

}
