package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {
	private Collision detecteur;
	

	@Before
	public void initialisation() {
		
	}

	@Test
	public void test_collisionAxeYEntre2Sprite() {
		
		assertEquals(true, spaceInvaders.recupererDetecteurCollision().detecterCollisionY(spaceInvaders.getVaisseau(),
				spaceInvaders.recupererEnvahisseur()));
	}

	@Test
	public void test_collisionAxeXEntre2Sprite() {
		spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2, 2), new Position(7, 7), 1);
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(2, 2), new Position(6, 1), 1);
		
		assertEquals(true, spaceInvaders.recupererDetecteurCollision().detecterCollisionX(spaceInvaders.getVaisseau(),
				spaceInvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_collisionAxeXetAxeYEntre2Sprite() {
		spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2, 2), new Position(5, 2), 1);
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(2, 2), new Position(6, 1), 1);
		
		assertEquals(true, spaceInvaders.recupererDetecteurCollision().detecterCollision(spaceInvaders.getVaisseau(),
				spaceInvaders.recupererEnvahisseur()));
	}

}
