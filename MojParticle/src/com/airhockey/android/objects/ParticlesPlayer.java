package com.airhockey.android.objects;

import java.util.Random;

import android.opengl.Matrix;

import com.airhockey.android.util.Geometrija.Cestica;
import com.airhockey.android.util.Geometrija.Vektor;

public class ParticlesPlayer {

	Cestica cestica;
	Vektor vektor;
	int boja;
	
	private final float angleVariance;
	private final float speedVarince;
	
	private final Random random = new Random();
	
	private float [] rotationMatrix = new float [16];
	private float [] directionVector = new float [4];
	private float [] resultVector = new float [4];

	public ParticlesPlayer(Cestica cestica, Vektor vektor, int boja, float angleVarianceInDegrees, float speedVariance) {

		this.cestica = cestica;
		this.vektor = vektor;
		this.boja = boja;
		this.angleVariance = angleVarianceInDegrees;
		this.speedVarince = speedVariance;
		
		directionVector[0] = vektor.x;
		directionVector[1] = vektor.y;
		directionVector[2] = vektor.z;
	}

	/**Ubaciva atribute svake cestice u zadani array.
	 * @param particles
	 * @param vrijeme
	 * @param zbroj
	 */
	public void dodajCestice(Particles particles, float vrijeme, int zbroj) {
		
		for (int i = 0; i < zbroj; i++) {

			Matrix.setRotateEulerM(rotationMatrix, 0, 
					(random.nextFloat() - 0.5f) * angleVariance,
					(random.nextFloat() - 0.5f) * angleVariance,
					(random.nextFloat() - 0.5f) * angleVariance);
			
			Matrix.multiplyMV(resultVector, 0, rotationMatrix, 0, directionVector, 0);
			float speedAdjustment = 1f + random.nextFloat() * speedVarince;
			
			Vektor thisDirection = new Vektor(
					resultVector[0] * speedAdjustment,
					resultVector[1] * speedAdjustment,
					resultVector[2] * speedAdjustment);
			
			particles.dodajCestice(cestica, boja, thisDirection, vrijeme);

		}
	}
}
