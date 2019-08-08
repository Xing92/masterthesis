package com.xing.neuralnetwork.diplomathesis.logic;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;
import com.xing.neuralnetwork.diplomathesis.data.Obstacle;

import javafx.scene.Group;

public class MapHelper {
	private Group entities;

	public MapHelper(Group entities) {
		this.entities = entities;
	}

	public List<Obstacle> prepareMap1() {
		List<Obstacle> walls = new ArrayList();

		walls.add(generateMaze1());
		walls.add(generateMaze2());

		return walls;
	}

	private Obstacle generateMaze1() { // Outer layer
		Obstacle obstacle = new Obstacle();

		obstacle.addVetex(new Vec2d(400, 75));
		obstacle.addVetex(new Vec2d(700, 75));
		obstacle.addVetex(new Vec2d(1000, 300));
		obstacle.addVetex(new Vec2d(1000, 300));
		obstacle.addVetex(new Vec2d(1000, 500));
		obstacle.addVetex(new Vec2d(700, 725));
		obstacle.addVetex(new Vec2d(400, 725));
		obstacle.addVetex(new Vec2d(100, 500));
		obstacle.addVetex(new Vec2d(100, 300));
		obstacle.addVetex(new Vec2d(400, 75));

		obstacle.drawEntity(entities);
		return obstacle;
	}

	private Obstacle generateMaze2() { // Inner Layer
		Obstacle obstacle = new Obstacle();

		obstacle.addVetex(new Vec2d(400, 150));
		obstacle.addVetex(new Vec2d(700, 150));
		obstacle.addVetex(new Vec2d(900, 300));
		obstacle.addVetex(new Vec2d(900, 500));
		obstacle.addVetex(new Vec2d(700, 650));
		obstacle.addVetex(new Vec2d(400, 650));
		obstacle.addVetex(new Vec2d(200, 500));
		obstacle.addVetex(new Vec2d(200, 300));
		obstacle.addVetex(new Vec2d(400, 150));

		obstacle.drawEntity(entities);
		return obstacle;
	}
}
