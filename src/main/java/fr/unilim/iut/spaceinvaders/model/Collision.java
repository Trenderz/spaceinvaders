package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	
	public boolean detecterCollisionY(Sprite premier, Sprite second) {
		boolean collision = false;
		if (premier.ordonneeLaPlusHaute() >= second.ordonneeLaPlusBasse())
					collision = true;
		if (premier.ordonneeLaPlusBasse() <= second.ordonneeLaPlusHaute())
			collision = true;
		
		return collision;
	}

	public boolean detecterCollisionX(Sprite premier, Sprite second) {
		boolean collision = false;
		
			if (second.estAbscisseCouverte(i))
					collision = true;
		return collision;
	}

	public boolean detecterCollision(Sprite premier, Sprite second) {
		return detecterCollisionX(premier, second) && detecterCollisionY(premier, second);
	}

}
