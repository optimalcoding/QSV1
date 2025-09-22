
package org.firstinspires.ftc.teamcode.pedroPathing;



import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {


    /* Public OpMode members. */
    public DcMotor frontRight;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor lift1;
    public DcMotor lift2;
    // public CRServo DroneLaunch;
    public Servo claw;
    // public DcMotor Rigging;
    // public DcMotor Rigging2;
    public Servo clawpivot1;
    public Servo clawpivot2;
    // public DcMotor lift4;


    //continuous rotation continuous rotation CR (Like a motor 360 degree rot.
    //Reg. servo (x degrees rot. limited so 180 degree but u can set specific angle to have servo rot.)

    /* local OpMode members. */
    HardwareMap hwMap = null;
    public ElapsedTime runtime = new ElapsedTime();

    /* Constructor */
    public Robot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;


        // Define and Initialize Devices
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        //   lift = hwMap.get(DcMotor.class, "lift");
        // DroneLaunch = hwMap.get(CRServo.class, "DroneLaunch");
        claw = hwMap.get(Servo.class, "claw");
        // Rigging = hwMap.get(DcMotor.class,"Rigging");
        // Rigging2 = hwMap.get(DcMotor.class,"Rigging2");
        clawpivot1 = hwMap.get(Servo.class, "clawpivot1");
        clawpivot2 = hwMap.get(Servo.class, "clawpivot2"); //(1 Servo Being used now)
        lift1 = hwMap.get(DcMotor.class, "lift1");
        lift2 = hwMap.get(DcMotor.class, "lift2");


        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        // Setting motor directions to turn
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        lift1.setDirection(DcMotor.Direction.REVERSE);
        lift2.setDirection(DcMotor.Direction.FORWARD);

        // lift1.setDirection(DcMotor.Direction.FORWARD);
        // lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw.setDirection(Servo.Direction.FORWARD);
        claw.setDirection(Servo.Direction.REVERSE);
        // clawpivot1.setDirection(Servo.Direction.FORWARD);
        // clawpivot1.setDirection(Servo.Direction.REVERSE);
        // clawpivot2.setDirection(Servo.Direction.FORWARD);
        // clawpivot2.setDirection(Servo.Direction.REVERSE);

        // Rigging.setDirection(DcMotor.Direction.REVERSE);
        // Rigging2.setDirection(DcMotorSimple.Direction.FORWARD);
        // DroneLaunch.setDirection(CRServo.Direction.REVERSE);

        // lift3.setDirection(DcMotor.Direction.FORWARD);
        // lift4.setDirection(DcMotor.Direction.REVERSE);

        // forward = clockwise tetrix 0.0-1.0 counter is -1.0 to -0.0
        //no need to def direction bc its reg servo

    }

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}