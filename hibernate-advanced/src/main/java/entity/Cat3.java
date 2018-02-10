package entity;

import javax.persistence.Entity;

@Entity
public class Cat3 extends Animal3 {

	@Override
	public String makeNoise() {
		return "meow meow";
	}
	

}
