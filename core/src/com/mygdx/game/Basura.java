package com.mygdx.game;

public class Basura implements Consumible {
	private Jugador j; //referencia al jugador 
	public Basura(Jugador ju){ //guarda al jugador
		j= ju; 
	}
	@Override
	public void afectar(){
		j.recibirDano(1); //pierde una vida
	}
}
