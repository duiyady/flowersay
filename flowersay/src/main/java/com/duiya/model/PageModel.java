package com.duiya.model;

import java.util.List;
import java.util.Map;

public class PageModel {
	private Integer allPage;//总页数
	private Integer count;//每页条数
	private Integer page;//此页
	private Integer allCount;//总记录数
	private List<Map<String,Object>> dataList;//详细
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "ThemePage [allPage=" + allPage + ", count=" + count + ", page=" + page + ", allCount=" + allCount
				+ ", dataList=" + dataList + "]";
	}
	/**
	 * @param allPage
	 * @param count
	 * @param page
	 * @param allCount
	 * @param dataList
	 */
	public PageModel(Integer allPage, Integer count, Integer page, Integer allCount,
			List<Map<String, Object>> dataList) {
		super();
		this.allPage = allPage;
		this.count = count;
		this.page = page;
		this.allCount = allCount;
		this.dataList = dataList;
	}
	/**
	 * 
	 */
	public PageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
