	.type	g_0,@object
	.section	.rodata
	g_0:
	.asciz	"Head != i"
	.size	g_0, 10

	.type	g_1,@object
	.section	.rodata
	g_1:
	.asciz	"Failed: q.pop() != i"
	.size	g_1, 21

	.type	g_2,@object
	.section	.rodata
	g_2:
	.asciz	"q.size() != N - i - 1"
	.size	g_2, 22

	.type	g_3,@object
	.section	.rodata
	g_3:
	.asciz	"Passed tests."
	.size	g_3, 14

	.type	g_4,@object
	.section	.rodata
	g_4:
	.asciz	"Warning: Queue_int::pop: empty queue"
	.size	g_4, 37

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
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	li a0, 12
	call malloc 
	mv s3, a0
	mv a0, s3
	call _Queue_int.Queue_int__myfunc 
	li s4, 100
	li s6, 0
	l_1:
	blt s6, s4, l_2
	l_3:
	li s6, 0
	l_4:
	blt s6, s4, l_5
	l_6:
	la t2, g_3
	mv a0, t2
	call puts 
	li a0, 0
	j l_7
	l_5:
	mv a0, s3
	call _Queue_int.top__myfunc 
	bne a0, s6, l_8
	l_9:
	mv a0, s3
	call _Queue_int.pop__myfunc 
	bne a0, s6, l_10
	l_11:
	mv a0, s3
	call _Queue_int.size__myfunc 
	mv a1, a0
	mv a0, s4
	sub a0, a0, s6
	li t1, 1
	sub a0, a0, t1
	bne a1, a0, l_12
	l_13:
	l_14:
	addi s6, s6, 1
	j l_4
	l_12:
	la t2, g_2
	mv a0, t2
	call puts 
	li a0, 1
	j l_7
	l_10:
	la t2, g_1
	mv a0, t2
	call puts 
	li a0, 1
	j l_7
	l_8:
	la t2, g_0
	mv a0, t2
	call puts 
	li a0, 1
	l_7:
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
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
	mv a0, s3
	mv a1, s6
	call _Queue_int.push__myfunc 
	l_15:
	addi s6, s6, 1
	j l_1

.globl	_Queue_int.Queue_int__myfunc
.p2align	1
.type	_Queue_int.Queue_int__myfunc,@function

_Queue_int.Queue_int__myfunc:
	l_16:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	mv s3, a0
	mv tp, zero
	add tp, tp, s3
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s3
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	li s6, 16
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s6
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s6, 0(tp)
	l_17:
	bgtz s6, l_18
	l_19:
	mv tp, zero
	add tp, tp, s3
	sw a0, 0(tp)
	l_20:
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_18:
	mv tp, zero
	add tp, tp, s6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s6, s6, -1
	j l_17

.globl	_Queue_int.push__myfunc
.p2align	1
.type	_Queue_int.push__myfunc,@function

_Queue_int.push__myfunc:
	l_21:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	mv s4, a0
	mv s3, a1
	mv a0, s4
	call _Queue_int.size__myfunc 
	mv s6, a0
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	call __lib_array_size 
	li t1, 1
	sub a0, a0, t1
	bne s6, a0, l_22
	l_23:
	mv a0, s4
	call _Queue_int.doubleStorage__myfunc 
	l_22:
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 8
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw s3, 0(tp)
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 8
	lw s3, 0(tp)
	addi s3, s3, 1
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s4
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, a0
	mv a0, s3
	rem a0, a0, a1
	mv tp, zero
	add tp, tp, s4
	addi tp, tp, 8
	sw a0, 0(tp)
	l_24:
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
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
	l_25:
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
	l_26:
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
	l_27:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	mv s6, a0
	mv a0, s6
	call _Queue_int.size__myfunc 
	bnez a0, l_28
	l_29:
	la t2, g_4
	mv a0, t2
	call puts 
	l_28:
	mv a0, s6
	call _Queue_int.top__myfunc 
	mv s4, a0
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 4
	lw s3, 0(tp)
	addi s3, s3, 1
	mv tp, zero
	add tp, tp, s6
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, a0
	mv a0, s3
	rem a0, a0, a1
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 4
	sw a0, 0(tp)
	mv a0, s4
	l_30:
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
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
	l_31:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	mv s6, a0
	mv tp, zero
	add tp, tp, s6
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, a0
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw a0, 0(tp)
	add a0, a0, a1
	mv s3, a0
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 4
	lw t0, 0(tp)
	sub s3, s3, t0
	mv tp, zero
	add tp, tp, s6
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	lw a0, 0(tp)
	call __lib_array_size 
	mv a1, s3
	rem a1, a1, a0
	mv a0, a1
	l_32:
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
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
	l_33:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s8, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s5, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	mv s5, a0
	mv tp, zero
	add tp, tp, s5
	lw s3, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 4
	lw s6, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	lw s4, 0(tp)
	mv a0, s3
	mv a0, s3
	call __lib_array_size 
	li t1, 2
	mul a0, a0, t1
	mv s8, a0
	sub s10, s10, s10
	addi s10, s10, 4
	mul s10, s10, s8
	addi s10, s10, 4
	mv a0, s10
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s8, 0(tp)
	l_34:
	bgtz s8, l_35
	l_36:
	mv tp, zero
	add tp, tp, s5
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	mv a1, s6
	l_37:
	bne a1, s4, l_38
	l_39:
	l_40:
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s5, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s8, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_38:
	mv tp, zero
	add tp, tp, s5
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	lw a3, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	addi tp, tp, 4
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw a2, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s5
	addi tp, tp, 8
	addi t0, t0, 1
	sw t0, 0(tp)
	mv s6, a1
	addi s6, s6, 1
	mv a0, s3
	mv a0, s3
	call __lib_array_size 
	mv a1, a0
	mv a0, s6
	rem a0, a0, a1
	mv a1, a0
	j l_37
	l_35:
	mv tp, zero
	add tp, tp, s8
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s8, s8, -1
	j l_34

.globl	main
.p2align	1
.type	main,@function

main:
	l_41:
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

