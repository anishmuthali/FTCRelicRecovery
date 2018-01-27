package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Alex on 1/13/2018.
 */
@Autonomous(name = "JewelKnockerRed")
public class JewelKnockerRed extends LinearOpMode{

    private ColorSensor colorsensor = null;
    private Servo servoUpDown = null;
    private Servo servoSide = null;
    private Servo rightClaw;
    private Servo leftClaw;



    @Override
    public void runOpMode() throws InterruptedException
    {
        waitForStart();
        // Find servos and sensors on hardware map
        colorsensor = hardwareMap.get(ColorSensor.class, "colorsensor");
        servoUpDown = hardwareMap.get(Servo.class, "servoUpDown");
        servoSide = hardwareMap.get(Servo.class, "servoSide");
        rightClaw = hardwareMap.servo.get("right");
        leftClaw = hardwareMap.servo.get("left");


        rightClaw.setPosition(0.5);
        leftClaw.setPosition(0.5);



        servoUpDown.setPosition(0.8);
        servoSide.setPosition(0.55);
        sleep(1000);




        servoUpDown.setPosition(0.1);
        sleep(1000);


        telemetry.addData("Red Value:", colorsensor.red());
        telemetry.addData("Blue Value:", colorsensor.blue());
        telemetry.update();
        if (colorsensor.blue() > colorsensor.red()) {
            servoSide.setPosition(0.0);
            telemetry.addLine("Blue");
            telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
            telemetry.addData("servoSide: ", servoSide.getPosition());
            telemetry.update();
            sleep(1000);

        } else {
            servoSide.setPosition(0.8);
            telemetry.addLine("Red");
            telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
            telemetry.addData("servoSide: ", servoSide.getPosition());
            telemetry.update();
            sleep(1000);

        }

        servoUpDown.setPosition(1);
        sleep(1000);

    }



}
