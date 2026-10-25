package com.mygdx.game;

public class Basura implements Consumible {
	private Personaje j; //referencia al jugador 
	public Basura(Personaje ju){ //guarda al jugador
		j= ju; 
	}
	@Override
	public void afectar(){
		j.daniar(1); //pierde una vida
	}
}
