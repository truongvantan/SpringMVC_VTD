package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.dto.SanPhamDTO;
import com.springmvc.model.SanPham;
import com.springmvc.model.ThuongHieu;
import com.springmvc.service.SanPhamService;
import com.springmvc.service.ThuongHieuService;

@Controller
@RequestMapping("/product")
public class SanPhamController {

	@Autowired
	private SanPhamService sanPhamService;
	@Autowired
	private ThuongHieuService thuongHieuService;

	@RequestMapping("/show")
	public String showProductList(Model model, HttpSession session,
			@RequestParam(value = "idThuongHieu", required = false) String idThuongHieu,
			@RequestParam(value = "page", required = false) String page) {
		session.removeAttribute("searchText");
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			List<ThuongHieu> listThuongHieus = thuongHieuService.layDanhSachThuongHieu();
			List<SanPhamDTO> listSanPhams = null;
			if ("".equals(idThuongHieu) || idThuongHieu == null) {
				listSanPhams = sanPhamService.layDanhSachSanPham();
			} else {
				listSanPhams = sanPhamService.layDanhSachSanPhamTheoThuongHieu(idThuongHieu);
			}

			model.addAttribute("listThuongHieus", listThuongHieus);
			model.addAttribute("listSanPhams", listSanPhams);
			return "productList";
		}
	}

	@GetMapping("/delete")
	public String deleteProduct(Model model, HttpSession session,
			@RequestParam(value = "idProduct", required = false) String idProduct) {
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			String message = sanPhamService.deleteProduct(idProduct);
			if ("No error".equals(message)) {
				model.addAttribute("successMessage", "Xóa sản phẩm thành công");
			} else if ("Invalid idProduct".equals(message)) {
				model.addAttribute("errorMessage", "Mã sản phẩm không hợp lệ");
			} else {
				model.addAttribute("errorMessage", "Đã xảy ra lỗi không xác định. Vui lòng thử lại");
			}
			return "forward:/product/show";
		}

	}

	@RequestMapping("/showAdd")
	public String showInsertProduct(HttpSession session, Model model) {
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			List<ThuongHieu> listThuongHieus = thuongHieuService.layDanhSachThuongHieu();
			model.addAttribute("listThuongHieus", listThuongHieus);
			return "createProduct";
		}
	}

	@PostMapping("/add")
	public String insertProduct(Model model, HttpSession session, @RequestParam("tenSanPham") String tenSanPham,
			@RequestParam("thuongHieu") String idThuongHieu, @RequestParam("donGiaNhap") String donGiaNhap,
			@RequestParam("donGiaBan") String donGiaBan, @RequestParam("linkHinhAnh") String linkHinhAnh,
			@RequestParam("moTa") String moTa) {
		System.out.println(
				tenSanPham + "," + idThuongHieu + "," + donGiaNhap + "," + donGiaBan + "," + linkHinhAnh + "," + moTa);
		String errorMessage = sanPhamService.themSanPham(tenSanPham, idThuongHieu, donGiaNhap, donGiaBan, linkHinhAnh,
				moTa);
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			if ("No error".equals(errorMessage)) {
				model.addAttribute("successMessage", "Thêm sản phẩm thành công");
				return "forward:/product/show";
			} else if ("Duplicate ID Error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Mã sản phẩm đã tồn tại. Vui lòng thử lại");
			} else if ("Conflict foreign key".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Mã thương hiệu không tồn tại. Vui lòng chọn đúng thương hiệu");
			} else if ("Required fields error".equals(errorMessage)) {
				model.addAttribute("errorMessage",
						"Lỗi chưa nhập Tên sản phẩm, Thương hiệu, Đơn giá nhập, Đơn giá bán!");
			} else if ("Invalid idThuongHieu error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Id Thương hiệu phải là số");
			} else if ("Invalid donGiaNhap error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Đơn giá nhập phải là số");
			} else if ("Invalid donGiaBan error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Đơn giá bán phải là số");
			} else {
				model.addAttribute("errorMessage", "Xảy ra lỗi không xác định, Vui lòng thử lại");
			}
			return "forward:/product/showAdd";
		}
	}

	@RequestMapping("/showEdit")
	public String showEditProduct(HttpSession session, Model model,
			@RequestParam(value = "idProduct", required = false) String maSanPham) {
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			System.out.println("idProduct edit:" + maSanPham);
			SanPham sp = sanPhamService.layThongTinSanPhamTheoId(maSanPham);
			if (sp == null) {
				model.addAttribute("errorMessage", "Không thể chỉnh sửa sản phẩm. Sản phẩm không tồn tại");
				return "forward:/product/show";
			} else {
				List<ThuongHieu> listThuongHieus = thuongHieuService.layDanhSachThuongHieu();
				model.addAttribute("listThuongHieus", listThuongHieus);
				model.addAttribute("sanPham", sp);
				return "editProduct";
			}
		}
	}

	@PostMapping("/edit")
	public String editProduct(@RequestParam("maSanPham") String maSanPham,
			@RequestParam("tenSanPham") String tenSanPham, @RequestParam("thuongHieu") String idThuongHieu,
			@RequestParam("donGiaNhap") String donGiaNhap, @RequestParam("donGiaBan") String donGiaBan,
			@RequestParam("linkHinhAnh") String linkHinhAnh, @RequestParam("moTa") String moTa, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		System.out.println(maSanPham+","+
				tenSanPham + "," + idThuongHieu + "," + donGiaNhap + "," + donGiaBan + "," + linkHinhAnh + "," + moTa);
		
		String errorMessage = sanPhamService.chinhSuaSanPham(maSanPham, tenSanPham, idThuongHieu, donGiaNhap, donGiaBan,
				linkHinhAnh, moTa);
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			if ("No error".equals(errorMessage)) {
				model.addAttribute("successMessage", "Chỉnh sửa sản phẩm thành công");
				return "forward:/product/show";
			} else if ("Duplicate ID Error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Mã sản phẩm đã tồn tại. Vui lòng thử lại");
			} else if ("Conflict foreign key".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Mã thương hiệu không tồn tại. Vui lòng chọn đúng thương hiệu");
			} else if ("Required fields error".equals(errorMessage)) {
				model.addAttribute("errorMessage",
						"Lỗi chưa nhập Tên sản phẩm, Thương hiệu, Đơn giá nhập, Đơn giá bán!");
			} else if ("Invalid idThuongHieu error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Id Thương hiệu phải là số");
			} else if ("Invalid donGiaNhap error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Đơn giá nhập phải là số");
			} else if ("Invalid donGiaBan error".equals(errorMessage)) {
				model.addAttribute("errorMessage", "Đơn giá bán phải là số");
			} else {
				model.addAttribute("errorMessage", "Xảy ra lỗi không xác định, Vui lòng thử lại");
			}
			return "forward:/product/showEdit?idProduct=" + maSanPham;
		}
	}
	
	@PostMapping("/search")
	public String searchProduct(HttpSession session, Model model, @RequestParam(value = "searchText", required = false) String searchText) {
		if (session.getAttribute("sessionNguoiDung") == null) {
			return "redirect:/login?error=1";
		} else {
			if (searchText == null) {
				searchText = (String)session.getAttribute("searchProductText");
			}
			List<ThuongHieu> listThuongHieus = thuongHieuService.layDanhSachThuongHieu();
			List<SanPhamDTO> listSanPhams = sanPhamService.timKiemSanPham(searchText);
			model.addAttribute("listThuongHieus", listThuongHieus);
			model.addAttribute("listSanPhams", listSanPhams);
			session.setAttribute("searchText", searchText);
			
			return "productList";
		}
	}
}
