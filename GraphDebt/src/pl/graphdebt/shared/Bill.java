package pl.graphdebt.shared;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Bill {
    @Id Long id;
    private String name;
    List<Key<Position>> positions = new ArrayList<Key<Position>>();
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
    
    
}

//@Parent Key<Person> owner;
