package com.ptyt.sample.bean;


import java.util.List;

public class PageSupport extends BaseBean{
	private static final long serialVersionUID = 1L;
	// 当前页号
	private int currentPageNo = 0;
	// 总记录数
	private int totalRecordCount;
	// 每页大小
	private int pageSize = 12;
	// 查询结果
	private List<?> result;

	public PageSupport() {
		
	}	
	
	public PageSupport(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	/**
	 * 获取当前页号
	 * 
	 * @return
	 */
	public int getCurrentPageNo() {
		return this.currentPageNo;
	}
	
	/**
	 * 设置当前页号
	 * @param currentPageNo
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	
	/**
	 * 获取查询结果
	 * 
	 * @return
	 */
	public List<?> getResult() {
		return this.result;
	}
	
	/**
	 * 设置查询结果
	 * @param result
	 */
	public void setResult(List<?> result) {
		this.result = result;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void clear(){
		if(result!=null){
			result.clear();
		}
	}
	
	/**
	 * 设置每页大小
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalPageCount() {
		if (totalRecordCount == 0) {
			return 0;
		}
		if (totalRecordCount % pageSize == 0) {
			return totalRecordCount / pageSize;
		} else {
			return totalRecordCount / pageSize + 1;
		}
	}

	/**
	 * 获取下一页的页号
	 * 
	 * @return -1,表示没有下一页
	 */
	public int getNextPageNo() {
		if (currentPageNo + 1 > getTotalPageCount()) {
			return -1;
		}
		return this.currentPageNo + 1;
	}

	/**
	 * 获取前一页的页号
	 * 
	 * @return -1,表示没有前一页
	 */
	public int getPrePageNo() {
		if (currentPageNo - 1 < 1) {
			return -1;
		}
		return this.currentPageNo - 1;
	}

	/**
	 * 获取下N页的起始页码
	 * 
	 * @param i
	 * @return -1，表示没有下N页
	 */
	public int getNextNPageStartNo(int i) {
		if (currentPageNo + i > getTotalPageCount()) {
			return -1;
		}
		return this.currentPageNo + i;
	}

	/**
	 * 获取前N页的起始页码
	 * 
	 * @param i
	 * @return -1，表示没有前N页
	 */
	public int getPreNPageStartNo(int i) {
		if (currentPageNo - i < 1) {
			return -1;
		}
		return this.currentPageNo - i;
	}	

	/**
	 * 获取最后一页的页号
	 * 
	 * @return -1,表示当前页就是最后一页了
	 */
	public int getLastPageNo() {
		return getTotalPageCount() > 0 ? getTotalPageCount() : -1;
	}

	/**
	 * 获取当前页的第一条记录数
	 * @return
	 */
	public int getCurrentFirst() {
		if (getTotalPageCount() == 0) {
			return -1;
		}
		return this.pageSize * (this.currentPageNo - 1) + 1;
	}

	/**
	 * 获取当前页的最后一条记录数
	 * @return
	 */
	public int getCurrentLast() {
		if (getTotalPageCount() == 0) {
			return -1;
		}
		int count = pageSize * currentPageNo;
		if (count < getTotalRecordCount()) {
			return count;
		} else {
			return getTotalRecordCount();
		}
	}

	public int getFirstPage() {
		if (getTotalPageCount() == 0) {
			return -1;
		}
		return 1;
	}

	public boolean isFirstPage() {
		return this.getFirstPage()!= 1;
	}
}
