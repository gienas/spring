package pl.ene.springplayground.model;

public class PaymentStatus {

	private String status;
	
	public PaymentStatus() {
		// TODO Auto-generated constructor stub
	}
	
	public PaymentStatus(String status) {
		this.status = status;
	}
	
	public PaymentStatus(Boolean status) {
		this.status = Boolean.toString(status);
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
