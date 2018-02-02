/**
 * 
 */
package com.dituhui.saas.alan.a.in;

import java.io.Serializable;

/**
 * 点实体
 */
public class Point implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * x坐标
	 */
	private double x;
	/**
	 * y坐标
	 */
	private double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Point() {
		super();
	}
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return x + "," + y;
	}
}
