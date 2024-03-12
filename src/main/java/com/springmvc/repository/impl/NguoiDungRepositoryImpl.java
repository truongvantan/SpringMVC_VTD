package com.springmvc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springmvc.model.NguoiDung;
import com.springmvc.repository.NguoiDungRepository;

@Repository("nguoiDungRepository")
public class NguoiDungRepositoryImpl extends JdbcDaoSupport implements NguoiDungRepository {
	
	@Override
	public NguoiDung layThongTinNguoiDung(NguoiDung nguoiDung) {
		NguoiDung n = null;
		String sql = "SELECT TenDangNhap, MatKhau, VaiTro FROM NGUOIDUNG WHERE TenDangNhap = ? AND MatKhau = ?";

		RowMapper<NguoiDung> mapper = new RowMapper<NguoiDung>() {

			@Override
			public NguoiDung mapRow(ResultSet rs, int rowNum) throws SQLException {
				NguoiDung n = new NguoiDung();
				n.setTenDangNhap(rs.getString("TenDangNhap"));
				n.setVaiTro(rs.getString("VaiTro"));
				return n;
			}
		};

		try {
			n = this.getJdbcTemplate().queryForObject(sql, mapper, nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}

		return n;
	}

}
