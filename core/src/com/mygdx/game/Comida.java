package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Comida extends Objeto implements Consumible {
	public Comida(float x, float y, float velocidad){ 
		super(GestorRecursos.getInstancia().getTextura(elegirComida()), x, y, velocidad); 
	}
	
	private static String elegirComida() {
		int indice = MathUtils.random(0, GestorRecursos.IMG_COMIDAS.length - 1);
		return GestorRecursos.IMG_COMIDAS[indice];
	}
	
	@Override
	public void mover(float delta) {
		caer(delta);
	}
	
	@Override
	public void chocar(Personaje p) {
		afectar(p);
	}
	
	@Override
	public void afectar(Personaje p){
		p.comer(); //le anade comida. Comer depende del personaje con el que se este jugando
	}
}