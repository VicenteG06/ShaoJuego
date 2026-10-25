package com.mygdx.game;

// ESTO ES PARA EL PATRON BUILDER

public class Builder {
	private TipoPersonaje personaje;
    private float probComida = 0;
    private float probBasura = 0;
    private float probGoblin = 0;
    private float probEstrellaBuena = 0;
    private float probEstrellaMala = 0;
    private float intervalo = 0.8f;
    private float velocidadCaida = 250f;
 
    public Builder personaje(TipoPersonaje personaje) {
        this.personaje = personaje;
        return this;
    }
 
    public Builder probComida(float peso) {
        this.probComida = peso;
        return this;
    }
 
    public Builder probBasura(float peso) {
        this.probBasura = peso;
        return this;
    }
 
    public Builder probGoblin(float peso) {
        this.probGoblin = peso;
        return this;
    }
 
    public Builder probEstrellaBuena(float peso) {
        this.probEstrellaBuena = peso;
        return this;
    }
 
    public Builder probEstrellaMala(float peso) {
        this.probEstrellaMala = peso;
        return this;
    }
 
    public Builder intervalo(float segundos) {
        this.intervalo = segundos;
        return this;
    }
 
    public Builder velocidadCaida(float pixelesPorSegundo) {
        this.velocidadCaida = pixelesPorSegundo;
        return this;
    }
 
    public Secuencia build() {
        return new Secuencia(this);
    }
 
    // Los getters que va a usar el constructor de la clase Fase unu
    TipoPersonaje getPersonaje() { return personaje; }
    float getProbComida() { return probComida; }
    float getProbBasura() { return probBasura; }
    float getProbGoblin() { return probGoblin; }
    float getProbEstrellaBuena() { return probEstrellaBuena; }
    float getProbEstrellaMala() { return probEstrellaMala; }
    float getIntervalo() { return intervalo; }
    float getVelocidadCaida() { return velocidadCaida; }
}
