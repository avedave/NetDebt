package pl.graphdebt.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Bill {
    @Id Long id;
    private String name;
    private List<Key<Position>> positions = new ArrayList<Key<Position>>();
    private List<Key<Money>> payers = new ArrayList<Key<Money>>();
    
	public List<Key<Position>> getPositions() {
		return positions;
	}
	public void setPositions(List<Key<Position>> positions) {
		this.positions = positions;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	/*
	 * Business model
	 */
	
	/**
	 * @return all {@link Person} objects involved in a bill, either as a payer or as a buyer
	 */
	public Collection<Person> getInvolved() {
		if (positions == null)
			return null;
		Collection<Person> involved = new HashSet<Person>();
		for (Position pos : ObjectifyService.ofy().load().keys(positions).values()) {
			involved.addAll(pos.getInvolved());
		}
		Collection<Key<Person>> payerKeys = new HashSet<Key<Person>>();
		for (Money mon : ObjectifyService.ofy().load().keys(payers).values()) {
			payerKeys.add(mon.getPerson());
		}
		involved.addAll(ObjectifyService.ofy().load().keys(payerKeys).values());
		return involved;
	}
}

//@Parent Key<Person> owner;
