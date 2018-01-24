package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 1/23/2018.
 */

@Autonomous
public class jewelServoTest extends OpMode{

    private ColorSensor colorsensor = null;
    private Servo servoUpDown = null;
    private Servo servoSide = null;

    @Override
    public void init() {
        // Find servos and sensors on hardware map
        colorsensor = hardwareMap.get(ColorSensor.class, "colorsensor");
        servoUpDown = hardwareMap.get(Servo.class, "servoUpDown");
        servoSide = hardwareMap.get(Servo.class, "servoSide");



        servoUpDown.setDirection(Servo.Direction.REVERSE);
        servoUpDown.setPosition(0.6);
        servoSide.setPosition(0.35);
    }

    @Override
    public void loop() {

        if(gamepad1.right_stick_y < 0) {
            servoUpDown.setPosition(servoUpDown.getPosition() + 0.01);
        } else if(gamepad1.right_stick_y > 0) {
            servoUpDown.setPosition(servoUpDown.getPosition() - 0.01);
        }

        if(gamepad1.left_stick_y < 0) {
            servoSide.setPosition(servoSide.getPosition() + 0.01);
        } else if(gamepad1.left_stick_y > 0) {
            servoSide.setPosition(servoSide.getPosition() - 0.01);
        }

        telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
        telemetry.addData("servoSide: ", servoSide.getPosition());
    }
}
