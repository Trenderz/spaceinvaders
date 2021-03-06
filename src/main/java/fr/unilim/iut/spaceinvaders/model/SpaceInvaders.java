package fr.unilim.iut.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.*;
import fr.unilim.iut.spaceinvaders.utils.*;

public class SpaceInvaders implements Jeu {

	private int longueur;
	private int hauteur;
	private Vaisseau vaisseau;
	private List<Missile> missiles;
	private Envahisseur envahisseur;
	private Collision detecteurCollision;
	private int tempsProchainMissile;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.detecteurCollision = new Collision();
		this.missiles = new ArrayList<Missile>();
	}

	public int getTempsProchainMissile() {
		return tempsProchainMissile;
	}

	public void setTempsProchainMissile(int tempsProchainMissile) {
		this.tempsProchainMissile = tempsProchainMissile;
	}

	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	protected boolean aUnEnvahisseur() {
		return null != envahisseur;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		boolean continuer = true;
		int index = 0;
		boolean resultat = false;
		if (this.aUnMissile())
			while (continuer && index < missiles.size()) {
				if (missiles.get(index).occupeLaPosition(x, y)) {
					continuer = false;
					resultat = true;
				}
				index++;
			}
		return resultat;
	}

	protected boolean aUnMissile() {
		return !missiles.isEmpty();
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	protected boolean aUnVaisseau() {
		return null != vaisseau;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.getDimension().longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);

		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	@Override
	public void evoluer(Commande commande) {
		if (aUnMissile()) {
			deplacerMissile();
		}

		if (aUnEnvahisseur()) {
			deplacerEnvahisseur();
			if (envahisseurEstContreUneBordure())
				envahisseur.changerDirection();

		}
		if (commande.gauche)
			deplacerVaisseauVersLaGauche();

		if (commande.droite)
			deplacerVaisseauVersLaDroite();

		if (commande.tir)
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		if (tempsProchainMissile > 0)
			tempsProchainMissile--;

	}

	private boolean envahisseurEstContreUneBordure() {
		return (envahisseur.abscisseLaPlusADroite() >= (longueur - 1)) || (envahisseur.abscisseLaPlusAGauche() <= 0);
	}

	public void deplacerEnvahisseur() {
		if (envahisseur.getDirection() == Direction.DROITE)
			deplacerEnvahisseurVersLaDroite();
		else
			deplacerEnvahisseurVersLaGauche();
	}

	@Override
	public boolean etreFini() {
		boolean fini = false;
		if (aUnEnvahisseur() && aUnMissile())
			fini = detecterCollisionMissiles(envahisseur);
		return fini;
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		Position positionEnvahisseur = new Position(this.longueur / 2, Constante.ENVAHISSEUR_HAUTEUR * 2);
		positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);

		tempsProchainMissile = 0;
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		int nbrMissiles = this.missiles.size();
		Missile missile;
		if ((vaisseau.dimension.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		if (tempsProchainMissile == 0) {

			missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
			if (nbrMissiles == 0 || (nbrMissiles > 0 && !this.detecterCollisionMissiles(missile))) {
				this.missiles.add(missile);
				tempsProchainMissile = Constante.MISSILE_DELAI;
			}
		}
	}

	public List<Missile> recupererMissiles() {
		return this.missiles;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public void deplacerMissile() {
		if (this.aUnMissile())
			for (int i = 0; i < missiles.size(); i++)
				if (missiles.get(i).ordonneeLaPlusBasse() > 0) {
					missiles.get(i).deplacerVerticalementVers(Direction.HAUT_ECRAN);

				} else {
					missiles.remove(i);
				}

	}

	public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimension, position, vitesse);

	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.getDimension().longueur(),
						envahisseur.ordonneeLaPlusHaute());
			}
		}

	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);

		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		}

	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	public boolean detecterCollisionMissiles(Sprite secondSprite) {
		boolean resultat = false;
		for (Missile missile : this.missiles) {
			if (this.detecteurCollision.detecterCollision(missile, secondSprite))
				resultat = true;
		}
		return resultat;
	}

}
