package com.zepto.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zepto.invoice.request.responce.InvoiceRequest;
import com.zepto.invoice.request.responce.InvoiceResponce;
import com.zepto.invoice.service.InvoiceService;

@RestController
@RequestMapping("invoice")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	

	@PostMapping("create")
	public ResponseEntity<InvoiceResponce> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
		
		
		InvoiceResponce response = invoiceService.createInvoice(invoiceRequest);
		

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

	@GetMapping("listInvoice")
	public ResponseEntity<List<InvoiceResponce>> getAllInvoice(@RequestParam int page, @RequestParam int size) 
	
	{
		
		List<InvoiceResponce> response = invoiceService.getInvoices(page, size);
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}
