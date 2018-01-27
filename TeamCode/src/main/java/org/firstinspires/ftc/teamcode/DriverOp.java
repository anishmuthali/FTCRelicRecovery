package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Thread.sleep;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Alex Yang on 9/27/17.
 */
@TeleOp(name="DriverOp", group="OpMode")
//@Disabled
public class DriverOp extends OpMode {
    ElapsedTime runtime = new ElapsedTime();
    //------------------------------------------------------------------------------------
    //Objects for Arms
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;
    private Servo leftClaw = null;
    private Servo rightClaw = null;
    final int l_initial_position = 200;
    final int r_initial_position = 180;
//    final int lUP = 1440;
//    final int lDOWN = 165;
//    final int lLEFT = 639;
//    final int lRIGHT = 1000;
//    final int rUP = 982;
//    final int rDOWN = 140;
//    final int rLEFT = 482;
//    final int rRIGHT = 727;
    private boolean slowMode = false;
    private boolean fastMode = false;
    private boolean normalMode = true;
    boolean rclosed = true;
    boolean rightReleased = true;
    boolean lclosed = true;
    boolean leftReleased = true;
    //------------------------------------------------------------------------------------
    //Objects for Wheels
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    //------------------------------------------------------------------------------------
    //Objects for Slides
    private Servo servo1 = null;
    private DcMotor extendMotor = null;
    private DcMotor retractMotor = null;
    boolean closed;
    boolean pressed;
    boolean rightClosed = false;



    private Servo servoUpDown = null;
    private Servo servoSide = null;

    @Override
    public void init() {
        //Initialization for JewelKnocker Servos:
        servoUpDown = hardwareMap.get(Servo.class, "servoUpDown");
        servoSide = hardwareMap.get(Servo.class, "servoSide");

        //------------------------------------------------------------------------------------
        //Initialization for Wheels:
        // Find motors on hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        /*
        * Set direction of motors
        * Right: reversed
        * Left: forward (normal)
        * (reversed on 12/7)
        */
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //------------------------------------------------------------------------------------
        //Initialization for Arms:
        //declaring the servos and motors to be objects
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        leftClaw = hardwareMap.servo.get("left");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        rightClaw = hardwareMap.servo.get("right");

        //initialize the position of the arm
        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftArm.setTargetPosition(l_initial_position);
        rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArm.setTargetPosition(r_initial_position);
        rightArm.setDirection(DcMotor.Direction.REVERSE);

        //------------------------------------------------------------------------------------
        //Initialization for Slides
        servo1 = hardwareMap.get(Servo.class, "servo1");
        retractMotor = hardwareMap.get(DcMotor.class, "retractMotor");
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
        this.closed = true;
        this.pressed = false;
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        retractMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Resetting telemetry data
        telemetry.clearAll();
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {


        //Code for Servos
        servoSide.setPosition(0.2);
        try {
            sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        servoUpDown.setDirection(Servo.Direction.REVERSE);
        servoUpDown.setPosition(0.3);



        //Code for Wheels

        double leftPower = -0.25 * (gamepad1.right_stick_y);
        double rightPower = -0.25 * (gamepad1.left_stick_y);


        // Set speed of motor
        if (gamepad1.b) {
            slowMode = false;
            fastMode = false;
            normalMode = true;
        } else if (gamepad1.x) {
            slowMode = false;
            fastMode = true;
            normalMode = false;
        } else if (gamepad1.y) {
            slowMode = true;
            fastMode = false;
            normalMode = false;
        }

        // Get data from controllers
        if (normalMode) {
            leftPower = -0.25 * (gamepad1.right_stick_y);
            rightPower = -0.25 * (gamepad1.left_stick_y);
            telemetry.addLine("Normal Mode");
        }else if (slowMode) {
            leftPower = -0.15 * (gamepad1.right_stick_y);
            rightPower = -0.15 * (gamepad1.left_stick_y);
            telemetry.addLine("Slow Mode");
        } else if (fastMode) {
            leftPower = -0.4 * (gamepad1.right_stick_y);
            rightPower = -0.4 * (gamepad1.left_stick_y);
            telemetry.addLine("Fast Mode");
        }


        // Set power of all motors
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);


        //------------------------------------------------------------------------------------


        //Code for Arms


         /*
        This part is for manual control for the arm, which means the driver can set the power of the arm motor himself.
         */

        //if left joystick is up, move the arm up
        if (gamepad2.dpad_up) {
            leftArm.setPower(0.6);
            rightArm.setPower(0.6);
        }
        // if left joystick is down, drop the arm down
        else if (gamepad2.dpad_down) {
            leftArm.setPower(-0.1);
            rightArm.setPower(-0.1);
        }
        else{
            rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightArm.setPower(0);
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftArm.setPower(0);
        }

        // Code for controlling the claw
        if(gamepad2.right_stick_x < 0){
            rightClaw.setPosition(0);
        }
        else if(gamepad2.right_stick_x > 0){
            rightClaw.setPosition(1);
        }
        else{
            rightClaw.setPosition(0.5);
        }

        if(gamepad2.left_stick_x < 0){
            leftClaw.setPosition(0);
        }
        else if(gamepad2.left_stick_x > 0){
            leftClaw.setPosition(1);
        }
        else{
            leftClaw.setPosition(0.5);
        }





        //------------------------------------------------------------------------------------


        /*
        Code for Slides
         */

        if (retractMotor.getPower() != 0) {
            extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } else if (extendMotor.getPower() != 0) {
            retractMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        if (gamepad1.left_bumper || gamepad2.left_bumper) {
            retractMotor.setPower(0.2);
        } else if (gamepad1.right_bumper || gamepad2.right_bumper) {
            retractMotor.setPower(-0.2);
        } else {
            retractMotor.setPower(0);
        }

        if (gamepad1.left_trigger != 0 || gamepad2.left_trigger != 0) {
            extendMotor.setPower(0.3);
        } else if (gamepad1.right_trigger != 0 || gamepad2.right_trigger != 0) {
            extendMotor.setPower(-0.3);
        } else {
            extendMotor.setPower(0);
        }


        if (pressed) {
            if (!gamepad2.a && !gamepad1.a)
                pressed = false;
        } else {
            if (gamepad2.a || gamepad1.a) {
                if (!closed) {
                    //closing the servo
                    servo1.setDirection(Servo.Direction.FORWARD);
                    servo1.setPosition(0.08);
                    closed = true;
                    telemetry.addLine("closed");
                } else {
                    //opening the servo
                    servo1.setDirection(Servo.Direction.FORWARD);
                    servo1.setPosition(0.45);
                    closed = false;
                    telemetry.addLine("opened");
                }
                pressed = true;
            }
        }



        //Telemetries
        telemetry.addData("Motors", ("left: " + leftPower + "right: " + rightPower));
        telemetry.addData("leftArm Power:     ", leftArm.getPower());
        telemetry.addData("leftArm Position:  ", leftArm.getCurrentPosition());
        telemetry.addData("rightArm Power:    ", rightArm.getPower());
        telemetry.addData("rightArm Position: ", rightArm.getCurrentPosition());
        telemetry.addData("SlideClaw: ", servo1.getPosition());
        telemetry.addData("retractMotor: ", retractMotor.getPower());
        telemetry.addData("retractMotor: ", retractMotor.getZeroPowerBehavior());
        telemetry.addData("extendMotor: ", extendMotor.getPower());
        telemetry.addData("extendMotor: ", extendMotor.getZeroPowerBehavior());
        telemetry.addData("rightClosed: ", rclosed);
        telemetry.addData("leftClosed: ", lclosed);
        telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
        telemetry.addData("leftClaw", leftClaw.getPosition());
        telemetry.addData("rightClaw", rightClaw.getPosition());
        telemetry.addData("servoSide: ", servoSide.getPosition());
        telemetry.addData("Runtime:", getRuntime());


    }
}

