package com.mygdx.game;

public class Basura extends Objeto implements Consumible {
	public Basura(float x, float y, float velocidad){ 
		super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_BASURA), x, y, velocidad);
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
		p.daniar(1); //pierde una vida
	}
}
