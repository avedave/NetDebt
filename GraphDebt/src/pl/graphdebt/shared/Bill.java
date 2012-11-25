package pl.graphdebt.shared;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Bill {
    @Id Long id;
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
    
    
}

//@Parent Key<Person> owner;
