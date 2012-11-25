package pl.graphdebt.shared;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Money {
	@Id Long id;
	private Double amount;
	private Key<Person> person;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Key<Person> getPerson() {
		return person;
	}
	public void setPerson(Key<Person> person) {
		this.person = person;
	}
	public Long getId() {
		return id;
	}
}
