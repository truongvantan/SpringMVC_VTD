package com.springmvc.model;

import org.springframework.stereotype.Component;

@Component
public class ThuongHieu {
	private int id;
	private String tenThuongHieu;

	public ThuongHieu() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenThuongHieu() {
		return tenThuongHieu;
	}

	public void setTenThuongHieu(String tenThuongHieu) {
		this.tenThuongHieu = tenThuongHieu;
	}

	@Override
	public String toString() {
		return "ThuongHieu [id=" + id + ", tenThuongHieu=" + tenThuongHieu + "]";
	}

}
