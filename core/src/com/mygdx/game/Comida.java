package com.mygdx.game;

public class Comida extends Objeto implements Consumible {
	public Comida(float x, float y, float velocidad){ 
		super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_COMIDA), x, y, velocidad);
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
