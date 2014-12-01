package com.airhockey.android.objects;

import com.airhockey.android.util.Geometrija.Cestica;
import com.airhockey.android.util.Geometrija.Vektor;

/**
 * @author Sirius
 *Objedinjuje sva obiljezja cestice na jednom mjestu.
 */
public class ParticlesPlayer {

	Cestica cestica;
	Vektor vektor;
	int boja;

	public ParticlesPlayer(Cestica cestica, Vektor vektor, int boja) {

		this.cestica = cestica;
		this.vektor = vektor;
		this.boja = boja;
	}

	/**Ubaciva atribute svake cestice u zadani array.
	 * @param particles
	 * @param vrijeme
	 * @param zbroj
	 */
	public void dodajCestice(Particles particles, float vrijeme, int zbroj) {

		for (int i = 0; i < zbroj; i++) {

			particles.dodajCestice(cestica, boja, vektor, vrijeme);
		}
	}
}
