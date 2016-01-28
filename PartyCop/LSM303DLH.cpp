#include "LSM303DLH.h"

LSM303DLH::LSM303DLH(PinName sda, PinName scl): _i2c(sda, scl) {
	_i2c.frequency(30);
}

bool LSM303DLH::read_b_acc_reg(int reg_addr, char *store) {
	bool res = false;
	char reg_addr_and_data = reg_addr;

	if ((_i2c.write(ADDR_ACC_WRITE, &reg_addr_and_data, 1) == 0) && (_i2c.read(ADDR_ACC_READ, &reg_addr_and_data, 1) == 0)) {
		res = true;
		*store = reg_addr_and_data;
	}

	return res;
}

bool LSM303DLH::read_s_acc_reg(int reg_addr, short *store) {
	bool res = false;
	char *pt = (char *)store;

	res  = read_b_acc_reg(reg_addr+0, pt+0);
	res &= read_b_acc_reg(reg_addr+1, pt+1);

	return res;
}
