package com.example.jsonexample;

public class Fruta
{
	private String nombre;
	private int unidades;

	public Fruta(String nombre, int unidades)
	{
		this.nombre = nombre;
		this.unidades = unidades;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getUnidades()
	{
		return unidades;
	}

	@Override
	public String toString()
	{
		return nombre + " (" + unidades + ")";
	}
}
