package pl.graphdebt.shared;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Position {
	@Id Long id;
	private String name;
	private Double amount;
	private List<Key<Money>> money;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public List<Key<Money>> getMoney() {
		return money;
	}
	public void setMoney(List<Key<Money>> money) {
		this.money = money;
	}
	
	/*
	 * Business model
	 */
	
	
	/**
	 * @return all {@link Person} objects involved in a bill as a buyer of this position
	 */
	public Collection<Person> getInvolved() {
		if (money == null)
			return null;
		Collection<Key<Person>> involved = new HashSet<Key<Person>>();
		for (Money mon : ObjectifyService.ofy().load().keys(money).values()) {
			involved.add(mon.getPerson());
		}
		return ObjectifyService.ofy().load().keys(involved).values();
	}
}
