package com.example.jsonexample;

import java.util.List;

public class ObjectoJSON
{
	private List<Fruta> frutas;

	public ObjectoJSON(List<Fruta> frutas)
	{
		this.frutas = frutas;
	}

	public List<Fruta> getFrutas()
	{
		return frutas;
	}

	@Override
	public String toString()
	{
		return "Frutas{" +
				"frutas=" + frutas +
				'}';
	}
}
