.SUFFIXES: .c .u
CC= gcc
CFLAGS  = 
LDFLAGS = -lm



INCLUDES = -I$(CURDIR)/includes -I$(CURDIR)
COMPILE_C = $(CC) $(CFLAGS) $(INCLUDES)
COMMON_SRC := $(wildcard $(CURDIR)/*.c)
C_SRC := $(wildcard $(CURDIR)/includes/*.c)
SMALL = 
MEDIUM =
LARGE =

compile: small medium large

run-all: run-small run-medium run-large


small: $(C_SRC)
	$(COMPILE_C) $(COMMON_SRC) $(C_SRC) $(CFLAGS) -o dt-small $(LDFLAGS)

medium: $(C_SRC)
	$(COMPILE_C) $(COMMON_SRC) $(C_SRC) $(CFLAGS) -o dt-medium $(LDFLAGS)

large: $(C_SRC)
	$(COMPILE_C) $(COMMON_SRC) $(C_SRC) $(CFLAGS) -o dt-large $(LDFLAGS)

run-small:
	-./dt-small small.data  R 2>&1 | tee ../results/dt.small

run-medium:
	#ToDo
	#-./dt-medium medium.data 722 800 R 2>&1 | tee ../results/dt.medium

run-large:
	#ToDo
	#-./dt-large large.data 5000 1059 R 2>&1 | tee ../results/dt.large


clean:
	-rm -f *.o dt-small dt-medium dt-large