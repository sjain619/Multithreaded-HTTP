all: main

main:MyServer.java
	javac MyServer.java
	java MyServer

clean:
	rm *.class
	
