package fr.unilim.iut.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;

	public DessinSpaceInvaders(SpaceInvaders jeu) {
		this.jeu = jeu;
	}

	@Override
	public void dessiner(BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillRect(jeu.getVaisseau().abscisseLaPlusAGauche(),
				jeu.getVaisseau().ordonneeLaPlusBasse(),
				jeu.getVaisseau().getDimension().longueur(),
				jeu.getVaisseau().getDimension().hauteur());
	}
}
