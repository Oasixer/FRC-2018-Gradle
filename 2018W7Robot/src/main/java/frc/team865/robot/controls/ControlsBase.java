package frc.team865.robot.controls;

import static frc.team865.robot.Constants.DRIVER_ID;

import static frc.team865.robot.Constants.OPERATOR_ID;

import frc.team865.robot.Robot;
import frc.team865.robot.misc.DataPool;
import frc.team865.robot.subsystems.Climber;
import frc.team865.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Timer;

import frc.team865.robot.subsystems.Intake;
import frc.team865.robot.subsystems.Lift;

public abstract class ControlsBase {

	public static DataPool controlPool = new DataPool("controls");
	
	protected XboxControllerPlus driver;
	protected XboxControllerPlus operator;
	protected Climber climber;
	protected Drive drive;
	protected Intake intake;
	protected Lift lift;
	
	public ControlsBase(){
		driver = new XboxControllerPlus(DRIVER_ID);
		operator = new XboxControllerPlus(OPERATOR_ID);
		
		lift = Robot.lift;
		intake = Robot.intake;
		climber = Robot.climber;
		drive = Robot.drive;
	}
	
	abstract public void periodic();
	
	protected double timer = -1;
	protected boolean timePassed(double seconds) {
		if(timer <= 0)
			timer = Timer.getFPGATimestamp();
		
		if(Timer.getFPGATimestamp() - timer >= seconds){
			timer = -1;
			return true;
		}else{
			return false;
		}
	}
    
}