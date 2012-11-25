package pl.graphdebt.shared;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Money {
	@Id Long id;
	private Double amount;
	private Key<Person> person;
}
