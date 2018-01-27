package org.firstinspires.ftc.teamcode.OldCodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by anishmuthali on 1/21/18.
 */
@TeleOp(name="ClawTester")
@Disabled
public class ClawTester extends OpMode {
    private Servo leftClaw;
    private Servo rightClaw;
    @Override
    public void init() {
        leftClaw = hardwareMap.servo.get("left");
        rightClaw = hardwareMap.servo.get("right");


    }

    @Override
    public void loop() {


        if(gamepad1.left_stick_x < 0){
            rightClaw.setPosition(0);
        }
        else if(gamepad1.left_stick_x > 0){
            rightClaw.setPosition(1);
        }
        else{
            rightClaw.setPosition(0.5);
        }

        if(gamepad1.right_stick_x < 0){
            leftClaw.setPosition(0);
        }
        else if(gamepad1.right_stick_x > 0){
            leftClaw.setPosition(1);
        }
        else{
            leftClaw.setPosition(0.5);
        }
    }
}
