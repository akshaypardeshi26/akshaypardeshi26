#include "mbed.h"

#define OUT_X_H_M 3
#define OUT_X_L_M 4

I2C i2c(p28,p27);
Serial pc(USBTX, USBRX);

int main() {
    char data[2];
    char temp;
    int value;

    while(1) {
        data[0] = 0x20;
        data[1] = 0x27;
        i2c.write(0x32, data, 2);

        // read x-axis of accelerometer
        temp = 0x29;
        i2c.write(0x32, &temp, 1);
        i2c.read(0x33, data+1, 1);

        temp = 0x28;
        i2c.write(0x32, &temp, 1);
        i2c.read(0x33, data, 1);

        value = (data[1] << 8) | data[0];
        pc.printf("[*] %d\r\n", value);
    }
}
