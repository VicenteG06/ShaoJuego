package com.mygdx.game;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;




public class Lluvia {
	private ArrayList<Objeto> objetos;
    private Secuencia secuencia;
    private float tiempoDesdeUltimo = 0;
 
    public Lluvia(Secuencia faseInicial) {
		this.secuencia = faseInicial;
		objetos = new ArrayList<Objeto>();
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
		objetos.clear();
		tiempoDesdeUltimo = 0;
	}
 
    public void agregar(Objeto objeto) {
		objetos.add(objeto);
	}
 
    public void actualizar(float delta, Personaje personaje) {
		tiempoDesdeUltimo += delta;
		if (tiempoDesdeUltimo >= secuencia.getIntervalo()) {
			secuencia.generarObjeto(this);
			tiempoDesdeUltimo = 0;
		}
 
		// se recorre de atras hacia adelante para poder borrar sin saltarse objetos
		for (int i = objetos.size() - 1; i >= 0; i--) {
			Objeto objeto = objetos.get(i);
			objeto.actualizar(delta, personaje);   // metodo plantilla
			if (!objeto.estaActivo()) {
				objetos.remove(i);
			}
		}
	}
 
    public void dibujar(SpriteBatch batch) {
		for (Objeto objeto : objetos) {
			objeto.dibujar(batch);
		}
	}
 
	public void destruir() {
		GestorRecursos.getInstancia().getMusica(GestorRecursos.MUSICA_FONDO).stop();
	}
}
