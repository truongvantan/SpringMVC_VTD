package com.springmvc.dto;

public class SanPhamDTO {
	private int id;
	private String tenSanPham;
	private String tenThuongHieu;
	private double donGiaNhap;
	private double donGiaBan;
	private double soLuongTonKho;
	private String linkHinhAnh;
	private String moTa;

	public SanPhamDTO() {
		super();
	}

	public SanPhamDTO(int id, String tenSanPham, String tenThuongHieu, double donGiaNhap, double donGiaBan,
			double soLuongTonKho, String linkHinhAnh, String moTa) {
		super();
		this.id = id;
		this.tenSanPham = tenSanPham;
		this.tenThuongHieu = tenThuongHieu;
		this.donGiaNhap = donGiaNhap;
		this.donGiaBan = donGiaBan;
		this.soLuongTonKho = soLuongTonKho;
		this.linkHinhAnh = linkHinhAnh;
		this.moTa = moTa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getTenThuongHieu() {
		return tenThuongHieu;
	}

	public void setTenThuongHieu(String tenThuongHieu) {
		this.tenThuongHieu = tenThuongHieu;
	}

	public double getDonGiaNhap() {
		return donGiaNhap;
	}

	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}

	public double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public double getSoLuongTonKho() {
		return soLuongTonKho;
	}

	public void setSoLuongTonKho(double soLuongTonKho) {
		this.soLuongTonKho = soLuongTonKho;
	}

	public String getLinkHinhAnh() {
		return linkHinhAnh;
	}

	public void setLinkHinhAnh(String linkHinhAnh) {
		this.linkHinhAnh = linkHinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public String toString() {
		return "SanPhamDTO [id=" + id + ", tenSanPham=" + tenSanPham + ", tenThuongHieu=" + tenThuongHieu
				+ ", donGiaNhap=" + donGiaNhap + ", donGiaBan=" + donGiaBan + ", soLuongTonKho=" + soLuongTonKho
				+ ", linkHinhAnh=" + linkHinhAnh + ", moTa=" + moTa + "]";
	}

}
