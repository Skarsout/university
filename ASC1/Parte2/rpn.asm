.data
stack: .space 4000
inputBuffer: .space 100
temp: .space 100
header: .asciiz "\n ************************************************\n *** RPN - Reverse Polish Notation Calculator ***\n *** Bernardo (40535) e Bruno (45460)         ***\n *** ASC1 2019/2020                           ***\n ************************************************\n"
print1: .asciiz "\nStack:"
print_empty: .asciiz "\n(Empty)"
print2: .asciiz "\n-> "
newline: .asciiz "\n"
shutdown: .asciiz "\nShutting off\n"
compAdd: .ascii "+"
compSub: .ascii "-"
compMult: .ascii "*"
compDiv: .ascii "/"
compNeg: .ascii "neg"
compSwap: .ascii "swap"
compDup: .ascii "dup"
compDrop: .ascii "drop"
compOff: .ascii "off"
invalidInput: .asciiz "\nInvalid input, try again\n"
printUnderflow: .asciiz "\nStack is empty or not enough elements\n"
printOverflow: .asciiz "\nStack is full\n"

.text

main:
	#print do header
	li $v0, 4
	la $a0, header
	syscall
	
	#tamanho da stack
	addi $t0, $zero, 0
	addi $s6, $zero, -1
mainLoop:
	j printStack


#READ STRING
strRead:
	la $s1, temp

loopString:
	lb $t2, 0($s0) 
	
	beq $t2,'\0', endLoop
	beq $t2, 0x20,endLoop
	nop
	sb $t2, 0($s1)
	addi $s1, $s1, 1	
	addi $s0, $s0, 1	
				
	j loopString
	nop
	
endLoop:
	li $t9, '\0'
	sb $t9, 0($s1)
	
	addi $s0, $s0, 1
	
	la $s0, temp
	j checker
	nop

#INPUT
input:
	#input
	li $v0, 4
	la $a0, print2
	syscall
	
	li $v0, 8
	la $a0, inputBuffer
	la $a1, 100
	syscall
	
	move $s0, $a0
	
	j strRead
	nop

#STACK PRINTER	
printStack:
	li $v0, 4
	la $a0, print1
	syscall
	
	
	move $t1, $t0
	add $t3, $zero, $zero
printStackLoop:
	
	beq $s6, -1, emptyStack
	nop
	
	beq $t3, $s6, input
	
	lw $t2, stack($t1)
	
	addi $t1, $t1, 4
	addi $t3, $t3, 1
	li $v0, 1
	move $a0, $t2
	syscall
	
	li $v0, 4
	la $a0, newline
	syscall
	
	
	j printStackLoop
		
emptyStack:
	li $v0, 4
	la $a0, print_empty
	syscall
	j input
	nop

#STACK OPERATIONS
push:
	sw $t4, stack($t0)
	addi $t0, $t0, 4
	addi $s6, $s6, 1
	jr $ra
	nop
	
pop:
	beq $s6, -1, underflow
	nop
	lw $t5, stack($t0)
	addi $t0, $t0, -4
	addi $s6, $s6, -1
	jr $ra	
	nop

#CHECKER
checker:
	
	add $t9, $zero, $zero
	j isDigit
	nop

resume:
	la $s2, temp
	la $s3, compAdd
	jal strcmp
	nop
	bne $t7, $zero, addition
	nop
	
	la $s2, temp
	la $s3, compSub
	jal strcmp
	nop
	bne $t7, $zero, subtraction
	nop
	
	la $s2, temp
	la $s3, compMult
	jal strcmp
	nop
	bne $t7, $zero, multiplication
	nop
	
	la $s2, temp
	la $s3, compDiv
	jal strcmp
	nop
	bne $t7, $zero, division
	nop
	
	la $s2, temp
	la $s3, compNeg
	jal strcmp
	nop
	bne $t7, $zero, negation	
	nop
	
	la $s2, temp
	la $s3, compSwap
	jal strcmp
	nop
	bne $t7, $zero, swap
	nop
	
	la $s2, temp
	la $s3, compDup
	jal strcmp
	nop
	bne $t7, $zero, duplicate
	nop
	
	la $s2, temp
	la $s3, compDrop
	jal strcmp
	nop
	bne $t7, $zero, drop
	nop
	
	la $s2, temp
	la $s3, compOff
	jal strcmp
	nop
	bne $t7, $zero, off		
	nop
	
	li $v0, 4
	la $a0, invalidInput
	syscall
	
	j mainLoop
	nop
#STRING COMPARE
strcmp:
	lb $t8, ($s2)
	lb $t9, ($s3)
	bne $t8, $t9, different
	nop
	beq $t8, $t9, success
	nop
	addi $s2, $s2, 1
	addi $s3, $s3, 1
	j       strcmp
	nop
success:
	addi $t7, $zero, 1
	jr $ra
	nop
different:
	addi $t7, $zero, 0
	jr $ra
	nop
#NUMBER
isDigit:
	la $a0, temp
	lb $t3, ($a0)
	
	beq $t2, '\0', atoi
	addi $t3, $t3, -48
	
	blt $t3, 0, resume
	bgt $t3, 9, resume
	
	addi $a0, $a0, 1
	addi $t9, $t9, 1
	
	mul $t3, $t3, 10
	add $s5, $s5, $t3
	
	j isDigit
	nop
atoi:
	move $t4, $s5
	jal push
	j mainLoop
	nop
	
#OPERADORES	
addition:
	jal pop
	move $s4, $t5
	jal pop 
	move $s5, $t5
	add $t4, $s4, $s5
	jal push
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop
	
subtraction:
	jal pop
	move $s4, $t5
	jal pop 
	move $s5, $t5
	sub $t4, $s5, $s4
	jal push
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop

multiplication:
	jal pop
	move $s4, $t5
	jal pop 
	move $s5, $t5
	mul $t4, $s4, $s5
	jal push
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop

division:
	jal pop
	move $s4, $t5
	jal pop 
	move $s5, $t5
	div $s4, $s5
	mflo $t4
	jal push
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop

negation:
	jal pop
	move $s4, $t5
	sub $t4, $zero, $s4
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
swap:
	jal pop
	nop
	move $s5, $t5
	jal pop
	nop
	move $s4, $t5
	
	move $t4, $s5
	jal push
	nop
	move $t4, $s4
	jal push
	nop
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop
	
duplicate:
	jal pop
	nop
	move $t4, $t5
	
	jal push
	nop
	jal push
	nop
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop
drop:
	jal pop
	nop
	
	lb $s7, ($s0)
	beq $s7, '\0', mainLoop
	nop
	j strRead
	nop

overflow:
	li $v0, 4
	la $a0, printOverflow
	j mainLoop
	nop

underflow:
	li $v0, 4
	la $a0, printUnderflow
	j mainLoop
	nop
	
off:
	li $v0, 4
	la $a0, shutdown
	syscall
