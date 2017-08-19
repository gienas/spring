package pl.ene.springplayground.service;

public interface CustomerService {

	Boolean payBill(String customerId, String paymentId);

	InvoiceService getInvoiceService();
}