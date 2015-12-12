package com.zooplus.challange.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zooplus.challange.domain.CurrencyData;
import com.zooplus.challange.hibernate.DataManager;
import com.zooplus.challange.property.PropertiesUtils;
import com.zooplus.challange.util.SessionManager;
import com.zooplus.challange.util.TextUtils;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, ModelMap model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String errorCode = DataManager.getInstance().validateUser(username, password);
		if (TextUtils.isNotEmptyTrimmedStr(errorCode)) {
			model.addAttribute("error", PropertiesUtils.getInstance().getProperty(errorCode, "Login error"));
			return "login";
		}
		SessionManager.getInstance().addSession(request.getSession().getId());
		CurrencyData data = new CurrencyData();
		data.setRates(DataManager.getInstance()
				.getRates(PropertiesUtils.getInstance().getPropertyAsInteger("cc.querySize", 10)));
		model.addAttribute("data", data);
		model.addAttribute("currencies", DataManager.getInstance().getCurrencies());
		return "main";
	}
}
