package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import static com.sun.tools.doclint.Entity.le;

/**
 * Created by Alex on 12/17/2017.
 */
@TeleOp(name = "BothArms",  group="OpMode")
//@Disabled
public class BothArms extends OpMode
{
    DcMotor leftArm;
    DcMotor rightArm;
    //initiating the two servos on the left arm claw. leftl is the left servo and leftr is the right servo.
    Servo leftl;
    Servo leftr;
    Servo rightl;
    Servo rightr;

    //initiating the presets parameter of the position of the arm motor
    //TODO: getting the initial parameters of the position of the motor
    //TODO: rename the position int. Instead if "UP", try to use "glyph1" or similar names
    final int l_initial_position=-970;
    final int r_initial_position=100;
    final double lpos_l=0.75;
    final double lpos_r=0.61;
    final double rpos_l=0.5;
    final double rpos_r=0.65;
    final double close_value =0.13;
    final int lUP = 1440;
    final int lDOWN = -950;
    final int lLEFT = 500;
    final int lRIGHT = 1000;
    final int rUP = 1440;
    final int rDOWN = 130;
    final int rLEFT = 500;
    final int rRIGHT = 1000;

    @Override
    public void init()
    {
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

        //resetting telemetry data
        telemetry.clearAll();
    }

    @Override
    public void loop()
    {

        /*
        This part is for manual control for the arm, which means the driver can set the power of the arm motor himself.
         */
        if (gamepad1.a) {
            leftl.setPosition(lpos_l - close_value);
            leftr.setPosition(lpos_r - close_value);
            // COMPLETED: add space management code for the right arm
        }
        else if (gamepad1.b) {
            leftl.setPosition(lpos_l);
            leftr.setPosition(lpos_r);
            // COMPLETED: add space management code for the right arm
        }

        if (gamepad1.x) {
            rightl.setPosition(rpos_l - close_value);
            rightr.setPosition(rpos_r - close_value);
            // COMPLETED: add space management code for the right arm
        }
        else if (gamepad1.y) {
            rightl.setPosition(rpos_l);
            rightr.setPosition(rpos_r);
            // COMPLETED: add space management code for the right arm
        }



        boolean left_on = gamepad1.left_bumper;
        double left_triggerValue = gamepad1.left_trigger;
        boolean right_on = gamepad1.right_bumper;
        double right_triggerValue = gamepad1.right_trigger;

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
        if (gamepad1.dpad_down) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lDOWN);
            rightArm.setTargetPosition(rDOWN);
            telemetry.addLine("Down");
        } else if (gamepad1.dpad_left) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lLEFT);
            rightArm.setTargetPosition(rLEFT);
            telemetry.addLine("LEFT");
        } else if (gamepad1.dpad_right) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lRIGHT);
            rightArm.setTargetPosition(rRIGHT);
            telemetry.addLine("RIGHT");
        } else if (gamepad1.dpad_up) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.4);
            rightArm.setPower(0.4);
            leftArm.setTargetPosition(lUP);
            rightArm.setTargetPosition(rUP);
            telemetry.addLine("UP");
        }

        telemetry.addData("leftArm Power:     ",leftArm.getPower());
        telemetry.addData("leftArm Position:  ",leftArm.getCurrentPosition());
        telemetry.addData("rightArm Power:    ",rightArm.getPower());
        telemetry.addData("rightArm Position: ",rightArm.getCurrentPosition());
        telemetry.addData("leftl:   ",leftl.getPosition());
        telemetry.addData("leftr:   ",leftr.getPosition());
        telemetry.addData("rightl:  ",rightl.getPosition());
        telemetry.addData("rightr:  ",rightr.getPosition());

    }
}
