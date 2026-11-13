package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Comida extends Objeto{

	//""receta" para crear este objeto (la usan los builders de los niveles)
	public static final FabricaObjeto FABRICA = new FabricaObjeto() {
		@Override
		public Objeto crear(float velocidad) {
			return new Comida(velocidad);
		}
	};
	public Comida(float velocidad) {
		super(GestorRecursos.getInstancia().getTextura(elegirComida()), xAlAzar(), arriba(), velocidad);
	}
	
	private static String elegirComida() {
		int indice = MathUtils.random(0, GestorRecursos.IMG_COMIDAS.length - 1);
		return GestorRecursos.IMG_COMIDAS[indice];
	}
	
	@Override
	public void mover(float delta) {
		caer(delta);
	}
	
	@Override
	public void chocar(Personaje p) {
		p.comer();
	}
	
}