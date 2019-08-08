package com.xing.neuralnetwork.diplomathesis.data;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class Obstacle implements Entity {

	private Shape shape;
	List<Vec2d> vertices = new ArrayList();
	List<Line> lines = new ArrayList();

	public Shape getShape() {
		Path p = new Path();
		return shape;
	}

	public void addVetex(Vec2d vertex) {
		vertices.add(vertex);
	}
	
	public List<Line> getLines(){
		return lines;
	}

	@Override
	public void drawEntity(Group entities) {
		Vec2d v1 = vertices.get(0);
		Vec2d v2;
		for (int i = 1; i < vertices.size(); i++) {
			v2 = vertices.get(i);
			Line line = new Line(v1.x, v1.y, v2.x, v2.y);
			lines.add(line);
			entities.getChildren().add(line);
			v1 = v2;
		}
	}

	@Override
	public void updatePosition(double time) {
		// TODO Auto-generated method stub
	}

}
