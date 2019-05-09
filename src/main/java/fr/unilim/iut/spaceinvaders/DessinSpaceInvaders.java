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
		if (this.jeu.aUnVaisseau()) {
			   Vaisseau vaisseau = this.jeu.recupererVaisseau();
			   this.dessinerUnVaisseau(vaisseau, image);
		   }
		if (this.jeu.aUnMissile()) {
			Missile missile = this.jeu.recupererUnMissile();
			this.dessinerUnMissile(missile, image);
		}
	}
	
	public void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.red);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(),
				vaisseau.ordonneeLaPlusBasse(),
				vaisseau.getDimension().longueur(),
				vaisseau.getDimension().hauteur());
	}
	
	public void dessinerUnMissile(Missile missile, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillRect(missile.abscisseLaPlusAGauche(),
				missile.ordonneeLaPlusBasse(),
				missile.getDimension().longueur(),
				missile.getDimension().hauteur());
	}
}
