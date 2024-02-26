package pl.ene.springbootrestjpa.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
