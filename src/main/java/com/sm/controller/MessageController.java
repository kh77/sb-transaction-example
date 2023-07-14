package com.sm.controller;

import com.sm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/*
	it will create new transaction
	 */
	@GetMapping("/support-transaction")
	public String addMessage() {
		return messageService.saveMessageWithSupportTransaction();
	}

	/*
	it will be saved without adding transaction annotation
	 */
	@GetMapping("/without-transaction")
	public String addMessageWithoutTransaction() {
		return messageService.saveMessageWithoutTransaction(1);
	}

}
