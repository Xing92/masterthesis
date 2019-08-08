package com.xing.neuralnetwork.diplomathesis.data;

import java.util.ArrayList;
import java.util.List;

import com.xing.neuralnetwork.diplomathesis.App;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Ray {

	private static final double startAngle = -90;
	private static final int numberOfRays = 5;
	private static final double angleDelta = (-startAngle) * 2 / (numberOfRays - 1);

	private Line line;
	private Car car;

	private Circle tempCircle;

	private Ray(Car car, double angle) {
		this.car = car;
		Rectangle carR = (Rectangle) car.getShape();
		// TODO: Use the midle point of car, not the upper-left corner
		// double x2 = carR.getX() + (carR.getWidth() / 2) * Math.sin(carR.getRotate());
		// double y2 = carR.getY() - (carR.getWidth() / 2) * Math.cos(carR.getRotate());
		double x2 = carR.getX() + (carR.getWidth() / 2);
		double y2 = carR.getY() + (carR.getHeight() / 2);
		System.out.println(x2);
		System.out.println(y2);
		tempCircle = new Circle(x2, y2, 2, Color.RED);
		App.entities.getChildren().add(tempCircle);
	}

	public void update() {
		Rectangle carR = (Rectangle) car.getShape();
		double x2 = carR.getX() + (carR.getWidth() / 2);
		double y2 = carR.getY() + (carR.getHeight() / 2);
		tempCircle.setCenterX(x2);
		tempCircle.setCenterY(y2);
	}

	public static List<Ray> createRays(Car car) {
		List<Ray> rays = new ArrayList();
		rays.add(new Ray(car, 0));
		// for (double angle = startAngle; angle <= -startAngle; angle += angleDelta) {
		// System.out.println("Angle:" + angle);
		// }
		return rays;
	}

}
