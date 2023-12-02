# Both BNO055 and PA1010D data combines 

import smbus2
import time
import board
import busio
import adafruit_bno055

# I2C bus and address configuration for the GPS sensor (PA1010D)
i2c_bus = 1  
i2c_address_gps = 0x10  

# I2C bus for the IMU sensor (BNO055)
i2c = busio.I2C(board.SCL, board.SDA)
bno = adafruit_bno055.BNO055_I2C(i2c)

# Open the I2C connection for the GPS sensor (PA1010D)
bus = smbus2.SMBus(i2c_bus)

# Read 16-bit signed integer from I2C registers
def read_int16(address, register):
    high_byte = bus.read_byte_data(address, register)
    low_byte = bus.read_byte_data(address, register + 1)
    value = (high_byte << 8) | low_byte
    if value & 0x8000:
        value -= 0x10000
    return value

# Printting Date, Time, Satelliates (Sat), Latitude(Lat), Longitude(Lon-MSL), Angular Velocity (Gyro), Acceleration(Accel), Magnetic Field(Mag)
print("Date\t\tTime\t\tSat\tLat\tLon\tEle(MSL)\tX Gyro\tYGyro\tZ Gyro \tX Accel\tY Accel\tZ Accel\tX Mag\tY Mag\tZ Mag")

# Continuously read and process PA1010D and BNO055 data
while True:
# Read latitude from PA1010D 
    latitude = read_int16(i2c_address_gps, 0x00) / 100000.0

# Read longitude from PA1010D 
    longitude = read_int16(i2c_address_gps, 0x02) / 100000.0

# Read elevation (Mean Sea Level - MSL) from PA1010D 
    elevation = read_int16(i2c_address_gps, 0x04) / 100.0

# Read number of satellites locked from PA1010D 
    satellites = read_int16(i2c_address_gps, 0x06)

# Get current date and time
    current_time = time.strftime('%Y-%m-%d\t%H:%M:%S')

# Read angular velocity (Gyro) data from BNO055
    ang_velocity = bno.gyro
    ang_velocity_x, ang_velocity_y, ang_velocity_z = ang_velocity

# Read acceleration (Accel) data from BNO055
    acceleration = bno.acceleration
    accel_x, accel_y, accel_z = acceleration

# Read magnetic field (Mag) data from BNO055
    mag_field = bno.magnetic
    mag_field_x, mag_field_y, mag_field_z = mag_field
    
# Printing captured data
    print(f"{current_time}\t{  satellites}\t\t{latitude:.6f}\t{longitude:.6f}\t{elevation:.2f}\t\t{ang_velocity_x:.2f}\t\t{ang_velocity_y:.2f}\t\t{ang_velocity_z:.2f}\t\t{accel_x:.2f}\t\t{accel_y:.2f}\t\t{accel_z:.2f}\t\t{mag_field_x:.2f}\t\t{mag_field_y:.2f}\t\t{mag_field_z:.2f}")

# Delay between readings
    time.sleep(1)

