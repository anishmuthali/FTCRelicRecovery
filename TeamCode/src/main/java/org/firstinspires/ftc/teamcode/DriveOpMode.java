package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by anishmuthali on 9/27/17.
 */
@TeleOp(name="TestBaseOpMode", group="OpMode")
@Disabled
public class DriveOpMode extends OpMode{
    ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Find motors on hardware map
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");

        /*
        * Set direction of motors
        * Right: reversed
        * Left: forward (normal)
        */
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start(){
        runtime.reset();
    }

    @Override
    public void loop() {
        double leftPower = 0;
        double rightPower = 0;

        // Get data from controllers
        leftPower = gamepad1.left_stick_y;
        rightPower = gamepad1.right_stick_y;

        // Limit values of left and right power
        Range.clip(leftPower, -1.0, 1.0);
        Range.clip(rightPower, -1.0, 1.0);

        // Set power of all motors
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);

        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);

        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }
}
