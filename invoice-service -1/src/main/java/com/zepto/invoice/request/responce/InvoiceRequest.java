package com.zepto.invoice.request.responce;

public class InvoiceRequest {

	
	String customerName;
	int gst;
	String stastus;

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		this.gst = gst;
	}

	public String getStastus() {
		return stastus;
	}

	public void setStastus(String stastus) {
		this.stastus = stastus;
	}

}
