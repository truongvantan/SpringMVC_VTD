package com.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.model.NguoiDung;
import com.springmvc.service.NguoiDungService;

@Controller
public class NguoiDungController {
	
	@Autowired
	private NguoiDungService nguoiDungService;
	
	@GetMapping(value = {"", "/", "/login"})
	public String showLogin(Model model, @RequestParam(value = "error", required = false) String error) {
		if ("1".equals(error)) {
			model.addAttribute("errorMessage", "Vui lòng đăng nhập để sử dụng hệ thống");
		}
		return "login";
	}
	
	@PostMapping("/checkLogin")
	public String checkLogin(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, Model model, HttpSession session) {
		NguoiDung n = nguoiDungService.layThongTinNguoiDung(nguoiDung);
		String sessionNguoiDung = null;
		if (n == null) {
			model.addAttribute("errorMessage", "Thông tin đăng nhập không chính xác");
			return "login";
		} else if ("admin".equals(n.getVaiTro())) {
			sessionNguoiDung = n.getTenDangNhap() + "(admin)";
			session.setAttribute("sessionNguoiDung", sessionNguoiDung);
			return "welcomeAdmin";
		} else {
			sessionNguoiDung = n.getTenDangNhap() + "(user)";
			session.setAttribute("sessionNguoiDung", sessionNguoiDung);
			return "welcomeUser";
		}
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/login?error=1";
	}
	
}
