precision mediump float;

varying vec3 v_Boja;
varying float v_ProtekloVrijeme;

void main(){

	gl_FragColor = vec4(v_Boja / v_ProtekloVrijeme, 1.0);
	
	}