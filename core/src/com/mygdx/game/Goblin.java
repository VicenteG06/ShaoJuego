package com.mygdx.game;

public class Goblin extends Enemigo { //
	private Jugador j; //referencia al jugador
	//constructor
	public Goblin(Jugador ju){ //darle velocidad
		setDano(1);
		setVelocidad(X); //NO SE 
		j= ju;
	}
	@Override
	public void atacar(){
		j.recibirDano(getDano());
	}	
	
}
