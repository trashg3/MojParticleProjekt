/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
 ***/
package com.airhockey.android;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

import com.airhockey.android.data.VertexArray;
import com.airhockey.android.objects.Particles;
import com.airhockey.android.objects.ParticlesPlayer;
import com.airhockey.android.programs.ParticleProgram;
import com.airhockey.android.util.Geometrija.Cestica;
import com.airhockey.android.util.Geometrija.Vektor;
import com.airhockey.android.util.MatrixHelper;

public class ParticlesRenderer implements Renderer {

	private final Context context;

	private final float[] projectionMatrix = new float[16];
	private final float[] viewMatrix = new float[16];
	private final float[] viewProjectionMatrix = new float[16];

	ParticleProgram particleProgram;
	VertexArray vertexArray;
	Particles particles;
	ParticlesPlayer crveniParticlesPlayer;
	ParticlesPlayer zeleniParticlesPlayer;
	ParticlesPlayer plaviParticlesPlayer;
	Vektor vektor;
	Cestica cestica;
	long globalStartTime;

	public ParticlesRenderer(Context context) {

		this.context = context;

	}

	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		particleProgram = new ParticleProgram(context);
		particles = new Particles(10000);
		globalStartTime = System.nanoTime();

		vektor = new Vektor(0f, 0.5f, 0f);

		crveniParticlesPlayer = new ParticlesPlayer(new Cestica(-1f, 0f, 0f),
				vektor, Color.rgb(255, 88, 122));
		zeleniParticlesPlayer = new ParticlesPlayer(new Cestica(0f, 0f, 0f),
				vektor, Color.rgb(55, 255, 88));
		plaviParticlesPlayer = new ParticlesPlayer(new Cestica(1f, 0f, 0f),
				vektor, Color.rgb(67, 17, 255));

	}

	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		// Set the OpenGL viewport to fill the entire surface.
		glViewport(0, 0, width, height);

		MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width
				/ (float) height, 1f, 10f);
		Matrix.setIdentityM(viewMatrix, 0);
		Matrix.translateM(viewMatrix, 0, 0f, -1.5f, -5f);
		Matrix.multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0,
				viewMatrix, 0);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		// Clear the rendering surface.
		glClear(GL_COLOR_BUFFER_BIT);

		long trenutnoVrijeme = (System.nanoTime() - globalStartTime) / 1000000000;
		
		crveniParticlesPlayer.dodajCestice(particles, trenutnoVrijeme, 5);
		zeleniParticlesPlayer.dodajCestice(particles, trenutnoVrijeme, 5);
		plaviParticlesPlayer.dodajCestice(particles, trenutnoVrijeme, 5);
		
		particleProgram.useProgram();
		particleProgram.setUniforms(viewProjectionMatrix, trenutnoVrijeme);
		particles.objediniVertexAttribPoint(particleProgram);
		particles.iscrtaj();
		
	}
}