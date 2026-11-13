package com.mygdx.game;

// CONCRETE BUILDER DE nivel de Usagi: cae comida, estrellas buenas y estrellas malas.
public class NivelUsagiBuilder implements SecuenciaBuilder {
	private Secuencia secuencia;

	@Override
	public void reset() {
		secuencia = new Secuencia();
	}

	@Override
	public void construirPersonaje() {
		secuencia.setPersonaje(new Usagi());
	}

	@Override
	public void construirObjetos() {
		secuencia.agregarObjeto(Comida.FABRICA, 3);
		secuencia.agregarObjeto(EstrellaBuena.FABRICA, 0.5f);
		secuencia.agregarObjeto(EstrellaMala.FABRICA, 1.5f);
	}

	@Override
	public void construirRitmo() {
		secuencia.setIntervalo(0.5f);
		secuencia.setVelocidadCaida(300);
	}

	@Override
	public Secuencia getResult() {
		return secuencia;
	}
}