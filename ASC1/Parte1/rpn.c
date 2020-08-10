#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define STACK_SIZE 40
#define INPUT_SIZE 40

int loop = 1;
//construçao da stack
struct stack{
	int size;
	int stk[STACK_SIZE];
};
struct stack pilha;


void push(int b){
	if(pilha.size == STACK_SIZE-1){
		printf("\n Stack is full");
	}
	else{
		pilha.stk[pilha.size+1] = b;
		pilha.size+=1;
	}
}

double pop(){
	double b;
	if(pilha.size == -1){
		printf("\n Stack is empty or not enough elements\n");
	}
	else{
		b = pilha.stk[pilha.size];
		pilha.size-=1;
		return b;
	}
}






//funçao para ler e fazer print da stack
void printStack(struct stack a){
	printf("\n Stack:");
	if(a.size == -1){
		printf("\n (empty) ");
	}
	else{
		int i=0;
		while(i<=a.size){
			printf("\n %d", a.stk[i]);
			i++;
		}
	}
}


//operaçoes
void add(){
	int a, b;
	b = pop();
	a = pop();
	push(a+b);
}

void sub(){
	int a, b;
	b = pop();
	a = pop();
	push(a-b);
}

void mult(){
	int a, b;
	b = pop();
	a = pop();
	push(a*b);
}

void divi(){
	int a, b;
	b = pop();
	a = pop();
	push(a/b);
}

void neg(){
	int a;
	a = pop();
	push(-a);
}

void swap(){
	int a, b;
	b = pop();
	a = pop();
	push(b);
	push(a);
}

void dup(){
	int a = pop();
	push(a);
	push(a);
}

void drop(){
	pop();
}

void clear(){
	int b = pilha.size;
	for(int a = 0; a<=b;a++){
		drop();
	}
}

void printHelp(){
	printf("\n insert numbers to add to stack");
	printf("\n available commands:");
	printf("\n + for addition");
	printf("\n - for subtraction");
	printf("\n * for multiplication");
	printf("\n / for division");
	printf("\n neg to turn a number into it's symetric");
	printf("\n swap to swap 2 numbers");
	printf("\n dup to dublicate a number");
	printf("\n drop to delete a number");
	printf("\n clear to clear the stack");
	printf("\n off to shutdown the calculator\n");
}
//verificar se numero ou se caracter
void checker(char buffer[]){
	if(isdigit(buffer[0])!=0){
		push(atoi(buffer));
	}
	else{
		if(strcmp(buffer, "+")==0){
			add();
		}else if(strcmp(buffer, "-")==0){
			sub();
		}else if(strcmp(buffer, "*")==0){
			mult();
		}else if(strcmp(buffer, "/")==0){
			divi();
		}else if(strcmp(buffer, "neg")==0){
			neg();
		}else if(strcmp(buffer, "swap")==0){
			swap();
		}else if(strcmp(buffer, "dup")==0){
			dup();
		}else if(strcmp(buffer, "drop")==0){
			drop();
		}else if(strcmp(buffer, "clear")==0){
			clear();
		}else if(strcmp(buffer, "off")==0){
			loop = 0;
		}else if(strcmp(buffer, "help")==0){
			printHelp();
		}else{
			printf("\nInvalid input, try again\n");
		}
	}
}


int main(){
	char input[INPUT_SIZE];
	char temp[16];

	pilha.size = -1;
	//cabecario
	printf("\n ************************************************");
	printf("\n *** RPN - Reverse Polish Notation Calculator ***");
	printf("\n *** Bernardo (40535) e Bruno (45460)         ***");
	printf("\n *** ASC1 2019/2020                           ***");
	printf("\n ************************************************");
	printf("\n type 'help' for available commands");
	printf("\n");

	
	//main loop
	while(loop){

		//print stack
		printStack(pilha);

		//input
		printf("\n-> ");
		fgets(input, INPUT_SIZE, stdin);

		//ler input
		size_t length = strlen(input);
		for(int a =0; a<(length-1);a++){
			//limpar o temp
			memset(temp, 0, sizeof(temp));

			//separar o input pelos espaços
			int x = 0;
			while(input[a]!= ' '){
				temp[x] = input[a];
				x++;
				a++;
				if(input[a]== '\n'){
					break;
				}
			}
			checker(temp);

			//TESTES
			//printf("\n %s", temp);
			//printf("%c \n", input[a]);
		}
	}



	printf("Shutting off\n");
	return 0;
}

