#! /bin/bash

currentYear=$1
currentDay=$2
targetInputDirectory=$3

dayNumber=$((10#$currentDay))

inputUrl="https://adventofcode.com/${currentYear}/day/${dayNumber}/input"
targetFile="${targetInputDirectory}/input"
touch ${targetFile}
echo "Fetch '${inputUrl}' to '${targetFile}'"


sessionId=$(cat .private/SessionId.txt)

curl ${inputUrl} \
  -H "cookie: session=${sessionId};" \
  --compressed \
  -o ${targetFile}