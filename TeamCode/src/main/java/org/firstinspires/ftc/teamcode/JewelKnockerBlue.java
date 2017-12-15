package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Nitin on 10/17/2017.
 */
@Autonomous(name = "JewelKnockerBlue")
//@Disabled
public class JewelKnockerBlue extends LinearOpMode
{

    private ColorSensor colorsensor = null;
    private Servo servoUpDown = null;
    private Servo servoSide = null;


    @Override
    public void runOpMode() throws InterruptedException
    {
        waitForStart();
        // Find servos and sensors on hardware map
        colorsensor = hardwareMap.get(ColorSensor.class, "jewel_sensor");
        servoUpDown = hardwareMap.get(Servo.class, "jewel_knocker_updown");
        servoSide = hardwareMap.get(Servo.class, "jewel_knocker_sideways");

        servoUpDown.setPosition(0.8);
        //servoSide.setPosition(0.8);



        /*
        if (colorsensor.blue() > colorsensor.red()) {
            servoSide.setPosition(0.2);
            telemetry.addData("Red Value:", colorsensor.red());
            telemetry.addData("Blue Value:", colorsensor.blue());
        }else
        {
            servoSide.setPosition(0.8);
            telemetry.addData("Red Value:", colorsensor.red());
            telemetry.addData("Blue Value:", colorsensor.blue());
        }
        */
        //Commented by Alex on 12/06


    }
}
