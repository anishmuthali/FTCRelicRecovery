package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 11/2/2017.
 */
@TeleOp(name = "ArmServoTester")
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
        leftl = hardwareMap.get(Servo.class, "leftl");
        leftr = hardwareMap.get(Servo.class, "leftr");
        //rightl = hardwareMap.get(Servo.class, "right_servo");
        //rightr = hardwareMap.get(Servo.class, "rightmost_servo");

        telemetry.addLine("leftl: " + leftl.getPosition() + "; leftr: " + leftr.getPosition());


        leftl.setPosition(0.75);
        leftr.setDirection(Servo.Direction.REVERSE);
        leftr.setPosition(0.6);
        //rightl.setPosition(0.4);
        //rightr.setPosition(0.5);


    }

    @Override
    public void loop(){
        if(gamepad1.a){
            leftl.setPosition(0.69);
            leftr.setPosition(0.54);
        }
        if(gamepad1.b){
            leftl.setPosition(0.75);
            leftr.setPosition(0.6);
        }
        /*if(gamepad1.a && !closed)
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
        }*/
        telemetry.addLine("leftl: " + leftl.getPosition() + "; leftr: " + leftr.getPosition());
    }
}
