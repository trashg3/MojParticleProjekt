package com.airhockey.android.programs;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import android.content.Context;

import com.openGL.particles.R;

public class ParticleProgram extends ShaderProgram {

	private int uMatrixLokacija;
	private int uVrijemeLokacija;
	
	private int aPosicijaLokacija;
	private int aBojaLokacija;
	private int aVektorLokacija;
	private int aVrijemeNastankaLokacija;
	
	public ParticleProgram(Context context) {
		super(context, R.raw.particle_vertex_shader, R.raw.particle_fragment_shaderi);
		
		uMatrixLokacija = glGetUniformLocation(program, U_MATRIX);
		uVrijemeLokacija = glGetUniformLocation(program, U_VRIJEME);
		
		aPosicijaLokacija = glGetAttribLocation(program, A_POSICIJA);
		aBojaLokacija = glGetAttribLocation(program, A_BOJA);
		aVektorLokacija = glGetAttribLocation(program, A_VEKTOR);
		aVrijemeNastankaLokacija = glGetAttribLocation(program, A_VRIJEME_NASTANKA);

	}
	
	public void setUniforms(float [] matrix, float protekloVrijeme) {
		
		glUniformMatrix4fv(uMatrixLokacija, 1, false, matrix, 0);
		glUniform1f(uVrijemeLokacija, protekloVrijeme);
		
	}
	
	public int getPosicijaLokacija() {
		return aPosicijaLokacija;
	}
	
	public int getBojaLokacija() {
		return aBojaLokacija;
	}
	
	public int getVektorLokacija() {
		return aVektorLokacija;
	}
	
	public int getVrijemeNastankaLokacija(){
		return aVrijemeNastankaLokacija;
	}
	
	
	
	
}
