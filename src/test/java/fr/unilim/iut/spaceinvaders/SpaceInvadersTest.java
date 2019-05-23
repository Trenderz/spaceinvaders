package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvadersTest {

	private SpaceInvaders spaceInvaders;

	@Before
	public void initialisation() {
		spaceInvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		assertEquals("" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n"	+ 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n", spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(7,9), 1);
		assertEquals("" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n"	+ 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......V.......\n", spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1),new Position(15, 9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(-1,9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(14,-1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}
	
	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 1);
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......VVV.....\n" + 
		".......VVV.....\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
			
	}
	
	  @Test
	  public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
			
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
		spaceInvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"............VVV\n" + 
		"............VVV\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	  @Test
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

	       spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
	       spaceInvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "....VVV........\n" + 
	       "....VVV........\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	   }
	  
	  @Test
	    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
	       spaceInvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	     }
	  
	  public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

	       spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
	       spaceInvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "..........VVV..\n" + 
	       "..........VVV..\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	   }
	  
	  @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

	       spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
	       spaceInvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	    }
	  
	  @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

	       spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
	       spaceInvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	     }
	  
	  @Test
	     public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceInvaders.tirerUnMissile(new Dimension(3,2),2);

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	    }
	  
	  @Test(expected = MissileException.class)
		public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
		   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		   spaceInvaders.tirerUnMissile(new Dimension(7,9),1);
		}	
	  
	  @Test
	    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

		   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		   spaceInvaders.tirerUnMissile(new Dimension(3,2),2);

		   spaceInvaders.deplacerMissile();
		   
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       ".......MMM.....\n" + 
	       ".......MMM.....\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	   }
	  
	  @Test
	   public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		   spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		   spaceInvaders.tirerUnMissile(new Dimension(3,2),1);
		   for (int i = 1; i <=6 ; i++) {
			   spaceInvaders.deplacerMissile();
		   }
		   
		   spaceInvaders.deplacerMissile();
		   
	       assertEquals("" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	   }
	  
	  @Test
		public void test_unNouvelEnvahisseurAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
			spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(7,2), 1);
			assertEquals("" + 
			"...............\n" + 
			".......EE......\n" +
			".......EE......\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" + 
			"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
		}
	  
	  @Test
		public void test_UnNouvelEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {

			try {
				spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(1, 1),new Position(15, 9), 1);
				fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(-1,9), 1);
				fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(14,10), 1);
				fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(1,1),new Position(14,-1), 1);
				fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

		}
	  
	  @Test
		public void test_UnNouvelEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
			
			try {
				spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(9,2),new Position(7,9), 1);
				fail("Dépassement de l'envahisseur à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
			
			
			try {
				spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(3,4),new Position(7,1), 1);
				fail("Dépassement de l'envahisseur vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}
				
		}
	  
	  @Test
	  public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaDroite() {
			
		spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(13,2), 3);
		spaceInvaders.deplacerEnvahisseurVersLaDroite();
		assertEquals("" + 
		"...............\n" + 
		".............EE\n" +
		".............EE\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	}
	  @Test
	    public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche() {

	       spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(7,2), 3);
	       spaceInvaders.deplacerEnvahisseurVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "....EE.........\n" +
	       "....EE.........\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	   }
	  
	  @Test
	    public void test_EnvahisseurImmobile_DeplacerEnvahisseurVersLaGauche() {

		   spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(0,2), 3);
	       spaceInvaders.deplacerEnvahisseurVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "EE.............\n" +
	       "EE.............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	     }
	  
	  @Test
	  public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite() {

	       spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(8,2),3);
	       spaceInvaders.deplacerEnvahisseurVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...........EE..\n" +
	       "...........EE..\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	   }
	  
	  @Test
	    public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaDroite() {

	       spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(10,2),3);
	       spaceInvaders.deplacerEnvahisseurVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       ".............EE\n" +
	       ".............EE\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	    }
	  
	  @Test
	    public void test_EnvahisseurAvancePartiellement_DeplacerEnvahisseurVersLaGauche() {

	       spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(1,2), 3);
	       spaceInvaders.deplacerEnvahisseurVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "EE.............\n" +
	       "EE.............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
	     }
	  
	  @Test 
	  	public void test_FinDuJeuUnMissileToucheEnvahisseur() {
		  spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
		  spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2), new Position(7,3), 1);
		  spaceInvaders.tirerUnMissile(new Dimension(1,2), 2);
		  
		  spaceInvaders.deplacerMissile();
		  spaceInvaders.deplacerMissile();
		  
		  assertTrue(spaceInvaders.detecterCollisionMissiles(spaceInvaders.recupererEnvahisseur()));
	  }
	  
	  @Test 
	  	public void test_Tirer2MissilesLUnApresLAutreDepuisLeVaisseau() {
		  spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		  
		  spaceInvaders.tirerUnMissile(new Dimension(3,2), 3);
		  spaceInvaders.deplacerMissile();
		  spaceInvaders.setTempsProchainMissile(0);
		  spaceInvaders.tirerUnMissile(new Dimension(3,2), 3);
		  
		  
		  assertEquals("" + 
			       "...............\n" + 
			       "...............\n" +
			       "...............\n" + 
			       ".......MMM.....\n" + 
			       ".......MMM.....\n" + 
			       "...............\n" + 
			       ".......MMM.....\n" + 
			       ".......MMM.....\n" + 
			       ".....VVVVVVV...\n" + 
			       ".....VVVVVVV...\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());	  }
	  
	 
}