package com.hcycom.jhipster.domain.tool;

public class Page {

	/**
	 * 初始页数或传入页数
	 */
	private int pageIndex = 1; 

	/**
	 * 每页显示数据库的记录数
	 */
	private int pageSize = 5;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 分页后的总页数
	 */
	private int totalPageCount; 

	/**
	 * 数据开始位置
	 */
	private int startPos; 
	
	private int totalPosCount;

	
	
	

	public int getTotalPosCount() {
		return totalPosCount;
	}

	public void setTotalPosCount(int totalPosCount) {
		this.totalPosCount = totalPosCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	
	/**
	 * 通过pageNow、pageSize、totalCount计算出其他数值
	 * @param page
	 * @return
	 */
	public Page initPage(Page page) {
			int totalPageCount = page.getTotalCount() / page.getPageSize();
			int a= (page.getTotalCount()%page.getPageSize()==0) ? totalPageCount:totalPageCount+1;
			page.setTotalPageCount(a);
			
			a=(page.getPageIndex() - 1) * page.getPageSize();
			page.setStartPos(a);
			
			page.setTotalPosCount(page.getStartPos()+page.getPageSize());
			
			return page;
	}

	@Override
	public String toString() {
		return "Page [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPageCount=" + totalPageCount + ", startPos=" + startPos + ", totalPosCount=" + totalPosCount
				+ "]";
	}

	


}
