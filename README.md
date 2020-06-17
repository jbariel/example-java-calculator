# Simple Calculator - in Java
Simple Java app (showing off the Stream API and Java Lambdas) that operates as a calculator

## Build
`mvn` => builds the jar

`docker build -t calc:latest .` => builds the docker image

## Run
`docker run --rm calc:latest <action> <List<Number>> ` => does the math

### Add
`docker run --rm calc:latest "+" 1 2 3` => 6

`docker run --rm calc:latest add 1 2 3` => 6

### Subtract
`docker run --rm calc:latest "-" 10 1` => 9

`docker run --rm calc:latest subtract 10 1` => 9

### Multiply
`docker run --rm calc:latest "*" 10 1 8` => 80

`docker run --rm calc:latest multiply 10 1 8` => 80

### Divide
`docker run --rm calc:latest "/" 10 1` => 10

`docker run --rm calc:latest divide 10 1` => 10


# License
This is licensed under the Apache-2.0