#!/bin/bash

while :
do
	cat testdata > /dev/serial0
	sleep 1
done
