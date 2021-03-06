package pl.ene.springbootrestjpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="item")
public class Item {

	public Item() {
		//comment
	}

	@Id
	@GeneratedValue(generator="item_seq")
	@SequenceGenerator(name="item_seq",sequenceName="item_seq", allocationSize=1)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}
