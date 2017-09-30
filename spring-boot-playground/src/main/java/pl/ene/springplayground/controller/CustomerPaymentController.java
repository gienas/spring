package pl.ene.springplayground.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.ene.springplayground.model.PaymentStatus;
import pl.ene.springplayground.service.CustomerService;


@Controller
public class CustomerPaymentController {

	private CustomerService customerService;

	public CustomerPaymentController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@RequestMapping(path="/pay", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PaymentStatus pay( String customerId, String paymentId ) {	
		Boolean ps =  customerService.payBill(customerId, paymentId);
		return new PaymentStatus(ps);
	}
	
}
