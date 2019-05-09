package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.model.Constante;
import fr.unilim.iut.spaceinvaders.model.DessinSpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.moteurjeu.MoteurGraphique;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
		DessinSpaceInvaders afficheur = new DessinSpaceInvaders(jeu);

		jeu.initialiserJeu();
		MoteurGraphique moteur = new MoteurGraphique(jeu, afficheur);
		moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
	}
}
