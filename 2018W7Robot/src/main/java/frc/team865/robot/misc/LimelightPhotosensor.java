package frc.team865.robot.misc;

import frc.team865.robot.Robot;
import frc.team865.robot.subsystems.Limelight;

public class LimelightPhotosensor {
	private Limelight limelight;
	
	private boolean found = false;
	private int pipeline;
	
	public LimelightPhotosensor(Limelight limelight, int pipeline) {
		this.limelight = limelight;
		this.pipeline = pipeline;
	}
	
	public void update() {
		found = limelight.getPipeline() == pipeline && limelight.foundObject();
	}
	
	public boolean isTriggered() {
		return found;
	}
}
