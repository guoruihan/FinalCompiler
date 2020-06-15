	.type	n,@object
	.section	.bss
	.global	n
	.p2align	2
n:
	.Ln$local:
	.word	0
	.size	n, 4

	.type	a,@object
	.section	.bss
	.global	a
	.p2align	2
a:
	.La$local:
	.word	0
	.size	a, 4

	.type	i,@object
	.section	.bss
	.global	i
	.p2align	2
i:
	.Li$local:
	.word	0
	.size	i, 4

.text

.globl	_jud__myfunc
.p2align	1
.type	_jud__myfunc,@function

_jud__myfunc:
	l_0:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a6, a0
	li a1, 0
	l_1:
	la tp, n
	lw a0, 0(tp)
	div a0, a0, a6
	blt a1, a0, l_2
	l_3:
	li a0, 0
	j l_4
	l_2:
	l_5:
	li a3, 0
	l_6:
	li a4, 0
	l_7:
	mv a0, a6
	li t1, 1
	sub a0, a0, t1
	blt a4, a0, l_8
	l_9:
	bnez a3, l_10
	l_11:
	li a0, 1
	l_4:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_10:
	l_12:
	mv a0, a1
	addi a1, a1, 1
	j l_1
	l_8:
	mv a0, a1
	mul a0, a0, a6
	mv a2, a0
	add a2, a2, a4
	mv a0, a1
	mul a0, a0, a6
	add a0, a0, a4
	addi a0, a0, 1
	la tp, a
	lw a5, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a5
	addi tp, tp, 4
	lw a2, 0(tp)
	la tp, a
	lw a5, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a5
	addi tp, tp, 4
	lw t0, 0(tp)
	ble a2, t0, l_13
	l_14:
	l_15:
	li a3, 1
	l_16:
	l_13:
	l_17:
	mv a0, a4
	addi a4, a4, 1
	j l_7

.globl	_main__myfunc
.p2align	1
.type	_main__myfunc,@function

_main__myfunc:
	l_18:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	call getInt 
	la tp, n
	sw a0, 0(tp)
	li a0, 0
	la tp, i
	sw a0, 0(tp)
	l_19:
	la tp, i
	lw a1, 0(tp)
	la tp, n
	lw a0, 0(tp)
	blt a1, a0, l_20
	l_21:
	la tp, n
	lw a0, 0(tp)
	la tp, i
	sw a0, 0(tp)
	l_22:
	la tp, i
	lw a0, 0(tp)
	bgtz a0, l_23
	l_24:
	li a0, 0
	j l_25
	l_23:
	la tp, i
	lw a0, 0(tp)
	call _jud__myfunc 
	bgtz a0, l_26
	l_27:
	l_28:
	la tp, i
	lw a0, 0(tp)
	li t1, 2
	div a0, a0, t1
	la tp, i
	sw a0, 0(tp)
	j l_22
	l_26:
	la tp, i
	lw a0, 0(tp)
	call toString 
	call print 
	li a0, 0
	l_25:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_20:
	call getInt 
	la tp, a
	lw a1, 0(tp)
	la tp, i
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	sw a0, 0(tp)
	l_29:
	la tp, i
	lw a0, 0(tp)
	la tp, i
	lw a0, 0(tp)
	addi a0, a0, 1
	la tp, i
	sw a0, 0(tp)
	j l_19

.globl	main
.p2align	1
.type	main,@function

main:
	l_30:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	li s4, 20
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s4
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s4, 0(tp)
	l_31:
	bgtz s4, l_32
	l_33:
	la tp, a
	sw a0, 0(tp)
	call _main__myfunc 
	lw s4, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_32:
	mv tp, zero
	add tp, tp, s4
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s4, s4, -1
	j l_31

