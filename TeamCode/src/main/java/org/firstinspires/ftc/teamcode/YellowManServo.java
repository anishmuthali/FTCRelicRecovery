package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


/**
 * Created by Alex on 11/28/2017.
 */

//TODO: Nitin, plz comment this when you're free. I've done the comments for basically all the other classes. Remember to be specific and includes the algorithm and methodology.
//getting the servo on the claw to grab the yellow man
@TeleOp(name="YellowManServo", group="OpMode")
//@Disabled
public class YellowManServo extends OpMode{
    ElapsedTime runtime = new ElapsedTime();
    private Servo servo1 = null;
    boolean closed;
    boolean pressed;

    @Override
    public void init() {
        telemetry.clearAll();
        telemetry.addData("Status", "Initialized");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        telemetry.addData("claw servo", servo1.getPosition());
        this.closed = true;
        this.pressed = false;
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

