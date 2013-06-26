package com.kaleb.math;

public class Vector2<T> {
	private T data1;
	private T data2;
	
	public Vector2(T d1, T d2) {
		data1 = d1;
		data2 = d2;
	}

	public T getI() { return data1; }
	public T getX() { return data1; }
	public T getJ() { return data2; }
	public T getY() { return data2; }
	
	public void setI(T val) { data1 = val; }
	public void setX(T val) { data1 = val; }
	public void setJ(T val) { data2 = val; }
	public void setY(T val) { data2 = val; }
}
