package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by anish on 10/29/2017.
 */

@TeleOp(name="TorquenadoTest", group="OpMode")
public class TorquenadoTest extends OpMode{

    private DcMotor test = null;
    @Override
    public void init() {
        test = hardwareMap.get(DcMotor.class,"test");
    }

    @Override
    public void loop() {
        boolean on = false;
        on = gamepad1.left_bumper;
        boolean down = gamepad1.left_trigger != 0;
        if(on){
            telemetry.addLine("bumper");
            test.setPower(-0.5);
        }
        else{
            test.setPower(0);
        }
        /*else if(down){
            telemetry.addLine("trigger");
            test.setPower(0);

        }*/
    }
}
