NAME = avaj-launcher

SRC_DIR = src
BIN_DIR = bin

SRC = $(wildcard $(SRC_DIR)/*/*.java)

J = java
JFLAGS = -cp $(BIN_DIR)
JC = javac
JCFLAGS = -d $(BIN_DIR)

RM = rm -rf

all: banner $(NAME)

banner:
	@clear
	@printf "\n"
	@printf "✈........................................\r"; sleep 0.15
	@printf "..✈......................................\r"; sleep 0.15
	@printf ".....✈...................................\r"; sleep 0.15
	@printf "........✈................................\r"; sleep 0.15
	@printf ".............✈...........................\r"; sleep 0.15
	@printf "..................✈......................\r"; sleep 0.15
	@printf ".......................✈.................\r"; sleep 0.15
	@printf "............................✈............\r"; sleep 0.15
	@printf ".................................✈.......\r"; sleep 0.15
	@printf "......................................✈..\n"
	@echo ""
	@echo "  ___             _     _                        _               "
	@echo " / _ \           (_)   | |                      | |              "
	@echo "/ /_\ \_   ____ _ _    | | __ _ _   _ _ __   ___| |__   ___ _ __ "
	@echo "|  _  \ \ / / _  | |   | |/ _  | | | |  _ \ / __|  _ \ / _ \  __|"
	@echo "| | | |\ V / (_| | |   | | (_| | |_| | | | | (__| | | |  __/ |   "
	@echo "\_| |_/ \_/ \__ _| |   |_|\__ _|\__ _|_| |_|\___|_| |_|\___|_|   "
	@echo "                _/ |_____                                        "
	@echo "               |__/______|                                       "
	@echo ""
	@echo "                         ✈"
	@echo "                        /|\\"
	@echo "                       /_|_\\"
	@echo "                    __/_____\__"
	@echo "~~~~~~~~~~~~~~~~~~~~~~o~~~~~~~~~~~~"
	@echo "               TAKEOFF INITIATED..."
	@echo ""

$(NAME): $(BIN_DIR)
		$(JC) $(JCFLAGS) $(SRC)

$(BIN_DIR):
		mkdir -p $@

run:
		$(J) $(JFLAGS) src.simulator.Simulator scenarios/02.txt

fclean: clean
		$(RM) simulation.txt
	
clean:
		$(RM) $(BIN_DIR)

re: fclean all

.PHONY: all clean fclean re