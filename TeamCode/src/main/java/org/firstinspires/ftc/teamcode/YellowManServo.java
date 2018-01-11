package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


/**
 * Created by Alex on 11/28/2017.
 */

//COMPLETED: Nitin, plz comment this when you're free. I've done the comments for basically all the other classes. Remember to be specific and includes the algorithm and methodology.
//getting the servo on the claw to grab the yellow man
@TeleOp(name="YellowManServo", group="OpMode")
//@Disabled
public class YellowManServo extends OpMode{
    ElapsedTime runtime = new ElapsedTime();
    private Servo servo1 = null;
    private DcMotor extendMotor = null;
    private DcMotor retractMotor = null;

    boolean closed;
    boolean pressed;


    @Override
    public void init() {
        telemetry.clearAll();
        telemetry.addData("Status", "Initialized");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        retractMotor= hardwareMap.get(DcMotor.class, "retractMotor");
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
        telemetry.addData("claw servo", servo1.getPosition());
        this.closed = true;
        this.pressed = false;

        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        retractMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop()
    {
        if(gamepad1.dpad_up){
            retractMotor.setPower(0.3);
        }else if(gamepad1.dpad_down){
            retractMotor.setPower(-0.3);
        }else{
            retractMotor.setPower(0);
        }


        if(gamepad1.x){
            extendMotor.setPower(0.3);
        }else if(gamepad1.y){
            extendMotor.setPower(-0.3);
        }else{
            extendMotor.setPower(0);
        }

        if(gamepad1.a)
        {
            extendMotor.setPower(-0.3);
            retractMotor.setPower(0.3);
        } else if(gamepad1.b){
            extendMotor.setPower(0.3);
            retractMotor.setPower(-0.3);
        } else {
            extendMotor.setPower(0);
            retractMotor.setPower(0);
        }


        if(pressed)
        {
            if(!gamepad2.a)
                pressed = false;
        }
        else
        {
            if(gamepad2.a)
            {
                if(!closed)
                {
                    //closing the servo
                    servo1.setDirection(Servo.Direction.FORWARD);
                    servo1.setPosition(0.2);
                    closed = true;
                    telemetry.addLine("closed");
                }
                else
                {
                    //opening the servo
                    servo1.setDirection(Servo.Direction.REVERSE);
                    servo1.setPosition(0.8);
                    closed = false;
                    telemetry.addLine("opened");
                }
                pressed = true;
            }
        }
        telemetry.addData("servo1: ", servo1.getPosition());

        telemetry.addData("retractMotor: ", retractMotor.getPower());
        telemetry.addData("extendMotor: ", extendMotor.getPower());
        telemetry.addData("runtime", getRuntime());
    }
}

