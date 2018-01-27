package org.firstinspires.ftc.teamcode.OldCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by anishmuthali on 1/21/18.
 */
@Autonomous(name="AutonomousMovementTest")
public class AutonomousMovementTest extends OpMode {
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private boolean robotMovedBack = false;
    private boolean robotPointTurned = false;
    private final int MOVE_BACK_ENCODER_VALUE = 1400;
    private final int POINT_TURN_ENCODER_VALUE_RIGHT = -5000;
    private final int POINT_TURN_ENCODER_VALUE_LEFT = 2700;
    private final double MOTOR_POWER = 0.4;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        if(!robotMovedBack){
            moveRobotBack();
        }
    }

    // Method to move the motors using encoders to the area right in front of the glyph towers
    // TODO: Test this code again to check if it still works after the phone position has been swapped
    public void moveRobotBack(){
        frontLeft.setTargetPosition(MOVE_BACK_ENCODER_VALUE);
        backLeft.setTargetPosition(MOVE_BACK_ENCODER_VALUE);
        frontRight.setTargetPosition(-MOVE_BACK_ENCODER_VALUE);
        backRight.setTargetPosition(-MOVE_BACK_ENCODER_VALUE);
        if(backLeft.getCurrentPosition() >= MOVE_BACK_ENCODER_VALUE && backRight.getCurrentPosition() <= -MOVE_BACK_ENCODER_VALUE){
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
            robotMovedBack = true;
        }
        else{
            frontLeft.setPower(MOTOR_POWER);
            backLeft.setPower(MOTOR_POWER);
            frontRight.setPower(-MOTOR_POWER);
            backRight.setPower(-MOTOR_POWER);
        }

    }

    // DO NOT USE THIS CODE! IT'S BUGGY AND VERY DANGEROUS!
    public void pointTurnRobot(){
        frontLeft.setTargetPosition(POINT_TURN_ENCODER_VALUE_LEFT);
        frontRight.setTargetPosition(POINT_TURN_ENCODER_VALUE_RIGHT);
        backLeft.setTargetPosition(POINT_TURN_ENCODER_VALUE_LEFT);
        backRight.setTargetPosition(POINT_TURN_ENCODER_VALUE_RIGHT);
        if(backLeft.getCurrentPosition() >= POINT_TURN_ENCODER_VALUE_LEFT && backRight.getCurrentPosition() <= -POINT_TURN_ENCODER_VALUE_RIGHT){
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
            robotPointTurned = true;
        }
        else{
            frontLeft.setPower(MOTOR_POWER);
            backLeft.setPower(MOTOR_POWER);
            frontRight.setPower(MOTOR_POWER);
            backRight.setPower(MOTOR_POWER);
        }
    }

}
