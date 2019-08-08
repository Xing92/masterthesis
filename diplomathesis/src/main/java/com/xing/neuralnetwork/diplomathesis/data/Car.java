package com.xing.neuralnetwork.diplomathesis.data;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Car implements Entity {

	private static final double width = 20;

	private Shape shape;
	public double x;
	public double y;
	public double angle;
	private double speed = 0;
	private double acceleration = 50;
	private double rotationAcceleration = 50;
	private double size;
	private List<Ray> rays;
	private double timeLive;
	private double distance;
	private Movement movement;

	public Car(double x, double y, double size, double angle) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.angle = angle;
		shape = new Rectangle(x, y, width, width * size);
		// shape.setTranslateX(x); // TODO why do I need this?
		// shape.setTranslateY(y); // TODO why do I need this?
		shape.setRotate(angle);
		 this.rays = Ray.createRays(this);
		movement = new Movement();
	}

	public Shape getShape() {
		return shape;
	}

	@Override
	public void drawEntity(Group entities) {
		// TODO Auto-generated method stub

	}

	// public void rotate(double angleChange, double time) {
	// angle += angleChange * time;
	// if (angle > 360) {
	// angle -= 360;
	// }
	// shape.setRotate(angle);
	// }
	//
	// public void accelerate(double speedChange, double time) {
	// speed += speedChange * acceleration * time;
	// if (speed < 0)
	// speed = 0;
	// }

	public void rotate(double time) {
		angle += movement.getTurningDirection() * rotationAcceleration * time;
		if (angle > 360) {
			angle -= 360;
		}
		shape.setRotate(angle);
	}

	public void accelerate(double time) {
		speed += movement.getAccelerationDirection() * acceleration * time;
		if (speed < 0)
			speed = 0;
	}

	@Override
	public void updatePosition(double time) {

		double deltaX = Math.sin(angle * Math.PI / 180) * (speed * (1));
		double deltaY = Math.cos(angle * Math.PI / 180) * (speed * (-1));
		x += deltaX * time;
		y += deltaY * time;
		timeLive += time;
		distance += Math.sqrt(deltaX * time * deltaX * time + deltaY * time * deltaY * time);
		((Rectangle) shape).setX(x);
		((Rectangle) shape).setY(y);
		 rays.forEach(Ray::update);
	}
	
	public void updateMovement(double time) {
		accelerate(time);
		rotate(time);
	}

	public double getTimeLive() {
		return timeLive;
	}

	public double getDistance() {
		return distance;
	}

	public void changeMovement(Direction direction, boolean state) {
		movement.changeMovement(direction, state);
	}

}
