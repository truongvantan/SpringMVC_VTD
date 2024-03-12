package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.common.ValidateCommon;
import com.springmvc.dto.SanPhamDTO;
import com.springmvc.model.SanPham;
import com.springmvc.repository.SanPhamRepository;
import com.springmvc.service.SanPhamService;

@Service("sanPhamService")
public class SanPhamServiceImpl implements SanPhamService {

	@Autowired
	private SanPhamRepository sanPhamRepository;

	public SanPhamServiceImpl() {
		super();
	}

	public SanPhamServiceImpl(SanPhamRepository sanPhamRepository) {
		super();
		this.sanPhamRepository = sanPhamRepository;
	}

	public SanPhamRepository getSanPhamRepository() {
		return sanPhamRepository;
	}

	public void setSanPhamRepository(SanPhamRepository sanPhamRepository) {
		this.sanPhamRepository = sanPhamRepository;
	}

	@Override
	public List<SanPhamDTO> layDanhSachSanPham() {
		return sanPhamRepository.layDanhSachSanPham();
	}

	@Override
	public String deleteProduct(String idProduct) {
		if (!ValidateCommon.isValidStringNumber(idProduct)) {
			return "Invalid idProduct";
		}
		return sanPhamRepository.deleteProduct(idProduct);
	}

	@Override
	public List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu) {
		return sanPhamRepository.layDanhSachSanPhamTheoThuongHieu(idThuongHieu);
	}

	@Override
	public String themSanPham(String tenSanPham, String idThuongHieu, String donGiaNhap, String donGiaBan,
			String linkHinhAnh, String moTa) {
		String validateMessage = ValidateCommon.validateFieldsProduct(tenSanPham, idThuongHieu, donGiaNhap, donGiaBan);
		if (!"No error".equals(validateMessage)) {
			return validateMessage;
		} else {
			String errorMessage = null;
			for (int i = 1; i <= 10; i++) {
				String returnedMessage = sanPhamRepository.themSanPham(tenSanPham, idThuongHieu, donGiaNhap, donGiaBan,
						linkHinhAnh, moTa);
				if ("Duplicate ID Error".equals(returnedMessage)) {
					errorMessage = "Duplicate ID Error";
					continue;
				} else if ("Conflict foreign key".equals(returnedMessage)) {
					errorMessage = "Conflict foreign key";
					break;
				} else if ("No error".equals(returnedMessage)) {
					errorMessage = "No error";
					break;
				}
			}

			return errorMessage;
		}

	}

	@Override
	public SanPham layThongTinSanPhamTheoId(String maSanPham) {
		if (!ValidateCommon.isValidStringNumber(maSanPham)) {
			return null;
		}
		return sanPhamRepository.layThongTinSanPhamTheoId(maSanPham);
	}

	@Override
	public String chinhSuaSanPham(String maSanPham, String tenSanPham, String idThuongHieu, String donGiaNhap,
			String donGiaBan, String linkHinhAnh, String moTa) {
		String validateMessage = ValidateCommon.validateFieldsProduct(tenSanPham, idThuongHieu, donGiaNhap, donGiaBan);
		if (!"No error".equals(validateMessage)) {
			return validateMessage;
		} else {
			return sanPhamRepository.chinhSuaSanPham(maSanPham, tenSanPham, idThuongHieu, donGiaNhap, donGiaBan,
					linkHinhAnh, moTa);
		}
	}

	@Override
	public List<SanPhamDTO> timKiemSanPham(String searchText) {
		return sanPhamRepository.timKiemSanPham(searchText);
	}

}
