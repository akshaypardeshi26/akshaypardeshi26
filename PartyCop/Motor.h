#include "mbed.h"
#include "motor.h"

Motor::Motor(PinName _blue, PinName _pink, PinName _orange, PinName _yellow): blue(_blue), pink(_pink), orange(_orange), yellow(_yellow) {
	speed = 0.002;
	direction = 'L';
}

void Motor::rotateLeft(float speed)
{
	orange=1;
	yellow=0;
	pink=0;
	blue=0;
	wait(speed);    

	orange=1;
	yellow=1;
	pink=0;
	blue=0;
	wait(speed);

	orange=0;
	yellow=1;
	pink=0;
	blue=0;
	wait(speed);

	orange=0;
	yellow=1;
	pink=1;
	blue=0;
	wait(speed);

	orange=0;
	yellow=0;
	pink=1;
	blue=0;
	wait(speed);

	orange=0;
	yellow=0;
	pink=1;
	blue=1;
	wait(speed);

	orange=0;
	yellow=0;
	pink=0;
	blue=1;
	wait(speed);

	orange=1;
	yellow=0;
	pink=0;
	blue=1;
	wait(speed);


}

void Motor::rotateRight(float speed)
{       
	orange=1;
	yellow=0;
	pink=0;
	blue=0;
	wait(speed);

	orange=1;
	yellow=0;
	pink=0;
	blue=1;
	wait(speed);


	orange=0;
	yellow=0;
	pink=0;
	blue=1;
	wait(speed);


	orange=0;
	yellow=0;
	pink=1;
	blue=1;
	wait(speed);


	orange=0;
	yellow=0;
	pink=1;
	blue=0;
	wait(speed);

	orange=0;
	yellow=1;
	pink=1;
	blue=0;
	wait(speed);



	orange=0;
	yellow=1;
	pink=0;
	blue=0;
	wait(speed);

	orange=1;
	yellow=1;
	pink=0;
	blue=0;
	wait(speed);       
}

void Motor::changeSpeed(float speedvalue)
{
	speed=speedvalue;
}
