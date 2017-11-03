package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 11/2/2017.
 */

public class ArmServoTester extends OpMode{

    private Servo leftl = null;
    private Servo leftr = null;
    //private Servo rightl = null;
    //private Servo rightr = null;
    private boolean closed = false;

    @Override
    public void init(){
        telemetry.addData("Status", "Initialized");

        // Find motors on hardware map
        leftl = hardwareMap.get(Servo.class, "leftmost_servo");
        leftr = hardwareMap.get(Servo.class, "left_servo");
        //rightl = hardwareMap.get(Servo.class, "right_servo");
        //rightr = hardwareMap.get(Servo.class, "rightmost_servo");
    }

    @Override
    public void start(){
        leftl.setPosition(0.2);
        leftr.setPosition(0.2);
    }

    @Override
    public void loop(){
        if(gamepad1.a && !closed)
        {
            telemetry.addLine("Left claw closed");
            leftl.setPosition(0.5);
            leftr.setPosition(0.5);
            closed = true;
        }
        else if(gamepad1.a && closed)
        {
            telemetry.addLine("Left claw opened");
            leftl.setPosition(0.2);
            leftr.setPosition(0.2);
            closed = false;
        }
    }
}
