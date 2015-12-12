package com.zooplus.challange.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zooplus.challange.domain.CurrencyData;
import com.zooplus.challange.hibernate.DataManager;
import com.zooplus.challange.pojo.ExchangeRate;
import com.zooplus.challange.util.SessionManager;

@Controller
public class MainController {
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		CurrencyData data = new CurrencyData();
		model.addAttribute("data", data);
		return "main";
	}

	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String doQuery(@ModelAttribute("data") @Valid CurrencyData data, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("data", data);
			model.put(BindingResult.class.getName() + ".data", result);
			return "main";
		}
		List<ExchangeRate> searchData = DataManager.getInstance().getCurrentRate(data.getCurrentCurrency(),
				data.getCurrentDate());
		if (searchData != null && searchData.size() > 0) {
			data.setRates(searchData);
		}
		return "main";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		SessionManager.getInstance().removeSession(request.getSession().getId());
		return "redirect:/login?logout";
	}

	@ModelAttribute("currencies")
	public List<String> initializeCurrencies() {
		return DataManager.getInstance().getCurrencies();
	}
}
