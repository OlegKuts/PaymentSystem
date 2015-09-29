package ua.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.repository.interfaces.UserRepository;

@Controller
public class AccountController {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PaymentRepository paymentRepository;

	@RequestMapping("/useraccount/{userid}")
	public String showUserAccount(@PathVariable("userid") Long userid,
			Model model) {
		User user = userRepository.find(userid);
		Account account = user.getAccount();
		List<Payment> list = account.getPayers();
		model.addAttribute("user", user);
		model.addAttribute(account);
		model.addAttribute("list", list);
		return "userprofile";
	}
}
