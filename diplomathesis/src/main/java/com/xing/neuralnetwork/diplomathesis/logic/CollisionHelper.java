package com.xing.neuralnetwork.diplomathesis.logic;

import java.util.List;

import com.xing.neuralnetwork.diplomathesis.data.Entity;
import com.xing.neuralnetwork.diplomathesis.data.Obstacle;

import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class CollisionHelper {

	public static boolean isCollided(Entity entity1, Entity entity2) {

		boolean result = false;
		if (entity2 instanceof Obstacle) {
			List<Line> lines = ((Obstacle) entity2).getLines();
			for (Line line : lines) {
				result |= !((Path) Shape.intersect(entity1.getShape(), line)).getElements().isEmpty();
			}
		} else {
			Shape resultShape = Shape.intersect(entity1.getShape(), entity2.getShape());
			result = !((Path) resultShape).getElements().isEmpty();
		}
		return result;
	}

}
