uniform mat4 u_Matrix;
uniform float u_Vrijeme;

attribute vec3 a_Posicija;
attribute vec3 a_Boja;
attribute vec3 a_Vektor;
attribute float a_VrijemeNastanka;

varying vec3 v_Boja;
varying float v_ProtekloVrijeme;

void main(){

	v_Boja = a_Boja;
	v_ProtekloVrijeme = u_Vrijeme - a_VrijemeNastanka;
	float gravitacija = v_ProtekloVrijeme * v_ProtekloVrijeme / 8.0;
	vec3 TrenutnaPozicija = a_Posicija + (a_Vektor * v_ProtekloVrijeme);
	TrenutnaPozicija.y -= gravitacija; 
	gl_Position = u_Matrix * vec4(TrenutnaPozicija, 1.0);
	gl_PointSize = 25.0;	

}
	