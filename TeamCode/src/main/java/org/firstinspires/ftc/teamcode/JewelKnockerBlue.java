package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 10/17/2017.
 */
@Autonomous(name = "JewelKnockerBlue")
//@Disabled
public class JewelKnockerBlue extends LinearOpMode
{

    private ColorSensor colorsensor = null;
    private Servo servo1 = null;
    private Servo servo2 = null;

    @Override
    public void runOpMode() throws InterruptedException
    {
        waitForStart();
        // Find servos and sensors on hardware map
        colorsensor = hardwareMap.get(ColorSensor.class, "jewel_sensor");
        servo1 = hardwareMap.get(Servo.class, "jewel_knocker_updown");
        servo2 = hardwareMap.get(Servo.class, "jewel_knocker_sideways");

        servo1.setPosition(0.8);


        //If the color sensor detects blue, turn the servo in the direction it faces.
        // If not, turn it in the opposite direction.
        if (colorsensor.blue() > colorsensor.red()) {
            servo2.setPosition(0.2);
            telemetry.addData("Red Value:", colorsensor.red());
            telemetry.addData("Blue Value:", colorsensor.blue());
        }else
        {
            servo2.setPosition(0.8);
            telemetry.addData("Red Value:", colorsensor.red());
            telemetry.addData("Blue Value:", colorsensor.blue());
        }
    }
}
