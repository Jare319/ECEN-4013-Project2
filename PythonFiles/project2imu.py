import board
import busio
import time
import adafruit_bno055

i2c = busio.I2C(board.SCL, board.SDA)
bno = adafruit_bno055.BNO055_I2C(i2c)

print("XGyro(rad/s)  YGyro(rad/s)  ZGyro(rad/s)  XAccel(m/s^2)  YAccel(m/s^2)  ZAccel(m/s^2)  XMag(µT)  YMag(µT)  ZMag (µT)")

while True:
    # Angular velocity (Gyro) data reads in rad/s.
    ang_velocity = bno.gyro
    # ang_velocity_x, ang_velocity_y, ang_velocity_z = ang_velocity

    # Acceleration (Accel) data reads in m/s^2.
    acceleration = bno.acceleration
    # accel_x, accel_y, accel_z = acceleration

    # Magnetic field data (Mag) reads in µT.
    mag_field = bno.magnetic
    # mag_field_x, mag_field_y, mag_field_z = mag_field
    
    # Print the data vertically on the same line
    #print(f"   {ang_velocity_x:.2f}          {ang_velocity_y:.2f}       {ang_velocity_z:.2f}           {accel_x:.2f}          {accel_y:.2f}           {accel_z:.2f}        {mag_field_x:.2f}      {mag_field_y:.2f}     {mag_field_z:.2f}")
    print(acceleration)
    # Delay between readings data
    time.sleep(1)
