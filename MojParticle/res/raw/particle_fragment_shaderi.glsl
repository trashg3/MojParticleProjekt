precision mediump float;

varying vec3 v_Boja;
varying float v_ProtekloVrijeme;

void main(){

	float xDistance = 0.5 - gl_PointCoord.x;
	float yDistance = 0.5 - gl_PointCoord.y;
	float udaljenostOdCentra = sqrt(xDistance * xDistance + yDistance * yDistance);
	
	if (udaljenostOdCentra > 0.5){
		discard;
	}else{
		gl_FragColor = vec4(v_Boja / v_ProtekloVrijeme, 1.0);
	}
	
}