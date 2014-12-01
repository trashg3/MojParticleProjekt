package com.airhockey.android.objects;

import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;
import android.graphics.Color;

import com.airhockey.android.Constants;
import com.airhockey.android.data.VertexArray;
import com.airhockey.android.programs.ParticleProgram;
import com.airhockey.android.util.Geometrija.Cestica;
import com.airhockey.android.util.Geometrija.Vektor;

public class Particles {

	private static final int BROJ_KORDINATA = 3;
	private static final int BROJ_BOJE = 3;
	private static final int VEKTOR = 3;
	private static final int VRIJEME_POCETKA = 1;
	
	private static final int UKUPAN_BROJ_ELEMENATA = 
			BROJ_KORDINATA
		  + BROJ_BOJE
		  + VEKTOR
		  + VRIJEME_POCETKA;
	
	private static final int STRIDE = UKUPAN_BROJ_ELEMENATA * Constants.BYTES_PER_FLOAT;
	
	VertexArray vertexArray;
	private float [] podaci_za_particles;
	private int max_broj_cestica;
	private int sljedeca_cestica;
	private int aktivna_Cestica;
	
	
	public Particles(int max_broj_cestica) {
		podaci_za_particles = new float [max_broj_cestica * UKUPAN_BROJ_ELEMENATA];
		vertexArray = new VertexArray(podaci_za_particles);
		this.max_broj_cestica = max_broj_cestica;
	}
	
	public void dodajCestice(Cestica cestica, int boja, Vektor vektor, float vrijemePocetka) {
		
		final int particleOffset = sljedeca_cestica * UKUPAN_BROJ_ELEMENATA;
		
		int trenutniOffset = particleOffset;
		sljedeca_cestica++;
		
		if (aktivna_Cestica < max_broj_cestica) {
			aktivna_Cestica++;
		}
		
		if (sljedeca_cestica == max_broj_cestica ) {
			sljedeca_cestica = 0;
		}
		
		podaci_za_particles [trenutniOffset++] = cestica.x;
		podaci_za_particles [trenutniOffset++] = cestica.y;
		podaci_za_particles [trenutniOffset++] = cestica.z;
		
		podaci_za_particles [trenutniOffset++] = Color.red(boja) / 255f;
		podaci_za_particles [trenutniOffset++] = Color.green(boja) / 255f;
		podaci_za_particles [trenutniOffset++] = Color.blue(boja) / 255f;
		
		podaci_za_particles [trenutniOffset++] = vektor.x;
		podaci_za_particles [trenutniOffset++] = vektor.y;
		podaci_za_particles [trenutniOffset++] = vektor.z;
		
		podaci_za_particles [trenutniOffset++] = vrijemePocetka;
		
		vertexArray.updateFloatBuffer(podaci_za_particles, particleOffset, UKUPAN_BROJ_ELEMENATA);
	}
	
	/**
	 * Svrha - postavlja openGL funkciju glVertexAttribPointer za sve atribute (4).
	 * @param particleProgram - instanca klase koja sadrzi funkciju a koja je predlozak.
	 * 
	 */
	public void objediniVertexAttribPoint(ParticleProgram particleProgram) {
		
		int offset = 0;
		vertexArray.setVertexAttribPointer(offset, particleProgram.getPosicijaLokacija(),BROJ_KORDINATA, STRIDE );
		
		offset += BROJ_KORDINATA;
		vertexArray.setVertexAttribPointer(offset, particleProgram.getBojaLokacija(), BROJ_BOJE, STRIDE);
		
		offset += BROJ_BOJE;
		vertexArray.setVertexAttribPointer(offset, particleProgram.getVektorLokacija(), VEKTOR, STRIDE);
		
		offset += VEKTOR;
		vertexArray.setVertexAttribPointer(offset, particleProgram.getVrijemeNastankaLokacija(), VRIJEME_POCETKA, STRIDE);
		
		
	}
	
	public void iscrtaj() {
		glDrawArrays(GL_POINTS, 0, aktivna_Cestica);
	}
}
