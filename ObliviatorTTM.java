package TTM;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * ObliviatorTTM - a robot by (your name here)
 */
public class ObliviatorTTM extends AdvancedRobot {
    private boolean movingForward;
    /**
     * run: ObliviatorTTM's default behavior
     */
    public double randomInt(int min, int max) {
        double n;
        do {
            n = Math.random() * max;
        } while (n < min);
        return n;
    }


    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        setScanColor(Color.red);
        setColors(Color.black,Color.black,Color.black);
        setAdjustRadarForGunTurn(false);
        // body,gun,radar

        // Robot main loop

        while(true) {
            // Replace the next 4 lines with any behavior you would like
            setAhead(500);
            setTurnRight(360);
            turnGunRight(360);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        if (normalRelativeAngleDegrees(e.getBearing() - getGunHeading()) >= 180) {
            setTurnGunLeft(e.getBearing());
            setTurnGunLeft(getHeading() - getRadarHeading() + e.getBearing());
        }
        else {
            setTurnGunRight(e.getBearing());
            setTurnGunRight(getHeading() - getRadarHeading() + e.getBearing());
        }
        fire(3);
    }

    public void reverseDirection() {
        if (movingForward) {
            setBack(40000);
            movingForward = false;
        } else {
            setAhead(40000);
            movingForward = true;
        }
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        reverseDirection();
        setTurnLeft(100);

    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        reverseDirection();
    }

    public void onHitRobot(HitRobotEvent e) {
        if (e.isMyFault()) {
            reverseDirection();
        }
    }
}
