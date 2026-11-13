package com.mygdx.game;

//DIRECTOR: define ORDEN en que se construyen los niveles
public class DirectorNiveles {
	private SecuenciaBuilder builder;

	public DirectorNiveles(SecuenciaBuilder builder) {
		this.builder = builder;
	}

	public Secuencia construirNivel() {
		builder.reset();
		builder.construirPersonaje();
		builder.construirObjetos();
		builder.construirRitmo();
		return builder.getResult();
	}
}