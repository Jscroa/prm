package cn.prm.server.dto;

import java.util.List;

public class ListDto<T> extends BaseDto {
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
