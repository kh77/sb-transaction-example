package com.sm.controller;

import com.sm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/without-transaction")
	public String addUserWithoutTransaction() {
		return userService.saveUserWithoutTransactionAnnotation(1);
	}

	@GetMapping("/without-transaction-exception")
	public String addUserWithoutTransactionButExceptionInMessage() {
		return userService.saveUserWithoutTransactionAnnotation(2);
	}

	@GetMapping("/transaction-required")
	public String addUserWithTransactionRequired() {
		return userService.saveUserTransactionRequired(1);
	}

	@GetMapping("/transaction-required-exception")
	public String addUserWithTransactionRequiredException() {
		return userService.saveUserTransactionRequired(2);
	}


	@GetMapping("/transaction-required-new")
	public String addUserWithTransactionRequiredNew() {
		return userService.saveUserTransactionRequired(3);
	}

	@GetMapping("/transaction-required-new-exception")
	public String addUserWithTransactionRequiredNewException() {
		return userService.saveUserTransactionRequired(4);
	}


	@GetMapping("/transaction-support")
	public String addUserWithTransactionSupport() {
		return userService.saveUserTransactionRequired(5);
	}

	@GetMapping("/transaction-mandatory")
	public String addUserWithTransactionMandatory() {
		return userService.saveUserTransactionRequired(6);
	}




}
