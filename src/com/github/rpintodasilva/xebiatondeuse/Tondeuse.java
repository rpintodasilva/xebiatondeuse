/**
 * @author rpintodasilva
 * @version 1.0
 */

package com.github.rpintodasilva.xebiatondeuse;

public class Tondeuse {
	private Position maPosition;
	private Orientation monOrientation;
	private Pelouse maPelouse;

	public Tondeuse() {
	}
	
	/**
	 * constructeur de l'objet Tondeuse
	 * @param pMaPosition
	 * @param pMonOrientation
	 * @param pMaPelouse
	 */
	public Tondeuse(Position pMaPosition, Orientation pMonOrientation,
			Pelouse pMaPelouse) {
		maPosition = pMaPosition;
		monOrientation = pMonOrientation;
		maPelouse = pMaPelouse;
	}

	/**
	 * getter du champ maPosition
	 * @return le champ maPosition de type Position de l'objet Tondeuse
	 */
	public Position getMaPosition() {
		return maPosition;
	}

	/**
	 * getter du champ monOrientation
	 * @return le champ monOrientation de type Orientation de l'objet Tondeuse
	 */
	public Orientation getMonOrientation() {
		return monOrientation;
	}

	/**
	 * getter du champ maPelouse
	 * @return le champ maPelouse de type Pelouse de l'objet Tondeuse
	 */
	public Pelouse getMaPelouse() {
		return maPelouse;
	}

	/**
	 * setter du champ maPosition
	 * @param pMaPosition le champ maPosition de type Position à setter dans l'objet Tondeuse
	 */
	public void setMaPosition(Position pMaPosition) {
		maPosition = pMaPosition;
	}

	/**
	 * setter du champ monOrientation
	 * @param pMonOrientation le champ monOrientation de type Orientation à setter dans l'objet Tondeuse
	 */
	public void setMonOrientation(Orientation pMonOrientation) {
		monOrientation = pMonOrientation;
	}

	/**
	 * setter du champ maPelouse
	 * @param pMaPelouse le champ maPelouse de type Pelouse à setter dans l'objet Tondeuse
	 */
	public void setMaPelouse(Pelouse pMaPelouse) {
		maPelouse = pMaPelouse;
	}

	/**
	 * Gère les actions à apporter à la tondeuse en fonction du déplacement.
	 * @param d le déplacement de la tondeuse
	 */
	public void calculeNouvellePosition(Deplacement d){
		switch(d){
			case A:
				activeDeplacementTondeuse();
				break;
			case G:
				gereDeplacementGauche();
				break;
			case D:
				gereDeplacementDroite();
				break;
		}
	}
	
	/**
	 * Gère le déplacement de la tondeuse et plus seulement la rotation (A).
	 */
	public void activeDeplacementTondeuse(){
		int x = -1;
		int y = -1;
		switch(monOrientation){
			case N:
				y = maPosition.getY()+1;
				break;
			case E:
				x = maPosition.getX()+1;
				break;
			case W:
				x = maPosition.getX()-1;
				break;
			case S:
				y = maPosition.getY()-1;
				break;
		}
		if (x>-1 && isPositionValide(new Position(x, maPosition.getY()))){
			maPosition.setX(x);
		}
		if (y>-1 && isPositionValide(new Position(maPosition.getX(), y))){
			maPosition.setY(y);
		}
	}
	
	/**
	 * Vérifie si une position est valide.
	 * @param p la position à tester
	 * @return true si la position est valide ; false autrement
	 */
	public boolean isPositionValide(Position p){
		return ((p.getX()>=0 && p.getX()<=maPelouse.getX()) &&(p.getY()>=0 && p.getY()<=maPelouse.getY()));
		
	}
	
	/**
	 * Remet la tondeuse en position initiale.
	 * Position (0,0) ; Orientation Nord;
	 */
	public void reset(){
		maPosition = new Position(0,0);
		monOrientation = Orientation.N;
	}
	
	/**
	 * Gère les modifications de l'orientation de la tondeuse en fonction d'une rotation à gauche.
	 */
	public void gereDeplacementGauche(){
		switch(monOrientation){
			case N:
				setMonOrientation(Orientation.W);
				break;
			case E:
				setMonOrientation(Orientation.N);
				break;
			case W:
				setMonOrientation(Orientation.S);
				break;
			case S:
				setMonOrientation(Orientation.E);
				break;
			}
	}
	
	/**
	 * Gère les modifications de l'orientation de la tondeuse en fonction d'une rotation à droite.
	 */
	public void gereDeplacementDroite(){
		switch(monOrientation){
			case N:
				setMonOrientation(Orientation.E);
				break;
			case E:
				setMonOrientation(Orientation.S);
				break;
			case W:
				setMonOrientation(Orientation.N);
				break;
			case S:
				setMonOrientation(Orientation.W);
				break;
		}
	}
	
	/**
	 * Retourne un clone de notre tondeuse.
	 * @return une tondeuse Dolly.
	 */
	public Tondeuse getClone(){
		return (new Tondeuse(getMaPosition(), getMonOrientation(), getMaPelouse()));
	}

	/**
	 * Méthode d'affichage des principaux éléments de la tondeuse.
	 */
	public String toString(){
		return "Position de la tondeuse : "+getMaPosition().getX()+" "+getMaPosition().getY()+" "+getMonOrientation();
		
	}
	
}