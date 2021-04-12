CSC242 Project 3: Uncertain Inference
Leah Goodwin lgoodwi3
Jacob Freiheit jfreihei
Jayda Medina jmedina7

To build, navigate to the project folder and type "bash run.sh"

To run exact, type "java -cp "./bin" bn/Test [File Name] [Query Variable] [Names and values of evidence variables]"
Example:
java -cp "./bin" bn/Test bn/examples/aima-alarm.xml B J true M true

To run approximate, type "java -cp "./bin" bn/Test [Number of samples] [File Name] [Query Variable] [Names and values of evidence variables] [rejection/likelihood]"
Example:
java -cp "./bin" bn/Test 1000 bn/examples/aima-alarm.xml B J true M true rejection