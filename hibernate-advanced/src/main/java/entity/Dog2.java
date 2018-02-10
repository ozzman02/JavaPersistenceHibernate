package entity;

import javax.persistence.Entity;

@Entity
public class Dog2 extends Animal2 {

	@Override
	public String makeNoise() {
		return "woof woof";
	}

}
