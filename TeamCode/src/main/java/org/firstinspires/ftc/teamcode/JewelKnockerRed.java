package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 1/13/2018.
 */
@Autonomous(name = "JewelKnockerRed")
public class JewelKnockerRed extends LinearOpMode{

    private ColorSensor colorsensor = null;
    private Servo servoUpDown = null;
    private Servo servoSide = null;


    @Override
    public void runOpMode() throws InterruptedException
    {
        waitForStart();
        // Find servos and sensors on hardware map
        colorsensor = hardwareMap.get(ColorSensor.class, "colorsensor");
        servoUpDown = hardwareMap.get(Servo.class, "servoUpDown");
        servoSide = hardwareMap.get(Servo.class, "servoSide");

        servoUpDown.setDirection(Servo.Direction.REVERSE);
        servoUpDown.setPosition(0.6);
        servoSide.setPosition(0.35);


        while (opModeIsActive()) {

            sleep(2000);



            if (colorsensor.blue() < colorsensor.red()) {
                servoSide.setPosition(0.1);
                telemetry.addLine("Blue");

            } else {
                servoSide.setPosition(0.6);
                telemetry.addLine("Red");
            }


            telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
            telemetry.addData("servoSide: ", servoSide.getPosition());
            telemetry.addData("Red Value:", colorsensor.red());
            telemetry.addData("Blue Value:", colorsensor.blue());
            //Commented by Alex on 12/06
            //uncommented by Nitin on 1/9/18
            telemetry.update();
        }
    }
}
