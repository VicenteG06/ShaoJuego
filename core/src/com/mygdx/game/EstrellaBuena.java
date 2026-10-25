package com.mygdx.game;

public class EstrellaBuena implements Consumible {
	private Jugador j; //referencia al jugador 
	public EstrellaBuena(Jugador ju){ //guarda al jugador
		j= ju; 
	}
	@Override
	public void afectar(){
		if (j.getVidas() < 3) {
			j.setVidas( j.getVidas() + 1);
		}
	}
}
