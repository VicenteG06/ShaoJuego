package com.mygdx.game;

public class EstrellaMala extends Enemigo {
	private Personaje j; //referencia al jugador
	//constructor
	public EstrellaMala(Personaje ju){ //darle valor a d y v. cuando se decida, cambiar
		setDano(ju.getVidas());
		setVelocidad(X); //NO SE
		j= ju;
	}
	@Override
	public void atacar(){
		j.daniar(getDano()); 
	}	
}
