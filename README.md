# Calculator

# Design
- Calculator.java : actual consumer api for using the calculator
- Token : a smallest value in expression is considered a token. i.e 1+1 has three tokens, + (operator), 1 (operand), 1 (operand) 
  - Operand.java  : Anything other than operators are considered to be operands at the moment.
  - Operator.java : - + / - * are the operators that are currently supported
- ExpressionParser.java : Converts raw string expression to list of tokens of operators and operands
- InfixExpression.java : Models infix expression and converts to postfix
- PostfixExpression.java : Validates and evaluates the converted postfix expression. 

# TODO
- handle brackets
- handle unary operations. ie -1-1
- adding support for more operators

# issues
- does not handle spaces in expression; need to add a validation or logic to trip the spaces

# possible improvements
- Operator class currently support binary operations; this could to design change if unary operators needs to be supported
- Logic to identify a operand/digit can be improved; currently anything which is not a operator is considered operand
- Calculator class can be tested using mocks; This would need another wrapper class as a consumer api; covering this up at integration tests for now.
- Token - Operator - Operand : class hierarchy can be re-looked at. did not like the fact that operands had to have a precedence method;
- exception redundancy can be removed;  invalid expression exception can be reused wherever needed
- Design of exception model can be re-looked at; runtime vs compile time exceptions ? for now keeping runtime exceptions but can be changed. 
- Postfix expression can have a validation too; error is throughn while evaluating the expression for now
 

