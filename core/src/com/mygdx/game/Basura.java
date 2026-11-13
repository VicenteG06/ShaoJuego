package com.mygdx.game;

public class Basura extends Objeto implements Consumible {

	//""receta" para crear este objeto (la usan los builders de los niveles)
	public static final FabricaObjeto FABRICA = new FabricaObjeto() {
		@Override
		public Objeto crear(float velocidad) {
			return new Basura(velocidad);
		}
	};
	public Basura(float velocidad) {
		super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_BASURA), xAlAzar(), arriba(), velocidad);
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
	public void afectar(Personaje p){
		p.daniar(1); //pierde una vida
	}
}