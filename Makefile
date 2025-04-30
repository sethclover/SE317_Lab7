JAVAC = javac
SRC = $(wildcard src/**/*.java) src/Calculator.java
OUTDIR = build
MAIN_CLASS = Calculator

ifeq ($(OS),Windows_NT)
    RM = del /Q
    CP_SEP = ;
else
    RM = rm -rf
    CP_SEP = :
endif

all: compile

compile:
	$(JAVAC) -d $(OUTDIR) $(SRC)

run:
	java -cp $(OUTDIR) $(MAIN_CLASS)

clean:
	$(RM) /Q $(OUTDIR)\*.class
