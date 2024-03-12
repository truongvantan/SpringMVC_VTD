package com.springmvc.service;

import java.util.List;

import com.springmvc.dto.SanPhamDTO;
import com.springmvc.model.SanPham;

public interface SanPhamService {

	List<SanPhamDTO> layDanhSachSanPham();

	String deleteProduct(String idProduct);

	List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu);

	String themSanPham(String tenSanPham, String idThuongHieu, String donGiaNhap, String donGiaBan, String linkHinhAnh,
			String moTa);

	SanPham layThongTinSanPhamTheoId(String maSanPham);

	String chinhSuaSanPham(String maSanPham, String tenSanPham, String idThuongHieu, String donGiaNhap,
			String donGiaBan, String linkHinhAnh, String moTa);

	List<SanPhamDTO> timKiemSanPham(String searchText);

}
