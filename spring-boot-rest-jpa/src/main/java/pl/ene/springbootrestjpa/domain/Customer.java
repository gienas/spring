package pl.ene.springbootrestjpa.domain;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name="customer")
public class Customer {

	public Customer() {
		// TODO Auto-generated constructor stubbbbb
	}
	

	@Id
	@GeneratedValue(generator="customer_seq")
	@SequenceGenerator(name="customer_seq",sequenceName="customer_seq", allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Item> items;
	
}
