package cn.prm.server.dto;

public class PageDto<T> extends ListDto<T> {
	
	private int currentPage;
	private int total;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
