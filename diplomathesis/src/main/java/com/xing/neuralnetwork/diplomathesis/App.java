package com.xing.neuralnetwork.diplomathesis;

import java.util.ArrayList;
import java.util.List;

import com.xing.neuralnetwork.diplomathesis.data.Car;
import com.xing.neuralnetwork.diplomathesis.data.Entity;
import com.xing.neuralnetwork.diplomathesis.data.Obstacle;
import com.xing.neuralnetwork.diplomathesis.data.Ray;
import com.xing.neuralnetwork.diplomathesis.logic.GameLoop;
import com.xing.neuralnetwork.diplomathesis.logic.MapHelper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
	public static final double SCREEN_HEIGHT = 900;
	public static final double SCREEN_WIDTH = 1400;
	private static Scene scene;
	private static Group root;
	public static Group entities;
	private List<Car> cars = new ArrayList();
	private List<Line> lines = new ArrayList();
	public static Label distanceLabel;
	public static Label timeLiveLabel;
	public static Label fitnessLabel;
	public static Label fpsLabel;
	GameLoop gameLoop;

	Button respawnCarsButton;

	public static void main(String[] args) {
		System.out.println("Hello World!");
		launch();
		System.out.println("Goodbye World!");
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("XING - Road Crrrasherrr...");

		entities = new Group();
		root = new Group();
		root.getChildren().add(entities);
		scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		scene.setOnKeyPressed(handleInputPressedListener());
		scene.setOnKeyReleased(handleInputReleasedListener());

		respawnCarsButton = new Button("Respawn Cars");
		root.getChildren().add(respawnCarsButton);
		respawnCarsButton.setOnAction(respawnCarsListener());

		distanceLabel = new Label("Distance: ");
		distanceLabel.setLayoutX(30);
		distanceLabel.setLayoutY(30);
		root.getChildren().add(distanceLabel);
		
		timeLiveLabel = new Label("Time: ");
		timeLiveLabel.setLayoutX(30);
		timeLiveLabel.setLayoutY(60);
		root.getChildren().add(timeLiveLabel);

		fitnessLabel = new Label("Fitness: ");
		fitnessLabel.setLayoutX(30);
		fitnessLabel.setLayoutY(90);
		root.getChildren().add(fitnessLabel);

		fpsLabel = new Label("Fps: ");
		fpsLabel.setLayoutX(130);
		fpsLabel.setLayoutY(30);
		root.getChildren().add(fpsLabel);
		
		MapHelper mh = new MapHelper(entities);
		List<Obstacle> walls = mh.prepareMap1();

		gameLoop = new GameLoop(entities, System.nanoTime());
		gameLoop.setWalls(walls);

		cars = gameLoop.createCars();
		cars.forEach(car -> addEntity(car));
		gameLoop.start();

		stage.setScene(scene);
		stage.show();
	}

	private EventHandler respawnCarsListener() {
		return ae -> {
			gameLoop.stop();

			entities.getChildren().clear();
			gameLoop = new GameLoop(entities, System.nanoTime());
			MapHelper mh = new MapHelper(entities);
			List<Obstacle> walls = mh.prepareMap1();
			gameLoop.setWalls(walls);
			cars = gameLoop.createCars();
			cars.forEach(car -> addEntity(car));

			//
			// Circle shape = new Circle(80, 150, 2, Color.RED);
//			Rectangle shape = new Rectangle(80, 150, 20, 20);
//			addNode(shape);
			//

			gameLoop.start();
//			Ray.createRays(cars.get(0));

		};
	}

	private void addEntity(Entity entity) {
		addNode(entity.getShape());
	}

	private void addNode(Node node) {
		entities.getChildren().add(node);
	}

	
	private EventHandler handleInputReleasedListener() {
		return ae -> {
			KeyEvent ke = (KeyEvent) ae;
			gameLoop.handleInput(ke.getCode(), false);
		};
	}
	
	private EventHandler handleInputPressedListener() {
		return ae -> {
			KeyEvent ke = (KeyEvent) ae;
			gameLoop.handleInput(ke.getCode(), true);
		};
	}
}
