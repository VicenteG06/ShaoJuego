package com.mygdx.game;

public class Goblin extends Enemigo {

	//""receta"" para crear este objeto (la usan los builders de los niveles))
	public static final FabricaObjeto FABRICA = new FabricaObjeto() {
		@Override
		public Objeto crear(float velocidad) {
			return new Goblin(velocidad);
		}
	};
    private static final float FACTOR_VELOCIDAD = 1.5f;   // .5 veces mas rapido que lo demas

    public Goblin(float velocidadCaida) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_GOBLIN), xAlAzar(), arriba(), velocidadCaida * FACTOR_VELOCIDAD, 1);
    }

    @Override
    public void mover(float delta) {
        caer(delta);
    }
}