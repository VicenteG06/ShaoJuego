package com.mygdx.game;

public abstract class Enemigo {
	private int dano;
	private int velocidad; //esto depende de como se haga el spawn de los enemigos
	//setters
	public void setDano(int d) {dano= d;}
	public void setVelocidad(int v) {velocidad= v;}
	//getters
	public int getDano() { return dano; }
	public int getVelocidad() { return velocidad; }
	//metodos abstractos
	public abstract void atacar();
}