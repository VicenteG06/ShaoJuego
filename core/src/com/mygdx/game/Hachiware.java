package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

//ESTRATEGIA CONCRETA: Hachiware se mueve de lado a lado y puede dashear con el shift
public class Hachiware extends TipoPersonaje {
	private static final float VELOCIDAD_DASH = 1200f;//pixeles por segundo durante el dash
	private static final float DURACION_DASH = 0.15f;//cuanto dura el dash, en segundos
	private static final float RECARGA_DASH = 0.8f; // tiempo de espera entre un dash y otro

	private float tiempoDash = 0; //mientras sea mayor que 0, esta haciendo el dash
	private float tiempoRecarga = 0;// mientras sea mayor que 0, no puede volver a hacer dash (Cooldown)
	private int direccion = 1; // hacia donde mira para hacer el dash: 1 = derecha, -1 = izquierda

	public Hachiware() {
		super("Hachiware", GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_HACHIWARE), 25, 400);
	}

	@Override
	public void mover(Rectangle area, float delta) {
		if (tiempoRecarga > 0) {
			tiempoRecarga -= delta;
		}

		//si esta en medio de un dash, no se puede cambiar de direccion
		if (tiempoDash > 0) {
			tiempoDash -= delta;
			area.x += direccion * VELOCIDAD_DASH * delta;
			limitarHorizontal(area);
			return;
		}
		super.mover(area, delta);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			direccion = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			direccion = 1;
		}

		//empieza un dash si se aprieta shift y ya se recargo
		boolean apretoShift = Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)
				|| Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT);
		if (apretoShift && tiempoRecarga <= 0) {
			tiempoDash = DURACION_DASH;
			tiempoRecarga = RECARGA_DASH;
		}
	}

	//anuncio para avisar al jugador que puede dashear
	@Override
	public String getMensajeInicio() {
		return "SHIFT PARA DASH";
	}
}