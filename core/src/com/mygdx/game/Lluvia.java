package com.mygdx.game;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;


public class Lluvia {
    private ArrayList<Consumible> consumibles;
    private ArrayList<Enemigo> enemigos;
    private Secuencia secuencia;
    private float tiempoDesdeUltimo = 0;
 
    public Lluvia(Secuencia faseInicial) {
        this.secuencia = faseInicial;
        consumibles = new ArrayList<Consumible>();
        enemigos = new ArrayList<Enemigo>();
    }
 
    public void crear() {
        Music musica = GestorRecursos.getInstancia().getMusica(GestorRecursos.MUSICA_FONDO);
        musica.setLooping(true);
        musica.play();
    }

    public void setSecuencia(Secuencia nuevaFase) {
        this.secuencia = nuevaFase;
        reiniciar();
    }
 
    public void reiniciar() {
        consumibles.clear();
        enemigos.clear();
        tiempoDesdeUltimo = 0;
    }
 
    public void agregarConsumible(Consumible consumible) {
        consumibles.add(consumible);
    }
 
    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }
 
    public void actualizar(float delta, Personaje personaje) {
        tiempoDesdeUltimo += delta;
        if (tiempoDesdeUltimo >= secuencia.getIntervalo()) {
            secuencia.generarObjeto(this);
            tiempoDesdeUltimo = 0;
        }
 
        for (int i = consumibles.size() - 1; i >= 0; i--) {
            Consumible c = consumibles.get(i);
            c.actualizar(delta, personaje);
            if (!c.estaActivo()) {
                consumibles.remove(i);
            }
        }
        for (int i = enemigos.size() - 1; i >= 0; i--) {
            Enemigo e = enemigos.get(i);
            e.actualizar(delta, personaje);
            if (!e.estaActivo()) {
                enemigos.remove(i);
            }
        }
    }
 
    public void dibujar(SpriteBatch batch) {
        for (Consumible c : consumibles) {
            c.dibujar(batch);
        }
        for (Enemigo e : enemigos) {
            e.dibujar(batch);
        }
    }
 
    public void destruir() {
        GestorRecursos.getInstancia().getMusica(GestorRecursos.MUSICA_FONDO).stop();
    }
}
