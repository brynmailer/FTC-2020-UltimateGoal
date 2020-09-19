package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.HashMap;

public class Robot {
    private DcMotor rfDrive = null;
    private DcMotor rbDrive = null;
    private DcMotor lfDrive = null;
    private DcMotor lbDrive = null;

    private HardwareMap hwMap = null;
    private Telemetry telemetry = null;
    private Gamepad gamepad1 = null;
    private Gamepad gamepad2 = null;

    private HashMap<String, Boolean> robotConfig;

    public Robot(HashMap<String, Boolean> robotConfig) {
        this.robotConfig = robotConfig;
    }

    public void init(
        HardwareMap hwMap,
        Telemetry telemetry,
        Gamepad gamepad1,
        Gamepad gamepad2
    ) {
        this.hwMap = hwMap;
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;

        if (this.robotConfig.get("drive")) this.initDriveMotors();
    }

    private void initDriveMotors() {
        this.rfDrive = this.hwMap.get(DcMotor.class, "rfDrive");
        this.rbDrive = this.hwMap.get(DcMotor.class, "rbDrive");
        this.lfDrive = this.hwMap.get(DcMotor.class, "lfDrive");
        this.lbDrive = this.hwMap.get(DcMotor.class, "lbDrive");

        this.rfDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rfDrive.setPower(0);

        this.rbDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rbDrive.setPower(0);

        this.lfDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.lfDrive.setPower(0);

        this.lbDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.lbDrive.setPower(0);
    }

    public void drive() {
        float leftStickX = this.gamepad1.left_stick_x;
        float leftStickY = this.gamepad1.left_stick_y;
        float rightStickX = this.gamepad1.right_stick_x;

        double r = Math.hypot(leftStickX, leftStickY);
        double robotAngle = Math.atan2(leftStickY, leftStickX) - Math.PI / 4;

        final double p1 = r * Math.sin(robotAngle) - rightStickX;
        final double p2 = r * Math.cos(robotAngle) - rightStickX;
        final double p3 = r * Math.cos(robotAngle) + rightStickX;
        final double p4 = r * Math.sin(robotAngle) + rightStickX;

        this.rfDrive.setPower(p1);
        this.rbDrive.setPower(p2);
        this.lfDrive.setPower(p3);
        this.lbDrive.setPower(p4);
    }

}