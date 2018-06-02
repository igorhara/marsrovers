#**Mars Rover** 
_by Igor Hara Serafim da Silva_

CLI application implemented in Java 8 using Maven wrapper.

The specifications are described in this url: [github](https://github.com/abdulg/Mars-Rover)

###How to use
You must have a Java 8 SDK installed to run the project.

After downloading the project. In the _root folder_ of the project run:

**`./mvnw clean package`**

This command will compile, run the tests and generate, in the _target_ folder, the JAR file in that you will use to run 
the 
application.

The application JAR is a single jar. This jar does not need libraries to run.

To run the application just type the command line in the folder where the jar is:

**`java -jar marsrovers-1.0.jar`**



###Opening the project source code.

Just import the project in the IDE using maven as reference.

In Intellij you can use the option:
1. File->New->Project from existing sources
2. Select the root folder of the project.
3. Click on Import project from external model.
4. select maven, click next.
5. click next, next, next, finish


###Using the application

Every input will be submitted by pressing enter.
When the application starts it will ask how many rovers you will want to move. if you put 0 (zero) the application 
will finish it's execution.

After that it will ask for the  upper-right coordinates of the plateau. The application will ignore negative numbers 
or letters. It will block until you put two integer numbers separated by space.
Example: `6 6`

For every Rover it will ask the landing position, following this specific format: **number****number**_Letter_.
Where Letter will be one of these: N,E,S or W.
Example: `1 3 N`

And finally it will ask for the instructions to move the Rover. The instructions input consists of a sequence of the 
letters: R,L,M without space in between.
Example: `LMRMMMLMM`

After all inputs where done, for every Rover, the application will print the final position and direction the Rovers 
will be facing.

#####The application will ignore any Instruction that will make the Rover move out of the Plateau.
#####The application will ignore a rover if it's landing position is out of the plateau.
