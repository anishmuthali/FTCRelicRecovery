package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static com.sun.tools.doclint.Entity.le;

/**
 * Created by anish on 11/10/2017.
 */
@TeleOp(name = "FullLeftArm",  group="OpMode")
public class FullLeftArm extends OpMode
{
    DcMotor leftArm;
    Servo leftl;
    Servo leftr;
    final int UP = 0;
    final int DOWN = 0;
    final int LEFT = 0;
    final int RIGHT = 0;
    final int initial_position=0;

    @Override
    public void init() {
        leftArm = hardwareMap.get(DcMotor.class,"leftArm");
        leftl = hardwareMap.get(Servo.class, "leftl");
        leftr = hardwareMap.get(Servo.class, "leftr");

        //initislize the position of the arm
        leftArm.setTargetPosition(initial_position);

        // initialize position of claw fingers
        leftl.setPosition(0.745);
        leftr.setDirection(Servo.Direction.REVERSE);
        leftr.setPosition(0.585);
    }

    @Override
    public void loop() {
        /*
        This part is for manual control for the arm, which means the driver can set the power of the arm motor himself.
         */
        if (gamepad1.a) {
            leftl.setPosition(0.70);
            leftr.setPosition(0.54);
            // TODO: add space management code for the right arm
        }
        if (gamepad1.b) {
            leftl.setPosition(0.745);
            leftr.setPosition(0.585);
            // TODO: add space management code for the right arm
        }
        // check if L1 is held down
        boolean on = gamepad1.left_bumper;

        // check if L2 is held down
        float triggerValue = gamepad1.left_trigger;
        telemetry.addLine("left_trigger: " + gamepad1.left_trigger);
        // if L1 is held down, move the arm up
        if (on) {
            telemetry.addLine("Arm moving up");
            leftArm.setPower(0.5);
        }
        // if L2 is held, drop the arm down
        else if (triggerValue > 0.0) {
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftArm.setPower(0);
        }
        // if L1 is not held, keep the arm in place. provide enough power that the arm doesn't move up or down
        else {
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftArm.setPower(0);
        }






        /*
        presets for the left arm
         */
        if (gamepad1.dpad_down) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(DOWN);
        } else if (gamepad1.dpad_left) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(LEFT);
        } else if (gamepad1.dpad_right) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(RIGHT);
        } else if (gamepad1.dpad_up) {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(UP);
        }
    }
}
