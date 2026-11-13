package com.mygdx.game;

public class EstrellaBuena extends Objeto implements Consumible {

	//""receta" para crear este objeto (la usan los builders de los niveles)
	public static final FabricaObjeto FABRICA = new FabricaObjeto() {
		@Override
		public Objeto crear(float velocidad) {
			return new EstrellaBuena(velocidad);
		}
	};
	private static final float FACTOR_VELOCIDAD = 2f;
    public EstrellaBuena(float velocidad) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_ESTRELLA_BUENA), xAlAzar(), arriba(), velocidad* FACTOR_VELOCIDAD);
    }
 
    @Override
    public void mover(float delta) {
        caer(delta);
    }
 
    @Override
    public void chocar(Personaje p) {
        afectar(p);
    }
 
    @Override
    public void afectar(Personaje p) {
        p.ganarVida();
    }
}