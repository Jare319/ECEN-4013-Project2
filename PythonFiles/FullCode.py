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

#usb_serial = serial.Serial('/dev/ttyUSB0', 115200)


# Turn on the basic GGA and RMC info (what you typically want)
gps.send_command(b"PMTK314,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0")

# Set update rate to once a second (1hz)
gps.send_command(b"PMTK220,1000")

csv = open("Data.csv","a+")
header = csv.readline()
if header == '':
    csv.write("Lat,Long,Alt,Sats,GyroX,GyroY,GyroZ,AccelX,AccelY,AccelZ,MagX,MagY,MagZ\n")

# Main loop runs forever printing the location, etc. every second.
last_print = time.monotonic()
while True:
    try:
        gps.update()
    except:
        print("Exception occured")
    current = time.monotonic()
    if current - last_print >= 1.0:
        last_print = current
        # All sensor data in transmission order.
        # 'Lat', 'Long', 'Alt', 'NumSats', 'AngVelX', 'AngVelY', 'AngVelZ', 'AccelX', 'AccelY', 'AccelZ', 'MagX', 'MagY', 'MagZ'
        dataString = "{},{},{},{},{:.3f},{:.3f},{:.3f},{:.3f},{:.3f},{:.3f},{:.3f},{:.3f},{:.3f}".format(gps.latitude,gps.longitude,gps.altitude_m,gps.satellites,imu.gyro[0],imu.gyro[1],imu.gyro[2],imu.acceleration[0],imu.acceleration[1],imu.acceleration[2],imu.magnetic[0],imu.magnetic[1],imu.magnetic[2])
        dataString = dataString.replace('('," ")
        dataString = dataString.replace(')'," ")
        print(dataString)
        f = open("testdata", "w")
        csv.write(dataString+"\n")
        f.write(dataString)
        f.close()

