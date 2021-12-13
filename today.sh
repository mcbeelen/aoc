#! /bin/bash


currentYear=`date +%Y`
currentDay=`date +%d`
puzzleName=$1

./prepareForSomeDay.sh ${currentYear} ${currentDay} ${puzzleName}