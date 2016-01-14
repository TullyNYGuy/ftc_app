package org.tullyfirst.FTC8863.lib.FTCLib;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC8863 on 12/2/2015.
 * Class allows you to control a servo with one of 6 positions:
 * home, up, down, position1, position2, position3
 * It also allows you to setup an automatic servo "wiggle". This is where the servo will
 * automatically move back and forth between 2 positions for a given period of time. Each movement
 * in the wiggle will wait for a timer to expire and will make sure that the movement has completed.
 * This wiggle is useful for creating a vibration in the object controlled by the servo.
 */
public class Servo8863 {

    //*********************************************************************************************
    //          ENUMERATED TYPES
    //
    // user defined types
    //
    //*********************************************************************************************

    /**
     * The state of the servo wiggle.
     */
    public enum ServoWiggleState{
        WIGGLECOMPLETE, STARTPOSITION, WIGGLEPOSITION, NOWIGGLE
    }

    //*********************************************************************************************
    //          PRIVATE DATA FIELDS
    //
    // can be accessed only by this class, or by using the public
    // getter and setter methods
    //*********************************************************************************************

    /**
     * The home or default position of the servo
     */
    private double homePosition;

    /**
     * The up position of the servo
     */
    private double upPosition;

    /**
     * The down position of the servo
     */
    private double downPosition;

    /**
     * A generic position for the servo
     */
    private double positionOne;

    /**
     * A generic position for the servo
     */
    private double positionTwo;

    /**
     * A generic position for the servo
     */
    private double positionThree;

    /**
     * The servo that this class wraps around
     */
    private Servo teamServo;

    /**
     * The direction of the servo
     */
    private Servo.Direction direction;

    /**
     * A servo can be wiggled back and forth a little. This is the time delay between the
     * two positions of the wiggle.
     */
    private double wiggleDelay;

    /**
     * The wiggle of the servo is based off a position. The wiggle will start at the position
     * and then move to position + wiggleDelta. For example, if the position is .7 and the
     * wiggleDelta is -.2, the servo will wiggle between .7 and (.7-.2)= .5.
     */
    private double wiggleDelta = 0;

    /**
     * The position to start the wiggle from.
     */
    private double wiggleStartPosition = 0;

    /**
     * The tolerance used to see if the position for the wiggle movement has been reached
     */
    private double wigglePositionTolerance = 0;

    /**
     * The total time to wiggle the servo.
     */
    private double wiggleTime = 0;

    /**
     * A timer to use for controlling the total wiggle time of the servo.
     */
    private ElapsedTime elapsedTimeTotalWiggle;

    /**
     * A timer to use for controlling the time for each wiggle movement.
     */
    private ElapsedTime elapsedTimeEachWiggle;

    /**
     * Variable for holding the state of the servo wiggle
     */
    private ServoWiggleState servoWiggleState;

    /**
     * Enter debug mode
     */
    private boolean debug = false;


    //*********************************************************************************************
    //          GETTER and SETTER Methods
    //*********************************************************************************************

    public double getHomePosition() {
        return homePosition;
    }

    public void setHomePosition(double homePosition) {
        this.homePosition = homePosition;
    }

    public double getUpPosition() {
        return upPosition;
    }

    public void setUpPosition(double upPosition) {
        this.upPosition = upPosition;
    }

    public double getDownPosition() {
        return downPosition;
    }

    public void setDownPosition(double downPosition) {
        this.downPosition = downPosition;
    }

    public Servo.Direction getDirection() {
        return direction;
    }

    public void setDirection(Servo.Direction direction) {
        this.direction = direction;
        teamServo.setDirection(direction);
    }

    public double getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(double positionOne) {
        this.positionOne = positionOne;
    }

    public double getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(double positionTwo) {
        this.positionTwo = positionTwo;
    }

    public double getPositionThree() {
        return positionThree;
    }

    public void setPositionThree(double positionThree) {
        this.positionThree = positionThree;
    }

    /**
     * readonly
     * @return wiggle state of the servo
     */
    public ServoWiggleState getServoWiggleState() {
        return servoWiggleState;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    //*********************************************************************************************
    //          Constructors
    //*********************************************************************************************

    public Servo8863(String servoName, HardwareMap hardwareMap, double homePosition, double upPosition, double downPosition, Servo.Direction direction) {
        this.initServo(servoName, hardwareMap);
        setHomePosition(homePosition);
        setUpPosition(upPosition);
        setDownPosition(downPosition);
        this.setDirection(direction);
    }

    public Servo8863(String servoName, HardwareMap hardwareMap) {
        this.initServo(servoName, hardwareMap);
    }

    private void initServo(String servoName, HardwareMap hardwareMap) {
        teamServo = hardwareMap.servo.get(servoName);
        setHomePosition(0);
        setDownPosition(0);
        setUpPosition(0);
        setPositionOne(0);
        setPositionTwo(0);
        setPositionThree(0);
        setDirection(Servo.Direction.FORWARD);
        this.servoWiggleState = ServoWiggleState.NOWIGGLE;
        elapsedTimeTotalWiggle = new ElapsedTime();
        elapsedTimeEachWiggle = new ElapsedTime();
    }

    //*********************************************************************************************
    //          Public Methods
    //*********************************************************************************************

    public void goUp() {
        teamServo.setPosition(getUpPosition());
    }

    public void goDown() {
        teamServo.setPosition(getDownPosition());
    }

    public void goHome() {
        teamServo.setPosition(getHomePosition());
    }

    public void goPositionOne() {
        teamServo.setPosition(getPositionOne());
    }

    public void goPositionTwo() {
        teamServo.setPosition(getPositionTwo());
    }

    public void goPositionThree() {
        teamServo.setPosition(getPositionThree());
    }

    /**
     * Setup a servo wiggle. The servo can wiggle
     * between the wiggle start position and wiggleStartPosition + wiggleDelta. Each wiggle movement
     * will begin after the timer has expired and after the servo has reached the position
     * commanded +/- the wiggle position tolerance.
     * Note that you have to call startWiggle in order to start the wiggle.
     * Note that the looping routine will have to call updateWiggle after setting this up in order
     * to make the wiggle work.
     * @param wiggleStartPosition The position to start the wiggle from.
     * @param wiggleDelay The time to pass between each wiggle movement. If 0 then the only thing
     *                    that will be checked is if the servo has reached the wiggle position.
     * @param wiggleDelta How much to move the servo from the starting position. Can be + or -.
     */
    public void setupWiggle(double wiggleStartPosition, double wiggleDelay, double wiggleDelta, double wiggleTime) {
        this.wiggleStartPosition = wiggleStartPosition;
        this.wiggleDelay = wiggleDelay;
        this.wiggleDelta = wiggleDelta;
        this.wiggleTime = wiggleTime;
        // the servo will be said to have reached its position if it is within the range of
        // desired position - wigglePositionTolerance to desired position + wigglePositionTolerance
        this.wigglePositionTolerance = .05;
        // for now there is no wiggle started. We only setup one for the future
        servoWiggleState = ServoWiggleState.NOWIGGLE;
    }

    /**
     * Setup a servo wiggle. Then start the wiggle. The servo will wiggle
     * between the wiggle position and wigglePosition + wiggleDelta. Each wiggle movement will begin
     * after the timer has expired and after the servo has reached the position commanded +/-
     * the wiggle position tolerance.
     * Note that the looping routine will have to call updateWiggle after setting this up in order
     * to make the wiggle work.
     * @param wiggleStartPosition The position to start the wiggle from.
     * @param wiggleDelay The time to pass between each wiggle movement. If 0 then the only thing
     *                    that will be checked is if the servo has reached the wiggle position.
     * @param wiggleDelta How much to move the servo from the starting position. Can be + or -.
     */
    public void startWiggle(double wiggleStartPosition, double wiggleDelay, double wiggleDelta, double wiggleTime) {
        setupWiggle(wiggleStartPosition, wiggleDelay, wiggleDelta, wiggleTime);
        startWiggle();
    }

    /**
     * Then start the servo wiggle. It is assumed that you already setup the wiggle.
     * Should add error checking to make sure of this but won't now due to time.
     */
    public void startWiggle(){
        servoWiggleState = ServoWiggleState.STARTPOSITION;
        teamServo.setPosition(wiggleStartPosition);
        elapsedTimeTotalWiggle.reset();
        elapsedTimeEachWiggle.reset();
    }

    /**
     * This method needs to be called each time through the robot loop so that the wiggle is
     * controlled. It uses a state machine to keep track of things.
     */
    public void updateWiggle() {

        // if there is no wiggle started or ongoing, just return because there is nothing to do
        if(getServoWiggleState() == ServoWiggleState.NOWIGGLE){
            return;
        }
        // check to see if the total time the servo is supposed to wiggle has been exceeded.
        // if it has, the wiggle is done
        if(elapsedTimeTotalWiggle.time() > wiggleTime) {
            servoWiggleState = ServoWiggleState.WIGGLECOMPLETE;
        }

        switch(servoWiggleState) {
            case STARTPOSITION:
                // the servo is headed for the start position of the wiggle
                // see if the timer for this wiggle movement has expired
                if(elapsedTimeEachWiggle.time() > wiggleDelay) {
                    // timer has expired for this wiggle movement, see if the position has been
                    // reached within the tolerance limit
                    if (Math.abs(wiggleStartPosition - teamServo.getPosition()) < wigglePositionTolerance) {
                        // the position has been reached. Reset the movement timer, set the position to the wiggle position
                        elapsedTimeEachWiggle.reset();
                        teamServo.setPosition(wiggleStartPosition + wiggleDelta);
                        // move to the next state
                        servoWiggleState = ServoWiggleState.WIGGLEPOSITION;
                    }
                }
                break;

            case WIGGLEPOSITION:
                // the servo is headed for the "wiggle" position of the wiggle (start position +
                // wiggleDelta)
                // see if the timer for this wiggle movement has expired
                if(elapsedTimeEachWiggle.time() > wiggleDelay) {
                    // timer has expired for this wiggle movement, see if the position has been
                    // reached within the tolerance limit
                    if (Math.abs(wiggleStartPosition + wiggleDelta - teamServo.getPosition()) < wigglePositionTolerance) {
                        // the position has been reached. Reset the movement timer, set the next position to the start position
                        elapsedTimeEachWiggle.reset();
                        teamServo.setPosition(wiggleStartPosition);
                        // move to the next state
                        servoWiggleState = ServoWiggleState.STARTPOSITION;
                    }
                }
                break;

            case WIGGLECOMPLETE:
                // The wiggle has completed, set the position to the starting position of the wiggle
                teamServo.setPosition(wiggleStartPosition);
                break;

            case NOWIGGLE:
                // there has not been a servo wiggle started. Don't do anything
                break;
        }
    }

    /**
     * Stop or interrupt the wiggling of the servo. The servo position will be set to the starting
     * position of the wiggle.
     */
    public void stopWiggle() {
        servoWiggleState = ServoWiggleState.WIGGLECOMPLETE;
        teamServo.setPosition(wiggleStartPosition);
    }

    public double getPosition(){
        return teamServo.getPosition();
    }
}
