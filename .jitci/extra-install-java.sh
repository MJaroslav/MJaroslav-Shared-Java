#!/bin/sh
rm -rf /home/jitpack/tools/jdk16/
mkdir /home/jitpack/tools/jdk16/
wget -nv https://github.com/adoptium/temurin16-binaries/releases/download/jdk-16.0.2%2B7/OpenJDK16U-jdk_x64_linux_hotspot_16.0.2_7.tar.gz -O /home/jitpack/tools/jdk16/jdk16.tar.gz
tar xfz /home/jitpack/tools/jdk16/jdk16.tar.gz --strip-components=1 -C /home/jitpack/tools/jdk16
