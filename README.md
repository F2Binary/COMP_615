# COMP_615
COMP 615 Project - Checking if two Deterministic Finite Automata (DFAs) are equivalent. We define the scheme below:


# PART II: DFA Equivalence Checking
This section details the process of comparing two DFAs to determine their equivalence. 

Initially, both DFAs are minimized. This step ensures that each DFA is in 
its simplest form, making the equivalence checking process more efficient and accurate.
We establish associations between the states of DFA1 and DFA2 based on their State IDs. 
This mapping is crucial to compare the DFAs effectively.
The program iteratively walks through both DFAs for each input character (e.g., 'a', 'b'). 
At each step, it verifies that the current input character leads to the corresponding associated states in both DFAs.
During the walk, if the program encounters a situation where a new state 
association contradicts the existing structure of associations, it indicates a discrepancy between the two DFAs.
The equivalence of the two DFAs is established only if all input characters lead to 
consistent and matching state transitions in the associated states of both DFAs. Any contradiction in state 
associations signifies that the DFAs are not equivalent.