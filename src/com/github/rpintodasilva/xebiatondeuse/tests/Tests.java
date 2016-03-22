/**
 * @author rpintodasilva
 * @version 1.0
 */
package com.github.rpintodasilva.xebiatondeuse.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.rpintodasilva.xebiatondeuse.Deplacement;
import com.github.rpintodasilva.xebiatondeuse.Orientation;
import com.github.rpintodasilva.xebiatondeuse.Pelouse;
import com.github.rpintodasilva.xebiatondeuse.Position;
import com.github.rpintodasilva.xebiatondeuse.Tondeuse;

public class Tests {	
	@Test
	public final void testCloneTondeuse() {
		Tondeuse maTondeuse = new Tondeuse(new Position(0,0), Orientation.N, new Pelouse(5,5));
		Tondeuse dolly = maTondeuse.getClone();
		assertTrue("Clonage de Tondeuse", (maTondeuse.getMaPelouse().getX()==dolly.getMaPelouse().getX()&&maTondeuse.getMaPelouse().getY()==dolly.getMaPelouse().getY()) 
				&& (maTondeuse.getMaPosition().getX()==dolly.getMaPosition().getX()&&maTondeuse.getMaPosition().getY()==dolly.getMaPosition().getY())
				&& (maTondeuse.getMonOrientation().name()==dolly.getMonOrientation().name()));
	}
	
	@Test
	public final void testDeplacementGauche() {
		Tondeuse maTondeuse = new Tondeuse(new Position(0,0), Orientation.N, new Pelouse(5,5));
		maTondeuse.gereDeplacementGauche();
		assertTrue("Nord vers Gauche : Ouest ", Orientation.W.equals(maTondeuse.getMonOrientation()));
		maTondeuse.gereDeplacementGauche();
		assertTrue("Ouest vers Gauche : Sud ", Orientation.S.equals(maTondeuse.getMonOrientation()));
		maTondeuse.gereDeplacementGauche();
		assertTrue("Sud vers Gauche : Est ", Orientation.E.equals(maTondeuse.getMonOrientation()));
		maTondeuse.gereDeplacementGauche();
		assertTrue("Est vers Gauche : Nord ", Orientation.N.equals(maTondeuse.getMonOrientation()));
	}
	
	@Test
	public final void testDeplacementDroite() {
		Tondeuse maTondeuse = new Tondeuse(new Position(0,0), Orientation.N, new Pelouse(5,5));
		maTondeuse.gereDeplacementDroite();
		assertTrue("Nord vers Droite : Est ", Orientation.E.equals(maTondeuse.getMonOrientation()));
		maTondeuse.gereDeplacementDroite();
		assertTrue("Est vers Droite : Sud ", Orientation.S.equals(maTondeuse.getMonOrientation()));
		maTondeuse.gereDeplacementDroite();
		assertTrue("Sud vers Droite : Ouest ", Orientation.W.equals(maTondeuse.getMonOrientation()));
		maTondeuse.gereDeplacementDroite();
		assertTrue("Ouest vers Droite : Nord ", Orientation.N.equals(maTondeuse.getMonOrientation()));
	}
	
	@Test
	public final void testValiditePosition(){
		Tondeuse maTondeuse = new Tondeuse(new Position(5,5), Orientation.N, new Pelouse(5,5));
		assertTrue("Déplacement impossible", !maTondeuse.isPositionValide(new Position(6,5)));
		assertTrue("Déplacement impossible", !maTondeuse.isPositionValide(new Position(5,6)));
		assertTrue("Déplacement impossible", !maTondeuse.isPositionValide(new Position(-1,0)));
		assertTrue("Déplacement impossible", !maTondeuse.isPositionValide(new Position(0,-1)));
		assertTrue("Déplacement possible", maTondeuse.isPositionValide(new Position(5,5)));
		assertTrue("Déplacement possible", maTondeuse.isPositionValide(new Position(0,0)));
		assertTrue("Déplacement possible", maTondeuse.isPositionValide(new Position(1,2)));	
	}	
	
	@Test
	public final void testPositionEtAction(){
		Tondeuse maTondeuse = new Tondeuse(new Position(0,0), Orientation.N, new Pelouse(5,5));
		maTondeuse.calculeNouvellePosition(Deplacement.A);
		assertTrue("Nouvelle position : (0,1)", new Position(0,1).equals(maTondeuse.getMaPosition()));
		maTondeuse.gereDeplacementDroite();
		maTondeuse.calculeNouvellePosition(Deplacement.A);
		assertTrue("Nouvelle position : (1,1)", new Position(1,1).equals(maTondeuse.getMaPosition()));
		maTondeuse.gereDeplacementDroite();
		maTondeuse.calculeNouvellePosition(Deplacement.A);
		assertTrue("Nouvelle position : (1,0)", new Position(1,0).equals(maTondeuse.getMaPosition()));
	}
}