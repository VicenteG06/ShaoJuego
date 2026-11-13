package com.mygdx.game;

// Un objeto que puede caer en un nivel y que tan seguido sale (su peso).

public class OpcionObjeto {
	private FabricaObjeto fabrica;
	private float peso;
	//ej:comida con peso 3 y basura con peso 1:3 de cada 4 objetos son comida aprox
	public OpcionObjeto(FabricaObjeto fabrica, float peso) {
		this.fabrica = fabrica;
		this.peso = peso;
	}

	public FabricaObjeto getFabrica() { return fabrica; }
	public float getPeso() { return peso; }
}