package pl.ene.springplayground.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	InvoiceService invoiceService;
	
	@Value("${app.name}")
	private String appName;
	
	public CustomerServiceImpl(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	public Boolean payBill(String customerId, String paymentId) {
		invoiceService.getInvoice(customerId, paymentId);
		System.out.println("App name = " + appName);
		return null;
	}
	
	
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}
}
