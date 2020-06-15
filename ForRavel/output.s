	.type	g_0,@object
	.section	.rodata
	g_0:
	.asciz	"q.size() != N after pushes"
	.size	g_0, 27

	.type	g_1,@object
	.section	.rodata
	g_1:
	.asciz	"Head != i"
	.size	g_1, 10

	.type	g_2,@object
	.section	.rodata
	g_2:
	.asciz	"Failed: q.pop() != i"
	.size	g_2, 21

	.type	g_3,@object
	.section	.rodata
	g_3:
	.asciz	"q.size() != N - i - 1"
	.size	g_3, 22

	.type	g_4,@object
	.section	.rodata
	g_4:
	.asciz	"Passed tests."
	.size	g_4, 14

	.type	g_5,@object
	.section	.rodata
	g_5:
	.asciz	"Warning: Queue_int::pop: empty queue"
	.size	g_5, 37

.text

.globl	_main__myfunc
.p2align	1
.type	_main__myfunc,@function

_main__myfunc:
	l_0:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s1, 0(sp)
	addi sp, sp, -4
	sw s11, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	li a0, 12
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	mv s1, a0
	li s4, 100
	li s11, 0
	l_1:
	blt s11, s4, l_2
	l_3:
	mv a0, s1
	call _Queue_int.size__myfunc 
	bne a0, s4, l_4
	l_5:
	li s11, 0
	l_6:
	blt s11, s4, l_7
	l_8:
	la t2, g_4
	mv a0, t2
	call puts 
	li a0, 0
	j l_9
	l_7:
	mv a0, s1
	call _Queue_int.top__myfunc 
	bne a0, s11, l_10
	l_11:
	mv a0, s1
	call _Queue_int.pop__myfunc 
	bne a0, s11, l_12
	l_13:
	mv a0, s1
	call _Queue_int.size__myfunc 
	mv a1, s4
	sub a1, a1, s11
	li t1, 1
	sub a1, a1, t1
	bne a0, a1, l_14
	l_15:
	l_16:
	addi s11, s11, 1
	j l_6
	l_14:
	la t2, g_3
	mv a0, t2
	call puts 
	li a0, 1
	j l_9
	l_12:
	la t2, g_2
	mv a0, t2
	call puts 
	li a0, 1
	j l_9
	l_10:
	la t2, g_1
	mv a0, t2
	call puts 
	li a0, 1
	j l_9
	l_4:
	la t2, g_0
	mv a0, t2
	call puts 
	li a0, 1
	l_9:
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s11, 0(sp)
	addi sp, sp, 4
	lw s1, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_2:
	mv a0, s1
	mv a1, s11
	call _Queue_int.push__myfunc 
	l_17:
	addi s11, s11, 1
	j l_1

.globl	_Queue_int.Queue_int__myfunc
.p2align	1
.type	_Queue_int.Queue_int__myfunc,@function

_Queue_int.Queue_int__myfunc:
	l_18:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s11, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	mv s4, a0
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	li s11, 16
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s11
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s11, 0(tp)
	l_19:
	bgtz s11, l_20
	l_21:
	mv tp, zero
	add tp, tp, s4
	sw a0, 0(tp)
	l_22:
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s11, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_20:
	mv tp, zero
	add tp, tp, s11
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s11, s11, -1
	j l_19

.globl	_Queue_int.push__myfunc
.p2align	1
.type	_Queue_int.push__myfunc,@function

_Queue_int.push__myfunc:
	l_23:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s11, 0(sp)
	mv s11, a0
	mv s4, a1
	mv a0, s11
	call _Queue_int.size__myfunc 
	mv tp, zero
	add tp, tp, s11
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	lw a1, 0(tp)
	li t1, 1
	sub a1, a1, t1
	bne a0, a1, l_24
	l_25:
	mv a0, s11
	call _Queue_int.doubleStorage__myfunc 
	l_24:
	mv tp, zero
	add tp, tp, s11
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw s4, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	lw a1, 0(tp)
	addi a1, a1, 1
	mv tp, zero
	add tp, tp, s11
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	lw t0, 0(tp)
	rem a1, a1, t0
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	sw a1, 0(tp)
	l_26:
	lw s11, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.top__myfunc
.p2align	1
.type	_Queue_int.top__myfunc,@function

_Queue_int.top__myfunc:
	l_27:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv tp, zero
	add tp, tp, a0
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw a0, 0(tp)
	l_28:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.pop__myfunc
.p2align	1
.type	_Queue_int.pop__myfunc,@function

_Queue_int.pop__myfunc:
	l_29:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	mv s4, a0
	mv a0, s4
	call _Queue_int.size__myfunc 
	bnez a0, l_30
	l_31:
	la t2, g_5
	mv a0, t2
	call puts 
	l_30:
	mv a0, s4
	call _Queue_int.top__myfunc 
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 4
	lw a2, 0(tp)
	addi a2, a2, 1
	mv tp, zero
	add tp, tp, s4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	lw t0, 0(tp)
	rem a2, a2, t0
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 4
	sw a2, 0(tp)
	l_32:
	lw s4, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.size__myfunc
.p2align	1
.type	_Queue_int.size__myfunc,@function

_Queue_int.size__myfunc:
	l_33:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a1, a0
	mv tp, zero
	add tp, tp, a1
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a2
	lw t0, 0(tp)
	add a0, a0, t0
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 4
	lw t0, 0(tp)
	sub a0, a0, t0
	mv tp, zero
	add tp, tp, a1
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	lw t0, 0(tp)
	rem a0, a0, t0
	l_34:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.doubleStorage__myfunc
.p2align	1
.type	_Queue_int.doubleStorage__myfunc,@function

_Queue_int.doubleStorage__myfunc:
	l_35:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s1, 0(sp)
	addi sp, sp, -4
	sw s11, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	mv s6, a0
	mv tp, zero
	add tp, tp, s6
	lw s1, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 4
	lw s4, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw s11, 0(tp)
	mv a1, s1
	mv tp, zero
	add tp, tp, a1
	lw a1, 0(tp)
	li t1, 2
	mul a1, a1, t1
	mv s9, a1
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s9
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s9, 0(tp)
	l_36:
	bgtz s9, l_37
	l_38:
	mv tp, zero
	add tp, tp, s6
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	mv a2, s4
	l_39:
	bne a2, s11, l_40
	l_41:
	l_42:
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s11, 0(sp)
	addi sp, sp, 4
	lw s1, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_40:
	mv tp, zero
	add tp, tp, s6
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw a3, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s1
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	addi t0, t0, 1
	sw t0, 0(tp)
	mv a0, a2
	addi a0, a0, 1
	mv a1, s1
	mv tp, zero
	add tp, tp, a1
	lw t0, 0(tp)
	rem a0, a0, t0
	mv a2, a0
	j l_39
	l_37:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s9, s9, -1
	j l_36

.globl	main
.p2align	1
.type	main,@function

main:
	l_43:
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

