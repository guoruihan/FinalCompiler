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
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	li a0, 12
	call malloc 
	mv s10, a0
	mv a0, s10
	call _Queue_int.Queue_int__myfunc 
	li s4, 100
	li gp, 0
	l_1:
	blt gp, s4, l_2
	l_3:
	mv a0, s10
	call _Queue_int.size__myfunc 
	bne a0, s4, l_4
	l_5:
	li gp, 0
	l_6:
	blt gp, s4, l_7
	l_8:
	la t2, g_4
	mv a0, t2
	call puts 
	li a0, 0
	j l_9
	l_7:
	mv a0, s10
	call _Queue_int.top__myfunc 
	bne a0, gp, l_10
	l_11:
	mv a0, s10
	call _Queue_int.pop__myfunc 
	bne a0, gp, l_12
	l_13:
	mv a0, s10
	call _Queue_int.size__myfunc 
	mv a1, a0
	mv a0, s4
	sub a0, a0, gp
	li t1, 1
	sub a0, a0, t1
	bne a1, a0, l_14
	l_15:
	l_16:
	addi gp, gp, 1
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
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_2:
	mv a0, s10
	mv a1, gp
	call _Queue_int.push__myfunc 
	l_17:
	addi gp, gp, 1
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
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s10, a0
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	li s4, 16
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s4
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s4, 0(tp)
	l_19:
	bgtz s4, l_20
	l_21:
	mv tp, zero
	add tp, tp, s10
	sw a0, 0(tp)
	l_22:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_20:
	mv tp, zero
	add tp, tp, s4
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s4, s4, -1
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
	sw s10, 0(sp)
	mv s10, a0
	mv s4, a1
	mv a0, s10
	call _Queue_int.size__myfunc 
	mv gp, a0
	mv tp, zero
	add tp, tp, s10
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	lw a0, 0(tp)
	call __lib_array_size 
	li t1, 1
	sub a0, a0, t1
	bne gp, a0, l_24
	l_25:
	mv a0, s10
	call _Queue_int.doubleStorage__myfunc 
	l_24:
	mv tp, zero
	add tp, tp, s10
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	sw s4, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw s4, 0(tp)
	addi s4, s4, 1
	mv tp, zero
	add tp, tp, s10
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, s4
	rem a1, a1, a0
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	sw a1, 0(tp)
	l_26:
	lw s10, 0(sp)
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
	addi sp, sp, -4
	sw s10, 0(sp)
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
	mv gp, a0
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 4
	lw s10, 0(tp)
	addi s10, s10, 1
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, a0
	mv a0, s10
	rem a0, a0, a1
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 4
	sw a0, 0(tp)
	mv a0, gp
	l_32:
	lw s10, 0(sp)
	addi sp, sp, 4
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
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s4, a0
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, a0
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 8
	lw a0, 0(tp)
	add a0, a0, a1
	mv s10, a0
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 4
	lw t0, 0(tp)
	sub s10, s10, t0
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, a0
	mv a0, s10
	rem a0, a0, a1
	l_34:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
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
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s5, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s7, a0
	mv tp, zero
	add tp, tp, s7
	lw s6, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 4
	lw gp, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	lw s5, 0(tp)
	mv a0, s6
	mv a0, s6
	call __lib_array_size 
	li t1, 2
	mul a0, a0, t1
	mv s4, a0
	sub s10, s10, s10
	addi s10, s10, 4
	mul s10, s10, s4
	addi s10, s10, 4
	mv a0, s10
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s4, 0(tp)
	l_36:
	bgtz s4, l_37
	l_38:
	mv tp, zero
	add tp, tp, s7
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	mv a0, gp
	l_39:
	bne a0, s5, l_40
	l_41:
	l_42:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s5, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_40:
	mv tp, zero
	add tp, tp, s7
	lw a3, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s6
	addi tp, tp, 4
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a3
	addi tp, tp, 4
	sw a2, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	addi t0, t0, 1
	sw t0, 0(tp)
	mv s10, a0
	addi s10, s10, 1
	mv a0, s6
	mv a0, s6
	call __lib_array_size 
	mv a1, a0
	mv a0, s10
	rem a0, a0, a1
	j l_39
	l_37:
	mv tp, zero
	add tp, tp, s4
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s4, s4, -1
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

