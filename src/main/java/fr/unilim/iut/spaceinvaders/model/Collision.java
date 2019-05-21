package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public boolean detecterCollisionAxeY(Sprite premier, Sprite second) {
		boolean collision = false;
		if ((premier.ordonneeLaPlusHaute() >= second.ordonneeLaPlusBasse()
				&& premier.ordonneeLaPlusHaute() <= second.ordonneeLaPlusBasse())
				|| (premier.ordonneeLaPlusBasse() <= second.ordonneeLaPlusHaute()
						&& premier.ordonneeLaPlusBasse() >= second.ordonneeLaPlusBasse()))
			collision = true;
		return collision;
	}

	public boolean detecterCollisionAxeX(Sprite premier, Sprite second) {
		boolean collision = false;
		if ((premier.abscisseLaPlusADroite() >= second.abscisseLaPlusAGauche()
				&& premier.abscisseLaPlusADroite() <= second.abscisseLaPlusAGauche())
				|| (premier.abscisseLaPlusAGauche() <= second.abscisseLaPlusADroite()
						&& premier.abscisseLaPlusAGauche() >= second.abscisseLaPlusAGauche()))
			collision = true;
		return collision;
	}

	public boolean detecterCollision(Sprite premier, Sprite second) {
		return detecterCollisionAxeX(premier, second) && detecterCollisionAxeY(premier, second);
	}

}
