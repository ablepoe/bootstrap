package com.entity;

public class Page {

	private int totalCounts;
	private int totalPages;
	private int currentPage;
	private int pageMaxCounts = 5;
	private int startIndex;
	private int dbCounts;
	
	public int getTotalCounts() {
		return totalCounts;
	}
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
		count();
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getPageMaxCounts() {
		return pageMaxCounts;
	}
	public void setPageMaxCounts(int pageMaxCounts) {
		this.pageMaxCounts = pageMaxCounts;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getDbCounts() {
		return dbCounts;
	}
	public void setDbCounts(int dbCounts) {
		this.dbCounts = dbCounts;
	}
	
	private void init(){
		this.totalPages = this.pageMaxCounts / this.pageMaxCounts;
		if(this.pageMaxCounts > 0){
			//获取最大页数
			this.totalPages = this.totalPages > 0 ? this.totalPages : 1;
			//获取当前页数
			this.currentPage = this.startIndex / this.pageMaxCounts + 1;
		}
	}
	
	public void count(){
		//总页数
		int totalPageTemp = this.totalCounts / this.pageMaxCounts;
		int plus = (this.totalCounts % this.pageMaxCounts) == 0 ? 0 : 1;
		totalPageTemp = totalPageTemp + plus;
		if(totalPageTemp <= 0){
			totalPageTemp = 1;
		}
		this.totalPages = totalPageTemp;
		
		//设置当前页数
		if(this.totalPages < this.currentPage){
			this.currentPage = this.totalPages;
		}
		
		//当前页不小于1
		if(this.currentPage < 1){
			this.currentPage = 1;
		}
		
		//设置limit参数
		this.startIndex = (this.currentPage -1 ) * this.pageMaxCounts;
		this.dbCounts = this.pageMaxCounts;
	}
}
