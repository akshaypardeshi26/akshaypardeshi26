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
	$(COMPILE_C) $(COMMON_SRC) $(C_SRC) $(CFLAGS) -o rf-small $(LDFLAGS)

medium: $(C_SRC)
	$(COMPILE_C) $(COMMON_SRC) $(C_SRC) $(CFLAGS) -o rf-medium $(LDFLAGS)

large: $(C_SRC)
	$(COMPILE_C) $(COMMON_SRC) $(C_SRC) $(CFLAGS) -o rf-large $(LDFLAGS)

run-small:
	-./rf-small small.data  R 2>&1 | tee ../results/rf.small

run-medium:
	#ToDo
	#-./rf-medium medium.data 722 800 R 2>&1 | tee ../results/rf.medium

run-large:
	#ToDo
	#-./rf-large large.data 5000 1059 R 2>&1 | tee ../results/rf.large


clean:
	-rm -f *.o rf-small rf-medium rf-large