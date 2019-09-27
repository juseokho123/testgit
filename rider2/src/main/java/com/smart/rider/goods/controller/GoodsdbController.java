package com.smart.rider.goods.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.goods.dto.GoodsdbDTO;
import com.smart.rider.goods.service.GoodsdbService;

@Controller
public class GoodsdbController {
	@Autowired
	private GoodsdbService goodsdbservice;
	
	//05상품 검색메서드
	//19-09-18 문영성
	@PostMapping("/goodsDbSearchList")
	public String goodsDbSearchList(@RequestParam(value="select")String select
									,@RequestParam(value="searchInput")String searchInput
									,@RequestParam(value="beginDate")String beginDate
									,@RequestParam(value="endDate")String endDate
									,Model model) {
		//System.out.println(select + " <-- select goodsDbSearchList GoodsdbController.java");
		//System.out.println(searchInput + " <-- searchInput goodsDbSearchList GoodsdbController.java");
		//System.out.println(beginDate + " <-- beginDate goodsDbSearchList GoodsdbController.java");
		//System.out.println(endDate + " <-- endDate goodsDbSearchList GoodsdbController.java");
		List<GoodsdbDTO> search = goodsdbservice.goodsDbSearchList(select, searchInput, beginDate, endDate);
		model.addAttribute("goodsDbList", search);
		//System.out.println(search+"<==========검색확인");
		if(search.size()==0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		
		return "goods/goodsDbList";
	}
	
	//상품DB삭제 메서드
	//19-09-16 문영성
	@GetMapping("/goodsDbDelete")
	public String goodsDbDelete(@RequestParam(value="goodsDbCode")String goodsDbCode,Model model) {
		//System.out.println(goodsDbCode+"<..............코드가져오기");
		model.addAttribute("goodsDbCode",goodsDbCode);
		return "/goods/goodsDbDelete";
	}
	
	@PostMapping("/goodsDbDelete")
	public String goodsDbDelete(@RequestParam(value="goodsDbCode")String goodsDbCode,
								@RequestParam(value="memberId")String memberId,
								@RequestParam(value="memberPw")String memberPw,Model model) {
		//System.out.println(goodsDbCode+"<..............코드가져오기");
		//System.out.println(memberId+"<..............아이디");
		//System.out.println(memberPw+"<..............비빌번호");		
		
		  int result = goodsdbservice.goodsDbDelete(goodsDbCode, memberId, memberPw);
		  if(result==0) {
			  model.addAttribute("result", "비밀번호가 불일치합니다.");
			  model.addAttribute("goodsDbCode", goodsDbCode); 
			  return  "/goods/goodsDbDelete"; 
			  }
		  
		return "redirect:goodsDbList";	
	}
	
	
	
	//상품DB상세조회
	//19-09-16문영성
	@GetMapping("/getGoodsDbList")
	public String getGoodsDbList(@RequestParam(value="goodsDbCode")String goodsDbCode,Model model) {
		//System.out.println(goodsDbCode+"<==============넘어오는코드값 확인=GoodsdbController.java");
		//GoodsdbDTO ggg = goodsdbservice.getGoodsDbCode(goodsDbCode);
		
		 
		//GoodsdbDTO GoodsdbDTO = goodsdbservice.getGoodsDbCode(goodsDbCode);
		//System.out.println(GoodsdbDTO.toString() +"GoodsdbDTO.toString() <==============넘어오는코드값 확인=GoodsdbController.java");
		//입력처리 메서드 호출시에		
		model.addAttribute("goodsDB", goodsdbservice.getGoodsDbCode(goodsDbCode));
		return "goods/getGoodsDbList";
		
	}
	/*
	 * @PostMapping("/getGoodsDbList") public String getGoodsDbList(GoodsdbDTO
	 * goodsdbdto) {
	 * 
	 * return "redirect:/goodsDbList";
	 * 
	 * }
	 */



	//03상품db 세션아이디가져와서 코드자동증가, db에값들어가는지확인.
	//19-09-10 문영성
	@PostMapping("/goodsDbInsert")
	public String goodsDbInsert(GoodsdbDTO goodsdbdto, HttpSession session) {
		//System.out.println(goodsdbdto+"<<<<<<<<<<넘어오는값 확인하기");
		goodsdbservice.goodsDbInsert(goodsdbdto, session);
		return "redirect:/goodsDbList";
		
	}
	
	@GetMapping("/goodsDbInsert")
	public String goodsdbInsert() {
		return "/goods/goodsDbInsert";
	}
	

	//02
	// 상품DB등록 확인메서드(처음단계)
	// 문영성

	/*
	 * @PostMapping("/goodsDbInsert") public String goodsdbInsert(GoodsdbDTO
	 * goodsdbdto,Model model) {
	 * System.out.println(goodsdbdto+"<<<<<<<<<<넘어오는값 확인하기");
	 * //model.addAttribute("goodsdbdto",goodsdbservice.goodsDbInsert(goodsdbdto));
	 * return "/goods/goodsDbInsert";
	 * 
	 * }
	 */
	 
	
	//01상품db관리 페이지이동
	//04상품DB리스트 조회
		@GetMapping("/goodsDbList")
		public String goodsDbList(Model model) {
			//System.out.println(model.addAttribute("goodsDbList", goodsdbservice.goodsDbList())+"<======================");	
			model.addAttribute("goodsDbList", goodsdbservice.goodsDbList());
			return "goods/goodsDbList";
		}

		

}
