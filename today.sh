#! /bin/bash


currentYear=`date +%Y`
currentDay=`date +%d`
puzzleNameFirst=$1
puzzleNameSecond=$2

./prepareForSomeDay.sh ${currentYear} ${currentDay} ${puzzleNameFirst}${puzzleNameSecond}