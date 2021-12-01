# [Advent Of Code](https://adventofcode.com/)

This project will contain my solutions to the challenges of the Advent Of Code.

I will write the program in Kotlin.

This repo was started for the Advent of Code of 2018 and has been used since.


## Daily setup

In order to get coding quickly I wanted to automate some setup work.
My daily initialization script creates the correct directories for the day and fetches the input for the day.

### Run the script

```bash
./prepareForToday.sh <NameOfThePuzzle>
```

```
./prepareForToday.sh SonarSweep
```

### Fetching the input

In order to fetch the correct input file, the script needs to be able to access adventofcode.com on your behalf. This is done by using a valid session-cookie read from `./private/SessionId.txt`. Determine your sessionId by inspecting a request to the AdventOfCode-website while you are logged in.





## Prepare older puzzles

In order to get ready to do a puzzle from previous events: 
```
./prepareForSomeDay.sh 2015 01 NotQuiteLisp
```

