
#PA1010D data reading

import smbus2
import time

# I2C buses  and addresses 
i2c_bus = 1  
i2c_address = 0x10  
# Open the I2C connection
bus = smbus2.SMBus(i2c_bus)

# Function to read 16-bit signed integer from I2C registers
def read_int16(address, register):
    high_byte = bus.read_byte_data(address, register)
    low_byte = bus.read_byte_data(address, register + 1)
    value = (high_byte << 8) | low_byte
    if value & 0x8000:
        value -= 0x10000
    return value

# Printting Date, Time, Satellites, Latitude, Longtitude, Elevation
print("Date\t\tTime\t\tSatellites\tLatitude\t        Longitude\t        Elevation")

# Continuously read and process PA1010D data
while True:
# Read latitude
    latitude = read_int16(i2c_address, 0x00) / 100000.0

# Read longitude
    longitude = read_int16(i2c_address, 0x02) / 100000.0

# Read elevation (Mean Sea Level - MSL)
    elevation = read_int16(i2c_address, 0x04) / 100.0

# Read number of satellites locked
    satellites = read_int16(i2c_address, 0x06)

# Get current date and time
    current_time = time.strftime('%Y-%m-%d\t%H:%M:%S')

# Captured GPS data print 
    print(f"{current_time}\t{satellites}\t\t{latitude:.6f}\t\t{   longitude:.6f}\t\t{    elevation:.2f}")

# Delay between readings data
    time.sleep(1)

    
