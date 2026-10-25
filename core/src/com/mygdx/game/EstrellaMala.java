package com.mygdx.game;

public class EstrellaMala extends Enemigo {
    private static final float FACTOR_VELOCIDAD = 2.5f;   // 2.5 veces mas rapido que lo demas

    public EstrellaMala(float x, float y, float velocidadCaida) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_GOBLIN), x, y, velocidadCaida * FACTOR_VELOCIDAD, 1);
    }

    @Override
    public void mover(float delta) {
        caer(delta);
    }
}
