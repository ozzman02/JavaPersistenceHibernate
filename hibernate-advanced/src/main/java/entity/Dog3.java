package entity;

import javax.persistence.Entity;

@Entity
public class Dog3 extends Animal3 {

	@Override
	public String makeNoise() {
		return "woof woof";
	}

}
