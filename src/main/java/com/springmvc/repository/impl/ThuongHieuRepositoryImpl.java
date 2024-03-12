package com.springmvc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springmvc.model.ThuongHieu;
import com.springmvc.repository.ThuongHieuRepository;

@Repository("thuongHieuRepository")
public class ThuongHieuRepositoryImpl extends JdbcDaoSupport implements ThuongHieuRepository {

	@Override
	public List<ThuongHieu> layDanhSachThuongHieu() {
		List<ThuongHieu> listThuongHieus = new ArrayList<ThuongHieu>();
		String sql = "SELECT Id, TenThuongHieu FROM THUONGHIEU";
		
		RowMapper<ThuongHieu> mapper = new RowMapper<ThuongHieu>() {
			
			@Override
			public ThuongHieu mapRow(ResultSet rs, int rowNum) throws SQLException {
				ThuongHieu t = new ThuongHieu();
				t.setId(rs.getInt("Id"));
				t.setTenThuongHieu(rs.getString("tenThuongHieu"));
				return t;
			}
		};
		
		try {
			listThuongHieus = this.getJdbcTemplate().query(sql, mapper);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return listThuongHieus;
	}

}
