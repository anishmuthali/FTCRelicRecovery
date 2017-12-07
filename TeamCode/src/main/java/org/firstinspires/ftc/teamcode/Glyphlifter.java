package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


/**
 * Created by Nitin on 10/29/2017.
 * Disabled by Alex on 12/06/2017
 */
@Autonomous(name = "Glyphlifter", group = "Autonomous")
@Disabled
public class Glyphlifter extends LinearOpMode
{
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor armLeft = null;
    private DcMotor armRight = null;
    private Servo leftl = null;
    private Servo leftr = null;
    private Servo rightl = null;
    private Servo rightr = null;
    @Override
    public void runOpMode() throws InterruptedException
    {
        waitForStart();
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        armLeft = hardwareMap.get(DcMotor.class, "arm_left");
        armRight = hardwareMap.get(DcMotor.class, "arm_right");



        leftl = hardwareMap.get(Servo.class, "left1");
        leftr = hardwareMap.get(Servo.class, "left2");
        rightl = hardwareMap.get(Servo.class, "right1");
        rightr = hardwareMap.get(Servo.class, "right2");

        leftl.setPosition(0.2);
        leftr.setPosition(0.2);
        rightl.setPosition(0.2);
        rightr.setPosition(0.2);

        //set the directions of the motors
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        armLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        armRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);
        Thread.sleep(500);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        leftl.setPosition(0.8);
        leftr.setPosition(0.8);
        rightl.setPosition(0.8);
        rightr.setPosition(0.8);

        armLeft.setPower(1);
        armRight.setPower(1);
        Thread.sleep(1000);
        armLeft.setPower(0);
        armRight.setPower(0);
    }
}
