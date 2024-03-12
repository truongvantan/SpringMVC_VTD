package com.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.model.NguoiDung;
import com.springmvc.repository.NguoiDungRepository;
import com.springmvc.service.NguoiDungService;

@Service("nguoiDungService")
public class NguoiDungServiceImpl implements NguoiDungService {

	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	public NguoiDungServiceImpl() {
		super();
	}

	public NguoiDungRepository getNguoiDungRepository() {
		return nguoiDungRepository;
	}

	public void setNguoiDungRepository(NguoiDungRepository nguoiDungRepository) {
		this.nguoiDungRepository = nguoiDungRepository;
	}

	@Override
	public NguoiDung layThongTinNguoiDung(NguoiDung nguoiDung) {
		if ("".equals(nguoiDung.getTenDangNhap()) || "".equals(nguoiDung.getMatKhau())
				|| nguoiDung.getTenDangNhap() == null || nguoiDung.getMatKhau() == null) {
			return null;
		}
		return nguoiDungRepository.layThongTinNguoiDung(nguoiDung);
	}
}
