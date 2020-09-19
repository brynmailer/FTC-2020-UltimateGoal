package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.HashMap;

@TeleOp(name="Driver Controlled")
public class DriverControlled extends OpMode {
    private Robot robot;

    public DriverControlled() {
        HashMap<String, Boolean> robotConfig = new HashMap<String, Boolean>();

        robotConfig.put("drive", true);

        this.robot = new Robot(robotConfig);
    }

    @Override
    public void init() {
        this.robot.init(
            this.hardwareMap,
            this.telemetry,
            this.gamepad1,
            this.gamepad2
        );
    }

    @Override
    public void loop() {
        robot.drive();
    }
}