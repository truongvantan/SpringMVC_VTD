package com.springmvc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springmvc.common.Page;
import com.springmvc.common.ValidateCommon;
import com.springmvc.dto.SanPhamDTO;
import com.springmvc.model.SanPham;
import com.springmvc.repository.SanPhamRepository;

@Repository("sanPhamRepository")
public class SanPhamRepositoryImpl extends JdbcDaoSupport implements SanPhamRepository {

	private static final class SanPhamDTOMapper implements RowMapper<SanPhamDTO> {

		@Override
		public SanPhamDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SanPhamDTO sp = new SanPhamDTO();
			sp.setId(rs.getInt("Id"));
			sp.setTenSanPham(rs.getString("TenSanPham"));
			sp.setTenThuongHieu(rs.getString("TenThuongHieu"));
			sp.setDonGiaNhap(rs.getDouble("DonGiaNhap"));
			sp.setDonGiaBan(rs.getDouble("DonGiaBan"));
			sp.setLinkHinhAnh(rs.getString("LinkHinhAnh"));
			sp.setMoTa(rs.getString("MoTa"));
			return sp;
		}

	}

	private static final class SanPhamMapper implements RowMapper<SanPham> {
		@Override
		public SanPham mapRow(ResultSet rs, int rowNum) throws SQLException {
			SanPham sp = new SanPham();
			sp.setId(rs.getInt("Id"));
			sp.setTenSanPham(rs.getString("TenSanPham"));
			sp.setThuongHieu(rs.getInt("ThuongHieu"));
			sp.setDonGiaNhap(rs.getDouble("DonGiaNhap"));
			sp.setDonGiaBan(rs.getDouble("DonGiaBan"));
			sp.setLinkHinhAnh(rs.getString("LinkHinhAnh"));
			sp.setMoTa(rs.getString("MoTa"));
			return sp;
		}

	}

	@Override
	public List<SanPhamDTO> layDanhSachSanPham() {
		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
		String sql = """
				SELECT SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
				FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH
				ON SP.ThuongHieu = TH.Id
				""";
		try {
			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return listSanPhams;
	}

	@Override
	public List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu) {
		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
		int thuongHieu = 0;
		if (ValidateCommon.isValidStringIntegerNumber(idThuongHieu)) {
			thuongHieu = Integer.valueOf(idThuongHieu);
		}

		String sql = """
				SELECT SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
				FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH
				ON SP.ThuongHieu = TH.Id
				WHERE TH.Id = ?
				""";

		try {
			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), thuongHieu);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listSanPhams;
	}

	@Override
	public String deleteProduct(String idProduct) {
		String message = "";
		String sql = "DELETE FROM SANPHAM WHERE Id = ?";
		try {
			int x = this.getJdbcTemplate().update(sql, Integer.valueOf(idProduct));
			System.out.println("Đã xóa " + x + " record.");
			message = "No error";
		} catch (DataAccessException e) {
			e.printStackTrace();
			message = "Unknown error";
		}
		return message;
	}

	@Override
	public String themSanPham(String tenSanPham, String idThuongHieu, String donGiaNhap, String donGiaBan,
			String linkHinhAnh, String moTa) {
		String message = "";
		String sql = """
				INSERT INTO SANPHAM (TenSanPham, ThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa)
				VALUES (?,?,?,?,?,?)
				""";
		try {
			int x = this.getJdbcTemplate().update(sql, tenSanPham, Integer.valueOf(idThuongHieu),
					Double.valueOf(donGiaNhap), Double.valueOf(donGiaBan), linkHinhAnh, moTa);
			System.out.println("Đã insert " + x + " record");
			message = "No error";
		} catch (DataAccessException e) {
			e.printStackTrace();
			String errorMessage = e.getMessage();
			if (errorMessage.contains("The duplicate key value is")) {
				message = "Duplicate ID Error";
			} else if (errorMessage.contains("conflicted with the FOREIGN KEY constraint")) {
				message = "Conflict foreign key";
			}
		}

		return message;
	}

	@Override
	public SanPham layThongTinSanPhamTheoId(String maSanPham) {
		SanPham sp = null;
		String sql = """
					SELECT Id, TenSanPham, ThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
					FROM SANPHAM
					WHERE Id = ?
				""";
		try {
			sp = this.getJdbcTemplate().queryForObject(sql, new SanPhamMapper(), Integer.valueOf(maSanPham));
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		return sp;
	}

	@Override
	public String chinhSuaSanPham(String maSanPham, String tenSanPham, String idThuongHieu, String donGiaNhap,
			String donGiaBan, String linkHinhAnh, String moTa) {
		String message = "";
		String sql = """
				UPDATE SANPHAM
				SET TenSanPham = ?,
					ThuongHieu = ?,
					DonGiaNhap = ?,
					DonGiaBan = ?,
					LinkHinhAnh = ?,
					MoTa = ?
				WHERE Id = ?
				""";
		try {
			int x = this.getJdbcTemplate().update(sql, tenSanPham, Integer.valueOf(idThuongHieu),
					Double.valueOf(donGiaNhap), Double.valueOf(donGiaBan), linkHinhAnh, moTa,
					Integer.valueOf(maSanPham));
			System.out.println("Đã chỉnh sửa " + x + " record");
			message = "No error";
		} catch (DataAccessException e) {
			e.printStackTrace();
			String errorMessage = e.getMessage();
			if (errorMessage.contains("The duplicate key value is")) {
				message = "Duplicate ID Error";
			} else if (errorMessage.contains("conflicted with the FOREIGN KEY constraint")) {
				message = "Conflict foreign key";
			}
		}

		return message;
	}

	@Override
	public List<SanPhamDTO> timKiemSanPham(String searchText) {
		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
		String sql = """
				SELECT SP.Id, TenSanPham, TH.TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
				FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH ON SP.ThuongHieu = TH.Id
				WHERE TenSanPham LIKE ? OR TenThuongHieu LIKE ? OR MoTa LIKE ? OR SP.Id = ? OR DonGiaNhap = ? OR DonGiaBan = ?
				""";
		String tenSanPham = "%" + searchText + "%";
		String tenThuongHieu = "%" + searchText + "%";
		String moTa = "%" + searchText + "%";
		int id = ValidateCommon.isValidStringIntegerNumber(searchText) ? Integer.valueOf(searchText) : -1;
		double donGiaNhap = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;
		double donGiaBan = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;

		listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), tenSanPham, tenThuongHieu, moTa, id,
				donGiaNhap, donGiaBan);

		return listSanPhams;
	}

	@Override
	public int layTongSoTrang() {
		int totalPageNumber = 1;
		String sql = "SELECT COUNT(Id) AS Total FROM SANPHAM";

		try {
			totalPageNumber = this.getJdbcTemplate().queryForObject(sql, Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 1;
		}
		return (int) Math.ceil((double)totalPageNumber / Page.NUM_OF_ELEMENT.value);
	}

//	@Override
//	public List<SanPhamDTO> layDanhSachSanPham(int pageNumber) {
//		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
//
//		String sql = """
//				SELECT * FROM
//					(
//						SELECT RowNum = ROW_NUMBER() OVER (ORDER BY SP.Id), SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
//						FROM SANPHAM SP
//						INNER JOIN THUONGHIEU TH ON SP.ThuongHieu = TH.Id
//					) AS tempTable
//				WHERE RowNum > (? * (? - 1)) AND RowNum <= (? * (? - 1)) + ?
//				ORDER BY Id
//				""";
//		try {
//			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(),
//					String.valueOf(Page.NUM_OF_ELEMENT.value), String.valueOf(pageNumber),
//					String.valueOf(Page.NUM_OF_ELEMENT.value), String.valueOf(pageNumber),
//					String.valueOf(Page.NUM_OF_ELEMENT.value));
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//
//		return listSanPhams;
//	}

	@Override
	public List<SanPhamDTO> layDanhSachSanPham(int pageNumber) {
		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();

		String sql = """
				SELECT SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH ON TH.Id = SP.ThuongHieu
				ORDER BY SP.Id
				OFFSET ? ROWS
				FETCH NEXT ? ROWS ONLY
			""";
		int currentRow = (Integer.valueOf(pageNumber) - 1) * Page.NUM_OF_ELEMENT.value;
		try {
			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), currentRow,
					Page.NUM_OF_ELEMENT.value);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return listSanPhams;
	}

	@Override
	public int layTongSoTrang(String idThuongHieu) {
		int totalPageNumber = 1;
		int thuongHieu = 0;
		if (ValidateCommon.isValidStringIntegerNumber(idThuongHieu)) {
			thuongHieu = Integer.valueOf(idThuongHieu);
		}

		String sql = "SELECT COUNT(Id) AS Total FROM SANPHAM WHERE THUONGHIEU = ?";
		try {
			totalPageNumber = this.getJdbcTemplate().queryForObject(sql, Integer.class, thuongHieu);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 1;
		}
		return (int) Math.ceil((double)totalPageNumber / Page.NUM_OF_ELEMENT.value);
	}

//	@Override
//	public List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu, int pageNumber) {
//		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
//		int thuongHieu = 0;
//		if (ValidateCommon.isValidStringIntegerNumber(idThuongHieu)) {
//			thuongHieu = Integer.valueOf(idThuongHieu);
//		}
//
//		String sql = """
//				SELECT * FROM
//					(
//						SELECT RowNum = ROW_NUMBER() OVER (ORDER BY SP.Id), SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
//						FROM SANPHAM SP
//						INNER JOIN THUONGHIEU TH ON SP.ThuongHieu = TH.Id
//						WHERE SP.ThuongHieu = ?
//					) AS tempTable
//				WHERE RowNum > (? * (? - 1)) AND RowNum <= (? * (? - 1)) + ?
//				ORDER BY Id
//				""";
//		try {
//			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), thuongHieu,
//					String.valueOf(Page.NUM_OF_ELEMENT.value), String.valueOf(pageNumber),
//					String.valueOf(Page.NUM_OF_ELEMENT.value), String.valueOf(pageNumber),
//					String.valueOf(Page.NUM_OF_ELEMENT.value));
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//
//		return listSanPhams;
//	}
	
	@Override
	public List<SanPhamDTO> layDanhSachSanPhamTheoThuongHieu(String idThuongHieu, int pageNumber) {
		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
		int thuongHieu = 0;
		if (ValidateCommon.isValidStringIntegerNumber(idThuongHieu)) {
			thuongHieu = Integer.valueOf(idThuongHieu);
		}

		String sql = """
				SELECT SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH ON TH.Id = SP.ThuongHieu
				WHERE TH.Id = ?
				ORDER BY SP.Id
				OFFSET ? ROWS
				FETCH NEXT ? ROWS ONLY
			""";
		int currentRow = (Integer.valueOf(pageNumber) - 1) * Page.NUM_OF_ELEMENT.value;
		try {
			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), thuongHieu, currentRow, Page.NUM_OF_ELEMENT.value);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return listSanPhams;
	}

	@Override
	public int layTongSoTrangTimKiem(String searchText) {
		int totalPageNumber = 1;
		String sql = """
				SELECT COUNT(SP.Id) AS TongSoDong
				FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH ON SP.ThuongHieu = TH.Id
				WHERE TenSanPham LIKE ? OR TenThuongHieu LIKE ? OR MoTa LIKE ? OR SP.Id = ? OR DonGiaNhap = ? OR DonGiaBan = ?
				""";

		String tenSanPham = "%" + searchText + "%";
		String tenThuongHieu = "%" + searchText + "%";
		String moTa = "%" + searchText + "%";
		int id = ValidateCommon.isValidStringIntegerNumber(searchText) ? Integer.valueOf(searchText) : -1;
		double donGiaNhap = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;
		double donGiaBan = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;

		try {
			totalPageNumber = this.getJdbcTemplate().queryForObject(sql, Integer.class, tenSanPham, tenThuongHieu, moTa,
					id, donGiaNhap, donGiaBan);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 1;
		}

		return (int) Math.ceil((double)totalPageNumber / Page.NUM_OF_ELEMENT.value);
	}

//	@Override
//	public List<SanPhamDTO> timKiemSanPham(String searchText, int pageNumber) {
//		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
//		String sql = """
//				SELECT * FROM
//					(
//						SELECT RowNum = ROW_NUMBER() OVER (ORDER BY SP.Id), SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa
//						FROM SANPHAM SP
//						INNER JOIN THUONGHIEU TH ON SP.ThuongHieu = TH.Id
//						WHERE TenSanPham LIKE ? OR TenThuongHieu LIKE ? OR MoTa LIKE ? OR SP.Id = ? OR DonGiaNhap = ? OR DonGiaBan = ?
//					) AS tempTable
//				WHERE RowNum > (? * (? - 1)) AND RowNum <= (? * (? - 1)) + ?
//				ORDER BY Id
//				""";
//
//		String tenSanPham = "%" + searchText + "%";
//		String tenThuongHieu = "%" + searchText + "%";
//		String moTa = "%" + searchText + "%";
//		int id = ValidateCommon.isValidStringIntegerNumber(searchText) ? Integer.valueOf(searchText) : -1;
//		double donGiaNhap = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;
//		double donGiaBan = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;
//
//		try {
//			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), tenSanPham, tenThuongHieu, moTa,
//					id, donGiaNhap, donGiaBan, String.valueOf(Page.NUM_OF_ELEMENT.value), String.valueOf(pageNumber),
//					String.valueOf(Page.NUM_OF_ELEMENT.value), String.valueOf(pageNumber),
//					String.valueOf(Page.NUM_OF_ELEMENT.value));
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//
//		return listSanPhams;
//	}
	
	@Override
	public List<SanPhamDTO> timKiemSanPham(String searchText, int pageNumber) {
		List<SanPhamDTO> listSanPhams = new ArrayList<SanPhamDTO>();
		String sql = """
				SELECT SP.Id, TenSanPham, TenThuongHieu, DonGiaNhap, DonGiaBan, LinkHinhAnh, MoTa FROM SANPHAM SP
				INNER JOIN THUONGHIEU TH ON TH.Id = SP.ThuongHieu
				WHERE TenSanPham LIKE ? OR TenThuongHieu LIKE ? OR MoTa LIKE ? OR SP.Id = ? OR DonGiaNhap = ? OR DonGiaBan = ?
				ORDER BY SP.Id
				OFFSET ? ROWS
				FETCH NEXT ? ROWS ONLY
			""";

		String tenSanPham = "%" + searchText + "%";
		String tenThuongHieu = "%" + searchText + "%";
		String moTa = "%" + searchText + "%";
		int id = ValidateCommon.isValidStringIntegerNumber(searchText) ? Integer.valueOf(searchText) : -1;
		double donGiaNhap = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;
		double donGiaBan = ValidateCommon.isValidStringNumber(searchText) ? Double.valueOf(searchText) : -1;
		int currentRow = (Integer.valueOf(pageNumber) - 1) * Page.NUM_OF_ELEMENT.value;
		
		try {
			listSanPhams = this.getJdbcTemplate().query(sql, new SanPhamDTOMapper(), tenSanPham, tenThuongHieu, moTa,
					id, donGiaNhap, donGiaBan, currentRow, Page.NUM_OF_ELEMENT.value);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return listSanPhams;
	}

}
