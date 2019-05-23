package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public boolean detecterCollisionAxeY(Sprite premier, Sprite second) {
		return detecterCollisionHautContreBas(premier, second) 
				|| detecterCollisionBasContreHaut(premier, second);
	}

	private boolean detecterCollisionBasContreHaut(Sprite premier, Sprite second) {
		return premier.ordonneeLaPlusBasse() <= second.ordonneeLaPlusHaute()
				&& premier.ordonneeLaPlusBasse() >= second.ordonneeLaPlusBasse();
	}

	private boolean detecterCollisionHautContreBas(Sprite premier, Sprite second) {
		return premier.ordonneeLaPlusHaute() >= second.ordonneeLaPlusBasse()
				&& premier.ordonneeLaPlusHaute() <= second.ordonneeLaPlusBasse();
	}

	public boolean detecterCollisionAxeX(Sprite premier, Sprite second) {
		return detecterCollisionDroitContreGauche(premier, second)
				|| detecterCollisionGaucheContreDroite(premier, second);

	}

	private boolean detecterCollisionGaucheContreDroite(Sprite premier, Sprite second) {
		return premier.abscisseLaPlusAGauche() <= second.abscisseLaPlusADroite()
				&& premier.abscisseLaPlusAGauche() >= second.abscisseLaPlusAGauche();
	}

	private boolean detecterCollisionDroitContreGauche(Sprite premier, Sprite second) {
		return premier.abscisseLaPlusADroite() >= second.abscisseLaPlusAGauche()
				&& premier.abscisseLaPlusADroite() <= second.abscisseLaPlusAGauche();
	}

	public boolean detecterCollision(Sprite premier, Sprite second) {
		return detecterCollisionAxeX(premier, second) && detecterCollisionAxeY(premier, second);
	}

}
