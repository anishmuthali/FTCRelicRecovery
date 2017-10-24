package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 10/17/2017.
 */
@Autonomous(name = "Blue Jewel Knocker")
public class JewelKnockerBlue extends LinearOpMode
{

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private ColorSensor colorsensor = null;
    private Servo servo1 = null;
    private Servo servo2 = null;

    @Override
    public void runOpMode()
    {
        waitForStart();
        // Find motors on hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        colorsensor = hardwareMap.get(ColorSensor.class, "jewel_sensor");
        servo1 = hardwareMap.get(Servo.class, "jewel_knocker_updown");
        servo2 = hardwareMap.get(Servo.class, "jewel_knocker_sideways");

        servo1.setPosition(0.8);
        try
        {
            backLeft.setPower(1);
            backRight.setPower(1);
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {

        }
        backLeft.setPower(0);
        backRight.setPower(0);


        //If the color sensor detects blue, turn the servo in the direction it faces.
        // If not, turn it in the opposite direction.
        if (colorsensor.blue() > colorsensor.red())
        {
            servo2.setPosition(0.2);
        }
        else
        {
            servo2.setPosition(0.8);
        }
    }
}
