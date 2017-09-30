package pl.ene.springplayground.service;

import org.springframework.stereotype.Component;

@Component
public class InvoiceServiceImp implements InvoiceService  {

	/* (non-Javadoc)
	 * @see pl.ene.springplayground.service.InvoiceService#getInvoice(java.lang.String)
	 */
	@Override
	public String getInvoice( String customerID, String paymantId) {
		System.out.println("getInvoice call");
		return "";
	}
	
}
