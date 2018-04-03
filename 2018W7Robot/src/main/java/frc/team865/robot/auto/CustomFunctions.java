package frc.team865.robot.auto;

import static frc.team865.robot.Constants.CUBE_DISTANCE_B;
import static frc.team865.robot.Constants.CUBE_DISTANCE_M;

import frc.team865.robot.Robot;
import frc.team865.robot.subsystems.Drive;
import frc.team865.robot.subsystems.Intake;
import frc.team865.robot.subsystems.Lift;
import frc.team865.robot.subsystems.Limelight;
import frc.team865.robot.subsystems.Navx;

public class CustomFunctions {
	
	private Drive drive = Robot.drive;
	private Navx navx = Robot.navx;
	private Limelight limelight = Robot.limelight;
	private AutoFunctions autoFunc = Robot.auto.autoFunc; 
	private Intake intake = Robot.intake;
	private Lift lift = Robot.lift;
	
	public void turnToScale(double driveLocationTurn1,double angle1,double driveLocationTurn2,double angle2,double liftLocation, double liftScale) {
		double dist = getOverallDistance();
		if (withinMiddle(dist,driveLocationTurn1,20))
			autoFunc.wantedAngle = angle1;
		if (withinMiddle(dist,driveLocationTurn2,20))
			autoFunc.wantedAngle = angle2;
		
		if (withinMiddle(dist,liftLocation,20))
			lift.setLoc(liftScale);
	}
	
	public void driveTurn(double driveLocationTurn1,double angle1) {
		double dist = getOverallDistance();
		if (withinMiddle(dist,driveLocationTurn1,20))
			autoFunc.wantedAngle = angle1;
	}
	
	public void driveIntakeUp(double driveLocation, double liftLocation){
		double dist = getOverallDistance();
		if (withinMiddle(dist,driveLocation,20))
			lift.setLoc(liftLocation);
	}
	
	public void turnDrop(double angleOuttake, double angleDrop) {
		double curAngle = navx.getAngle() % 360;
		
		if (withinMiddle(curAngle,angleOuttake,15))
			intake.setSpeed(-1);
		else
			intake.setSpeed(0.2);
		
		if (withinMiddle(curAngle,angleDrop,15))
			lift.setLoc(0);
	}
	
	private boolean withinMiddle(double angle, double setAngle, double thresh) {
		return (setAngle - thresh) < angle && (setAngle + thresh) > angle;
	}

	private boolean withinFront(double angle, double setAngle, double thresh) {
		return setAngle < angle && (setAngle + thresh) > angle;
	}

	private double getOverallDistance() {
		return (-drive.getLeftDistance() + -drive.getRightDistance()) / 2;
	}

	private double distancePredictor(double area) {
		return CUBE_DISTANCE_B - CUBE_DISTANCE_M * area;
	}
}
