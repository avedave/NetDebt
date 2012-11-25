package pl.graphdebt.shared;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Money {
	@Id Long id;
	private Double amount;
}
