package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="ServoTesting", group="OpMode")
//@Disabled
public class ServoTesting extends OpMode {
    ElapsedTime runtime = new ElapsedTime();
    private Servo servo1 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        telemetry.addData("servo1", servo1.getPosition());
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        boolean a;
        boolean b;


        a = gamepad1.a;
        b = gamepad1.b;


        if (a){
            servo1.setDirection(Servo.Direction.FORWARD);
            servo1.setPosition(0.2);
        }

        else if (b){
            servo1.setDirection(Servo.Direction.REVERSE);
            servo1.setPosition(0.2);
        }


        telemetry.addData("servo1", servo1.getPosition());
        telemetry.addData("runtime", getRuntime());
    }
}