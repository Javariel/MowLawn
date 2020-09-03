# Overview
## Uncertainty of subject
According to the question specification, I'm allowed myself to do two multi-processing implementations due
to the obscure description, and also the given example doesn't include concurrent movement.
1. Multiple mowers launch simultaneously and each one receives corresponded move instructions ensemble
2. Multiple mowers launch simultaneously with one move instruction every time

## Programming Language
At beginning I have quickly done an implementation in python, but as you may know that the multi-processing in python is
not optimal, I've also implemented the code in java. 

## Abstract Design
- Lawn

I choose a binary matrix to represent lawn, basically the array index corresponds to the cartesian coordinates.

- Mower

I need a class to carry the mower information, such as, its position value:
coordinates (x, y) and orientation, and methods to update position/orientation.

- Orientation & Movement Action

Orientation: We have 4 values: N, W, S, E; and I need to define the next orientation by an given action value.
Movement Action: 3 values: L, R, F; (L, R) cause an orientation change and 'F' may change the mower position.

- Concurrency

In java implementation, I create two classes to implement both 2 possibilities for my doubt.
In Python implementation, I've implemented the first solution.

# Code
## Java
I create two packages and main function class _MowLawnLauncher_: 
### models 
This package includes 3 classes: Lawn, MoveOrientation and Mower:

- MoveOrientation

It is an Enum type class with 4 values: N, W, S, E and a static method 
which gives the next orientation based on the given orientation and an action in string(L, R)

- Lawn

One attribute: lawnMatrix as a two-dimensional boolean array initialized with false value.
3 methods:
one is to check if given position (x, y) is inside the lawn;
one is to check if any mower is mowing the given position (x, y);
one is to update the mower located situation in lawn.

- Mower

The class is implemented as exactly described in the abstract design.

### processors
There are 4 classes in this packages: IOProcessor, MowerRunner, MowerMultiInstructionRunner and MultiMowersExecutor.
- IOProcessor

2 static IO functions, one is to load file to a list of string, each element corresponding to a line in the file.
and another is to write the output string to a file located in the given string path, this function includes the file
creation but not directory creation. 

- MowerRunner

The class implements Callable interface with the specification about how a mower moves in lawn, which is based on the 
given action, calculate the new mower position/orientation and check with lawn situation, update both mower and lawn.
As implementation will be called as multi-threads processing, I added the concurrent/thread-safe control.

- MowerMultiInstructionRunner

The class is similar to MowerRunner, instead of receive one move action, it receives all mower move action as char[].
And it creates sequentially MowerRunner instance and call its "call" method by given the value in the mower actions array.

- MultiMowersExecutor 

2 execution functions launch multi-threads with each of these two implementations MowerRunner and 
MowerMultiInstructionRunner. 

## Python
3 python files:
- mower
It includes class Mower and a dictionary defined next orientation of Movement Action

- mow_lawn_funcs
3 functions:
one is to load lawn matrix
one is to load mowers list
one is to move mower sequentially with all the actions

- main_mow_lawn
main script to launch, including file reading and multi-threads (no output writing implementation)

## Error Handling
I hope that the code reviewer, you wouldn't mind my ignorance of errors/exception, 
in most time I let function throw the exception and not handle the error (including value check etc). 
I think this may not the objective of this exercise.

# Test
In java implementation, I also create several unit tests to examine the code behavior. 