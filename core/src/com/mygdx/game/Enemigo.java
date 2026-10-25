package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


public abstract class Enemigo extends Objeto{
	private int danio;
	
    public Enemigo(Texture imagen, float x, float y, float velocidad, int danio) {
        super(imagen, x, y, velocidad);
        this.danio = danio;
    }
 
    @Override
    public final void chocar(Personaje p) {
        atacar(p);
    }
 
    // quita 1 vida
    public void atacar(Personaje p) {
        p.daniar(danio);
    }
 
    public int getDanio() { return danio; }
}