package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
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
    private Servo leftl = null;
    private Servo leftr = null;
    private Servo rightl = null;
    private Servo rightr = null;
    final int l_initial_position = 200;
    final int r_initial_position = 180;
    final double lpos_l = 0.72;
    final double lpos_r = 0.58;
    final double rpos_l = 0.5;
    final double rpos_r = 0.65;
    final double close_value = 0.25;
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
        leftl = hardwareMap.get(Servo.class, "leftl");
        leftr = hardwareMap.get(Servo.class, "leftr");

        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        rightl = hardwareMap.get(Servo.class, "rightl");
        rightr = hardwareMap.get(Servo.class, "rightr");

        //initialize the position of the arm
        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftArm.setTargetPosition(l_initial_position);
        rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArm.setTargetPosition(r_initial_position);
        rightArm.setDirection(DcMotor.Direction.REVERSE);

        // initialize position of claw fingers
        //COMPLETED: getting the initial parameters of the position of servos on the claw
        leftl.setPosition(lpos_l);
        leftr.setDirection(Servo.Direction.REVERSE);
        leftr.setPosition(lpos_r);

        rightl.setPosition(rpos_l);
        rightr.setDirection(Servo.Direction.REVERSE);
        rightr.setPosition(rpos_r);


        leftl.setPosition(lpos_l - close_value);
        leftr.setPosition(lpos_r - close_value);

        rightl.setPosition(rpos_l - close_value);
        rightr.setPosition(rpos_r - close_value);

        //------------------------------------------------------------------------------------
        //Initialization for Slides
        servo1 = hardwareMap.get(Servo.class, "servo1");
        retractMotor = hardwareMap.get(DcMotor.class, "retractMotor");
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
        this.closed = true;
        this.pressed = false;
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        retractMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
        servoUpDown.setDirection(Servo.Direction.REVERSE);
        servoUpDown.setPosition(0.3);
        servoSide.setPosition(0.35);


        //Code for Wheels

        double leftPower = -0.6 * (gamepad1.right_stick_y);
        double rightPower = -0.6 * (gamepad1.left_stick_y);


        // Set speed of motor
        if (gamepad1.x) {
            slowMode = false;
            fastMode = true;
        } else if (gamepad1.y) {
            slowMode = true;
            fastMode = false;
        }

            // Get data from controllers
            if (slowMode) {
                leftPower = -0.3 * (gamepad1.right_stick_y);
                rightPower = -0.3 * (gamepad1.left_stick_y);
                telemetry.addLine("Slow Mode");
            } else if (fastMode) {
                leftPower = -0.75 * (gamepad1.right_stick_y);
                rightPower = -0.75 * (gamepad1.left_stick_y);
                telemetry.addLine("Fast Mode");
            }


/*
        // Limit values of left and right power
        Range.clip(leftPower, -1.0, 1.0);
        Range.clip(rightPower, -1.0, 1.0);
        (Commented by Alex on 11/28)
        (Reason: Don't know what it's for!)
*/
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


//        if(gamepad2.right_bumper)
//        {
//            if(rightReleased)
//            {
//                if(rclosed)
//                {
//                    rclosed=false;
//                }
//                else
//                {
//                    rclosed = true;
//                }
//            }
//            rightReleased = false;
//        }
//        else {
//            rightReleased = true;
//        }
//
//
//
//        if(gamepad2.left_bumper)
//        {
//            if(leftReleased)
//            {
//                if(lclosed)
//                {
//                    lclosed=false;
//                }
//                else
//                {
//                    lclosed = true;
//                }
//            }
//            leftReleased = false;
//        }
//        else {
//            leftReleased = true;
//        }
//
//
//
//
//
//


            if (lclosed) {
                leftl.setPosition(lpos_l - close_value);
                leftr.setPosition(lpos_r - close_value);
                // COMPLETED: add space management code for the right arm
            } else {
                leftl.setPosition(lpos_l);
                leftr.setPosition(lpos_r);
                // COMPLETED: add space management code for the right arm
            }
            if (rclosed) {
                rightl.setPosition(rpos_l - close_value);
                rightr.setPosition(rpos_r - close_value);
                // COMPLETED: add space management code for the right arm
            } else {
                rightl.setPosition(rpos_l);
                rightr.setPosition(rpos_r);
                // COMPLETED: add space management code for the right arm
            }



            //if left joystick is up, move the arm up
            if (gamepad2.left_stick_y < 0) {
                leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightArm.setPower(0.4);
                leftArm.setPower(0.4);

            }
            // if left joystick is down, drop the arm down
            else if (gamepad2.left_stick_y > 0) {
                leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                leftArm.setPower(-0.01);
                rightArm.setPower(-0.01);

            }
            // if nothing is pressed, keep the arm in place. provide enough power that the arm doesn't move up or down
            // Noted: supply positive number first so the arms won't slide
            else {
                leftArm.setPower(0.2);
                leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                rightArm.setPower(0.2);
                rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
//
//            //Same algorithm for right arm
//            if (gamepad2.right_stick_y < 0) {
//                rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                rightArm.setPower(0.4);
//            } else if (gamepad2.right_stick_y > 0) {
//                rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                rightArm.setPower(-0.01);
//            } else {
//                rightArm.setPower(0.2);
//                rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            }




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
                        servo1.setPosition(0.1);
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
            telemetry.addData("leftl:   ", leftl.getPosition());
            telemetry.addData("leftr:   ", leftr.getPosition());
            telemetry.addData("rightl:  ", rightl.getPosition());
            telemetry.addData("rightr:  ", rightr.getPosition());
            telemetry.addData("SlideClaw: ", servo1.getPosition());
            telemetry.addData("retractMotor: ", retractMotor.getPower());
            telemetry.addData("retractMotor: ", retractMotor.getZeroPowerBehavior());
            telemetry.addData("extendMotor: ", extendMotor.getPower());
            telemetry.addData("extendMotor: ", extendMotor.getZeroPowerBehavior());
            telemetry.addData("rightClosed: ", rclosed);
            telemetry.addData("leftClosed: ", lclosed);
            telemetry.addData("servoUpDown: ", servoUpDown.getPosition());
            telemetry.addData("servoSide: ", servoSide.getPosition());
            telemetry.addData("Runtime:", getRuntime());


        }
    }

