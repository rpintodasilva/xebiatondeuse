/**
 * @author rpintodasilva
 * @version 1.0
 */

package com.github.rpintodasilva.xebiatondeuse;

public class Position {
	private int x;
	private int y;
	
	/**
	 * constructeur de l'objet Position
	 * @param pX
	 * @param pY
	 */
	public Position(int pX, int pY) {
		x = pX;
		y = pY;
	}

	/** 
	 * Retourne l'abcisse de la position.
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Définit l'abscisse de la position.
	 * @param pX l'abscisse de la position
	 */
	public void setX(int pX) {
		x = pX;
	}
	
	/** 
	 * Retourne l'ordonnée de la position.
	 */ 
	public int getY() {
		return y;
	}
	
	/**
	 * Définit l'ordonnée de la position.
	 * @param pY l'ordonnée à définir
	 */
	public void setY(int pY) {
		y = pY;
	}
	
	/**
	 * Teste l'égalité de deux positions.
	 * @param p la position à comparer
	 * @return true si les positions sont identiques ; false autrement
	 */
	public boolean equals(Position p){
		return this.x==p.x && this.y==p.y;
	}
}