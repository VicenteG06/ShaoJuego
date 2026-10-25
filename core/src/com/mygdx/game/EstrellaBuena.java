package com.mygdx.game;

public class EstrellaBuena extends Objeto implements Consumible {
	 
    public EstrellaBuena(float x, float y, float velocidad) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_ESTRELLA_BUENA), x, y, velocidad);
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
    public void afectar(Personaje p) {
        p.ganarVida();
    }
}
