package com.zepto.invoice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.stereotype.Service;

import com.zepto.invoice.entity.InvoiceEntity;
import com.zepto.invoice.repositry.InvoiceRespositry;
import com.zepto.invoice.request.responce.InvoiceRequest;
import com.zepto.invoice.request.responce.InvoiceResponce;

@Service
public class InvoiceService {

	private final RedisCacheConfiguration cacheConfiguration;
	@Autowired
	private InvoiceRespositry invoiceRespositry;

	InvoiceService(RedisCacheConfiguration cacheConfiguration) {
		this.cacheConfiguration = cacheConfiguration;
	}

	// Create Invoice
	public InvoiceResponce createInvoice(InvoiceRequest invoiceRequest) {

		InvoiceEntity entity = new InvoiceEntity();

		entity.setCustomerName(invoiceRequest.getCustomerName());
		entity.setGst(invoiceRequest.getGst());
		entity.setStastus("PAID");
		entity.setInvId("INV1234567");

		entity = invoiceRespositry.save(entity);

		InvoiceResponce invoiceResponce = new InvoiceResponce();

		if (entity.getId() > 0) {

			invoiceResponce.setId(entity.getId());
			invoiceResponce.setInvId(entity.getInvId());
			invoiceResponce.setCustomerName(entity.getCustomerName());
			invoiceResponce.setGst(entity.getGst());
			invoiceResponce.setStastus(entity.getStastus());
		}

		return invoiceResponce;
	}

	// Redis cache key example:
	// invoice :: 0-10 invoice:: 1-10 invoice::2-10

	@Cacheable(value = " invoices", key = "#page + '-' + #size")

	// Get Invoices with Pagination

	public List<InvoiceResponce> getInvoices(int page, int size) {

		System.out.println(" Feching invoice from DATABASE ");

		Pageable pageable = PageRequest.of(page, size);

		Page<InvoiceEntity> result = invoiceRespositry.findAll(pageable);

		List<InvoiceResponce> response = new ArrayList<>();

		for (InvoiceEntity invoiceEntity : result.getContent()) {

			InvoiceResponce invoiceResponce = new InvoiceResponce();

			invoiceResponce.setId(invoiceEntity.getId());
			invoiceResponce.setInvId(invoiceEntity.getInvId());
			invoiceResponce.setCustomerName(invoiceEntity.getCustomerName());
			invoiceResponce.setGst(invoiceEntity.getGst());
			invoiceResponce.setStastus(invoiceEntity.getStastus());

			response.add(invoiceResponce);
		}

		return response;
	}
}