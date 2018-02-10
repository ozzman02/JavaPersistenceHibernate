package entity;

import javax.persistence.Entity;

@Entity
public class Cat2 extends Animal2 {

	@Override
	public String makeNoise() {
		return "meow meow";
	}
	

}
