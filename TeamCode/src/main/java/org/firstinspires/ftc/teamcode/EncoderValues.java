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
<<<<<<< HEAD
        double leftPower = -0.1 * (gamepad1.right_stick_y);
        double rightPower = -0.1 * (gamepad1.left_stick_y);
        Range.clip(leftPower, -1.0, 1.0);
        Range.clip(rightPower, -1.0, 1.0);
=======
        double leftPower = 0.4 * (gamepad1.right_stick_y);
        double rightPower = -0.4 * (gamepad1.left_stick_y);
>>>>>>> ad875bcdf5fef07ac33796c36c4745c05c93a0cf
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
