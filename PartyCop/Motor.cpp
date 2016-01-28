#ifndef _MOTOR_H
#define _MOTOR_H

#include "mbed.h"

class Motor {
	private:
		float speed;
		char direction;
		//each color represents connection between the output pin on LPC1768 number and motor input
		DigitalOut blue, pink, orange, yellow;


	public:
		Motor(PinName blue, PinName pink, PinName orange, PinName yellow);
		void rotateLeft(float speed);
		void rotateRight(float speed);
		void changeSpeed(float speedValue);
};

#endif
