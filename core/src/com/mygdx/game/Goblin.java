package com.mygdx.game;

public class Goblin extends Enemigo { //
    private static final float FACTOR_VELOCIDAD = 1.5f;   // .5 veces mas rapido que lo demas

    public Goblin(float x, float y, float velocidadCaida) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_GOBLIN), x, y, velocidadCaida * FACTOR_VELOCIDAD, 1);
    }

    @Override
    public void mover(float delta) {
        caer(delta);
    }
}
