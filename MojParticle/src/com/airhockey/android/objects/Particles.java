package com.airhockey.android.objects;

import com.airhockey.android.Constants;

public class Particles {

	private static final int BROJ_KORDINATA = 3;
	private static final int BROJ_BOJE = 3;
	private static final int VEKTOR = 3;
	
	private static final int UKUPAN_BROJ_ELEMENATA = 
			BROJ_KORDINATA
		  + BROJ_BOJE
		  + VEKTOR;
	
	private static final int STRIDE = UKUPAN_BROJ_ELEMENATA * Constants.BYTES_PER_FLOAT;
	
	private float [] podaci_za_particles;
	private int max_broj_cestica;
	private int aktivna_cestica;
	
	
}
