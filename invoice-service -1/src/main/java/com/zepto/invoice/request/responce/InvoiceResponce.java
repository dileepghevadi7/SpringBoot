package com.zepto.invoice.request.responce;

public class InvoiceResponce {

	int id;
	String invId;
	String customerName;
	int gst;
	String stastus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvId() {
		return invId;
	}

	public void setInvId(String invId) {
		this.invId = invId;
	}

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
