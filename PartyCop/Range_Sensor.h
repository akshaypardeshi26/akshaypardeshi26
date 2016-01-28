#ifndef _RANGE_SENSOR_H
#define _RANGE_SENSOR_H
#include "mbed.h"
class RangeSensor {
	public:
		RangeSensor(PinName trigger_p, PinName echo_p);
		int getDistance(void);
		int getDistanceFromRef(void);
		void startTiming(void);
		void stopTiming(void);

	private:
		DigitalOut _trigger;
		InterruptIn _echo_interrupt;
		Timer timer;
		volatile bool waiting;
		volatile int start, stop, timeInterval_us;
		// Reference location that the sensor will calculate the distance from (cm)
		volatile int zero_location;
};
#endif
