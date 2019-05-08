package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurjeu.MoteurGraphique;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
		DessinSpaceInvaders afficheur = new DessinSpaceInvaders(jeu);

		jeu.initialiserJeu(new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR),
				new Position((Constante.ESPACEJEU_LONGUEUR - Constante.VAISSEAU_LONGUEUR)/2, 
						Constante.ESPACEJEU_HAUTEUR-Constante.VAISSEAU_HAUTEUR));
		MoteurGraphique moteur = new MoteurGraphique(jeu, afficheur);
		moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
	}
}
