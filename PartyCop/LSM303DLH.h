#ifndef _LSM303DLH_H
#define _LSM303DLH_H                                                                                 

#include "mbed.h"

#define OUT_X_L_A 0x28
#define OUT_X_H_A 0x29
#define OUT_Y_L_A 0x2A
#define OUT_Y_H_A 0x2B
#define OUT_Z_L_A 0x2C
#define OUT_Z_H_A 0x2D

#define ADDR_ACC_READ   0x33
#define ADDR_ACC_WRITE  0x32
#define ADDR_MAG_READ   0x3D
#define ADDR_MAG_WRITE  0x3C

class LSM303DLH                                                    
{
	public:
		LSM303DLH(PinName sda, PinName scl);
		bool read_b_acc_reg(int reg_addr, char *store);
		bool read_s_acc_reg(int reg_addr, short *store);

	private:
		I2C _i2c;
};                                                                                                      

#endif   
