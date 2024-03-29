#! /bin/bash

currentYear=$1
currentDay=$2
puzzleName=$3

targetInputDirectory=y${currentYear}/src/main/resources/y${currentYear}/day${currentDay}

mkdir -p y${currentYear}/src/main/kotlin/y${currentYear}/day${currentDay}
mkdir -p ${targetInputDirectory}
mkdir -p y${currentYear}/src/test/kotlin/y${currentYear}/day${currentDay}


sed "s/PuzzleName/${puzzleName}/g;s/placeholderForPackage/y${currentYear}.day${currentDay}/" src/template/TemplateForPuzzle.kt > y${currentYear}/src/main/kotlin/y${currentYear}/day${currentDay}/${puzzleName}.kt
sed "s/PuzzleName/${puzzleName}/g;s/placeholderForPackage/y${currentYear}.day${currentDay}/" src/template/TemplateForPuzzleTest.kt > y${currentYear}/src/test/kotlin/y${currentYear}/day${currentDay}/${puzzleName}Test.kt

./fetch_input.sh ${currentYear} ${currentDay} ${targetInputDirectory}


