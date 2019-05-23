package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

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
			List<Missile> missiles = this.jeu.recupererMissiles();
			for (Missile missile : missiles) {
				this.dessinerUnMissile(missile, image);

			}
		}
		if (this.jeu.aUnEnvahisseur()) {
			Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
			this.dessinerUnEnvahisseur(envahisseur, image);
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
		crayon.setColor(Color.GREEN);
		crayon.fillRect(missile.abscisseLaPlusAGauche(),
				missile.ordonneeLaPlusBasse(),
				missile.getDimension().longueur(),
				missile.getDimension().hauteur());
	}
	
	public void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillRect(envahisseur.abscisseLaPlusAGauche(),
				envahisseur.ordonneeLaPlusBasse(),
				envahisseur.getDimension().longueur(),
				envahisseur.getDimension().hauteur());
	}
}
