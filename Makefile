NAME = avaj-launcher

SRC_DIR = src
BIN_DIR = bin

SRC = $(wildcard $(SRC_DIR)/*/*.java)

J = java
JFLAGS = -cp $(BIN_DIR)
JC = javac
JCFLAGS = -d $(BIN_DIR)

RM = rm -rf
all: $(NAME)

$(NAME): $(BIN_DIR)
		$(JC) $(JCFLAGS) $(SRC)

$(BIN_DIR):
		mkdir -p $@

run:
		$(J) $(JFLAGS) simulator.Simulator scenarios/1.txt
	
clean:
		$(RM) $(BIN_DIR)

re: clean all

.PHONY: all clean re