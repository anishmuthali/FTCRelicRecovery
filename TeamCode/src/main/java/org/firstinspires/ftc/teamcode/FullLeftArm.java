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
public class FullLeftArm extends OpMode {
    DcMotor leftArm = null;
    Servo leftl = null;
    Servo leftr = null;
    int currentPos = 0;

    @Override
    public void init() {
        leftArm = hardwareMap.get(DcMotor.class,"leftArm");
        leftl = hardwareMap.get(Servo.class, "leftl");
        leftr = hardwareMap.get(Servo.class, "leftr");

        leftArm.setPower(0.05);

        // initialize position of claw fingers
        leftl.setPosition(0.745);
        leftr.setDirection(Servo.Direction.REVERSE);
        leftr.setPosition(0.585);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            leftl.setPosition(0.69);
            leftr.setPosition(0.54);
            // TODO: add space management code for the right arm
        }
        if(gamepad1.b){
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
        if(on){
            telemetry.addLine("Arm moving up");
            leftArm.setPower(0.5);
        }
        // if L2 is held, drop the arm down
        else if(triggerValue > 0.0){
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftArm.setPower(0);
        }
        // if L1 is not held, keep the arm in place. provide enough power that the arm doesn't move up or down
        else{
            leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftArm.setPower(0);
        }

        //presets for the left arm
        if(gamepad1.dpad_down)
        {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(0);
        }
        else if(gamepad1.dpad_left)
        {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(0);
        }
        else if(gamepad1.dpad_right)
        {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(0);
        }
        else if(gamepad1.dpad_up)
        {
            leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftArm.setTargetPosition(0);
        }
    }
}
