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
@TeleOp(name="ArmAndDrive", group="OpMode")
//@Disabled
public class ArmAndDrive extends OpMode{
    ElapsedTime runtime = new ElapsedTime();
    //------------------------------------------------------------------------------------
    //Objects for Arms
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;
    private Servo leftl = null;
    private Servo leftr = null;
    private Servo rightl = null;
    private Servo rightr = null;
    final int l_initial_position=200;
    final int r_initial_position=180;
    final double lpos_l=0.72;
    final double lpos_r=0.58;
    final double rpos_l=0.5;
    final double rpos_r=0.65;
    final double close_value =0.13;
    final int lUP = 1440;
    final int lDOWN = 165;
    final int lLEFT = 639;
    final int lRIGHT = 1000;
    final int rUP = 982;
    final int rDOWN = 140;
    final int rLEFT = 482;
    final int rRIGHT = 727;
    private boolean slowMode = false;
    private boolean normalMode = true;
    private boolean fastMode = false;
    private boolean TURBOMODE = false;
    //------------------------------------------------------------------------------------
    //Objects for Wheels
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    @Override
    public void init() {
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
        leftArm = hardwareMap.get(DcMotor.class,"leftArm");
        leftl = hardwareMap.get(Servo.class, "leftl");
        leftr = hardwareMap.get(Servo.class, "leftr");

        rightArm = hardwareMap.get(DcMotor.class,"rightArm");
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

        //resetting telemetry data
        telemetry.clearAll();
    }

    @Override
    public void start(){
        runtime.reset();
    }

    @Override
    public void loop() {

        /*
        Code for Wheels
         */

        double leftPower = -0.30 * (gamepad1.right_stick_y);
        double rightPower = -0.30 * (gamepad1.left_stick_y);


        // Set speed of motor
        if (gamepad1.b){
            slowMode = false;
            normalMode = false;
            fastMode = true;
            TURBOMODE = false;
        }
        else if(gamepad1.a){
            slowMode = false;
            normalMode = false;
            fastMode = false;
            TURBOMODE = true;
        }
        else if(gamepad1.dpad_right) {
            slowMode = true;
            normalMode = false;
            fastMode = false;
            TURBOMODE = false;
        }
        else if(gamepad1.dpad_down){
            slowMode = false;
            normalMode = true;
            fastMode = false;
            TURBOMODE = false;
        }



        // Get data from controllers
        if(slowMode)
        {
            leftPower = -0.175 * (gamepad1.right_stick_y);
            rightPower = -0.175 * (gamepad1.left_stick_y);
            telemetry.addLine("Slow Mode");
        }
        else if(normalMode) {
            leftPower = -0.30 * (gamepad1.right_stick_y);
            rightPower = -0.30 * (gamepad1.left_stick_y);
            telemetry.addLine("Normal Mode");
        }
        else if(fastMode)
        {
            leftPower = -0.50 * (gamepad1.right_stick_y);
            rightPower = -0.50 * (gamepad1.left_stick_y);
            telemetry.addLine("Fast Mode");
        }
        else if(TURBOMODE)
        {
            leftPower = -0.75 * (gamepad1.right_stick_y);
            rightPower = -0.75 * (gamepad1.left_stick_y);
            telemetry.addLine("TURBO!!!!");
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


        /*
        Code for Arms
         */

         /*
        This part is for manual control for the arm, which means the driver can set the power of the arm motor himself.
         */

        if (gamepad2.a) {
            leftl.setPosition(lpos_l - close_value);
            leftr.setPosition(lpos_r - close_value);
            // COMPLETED: add space management code for the right arm
        } else if (gamepad2.b) {
            leftl.setPosition(lpos_l);
            leftr.setPosition(lpos_r);
            // COMPLETED: add space management code for the right arm
        }if (gamepad2.x) {
            rightl.setPosition(rpos_l - close_value);
            rightr.setPosition(rpos_r - close_value);
            // COMPLETED: add space management code for the right arm
        } else if (gamepad2.y) {
            rightl.setPosition(rpos_l);
            rightr.setPosition(rpos_r);
            // COMPLETED: add space management code for the right arm
        }

        boolean left_on = gamepad2.left_bumper;
        double left_triggerValue = gamepad2.left_trigger;
        boolean right_on = gamepad2.right_bumper;
        double right_triggerValue = gamepad2.right_trigger;

        // if left bumper is pressed, move the arm up
        if (left_on) {
            leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftArm.setPower(0.4);

        }
        // if left trigger is pressed, drop the arm down
        else if (left_triggerValue > 0.0) {
            leftArm.setPower(-0.01);

        }
        // if nothing is pressed, keep the arm in place. provide enough power that the arm doesn't move up or down
        // Noted: supply positive number first so the arms won't slide
        else {
            leftArm.setPower(0.2);
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        //Same algorithm for right arm
        if (right_on) {
            rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightArm.setPower(0.4);
        }
        else if (right_triggerValue > 0.0) {
            rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightArm.setPower(-0.01);
        }
        else {
            rightArm.setPower(0.2);
            rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


        /*
        presets for the left arm
         */
        //COMPLETED: testing whether 0.5 is the proper moving speed of the object
        /*
        if (gamepad2.dpad_down) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lDOWN);
            rightArm.setTargetPosition(rDOWN);
            telemetry.addLine("Down");
        } else if (gamepad2.dpad_left) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lLEFT);
            rightArm.setTargetPosition(rLEFT);
            telemetry.addLine("LEFT");
        } else if (gamepad2.dpad_right) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lRIGHT);
            rightArm.setTargetPosition(rRIGHT);
            telemetry.addLine("RIGHT");
        } else if (gamepad2.dpad_up) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lUP);
            rightArm.setTargetPosition(rUP);
            telemetry.addLine("UP");
        }
    */

        telemetry.addData("Motors", ("left: " + leftPower + "right: " + rightPower));
        telemetry.addData("leftArm Power:     ",leftArm.getPower());
        telemetry.addData("leftArm Position:  ",leftArm.getCurrentPosition());
        telemetry.addData("rightArm Power:    ",rightArm.getPower());
        telemetry.addData("rightArm Position: ",rightArm.getCurrentPosition());
        telemetry.addData("leftl:   ",leftl.getPosition());
        telemetry.addData("leftr:   ",leftr.getPosition());
        telemetry.addData("rightl:  ",rightl.getPosition());
        telemetry.addData("rightr:  ",rightr.getPosition());
        telemetry.addData("Runtime:", getRuntime());

    }
}
