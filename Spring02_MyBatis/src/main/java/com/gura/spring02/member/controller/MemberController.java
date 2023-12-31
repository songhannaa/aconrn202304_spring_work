package com.gura.spring02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;


@Controller
public class MemberController {
   
   // spring bean container 로 부터 MemberDao 인터페이스 type 의 참조값을 DI 받는다. 
   @Autowired
   private MemberDao dao;
   
   @RequestMapping("/member/update")
   public String update(MemberDto dto) {
	   //MemberDao 객체를 이용해서 회원정보를 수정 반영하고
	   dao.update(dto);
	   //view page 로 forward 이동해서 응답
	   return "member/update";
   }
   
   @RequestMapping("/member/updateform")
   public String updateform(int num, HttpServletRequest request) {
	   //MemberDao 객체를 이용해서 수정할 회원의 정보를 DB에서 불러온다.
	   MemberDto dto=dao.getData(num);
	   
	   //view page에 전달할 모델(data) 를 request 영역에 담는다.
	   request.setAttribute("dto", dto);
	   
	   //view page 로 forward 이동해서 수정할 회원폼을 출력해 준다.
	   return "member/updateform";
   }
   
   @RequestMapping("/member/delete")
   public String delete(int num) {
	   //MemberDao 객체를 이용해서 DB 에서 삭제하고
	   dao.delete(num);
	   //view page로 forward 이동해서 응답한다.
	   return "member/delete";
   }
   
   @RequestMapping("/member/insert")
   public String insert(MemberDto dto) {
	   //MemberDao 객체를 이용해서 추가하기
	   dao.insert(dto);
	   //view page로 forward 이동해서 응답하기
	   return "member/insert";
   }
   
   @RequestMapping("/member/insertform")
   public String insertform() {
	   
	   return "member/insertform";
   }
   
   @RequestMapping("/member/list")
   public String list(HttpServletRequest request) {
      
      
      List<MemberDto> list=dao.getList();
      
      request.setAttribute("list", list);
      /*
       *  /WEB-INF/views/member/list.jsp 페이지로 forward 이동해서 
       *  회원 목록을 출력하면된다.
       *  
       *  그런데... 회원목록은 어떻게 얻어오지? 
       */
      return "member/list";
   }
}