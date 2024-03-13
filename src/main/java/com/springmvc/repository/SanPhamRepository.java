package com.springmvc.repository;

import java.util.List;

import com.springmvc.dto.SanPhamDTO;
import com.springmvc.model.SanPham;

public interface SanPhamRepository {

	List<SanPhamDTO> layDanhSachSanPham();

	String deleteProduct(String idProduct);

	List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu);

	String themSanPham(String tenSanPham, String idThuongHieu, String donGiaNhap, String donGiaBan, String linkHinhAnh,
			String moTa);

	SanPham layThongTinSanPhamTheoId(String maSanPham);

	String chinhSuaSanPham(String maSanPham, String tenSanPham, String idThuongHieu, String donGiaNhap,
			String donGiaBan, String linkHinhAnh, String moTa);

	List<SanPhamDTO> timKiemSanPham(String searchText);

	int layTongSoTrang();

	List<SanPhamDTO> layDanhSachSanPham(int pageNumber);

	int layTongSoTrang(String idThuongHieu);

	List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu, int pageNumber);

	int layTongSoTrangTimKiem(String searchText);

	List<SanPhamDTO> timKiemSanPham(String searchText, int pageNumber);

}
