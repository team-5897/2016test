
package org.usfirst.frc.team5620.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    SmartDashboard dash = new SmartDashboard();
    
    //Definition of classes
    Victor Vibrator;
    Joystick Methamatics;
    
    //the speed variable
    double speed = 0;
    
    //change these to change the high and low deadzones
    double deadzone = .25;
    
    String autoSelected;
    SendableChooser chooser;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	Vibrator = new Victor(6);       //victor set to port 6
    	Methamatics = new Joystick(0);  // first joystick
    	
    	
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	//autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
    		System.out.println("auto cus");
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
    		System.out.println("auto def");
    		
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	speed = Methamatics.getRawAxis(1); // set the speed variable to the joystick axis Y left stick
    	
    	//if the input is in the deadzone, the register input as 0
    	if(speed <= deadzone && speed >= -deadzone){
    		speed = 0;
    	}
    	
    	//scale the input field to a smaller scale to accomodate for deadzone
    	speed = speed * (((Math.abs(speed))-0.25)/0.75);
    	
        Vibrator.set(speed); //apply final speed to the moter
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }

}
