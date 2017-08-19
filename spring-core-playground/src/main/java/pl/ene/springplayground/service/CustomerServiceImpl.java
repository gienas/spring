package pl.ene.springplayground.service;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	InvoiceService invoiceService;
	
	public CustomerServiceImpl(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	public Boolean payBill(String customerId, String paymentId) {
		invoiceService.getInvoice(customerId, paymentId);
		return null;
	}
	
	
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}
}
