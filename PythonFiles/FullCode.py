# SPDX-FileCopyrightText: 2021 ladyada for Adafruit Industries
# SPDX-License-Identifier: MIT

import time
import board
import busio

import adafruit_gps
import adafruit_bno055

i2c = board.I2C()  # uses board.SCL and board.SDA

# Create a GPS module instance.
gps = adafruit_gps.GPS_GtopI2C(i2c, debug=False)  # Use I2C interface

# Create an IMU instance.
imu = adafruit_bno055.BNO055_I2C(i2c)

usb_serial = serial.Serial('/dev/ttyUSB0', 115200)
bt_serial = serial.Serial('/dev/tty0', 115200)

# Turn on the basic GGA and RMC info (what you typically want)
gps.send_command(b"PMTK314,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")

# Set update rate to once a second (1hz)
gps.send_command(b"PMTK220,1000")

# Main loop runs forever printing the location, etc. every second.
last_print = time.monotonic()
while True:
    gps.update()
    current = time.monotonic()
    if current - last_print >= 1.0:
        last_print = current
        # All sensor data in transmission order.
        # 'Lat', 'Long', 'Alt', 'NumSats', 'AngVelX', 'AngVelY', 'AngVelZ', 'AccelX', 'AccelY', 'AccelZ', 'MagX', 'MagY', 'MagZ'
        bt_serial.write(str(gps.latitude)+","+str(gps.longitude)+","+str(gps.altitude)+","+str(gps.satellites)+","+str(imu.acceleration)) #idk what the rest would be called and ill do it later
