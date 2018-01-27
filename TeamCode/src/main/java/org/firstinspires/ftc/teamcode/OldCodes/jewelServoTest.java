package org.firstinspires.ftc.teamcode.OldCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 1/23/2018.
 */

@TeleOp(name="JewelServoTest")
@Disabled
public class jewelServoTest extends OpMode {

    private ColorSensor colorsensor = null;
    private Servo servoUpDown = null;
    private Servo servoSide = null;

    @Override
    public void init() {
        // Find servos and sensors on hardware map
        colorsensor = hardwareMap.get(ColorSensor.class, "colorsensor");
        servoUpDown = hardwareMap.get(Servo.class, "servoUpDown");
        servoSide = hardwareMap.get(Servo.class, "servoSide");



//        servoUpDown.setDirection(Servo.Direction.REVERSE);
//        servoUpDown.setPosition(0.6);
        //servoSide.setPosition(0.35);
        servoSide.setDirection(Servo.Direction.REVERSE);
        servoUpDown.setPosition(0.6);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        servoUpDown.setPosition(0.5);
        servoSide.setPosition(0);
        telemetry.addData("servoSide", servoSide.getPosition());
    }

    @Override
    public void loop() {
        servoSide.setPosition(0.5);
        if(gamepad1.right_stick_y < 0) {
            servoUpDown.setPosition(servoUpDown.getPosition() + 0.1);
        } else if(gamepad1.right_stick_y > 0) {
            servoUpDown.setPosition(servoUpDown.getPosition() - 0.1);
        }

        if(gamepad1.left_stick_y < 0) {
            servoSide.setPosition(servoSide.getPosition() + 0.1);
        } else if(gamepad1.left_stick_y > 0) {
            servoSide.setPosition(servoSide.getPosition() - 0.1);
        }

        telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
        telemetry.addData("servoSide: ", servoSide.getPosition());
    }
}
