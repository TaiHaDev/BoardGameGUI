## Code Review

Reviewed by: Phuoc Ha, u7454578

Reviewing code written by: Matthew Richards u7499989

Component: https://gitlab.cecs.anu.edu.au/u7499989/comp1140-ass2/-/tree/main/src/comp1140/ass2/actionstrategies
https://gitlab.cecs.anu.edu.au/u7499989/comp1140-ass2/-/tree/main/src/comp1140/ass2/handlers
https://gitlab.cecs.anu.edu.au/u7499989/comp1140-ass2/-/tree/main/src/comp1140/ass2/pipeline

### Comments 
- The code makes use of Factory Method creational design pattern (BuilderFactory) and strategy behavioral design pattern (ActionStrategy) which ehance the code reusability, readability and extensibility. Addionally, Java Stream is heavily used to increase development speed and make the code even more readable and less error-prone.
- Every important features of the code are documented in the merge request (https://gitlab.cecs.anu.edu.au/u7499989/comp1140-ass2/-/merge_requests/5).
- Classes are divided into its suitable packages and interface are used to promote polymorephism and avoid tight coupling between new method and the concrete classes. All of the code do follow the Java coding and naming convention.


