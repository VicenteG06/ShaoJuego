package com.mygdx.game;

public class EstrellaBuena extends Objeto implements Consumible {
	private static final float FACTOR_VELOCIDAD = 2f;
    public EstrellaBuena(float x, float y, float velocidad) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_ESTRELLA_BUENA), x, y, velocidad* FACTOR_VELOCIDAD);
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
