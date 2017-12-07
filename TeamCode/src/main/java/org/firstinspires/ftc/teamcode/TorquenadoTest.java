package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by anish on 10/29/2017.
 */
//TODO: Try to get the parameters for the different level of glyph
//COMPLETED: create presets for different rows of the glyph tower (use dpad buttons)


@TeleOp(name="ArmMovementTest", group="OpMode")
//@Disabled
public class TorquenadoTest extends OpMode{

    private DcMotor test = null;
    @Override
    public void init() {
        // define motor based on hardware map
        test = hardwareMap.get(DcMotor.class,"test");
    }

    @Override
    public void loop() {
        // check if L1 is held down
        boolean on = gamepad1.left_bumper;

        // check if L2 is held down
        float triggerValue = gamepad1.left_trigger;
        telemetry.addLine("left_trigger: " + gamepad1.left_trigger);

        // if L1 is held down, move the arm up
        if(on){
            telemetry.addLine("Arm moving up");
            test.setPower(0.5);
        }
        // if L2 is held, drop the arm down
        else if(triggerValue > 0.0){
            test.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            test.setPower(0);
        }
        // if L1 is not held, keep the arm in place. provide enough power that the arm doesn't move up or down
        else{
            test.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            test.setPower(0);
        }

    }
}
