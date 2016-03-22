/**
 * @author rpintodasilva
 * @version 1.0
 */

package com.github.rpintodasilva.xebiatondeuse;

public class Pelouse {
	private int x;
	private int y;
	
	public Pelouse() {
	}
	
	/**
	 * constructeur de l'objet Pelouse
	 * @param pX
	 * @param pY
	 */
	public Pelouse(int pX, int pY) {
		x = pX;
		y = pY;
	}
	
	/**
	 * getter du champ x
	 * @return le champ x de type int de l'objet Pelouse
	 */
	public int getX() {
		return x;
	}
	/**
	 * getter du champ y
	 * @return le champ y de type int de l'objet Pelouse
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * setter du champ x
	 * @param pX le champ x de type int à setter dans l'objet Pelouse
	 */
	public void setX(int pX) {
		x = pX;
	}
	/**
	 * setter du champ y
	 * @param pY le champ y de type int à setter dans l'objet Pelouse
	 */
	public void setY(int pY) {
		y = pY;
	}
	
}