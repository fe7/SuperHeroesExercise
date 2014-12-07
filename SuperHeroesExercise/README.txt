SuperHeroesExercise program

### Description ###
Program will take three separate command line parameters: a text file, name of employee, and name of another employee.
First parameter will include the filename which contains the employee date:
A list of employees will be provided in a text file, one employee per line where first line is heading, and following lines are employee data separated by “|” includes ID of employee, name of employee and manager ID. 

Second and Third parameters of program are the employee name.

Output will be the shortest path between the two employees in the input list following the hierarchical structure.

Command line input after compiling:
	java ManagementChain superheroes.txt "Batman" "Super Ted"

Should output:
	Batman (16) -> Black Widow (6) -> Gonzo the Great (2) -> Dangermouse (1) <- Invisible Woman (3) <- Super Ted (15)

If two employees had same name but different employee IDs then paths for both employees will be output as they are not the same employees.


### Requirements ###
Latest version of JRE, if unsure refer to http://www.oracle.com/technetwork/java/javase/downloads/index.html
	(Compiled and tested with JDK1.7)

(JUnit 4 for Testing) - not required to run program 


### How to run ###
(presumed step: clone repo, or download and extract contents from compressed file)
1) From the command line navigate to the folder src inside SuperHeroesExercise
2) Compile the code using command below and enter:
	javac *.java
3) Run the program using command to run the program:
	java ManagementChain superheroes.txt "Batman" "Super Ted"

   This would run the program using the atchm_3134.txt file and would find message paths for the employees “Batman” and “Super Ted".

   Syntax for input:
   <inputfile.txt> is the first parameter, file which contains the employee data
   <employee1 name> is the second parameter which represents the first employee name
   <employee2 name> is the third parameter which represents the second employee name, in following command template:
   java ManagementChain <inputfile.txt> "<employee1 name>" "<emplopyee2 name>"
