package com.mygdx.game;

// CONCRETE BUILDER de nivel de Hachiware: cae comida y goblins.
public class NivelHachiwareBuilder implements SecuenciaBuilder {
	private Secuencia secuencia;

	@Override
	public void reset() {
		secuencia = new Secuencia();
	}

	@Override
	public void construirPersonaje() {
		secuencia.setPersonaje(new Hachiware());
	}

	@Override
	public void construirObjetos() {
		secuencia.agregarObjeto(Comida.FABRICA, 3);
		secuencia.agregarObjeto(Goblin.FABRICA, 1);
	}

	@Override
	public void construirRitmo() {
		secuencia.setIntervalo(0.7f);
		secuencia.setVelocidadCaida(270);
	}

	@Override
	public Secuencia getResult() {
		return secuencia;
	}
}