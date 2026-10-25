package com.mygdx.game;

public class Comida implements Consumible {
	private Personaje j; //referencia al jugador 
	public Comida(Personaje ju){ //guarda al jugador
		j= ju; 
	}
	@Override
	public void afectar(){
		j.comer(); //le anade comida. Comer depende del personaje con el que se este jugando
	}
}
