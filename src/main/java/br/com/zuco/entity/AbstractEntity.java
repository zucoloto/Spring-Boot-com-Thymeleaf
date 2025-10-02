package br.com.zuco.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "id = " + id;
	}
	
}
