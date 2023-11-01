package edu.kh.project.common.scheduling;

import java.io.File;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.project.board.model.service.BoardService;

// 스프링이 일정 시간마다 해당 객체를 이용해서 코드를 수행
// -> 스프링이 해당 클래스를 객체로 만듦
@Component // 기능의 구분 없이 bean으로 등록하는 어노테이션(@Controller, @Service, @Repository 등의 부모)
public class ImageDeleteScheduling {

	/*
	 * @Scheduled
	 * 
	 * * Spring에서 제공하는 스케줄러 - 스케줄러 : 시간에 따른 특정 작업(Job)의 순서를 지정하는 방법.
	 * 
	 * 설정 방법 
	 * 1) servlet-context.xml -> Namespaces 탭 -> task 체크 후 저장
	 * 2) servlet-context.xml -> Source 탭 -> <task:annotation-driven/> 추가
	 * 
	 *
	 * @Scheduled 속성
	 *  - fixedDelay : 이전 작업이 끝난 시점으로 부터 고정된 시간(ms)을 설정.
	 *    @Scheduled(fixedDelay = 10000) // 이전 작업이 끝난 후 10초 뒤에 실행
	 *    
	 *  - fixedRate : 이전 작업이 수행되기 시작한 시점으로 부터 고정된 시간(ms)을 설정.
	 *    @Scheduled(fixedRate = 10000) // 이전 작업이 시작된 후 10초 뒤에 실행
	 *    
	 *    
	 * * cron 속성 : UNIX계열 잡 스케쥴러 표현식으로 작성 - cron="초 분 시 일 월 요일 [년도]" - 요일 : 1(SUN) ~ 7(SAT) 
	 * ex) 2019년 9월 16일 월요일 10시 30분 20초 cron="20 30 10 16 9 2 " // 연도 생략 가능
	 * 
	 * - 특수문자 * : 모든 수. 
	 * - : 두 수 사이의 값. ex) 10-15 -> 10이상 15이하 
	 * , : 특정 값 지정. ex) 3,4,7 -> 3,4,7 지정 
	 * / : 값의 증가. ex) 0/5 -> 0부터 시작하여 5마다 
	 * ? : 특별한 값이 없음. (월, 요일만 해당) 
	 * L : 마지막. (월, 요일만 해당)
	 * @Scheduled(cron="0 * * * * *") // 매 분마다 실행
	 * 
	 * 
	 * 
	 * 
	 * * 주의사항 - @Scheduled 어노테이션은 매개변수가 없는 메소드에만 적용 가능.
	 * 
	 */

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BoardService service;
	
//												fixed Delay
//	@Scheduled(fixedDelay = 10000) // 일(1초) -> 대기(10초) -> 일(1초)
								
//									 fixedRate	
//									 대기(10초)	
//	@Scheduled(fixedRate = 10000) // 일(1초) -> 9초 뒤  -> 일(1초)
	
//	@Scheduled(cron = "0,30 * * * * *") // 매 분 0초, 30초 마다 실행
	@Scheduled(cron = "0 0 * * * *") // 정시
	public void test() {
		
		System.out.println("----------------- DB, 서버 불일치 파일 제거 --------------------");
		
		// 1) 서버에 저장된 파일 목록 조회 
		String webPath = "/resources/images/board";	
		String filePath = servletContext.getRealPath(webPath); //어플리케이션 객체 이용하여 경로 가져오기
		
		File path = new File(filePath); // 파일 객체 생성
		
		File[] imageArr = path.listFiles(); // 배열 형태로 저장
		
		List<File> serverImagelist = Arrays.asList(imageArr); // 리스트 형태로 저장
		
		
		// 2) DB에 저장 된 파일 목록 비교
		List<String> dbImageList = service.selectImageList(); // DB에 저장된 파일 목록 가져오기
		
		if(!serverImagelist.isEmpty()) {
			
			for(File serverF : serverImagelist) {
				
				// indexOf(객체) = 해당 객체의 인덱스 번호 반환, 없으면 -1
				if(dbImageList.indexOf(serverF.getName()) == -1){

					System.out.println(serverF.getName() + "삭제");
					serverF.delete(); // 삭제
					
				}
				
			}
			
		}
		
		// 3) 매칭되지 않는 서버 파일 제거
	}
	
}
