import serial
import csv

# Initialize Serial for USB output
usb_serial = serial.Serial('/dev/ttyUSB0', 9600)

# Setting up CSV file on SD card
with open('/path/to/sdcard/data_log.csv', 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['Latitude', 'Longitude', 'Altitude', 'NumSatellites', 'AngVelocityX', 'AngVelocityY', 'AngVelocityZ', 'AccelerationX', 'AccelerationY', 'AccelerationZ', 'MagneticX', 'MagneticY', 'MagneticZ'])

    while True:
        # Read from GPS


        # Read from IMU


        # Format data


        # Write to SD card
        writer.writerow(data)

        # Output to USB
        usb_serial.write(str(data).encode())
