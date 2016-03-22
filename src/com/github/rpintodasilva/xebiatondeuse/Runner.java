/**
 * @author rpintodasilva
 * @version 1.0
 */

package com.github.rpintodasilva.xebiatondeuse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
	String fichierIn;
	Pelouse p;
	Tondeuse t1, t2;
	List<Deplacement> lDeplacementsT1, lDeplacementsT2;
	
	/**
	 * 
	 * constructeur de l'objet Reader
	 * @param pFichierIn le fichier en entrée
	 */
	public Runner(String pFichierIn) {
		fichierIn = pFichierIn;
	}
	
	/**
	 * Lance la récupération de l'ensemble des paramètres depuis le fichier.
	 * Exécute ensuite le déplacement des tondeuses.
	 */
	private void runMe() {
		int l=1;
		
		try {
			/* ouverture du fichier */
			BufferedReader br = new BufferedReader(new FileReader(fichierIn));

			/* lecture du fichier */
			String ligneCour;
			ligneCour = br.readLine();
			while (ligneCour != null) {
				switch (l){
					case 1:
						p = extractPelouse(ligneCour);
						break;
					case 2:
						t1 = extractTondeuse(ligneCour, p);
						break;
					case 3:
						lDeplacementsT1 = extractDeplacements(ligneCour);
						break;
					case 4:
						t2 = extractTondeuse(ligneCour, p);
						break;
					case 5:
						lDeplacementsT2 = extractDeplacements(ligneCour);
						break;
				}
				ligneCour = br.readLine();
				l++;
			}
			br.close();
			lanceDeplacements();
		} catch (Exception e) {
			System.err.println("Erreur à la lecture du fichier.\n\n"+e.getMessage());
		}
	}

	/**
	 * Extrait les informations concernant la pelouse, depuis le fichier
	 * @param pLigneCour la ligne depuis laquelle on récupère les informations concernant la pelouse
	 * @return la pelouse
	 */
	private Pelouse extractPelouse(String pLigneCour) {
		Pelouse maPelouse = null;
		Pattern p = Pattern.compile("([0-9]+)\\ ([0-9]+)");
		Matcher m = p.matcher(pLigneCour);
		if (m.find()){
			maPelouse = new Pelouse(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
		}
		
		return maPelouse;
	}

	/**
	 * Extrait les informations d'une tondeuse, depuis le fichier
	 * @param pLigneCour la ligne depuis laquelle on récupère les informations concernant la tondeuse
	 * @return la tondeuse
	 */
	private Tondeuse extractTondeuse(String pLigneCour, Pelouse pMaPelouse) {
		Tondeuse t = null;
		Pattern p = Pattern.compile("([0-9]+)\\ ([0-9]+)\\ ([N|W|S|E])");
		Matcher m = p.matcher(pLigneCour);
		if (m.find()){
			t = new Tondeuse();
			t.setMaPosition(new Position(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
			t.setMonOrientation(Orientation.valueOf(m.group(3).toUpperCase()));
			t.setMaPelouse(pMaPelouse);
		}
		
		return t;
	}

	/**
	 * Extrait une liste de déplacements pour une tondeuse, depuis le fichier
	 * @param pLigneCour la ligne depuis laquelle on récupère les déplacements
	 * @return une liste de déplacements
	 */
	private List<Deplacement> extractDeplacements(String pLigneCour) {
		List<Deplacement> l = new ArrayList<Deplacement>();
		int i=0;
		while(i<pLigneCour.length()){
			l.add(Deplacement.valueOf(pLigneCour.substring(i, i+1)));
			i++;
		}
		
		return l;
	}
	
	/**
	 * Lance les déplacements des tondeuses d'après les paramètres récupérés.
	 */
	public void lanceDeplacements(){
		Iterator<Deplacement> lt1dep = lDeplacementsT1.iterator(); 
		while (lt1dep.hasNext()){
			t1.calculeNouvellePosition(lt1dep.next());
		}
		System.out.println(t1.toString());
		
		Iterator<Deplacement> lt2dep = lDeplacementsT2.iterator(); 
		while (lt2dep.hasNext()){
			t2.calculeNouvellePosition(lt2dep.next());
		}
		System.out.println(t2.toString());
	}
	
	public static void main(String[] args){
		new Runner("io/deplacements.txt").runMe();
	}
}