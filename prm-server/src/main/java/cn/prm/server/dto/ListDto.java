package cn.prm.server.dto;

import java.util.List;

public class ListDto<T> extends BaseDto {
	private List<T> rows;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
