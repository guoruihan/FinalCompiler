.text

.globl	_qpow__myfunc
.p2align	1
.type	_qpow__myfunc,@function

_qpow__myfunc:
	l_0:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a3, a2
	li a4, 1
	mv a2, a0
	l_1:
	bgtz a1, l_2
	l_3:
	mv a0, a4
	l_4:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_2:
	mv a0, a1
	li t1, 1
	and a0, a0, t1
	li t1, 1
	bne a0, t1, l_5
	l_6:
	mv a0, a4
	mul a0, a0, a2
	rem a0, a0, a3
	mv a4, a0
	l_5:
	mv a0, a2
	mul a0, a0, a2
	rem a0, a0, a3
	mv a2, a0
	li t1, 2
	div a1, a1, t1
	j l_1

.globl	_main__myfunc
.p2align	1
.type	_main__myfunc,@function

_main__myfunc:
	l_7:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	li a2, 10000
	li a1, 10
	li a0, 2
	call _qpow__myfunc 
	call toString 
	call puts 
	li a0, 0
	l_8:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	main
.p2align	1
.type	main,@function

main:
	l_9:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	call _main__myfunc 
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

