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
    boolean closed;
    boolean pressed;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        telemetry.addData("servo1", servo1.getPosition());
        closed = true;
        pressed = false;
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop()
    {
        boolean a;
        a = gamepad1.a;


        if(pressed)
        {
            if(!a)
                pressed = false;
        }
        else
        {
            if(a)
            {
                if(!closed)
                {
                    servo1.setDirection(Servo.Direction.FORWARD);
                    servo1.setPosition(0.2);
                    closed = true;
                    telemetry.addLine("closed");
                }
                else
                {
                    servo1.setDirection(Servo.Direction.REVERSE);
                    servo1.setPosition(0.2);
                    closed = false;
                    telemetry.addLine("opened");
                }
                pressed = true;
            }
        }
        telemetry.addData("servo1", servo1.getPosition());
        telemetry.addData("runtime", getRuntime());
    }
}