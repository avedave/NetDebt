package pl.graphdebt.shared;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Person {
	@Id Long id;
	private String name;
}
