package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.model.ThuongHieu;
import com.springmvc.repository.ThuongHieuRepository;
import com.springmvc.service.ThuongHieuService;

@Service("thuongHieuService")
public class ThuongHieuServiceImpl implements ThuongHieuService {
	
	@Autowired
	private ThuongHieuRepository thuongHieuRepository;

	public ThuongHieuServiceImpl() {
		super();
	}

	public ThuongHieuServiceImpl(ThuongHieuRepository thuongHieuRepository) {
		super();
		this.thuongHieuRepository = thuongHieuRepository;
	}

	public ThuongHieuRepository getThuongHieuRepository() {
		return thuongHieuRepository;
	}

	public void setThuongHieuRepository(ThuongHieuRepository thuongHieuRepository) {
		this.thuongHieuRepository = thuongHieuRepository;
	}

	@Override
	public List<ThuongHieu> layDanhSachThuongHieu() {
		return thuongHieuRepository.layDanhSachThuongHieu();
	}
	
	

}
