package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nitin on 10/29/2017.
 */
@Autonomous
public class MotorTest extends LinearOpMode
{
    private DcMotor test = null;

    @Override
    public void runOpMode() throws InterruptedException {
        test = hardwareMap.get(DcMotor.class,"test");
        test.setPower(1);
    }
}
