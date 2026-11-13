package com.mygdx.game;
 
//BUILDER
//declara los pasos para construir un nivel.
//Cada nivel del juego es un builder concreto que implementa estos pasos, a su manera
public interface SecuenciaBuilder {
	void reset();               //empieza un nivel nuevo vacio
	void construirPersonaje();  //que personaje se usa
	void construirObjetos();    //que objetos caen y con que peso
	void construirRitmo();      //cada cuanto cae un objeto y a que velocidad
	Secuencia getResult();      //entrega el nivel terminado
}
 