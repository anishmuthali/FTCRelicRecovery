package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nitin on 1/13/2018.
 */
@Autonomous
public class Colortest extends OpMode{

    private ColorSensor colorsensor = null;

    @Override
    public void init() {
        colorsensor = hardwareMap.get(ColorSensor.class, "colorsensor");
    }

    @Override
    public void loop()
    {


        if (colorsensor.blue() > colorsensor.red()) {
            telemetry.addLine("Blue");
        } else {
            telemetry.addLine("Red");
        }

        telemetry.addData("Red Value:", colorsensor.red());
        telemetry.addData("Blue Value:", colorsensor.blue());


    }
}
