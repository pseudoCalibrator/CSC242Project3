CSC242 Project 3: Uncertain Inference
Leah Goodwin lgoodwi3
Jacob Freiheit jfreihei
Jayda Medina jmedina7

To build, navigate to the project folder and type "bash run.sh"

To run exact, type "java -cp "./bin" bn/Test [File Name] [Query Variable] [Names and values of evidence variables]"
Examples:
java -cp "./bin" bn/Test bn/examples/aima-alarm.xml B J true M true
java -cp "./bin" bn/Test bn/examples/aima-wet-grass.xml R S true


To run approximate, type "java -cp "./bin" bn/Test [Number of samples] [File Name] [Query Variable] [Names and values of evidence variables] [rejection/likelihood]"
Examples:
java -cp "./bin" bn/Test 1000 bn/examples/aima-alarm.xml B J true M true likelihood
java -cp "./bin" bn/Test 1000 bn/examples/aima-wet-grass.xml R S true likelihood
java -cp "./bin" bn/Test 1000 bn/examples/aima-alarm.xml B J true M true rejection
java -cp "./bin" bn/Test 1000 bn/examples/aima-wet-grass.xml R S true rejection

Our program can handle both XMLBIF files and BIF files.