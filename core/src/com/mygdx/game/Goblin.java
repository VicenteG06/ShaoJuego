package com.mygdx.game;

public class Goblin extends Enemigo { //
	private Personaje j; //referencia al jugador
	//constructor
	public Goblin(Personaje ju){ //darle velocidad
		setDano(1);
		setVelocidad(X); //NO SE 
		j= ju;
	}
	@Override
	public void atacar(){
		j.daniar(getDano());
	}	
	
}
