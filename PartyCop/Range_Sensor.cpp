#include "rangeSensor.h"

#define TRIGGER_DELAY 0.000010

RangeSensor::RangeSensor(PinName trigger_p, PinName echo_p): _trigger(trigger_p), _echo_interrupt(echo_p) {
	_echo_interrupt.mode(PullDown);
	_echo_interrupt.rise(this, &RangeSensor::startTiming);
	_echo_interrupt.fall(this, &RangeSensor::stopTiming);
	timer.start();
	zero_location = 30.48;
}

// Returns distance in centimeters                                                                        
int RangeSensor::getDistance(void) {
	_trigger = 1;
	wait(TRIGGER_DELAY);
	_trigger = 0;

	return timeInterval_us;                                                                            
}

// Returns the distance from the reference point.
// (-) when the sensor is closer than the reference point,
// (+) when the sensor is further from the reference point
int RangeSensor::getDistanceFromRef(void) {
	_trigger = 1;
	wait(TRIGGER_DELAY);
	_trigger = 0;

	return timeInterval_us / 58 - zero_location;
}

void RangeSensor::startTiming(void) {
	start = timer.read_us();
}

void RangeSensor::stopTiming(void) {
	stop = timer.read_us();
	timeInterval_us = stop - start;
}
