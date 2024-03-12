package com.springmvc.model;

import org.springframework.stereotype.Component;

@Component
public class NguoiDung {
	private String tenDangNhap;
	private String matKhau;
	private String vaiTro;
	private int id;
	private String hoVaTen;
	private String sdt;
	private String diaChi;

	public NguoiDung() {
		super();
	}

	public NguoiDung(String tenDangNhap, String matKhau, String vaiTro) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
	}

	public NguoiDung(String tenDangNhap, String matKhau) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}

	public NguoiDung(String tenDangNhap, String matKhau, String vaiTro, int id, String hoVaTen, String sdt,
			String diaChi) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.id = id;
		this.hoVaTen = hoVaTen;
		this.sdt = sdt;
		this.diaChi = diaChi;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "NguoiDung [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", vaiTro=" + vaiTro + ", id=" + id
				+ ", hoVaTen=" + hoVaTen + ", sdt=" + sdt + ", diaChi=" + diaChi + "]";
	}

}
