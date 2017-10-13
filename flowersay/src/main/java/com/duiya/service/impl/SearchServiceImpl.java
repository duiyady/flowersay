package com.duiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duiya.dao.SearchDao;
import com.duiya.dto.FlowerSearchDto;
import com.duiya.model.PageModel;
import com.duiya.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Resource(name = "searchDao")
	private SearchDao searchDao;
	
	public PageModel searchFlower(FlowerSearchDto flowerSearchDto) {
		List<Map<String,Object>> infoList = searchDao.searchFlower(flowerSearchDto);
		int allCount = searchDao.getFlowerCount(flowerSearchDto);
		if(infoList == null){
			return null;
		}
		PageModel infoPage = new PageModel();
		infoPage.setCount(infoList.size());
		infoPage.setPage(flowerSearchDto.getPage());
		infoPage.setAllPage((allCount+flowerSearchDto.getCount()-1)/flowerSearchDto.getCount());
		infoPage.setAllCount(allCount);
		infoPage.setDataList(infoList);
		return infoPage;
	}

}
