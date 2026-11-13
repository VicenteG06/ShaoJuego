package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
public class EstrellaMala extends Enemigo {

	//""receta" para crear este objeto (la usa Builder en ShaoJuego)
	public static final FabricaObjeto FABRICA = new FabricaObjeto() {
		@Override
		public Objeto crear(float velocidad) {
			return new EstrellaMala(velocidad);
		}
	};
    private static final float FACTOR_VELOCIDAD = 1.6f;

    private float direccion;   //1 = hacia la derecha, -1 = hacia la izquierda

    //se elige de manera aleatoria de que lado aparece
    public EstrellaMala(float velocidadCaida) {
        this(velocidadCaida, MathUtils.random(0, 1) == 0);
    }

    private EstrellaMala(float velocidadCaida, boolean desdeIzquierda) {
        super(GestorRecursos.getInstancia().getTextura(GestorRecursos.IMG_ESTRELLA_MALA),
              xDeEntrada(desdeIzquierda), alturaAlAzar(), velocidadCaida * FACTOR_VELOCIDAD, 1);
        if (desdeIzquierda) {
            direccion = 1;
        } else {
            direccion = -1;
        }
    }

    private static float xDeEntrada(boolean desdeIzquierda) {
        if (desdeIzquierda) {
            return -TAMANIO;
        }
        return ANCHO_PANTALLA;
    }

    
    private static float alturaAlAzar() {
        return MathUtils.random(0, TipoPersonaje.ALTO_JUGABLE - TAMANIO); //altura al azar dentro de la zona donde Usagi se puede mover
    }

    @Override
    public void mover(float delta) {
        desplazar(direccion * getVelocidad() * delta, 0);
    }

    //sobrescribe el ataque para quitar todas las vidas que le queden
    @Override
    public void atacar(Personaje p) {
        p.daniar(p.getVidas());
    }
}