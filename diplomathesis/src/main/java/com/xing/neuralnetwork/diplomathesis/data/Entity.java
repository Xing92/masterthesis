package com.xing.neuralnetwork.diplomathesis.data;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

public interface Entity {
	
	Shape getShape();
	void drawEntity(Group entities);
	void updatePosition(double time);

}
