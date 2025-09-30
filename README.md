# response-tally

An exercise for counting responses to a survey.

## Getting Started
Fork and clone this repository. You can look at the instructions from our [previous exercise](https://github.com/auberonedu/github-intro) as a reminder of how to do this. MAKE SURE TO CLONE YOUR FORK, NOT THE ORIGINAL.

## Data Format
There is a file `responses.txt` that contains the data we'll be working with. Here's what's inside it.
```
studentA maps
studentB maps
studentA lists
studentB arrays
studentC loops
studentC arrays
studentA compound
```

The first string on each line is a pseudonymous id of a student. The second string is a topic they want to know more about.

## Template Code
Look at `Tallyer.java` to see the already provided code. Take a look over it and predict what it will do when you run it.

### Running the code
1. Open your terminal in VS Code. On Mac/Linux, it should have the right type of terminal by default. On Windows, follow [these instructions](https://stackoverflow.com/questions/42606837/how-do-i-use-bash-on-windows-from-the-visual-studio-code-integrated-terminal) to make Git Bash the default terminal. (You do not need to do step 1 because you already have installed git).
1. Verify you are in the `response-tally` directory by running `pwd` (print working directory) on the terminal. The printed directory should end in `response-tally`.
1. Compile the Java files by running `javac src/Tallyer.java`
1. Run the main method of Tallyer by running `java -cp src Tallyer < responses.txt`
1. You should expect that it will say "null" for how many times each topic appears. This is because you have not completed the assignment yet. Once completed, it will show the proper tallies.

## Submitting
Once you have completed the below section (Modifying the Code), please open a Pull Request (PR). Please copy and paste your PR URL into Canvas to receive credit. Include your attempt at Wave 2, even if you did not complete it.

## Modifying the code

### Wave 1 (Required)
Implement `tallyTopics`. Read the Javadoc carefully to understand what the method is meant to do. Make sure to compile your code each time before running it!

For the sample `responses.txt` file, a correct output would look similar to:
```
{maps=2, lists=1, loops=1, arrays=2, compound=1}
```
The order is unimportant.

Try also running it on `bigResponses.txt` and see the result.


### Wave 2 (Get as far as you can)
Implement `tallyTopicsFiltered`. Read the Javadoc carefully to understand what the method is meant to do. Make sure to compile your code each time before running it!

For the above sample file, a correct output would look similar to:
```
{maps=1, loops=1, arrays=2}
```
The order is unimportant.

Try also running it on `bigResponses.txt` and see the result.

### Extra Challenges (Entirely optional)
Some more ways to exercise:
1. Display the results in sorted order
1. Robustly handle malformed files
1. Come up with some other interesting way to work with the data!
