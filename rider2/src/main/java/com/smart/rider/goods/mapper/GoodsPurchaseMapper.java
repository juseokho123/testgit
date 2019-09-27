package com.smart.rider.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsPurchaseDTO;

@Mapper
public interface GoodsPurchaseMapper {
	
	//코드자동증가메서드
	public String purchaseCodeCount();
	//매입inset
	public int purchaseInsert(GoodsPurchaseDTO goodsPurchaseDto);
	//매입 리스트
	public List<GoodsHapDTO> purchaseList();
	//매입 상세조회
	public GoodsHapDTO getPurchaseList(String purchaseCode);
	//매입 검색
	public List<GoodsPurchaseDTO> purchaseSearchList(String select ,String searchInput,String beginDate,String endDate);
	//매입 삭제
	public int purchaseDelete(String purchaseCode,String memberId,String memberPw);
	
}
