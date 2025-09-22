package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import java.util.Arrays;

@TeleOp(name = "Teleop")
public class Teleop extends LinearOpMode {
    Robot robot = new Robot();
    int robotCycle = 0;

    // Servo claw1;
    // double setPosition = 0.0;
    //double clawpivot1Position = 0.5;
    //double clawpivot2Position = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {




        //initialization variables, notifying robot is initialized and shows how long robot ran for
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Status", "Runtime " + robot.runtime.toString());
        telemetry.update();


        robot.init(hardwareMap);

        // robot.init(hardwareMap);
        //robot.lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //robot.lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lift1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lift2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        // robot.lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Robot Cycle", robotCycle);
            //  telemetry.addData("Arm Encoder Value", robot.lift.getCurrentPosition());
            telemetry.addData("Match Time (s)", getRuntime());
            telemetry.addData("FL Count", robot.frontLeft.getCurrentPosition());
            telemetry.addData("FR Count", robot.frontRight.getCurrentPosition());
            telemetry.addData("BL Count", robot.backLeft.getCurrentPosition());
            telemetry.addData("BR Count", robot.backRight.getCurrentPosition());
            telemetry.addData("lift1", robot.lift1.getCurrentPosition());
            telemetry.addData("lift2", robot.lift2.getCurrentPosition());
            telemetry.addData("Status", "Resetting Values");
            telemetry.update();



            //controller 1 functions
            double FrontLeftVal = gamepad1.left_stick_y - gamepad1.left_stick_x + -gamepad1.right_stick_x;
            double FrontRightVal = gamepad1.left_stick_y + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            double BackLeftVal = gamepad1.left_stick_y + (gamepad1.left_stick_x) + -gamepad1.right_stick_x;
            double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;

            //Powerplay controller configs for reference to centerstage indirect drive
            //     double FrontLeftVal = gamepad1.left_stick_y - gamepad1.left_stick_x + -gamepad1.right_stick_x;
            //     double FrontRightVal = gamepad1.left_stick_y + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            //     double BackLeftVal = gamepad1.left_stick_y + (gamepad1.left_stick_x) + -gamepad1.right_stick_x;
            //     double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;


            // change orientation bc going forward is backwards
            //Move range to between 0 and +1, if not already
            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];

            }

            robot.frontLeft.setPower(FrontLeftVal * 0.6);
            robot.frontRight.setPower(FrontRightVal * 0.6);
            robot.backLeft.setPower(BackLeftVal * 0.6);
            robot.backRight.setPower(BackRightVal * 0.6);
            //double lift1Val = 0;
            //double lift2Val = 0;
            robot.lift1.setPower(-gamepad2.left_stick_y);
            robot.lift2.setPower(-gamepad2.left_stick_y);
            robot.lift1.setPower(-gamepad2.left_stick_y); //slides are slipping too much
            robot.lift2.setPower(-gamepad2.left_stick_y);

            telemetry.addData("frontLeft", FrontLeftVal); // Note: driver hub shows this and the count version but idk why nor what it is
            telemetry.addData("frontRight", FrontRightVal);
            telemetry.addData("backLeft", BackLeftVal);
            telemetry.addData("backRight", BackRightVal);

            telemetry.update();
            // set power to wheel motors
            robot.frontLeft.setPower(FrontLeftVal);
            robot.frontRight.setPower(FrontRightVal);
            robot.backLeft.setPower(BackLeftVal);
            robot.backRight.setPower(BackRightVal);



            if(gamepad2.a) {
                // move to 0 degrees.
                robot.claw.setPosition(0); //claw works DO NOT CHANGE
            }


            else if (gamepad2.b) {
                // move to 90 degrees.
                robot.claw.setPosition(0.3);
            }

            if (gamepad2.y) {   //this is pivot down
                //clawpivot1Position = 1;
                //clawpivot2Position = 1;
                robot.clawpivot1.setPosition(0.75); //check if the two 3d printed servo mounts will move together
                robot.clawpivot2.setPosition(0.45);


            }


            else if (gamepad2.x) {  //this is pivot up
                //clawpivot1Position = 0.5;
                //clawpivot2Position = 0.5;
                robot.clawpivot1.setPosition(0.38); //same for this too but i keep the values close so it dont change a lot
                robot.clawpivot2.setPosition(0.68);
            }


            /* frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            */



            //   robot.clawpivot1.setPosition(clawpivot1Position);
            // robot.clawpivot2.setPosition(clawpivot2Position);


            //    if(gamepad2.x) {
            // move to 0 degrees.
            //      robot.clawpivot1.setPosition(0);
            //     robot.clawpivot2.setPosition(0);
            // }
            // else if (gamepad2.y) {
            // move to 90 degrees.
            //    robot.clawpivot1.setPosition(0.5);
            //   robot.clawpivot2.setPosition(0.5);
            // }
            // else if (gamepad2.x) {
            // move to 180 degrees.
            //    robot.clawpivot1.setPosition(1);
            //   robot.clawpivot2.setPosition(1);
            // }




            // arm servo controls


            //   if (gamepad2.x) {
            //     robot.claw1.setPosition(1);
            // } else if (gamepad2.y) {
            //    robot.claw1.setPosition(0);     //pos 0 is closed and 1 is open
            // }

            // if (gamepad2.x) {
            //   robot.claw1.setPosition(1);
            //  } else if (gamepad2.y) {
            //    robot.claw1.setPosition(0);     //pos 0 is closed and 1 is open
            // }

            //robot.claw1.setPosition(gamepad2.right_trigger);
            // if (gamepad2.left_trigger > 0) {
            //   robot.claw1.setPosition(gamepad2.left_trigger);
            // }
            // Lift
            // if (gamepad2.left_trigger == 1) {

            //   robot.lift.setPower(1);
            // } else if (gamepad2.right_trigger == 1) {
            //   robot.lift.setPower(-1);
            // }
            // else {
            //   robot.lift.setPower(0);
            // }

            // only touch this
            //  robot.lift.setPower(-gamepad2.left_stick_y * 0.6);
            // update lift power if arm flies back (noted at field ins arm flew back and dropped pixel

            //Drone Launch Code

            //  if (gamepad1.b) {
            //    robot.DroneLaunch.setPower(0.5);
            // }
            // else if (gamepad1.y) {
            //   robot.DroneLaunch.setPower(-0.5);
            // }
            // else {
            //   robot.DroneLaunch.setPower(0);
            // }

            //Rigging

            //   if (gamepad1.a) {
            //     robot.Rigging.setPower(0.75);
            //   robot.Rigging2.setPower(0.75);

            // }
            // else if (gamepad1.x) {
            //   robot.Rigging.setPower(-0.75);
            // robot.Rigging2.setPower(-0.75);
            // }
            // else {
            //   robot.Rigging.setPower(0);
            // robot.Rigging2.setPower(0);
            // }

            idle();
        }
    }
}