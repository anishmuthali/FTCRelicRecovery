package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by anishmuthali on 1/19/18.
 */
@TeleOp(name="EncoderValues", group="OpMode")
public class EncoderValues extends OpMode {
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
    }

    // backwards movement: L = 2151, R = -183

    @Override
    public void loop() {
        telemetry.clear();
        telemetry.update();
        double leftPower = -0.3 * (gamepad1.right_stick_y);
        double rightPower = -0.3 * (gamepad1.left_stick_y);
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
        int leftPosition = backLeft.getCurrentPosition();
        int rightPosition = backRight.getCurrentPosition();
        telemetry.addData("left_position", leftPosition);
        telemetry.addData("right_position", rightPosition);
        telemetry.update();
    }
}
