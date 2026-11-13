package com.mygdx.game;

//CONCRETE BUILDER DE nivel de Chiikawa: cae comida y basura
public class NivelChiikawaBuilder implements SecuenciaBuilder {
	private Secuencia secuencia;

	@Override
	public void reset() {
		secuencia = new Secuencia();
	}

	@Override
	public void construirPersonaje() {
		secuencia.setPersonaje(new Chiikawa());
	}

	@Override
	public void construirObjetos() {
		secuencia.agregarObjeto(Comida.FABRICA, 3);
		secuencia.agregarObjeto(Basura.FABRICA, 1);
	}

	@Override
	public void construirRitmo() {
		secuencia.setIntervalo(0.8f);
		secuencia.setVelocidadCaida(250);
	}

	@Override
	public Secuencia getResult() {
		return secuencia;
	}
}