package pl.ene.springplayground.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentStatus {

	public PaymentStatus(Boolean status) {
		this.status = Boolean.toString(status);
	}
	
	private String status;
	
}
