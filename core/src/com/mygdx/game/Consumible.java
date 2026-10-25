package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public interface Consumible {
	void actualizar(float delta, Personaje p);
	void dibujar(SpriteBatch batch);
	boolean estaActivo();
	void afectar(Personaje p);
}
