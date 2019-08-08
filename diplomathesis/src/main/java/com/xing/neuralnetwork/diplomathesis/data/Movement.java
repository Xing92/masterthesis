package com.xing.neuralnetwork.diplomathesis.data;

public class Movement {
	private boolean isTurningLeft;
	private boolean isTurningRight;
	private boolean isAccelerating;
	private boolean isDeccelerating;

	public boolean isTurningLeft() {
		return isTurningLeft;
	}

	public boolean isTurningRight() {
		return isTurningRight;
	}

	public boolean isAccelerating() {
		return isAccelerating;
	}

	public boolean isDeccelerating() {
		return isDeccelerating;
	}

	public int getTurningDirection() {
		if (isTurningLeft)
			return -1;
		if (isTurningRight)
			return 1;
		return 0;
	}

	public int getAccelerationDirection() {
		if (isDeccelerating)
			return -1;
		if (isAccelerating)
			return 1;
		return 0;
	}
	
	public void changeMovement(Direction direction, boolean state) {
		if (direction == Direction.FORWARD) {
			isAccelerating = state;
			isDeccelerating = false;
		} else if (direction == Direction.BACKWARD) {
			isAccelerating = false;
			isDeccelerating = state;
		} else if (direction == Direction.LEFT) {
			isTurningLeft = state;
			isTurningRight = false;
		} else if (direction == Direction.RIGHT) {
			isTurningLeft = false;
			isTurningRight = state;
		}
	}

}
