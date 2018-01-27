package org.firstinspires.ftc.teamcode.OldCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Alex on 1/13/2018.
 */
@Autonomous(name = "AutoDriveTest")
@Disabled
public class AutoDriveTest extends LinearOpMode{

    //Objects for Wheels
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;


    @Override
    public void runOpMode() throws InterruptedException
    {
        waitForStart();

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

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




            telemetry.addData("fLeft position", frontLeft.getCurrentPosition());
            telemetry.addData("fRight position", frontRight.getCurrentPosition());
            telemetry.addData("bLeft position", backLeft.getCurrentPosition());
            telemetry.addData("bRight position", backRight.getCurrentPosition());


            forward(10000);

            telemetry.update();

            sleep(20000);


        }


    public void forward(int time) throws InterruptedException{

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition()+500);
        backLeft.setTargetPosition(backLeft.getCurrentPosition()+500);
        frontRight.setTargetPosition(frontRight.getCurrentPosition()+500);
        backRight.setTargetPosition(backRight.getCurrentPosition()+500);



        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(0.15);
        backLeft.setPower(0.15);
        frontRight.setPower(0.15);
        backRight.setPower(0.15);

        sleep(time);
    }
}
