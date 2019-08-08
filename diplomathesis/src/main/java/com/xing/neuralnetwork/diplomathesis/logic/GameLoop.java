package com.xing.neuralnetwork.diplomathesis.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.xing.neuralnetwork.diplomathesis.App;
import com.xing.neuralnetwork.diplomathesis.data.Car;
import com.xing.neuralnetwork.diplomathesis.data.Direction;
import com.xing.neuralnetwork.diplomathesis.data.Obstacle;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;

public class GameLoop extends AnimationTimer {

	private long previousNanoTime;
	private static final double speed = 1; // to speedup / slow down game
	private double gameSpeed = 0; // update in each handle
	private List<Car> cars = new ArrayList();
	private List<Obstacle> walls = new ArrayList();
	private Group entities;

	public GameLoop(Group entities, long previousNanoTime) {
		this.entities = entities;
		this.previousNanoTime = previousNanoTime;
	}

	public void setWalls(List<Obstacle> walls) {
		this.walls = walls;
	}

	public List<Car> createCars() {
		Car car = new Car(160, 320, 2, 0);
		cars.add(car);

		// Car car2 = new Car(130, 150, 2, 90);
		// cars.add(car2);

		return cars;
	}

	@Override
	public void handle(long currentNanoTime) {
		double deltaT = currentNanoTime - previousNanoTime;
		previousNanoTime = currentNanoTime;
		gameSpeed = speed * deltaT / 1_000_000_000;
		// System.out.println("FPS:" + 1 / gameSpeed);
		//

		App.fpsLabel.setText(String.format("FPS: %.3f",  1 / gameSpeed));
		updateCars(gameSpeed);
		detectCollisions();
		// cars.forEach(car -> car.rotate(45, gameSpeed));
	}

	private void detectCollisions() {
		AtomicBoolean resetLoop = new AtomicBoolean(false);
		cars.forEach(car -> {
			walls.forEach(wall -> {
				if (CollisionHelper.isCollided(car, wall)) {
					System.out.println("Collided!");
					resetLoop.set(true);
				}
			});
		});
		if (resetLoop.get()) {
			resetLoop();
		}
	}

	private void updateCars(double gameSpeed) {
		cars.forEach(car -> car.updateMovement(gameSpeed));
		cars.forEach(car -> car.updatePosition(gameSpeed));
		App.distanceLabel.setText(String.format("Distance: %.3f", cars.get(0).getDistance()));
		App.timeLiveLabel.setText(String.format("Time: %.3f", cars.get(0).getTimeLive()));
		App.fitnessLabel.setText(String.format("Fitness: %.3f", cars.get(0).getDistance() / cars.get(0).getTimeLive()));
	}

	private void resetLoop() {
		cars.forEach(car -> entities.getChildren().remove(car.getShape()));
		cars.clear();
		cars = createCars();
		cars.forEach(car -> entities.getChildren().add(car.getShape()));
	}

	public void handleInput(KeyCode key, boolean pressed) {
		if (key == KeyCode.UP) {
			cars.get(0).changeMovement(Direction.FORWARD, pressed);
		} else if (key == KeyCode.DOWN) {
			cars.get(0).changeMovement(Direction.BACKWARD, pressed);
		} else if (key == KeyCode.LEFT) {
			cars.get(0).changeMovement(Direction.LEFT, pressed);
		} else if (key == KeyCode.RIGHT) {
			cars.get(0).changeMovement(Direction.RIGHT, pressed);
		}
	}

	// public void handleInput(KeyCode key) {
	// if (key == KeyCode.UP) {
	// cars.get(0).accelerate(1, gameSpeed * 10);
	// } else if (key == KeyCode.DOWN) {
	// cars.get(0).accelerate(-1, gameSpeed * 10);
	// } else if (key == KeyCode.LEFT) {
	// cars.get(0).rotate(-45, gameSpeed * 10);
	// } else if (key == KeyCode.RIGHT) {
	// cars.get(0).rotate(45, gameSpeed * 10);
	// }
	// }

}
