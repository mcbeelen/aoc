#! /bin/bash

currentYear=$1
currentDay=$2
puzzleName=$3

targetInputDirectory=src/main/resources/y${currentYear}/day${currentDay}

mkdir -p src/main/kotlin/y${currentYear}/day${currentDay}
mkdir -p ${targetInputDirectory}
mkdir -p src/test/kotlin/y${currentYear}/day${currentDay}


sed "s/PuzzleName/${puzzleName}/g;s/placeholderForPackage/y${currentYear}.day${currentDay}/" src/template/TemplateForPuzzle.kt > src/main/kotlin/y${currentYear}/day${currentDay}/${puzzleName}.kt
sed "s/PuzzleName/${puzzleName}/g;s/placeholderForPackage/y${currentYear}.day${currentDay}/" src/template/TemplateForPuzzleTest.kt > src/test/kotlin/y${currentYear}/day${currentDay}/${puzzleName}Test.kt

./fetch_input.sh ${currentYear} ${currentDay} ${targetInputDirectory}


