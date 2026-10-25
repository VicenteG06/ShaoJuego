package com.mygdx.game;

public class EstrellaBuena implements Consumible {
	private Personaje j; //referencia al jugador 
	public EstrellaBuena(Personaje ju){ //guarda al jugador
		j= ju; 
	}
	@Override
	public void afectar(){
		j.ganarVida();
	}
}
