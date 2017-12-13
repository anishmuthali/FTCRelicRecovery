package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import static com.sun.tools.doclint.Entity.le;

/**
 * Created by anish on 11/10/2017.
 */
@TeleOp(name = "FullLeftArm",  group="OpMode")
//@Disabled
public class FullLeftArm extends OpMode
{
    DcMotor leftArm;
    //initiating the two servos on the left arm claw. leftl is the left servo and leftr is the right servo.
    Servo leftl;
    Servo leftr;

    //initiating the presets parameter of the position of the arm motor
    //TODO: getting the initial parameters of the position of the motor
    //TODO: rename the position int. Instead if "UP", try to use "glyph1" or similar names
    final int initial_position=10;
    final double pos_leftl=0.85;
    final double pos_leftr=0.61;
    final double close_value =0.15;
    final int UP = 1440;
    final int DOWN = 0;
    final int LEFT = 500;
    final int RIGHT = 1000;

    @Override
    public void init()
    {
        //declaring the servos and motors to be objects
        leftArm = hardwareMap.get(DcMotor.class,"leftArm");
        leftl = hardwareMap.get(Servo.class, "leftl");
        leftr = hardwareMap.get(Servo.class, "leftr");

        //initialize the position of the arm
        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftArm.setTargetPosition(initial_position);

        // initialize position of claw fingers
        //TODO: getting the initial parameters of the position of servos on the claw
        leftl.setPosition(pos_leftl);
        leftr.setDirection(Servo.Direction.REVERSE);
        leftr.setPosition(pos_leftr);

        // reset the position of the motor encoder
        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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
            leftl.setPosition(pos_leftl - close_value);
            leftr.setPosition(pos_leftr - close_value);
            // TODO: add space management code for the right arm
        }
        else if (gamepad1.b) {
            leftl.setPosition(pos_leftl);
            leftr.setPosition(pos_leftr);
            // TODO: add space management code for the right arm
        }


        // check if left bumper is pressed to move the arm up
        boolean on = gamepad1.left_bumper;
        telemetry.addData("left_bumper:",gamepad1.left_bumper);

        // check if left trigger is held down to move the arm down
        double triggerValue = gamepad1.left_trigger;
        telemetry.addLine("left_trigger: " + gamepad1.left_trigger);


        // if left bumper is pressed, move the arm up
        if (on) {
            leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftArm.setPower(0.5);
            telemetry.addLine("Arm moving up");
        }
        // if left trigger is pressed, drop the arm down
        else if (triggerValue > 0.0) {
            leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftArm.setPower(-0.05);
            telemetry.addLine("Arm moving down");
        }
        // if nothing is pressed, keep the arm in place. provide enough power that the arm doesn't move up or down
        // Noted: supply positive number first so the arms won't slide
        else {
            leftArm.setPower(0.05);
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            telemetry.addLine("Arm stopped");
        }






        /*
        presets for the left arm
         */
        //TODO: testing whether 0.5 is the proper moving speed of the object
        if (gamepad1.dpad_down) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.3);
            leftArm.setTargetPosition(DOWN);
            telemetry.addLine("Down");
        } else if (gamepad1.dpad_left) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.3);
            leftArm.setTargetPosition(LEFT);
            telemetry.addLine("Left");
        } else if (gamepad1.dpad_right) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.3);
            leftArm.setTargetPosition(RIGHT);
            telemetry.addLine("Right");
        } else if (gamepad1.dpad_up) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setPower(0.3);
            leftArm.setTargetPosition(UP);
            telemetry.addLine("Up");
        }

        telemetry.addData("leftArm: ",leftArm.getCurrentPosition());
        telemetry.addData("leftl: ",leftl.getPosition());
        telemetry.addData("leftr: ",leftr.getPosition());

    }
}
