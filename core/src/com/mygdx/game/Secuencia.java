package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;

//product del patrón builder (un nivel del juego)
//lo arma un SecuenciaBuilder concreto
public class Secuencia {
	private TipoPersonaje personaje;
	private List<OpcionObjeto> opciones = new ArrayList<OpcionObjeto>();
	private float intervalo = 0.8f;        // segundos entre un objeto y el siguiente
	private float velocidadCaida = 250f;   // pixeles por segundo

	public void setPersonaje(TipoPersonaje personaje) { this.personaje = personaje; }
	public void setIntervalo(float intervalo) { this.intervalo = intervalo; }
	public void setVelocidadCaida(float velocidadCaida) { this.velocidadCaida = velocidadCaida; }

	// agrega un tipo de objeto que puede caer en este nivel y que tan seguido sale
	public void agregarObjeto(FabricaObjeto fabrica, float peso) {
		opciones.add(new OpcionObjeto(fabrica, peso));
	}

	// sortea un objeto segun los pesos y se lo entrega a la lluvia
	public void generarObjeto(Lluvia lluvia) {
		if (opciones.isEmpty()) {
			return;
		}
		float sorteo = MathUtils.random(0f, getPesoTotal());

		OpcionObjeto elegida = opciones.get(opciones.size() - 1);
		float acumulado = 0;
		for (OpcionObjeto opcion : opciones) {
			acumulado += opcion.getPeso();
			if (sorteo < acumulado) {
				elegida = opcion;
				break;
			}
		}

		lluvia.agregar(elegida.getFabrica().crear(velocidadCaida));
	}

	public float getPesoTotal() {
		float total = 0;
		for (OpcionObjeto opcion : opciones) {
			total += opcion.getPeso();
		}
		return total;
	}

	public TipoPersonaje getPersonaje() { return personaje; }
	public float getIntervalo() { return intervalo; }
	public float getVelocidadCaida() { return velocidadCaida; }
}