package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

// ESTO ES PARA IMPLEMENTAR EL BUILDER

public class Secuencia {
	private TipoPersonaje personaje;
    private float probComida;
    private float probBasura;
    private float probGoblin;
    private float probEstrellaBuena;
    private float probEstrellaMala;
    private float intervalo;        // El intervalo de segundos entre un objeto y el siguiente
    private float velocidadCaida;   // La velocidad va enpixeles por segundo
 
    public Secuencia(Builder builder) {
        this.personaje = builder.getPersonaje();
        this.probComida = builder.getProbComida();
        this.probBasura = builder.getProbBasura();
        this.probGoblin = builder.getProbGoblin();
        this.probEstrellaBuena = builder.getProbEstrellaBuena();
        this.probEstrellaMala = builder.getProbEstrellaMala();
        this.intervalo = builder.getIntervalo();
        this.velocidadCaida = builder.getVelocidadCaida();
    }
 
 
    public void generarObjeto(Lluvia lluvia) {
        float x = MathUtils.random(0, Objeto.ANCHO_PANTALLA - Objeto.TAMANIO);
        float y = Objeto.ALTO_JUGABLE;
        float random = MathUtils.random(0f, getPesoTotal());
 
        float acumulado = probComida;
        if (random < acumulado) {
            lluvia.agregarConsumible(new Comida(x, y, velocidadCaida));
            return;
        }
        acumulado += probBasura;
        if (random < acumulado) {
            lluvia.agregarConsumible(new Basura(x, y, velocidadCaida));
            return;
        }
        acumulado += probEstrellaBuena;
        if (random < acumulado) {
            lluvia.agregarConsumible(new EstrellaBuena(x, y, velocidadCaida));
            return;
        }
        acumulado += probGoblin;
        if (random < acumulado) {
            lluvia.agregarEnemigo(new Goblin(x, y, velocidadCaida));
            return;
        }
        acumulado += probEstrellaMala;
        if (random < acumulado) {
            lluvia.agregarEnemigo(new EstrellaMala(x, y, velocidadCaida));
            return;
        }
        lluvia.agregarConsumible(new Comida(x, y, velocidadCaida));
    }
 
    public float getPesoTotal() {
        return probComida + probBasura + probGoblin + probEstrellaBuena + probEstrellaMala;
    }
 
    public TipoPersonaje getPersonaje() { return personaje; }
    public float getIntervalo() { return intervalo; }
    public float getVelocidadCaida() { return velocidadCaida; }
}
