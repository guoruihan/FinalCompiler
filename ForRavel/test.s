	.type	n,@object
	.section	.bss
	.global	n
	.p2align	2
n:
	.Ln$local:
	.word	0
	.size	n, 4

	.type	m,@object
	.section	.bss
	.global	m
	.p2align	2
m:
	.Lm$local:
	.word	0
	.size	m, 4

	.type	g,@object
	.section	.bss
	.global	g
	.p2align	2
g:
	.Lg$local:
	.word	0
	.size	g, 4

	.type	INF,@object
	.section	.bss
	.global	INF
	.p2align	2
INF:
	.LINF$local:
	.word	0
	.size	INF, 4

	.type	g_0,@object
	.section	.rodata
	g_0:
	.asciz	"-1"
	.size	g_0, 3

	.type	g_1,@object
	.section	.rodata
	g_1:
	.asciz	" "
	.size	g_1, 2

	.type	g_2,@object
	.section	.rodata
	g_2:
	.asciz	""
	.size	g_2, 1

	.type	g_3,@object
	.section	.rodata
	g_3:
	.asciz	"Warning: Queue_int::pop: empty queue"
	.size	g_3, 37

.text

.globl	_init__myfunc
.p2align	1
.type	_init__myfunc,@function

_init__myfunc:
	l_0:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	call getInt 
	la tp, n
	sw a0, 0(tp)
	call getInt 
	la tp, m
	sw a0, 0(tp)
	li a0, 16
	call malloc 
	mv s7, a0
	mv a0, s7
	call _EdgeList.EdgeList__myfunc 
	mv a0, s7
	la tp, g
	sw a0, 0(tp)
	la tp, g
	lw a0, 0(tp)
	mv a3, a0
	la tp, m
	lw a0, 0(tp)
	mv a2, a0
	la tp, n
	lw a0, 0(tp)
	mv a1, a0
	mv a0, a3
	call _EdgeList.init__myfunc 
	li s9, 0
	l_1:
	la tp, m
	lw a0, 0(tp)
	blt s9, a0, l_2
	l_3:
	l_4:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_2:
	call getInt 
	mv s7, a0
	call getInt 
	mv s10, a0
	call getInt 
	la tp, g
	lw a1, 0(tp)
	mv a4, a1
	mv a3, a0
	mv a2, s10
	mv a1, s7
	mv a0, a4
	call _EdgeList.addEdge__myfunc 
	l_5:
	addi s9, s9, 1
	j l_1

.globl	_spfa__myfunc
.p2align	1
.type	_spfa__myfunc,@function

_spfa__myfunc:
	l_6:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s10, a0
	la tp, n
	lw a0, 0(tp)
	mv s7, a0
	sub a1, a1, a1
	addi a1, a1, 4
	mul a1, a1, s7
	addi a1, a1, 4
	mv a0, a1
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s7, 0(tp)
	l_7:
	bgtz s7, l_8
	l_9:
	mv s7, a0
	li a1, 0
	l_10:
	la tp, n
	lw a0, 0(tp)
	blt a1, a0, l_11
	l_12:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	li a0, 12
	call malloc 
	mv s9, a0
	mv a0, s9
	call _Queue_int.Queue_int__myfunc 
	mv a0, s9
	mv a1, s10
	call _Queue_int.push__myfunc 
	l_13:
	mv a0, s9
	call _Queue_int.size__myfunc 
	bnez a0, l_14
	l_15:
	mv a0, s7
	l_16:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_14:
	mv a0, s9
	call _Queue_int.pop__myfunc 
	mv s10, a0
	la tp, g
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw s6, 0(tp)
	l_17:
	li a0, 1
	neg a0, a0
	bne s6, a0, l_18
	l_19:
	j l_13
	l_18:
	la tp, g
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a1, 0(tp)
	mv a0, a1
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a3, 0(tp)
	mv a2, a1
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a2
	addi tp, tp, 8
	lw t0, 0(tp)
	add a0, a0, t0
	mv tp, zero
	add tp, tp, a3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	lw t0, 0(tp)
	ble t0, a0, l_20
	l_21:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 8
	lw t0, 0(tp)
	add a0, a0, t0
	mv tp, zero
	add tp, tp, a3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	sw a0, 0(tp)
	mv a0, s9
	mv a1, a3
	call _Queue_int.push__myfunc 
	l_20:
	l_22:
	la tp, g
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw s6, 0(tp)
	j l_17
	l_11:
	la tp, INF
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	sw a0, 0(tp)
	l_23:
	addi a1, a1, 1
	j l_10
	l_8:
	mv tp, zero
	add tp, tp, s7
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s7, s7, -1
	j l_7

.globl	_main__myfunc
.p2align	1
.type	_main__myfunc,@function

_main__myfunc:
	l_24:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	call _init__myfunc 
	li s10, 0
	l_25:
	la tp, n
	lw a0, 0(tp)
	blt s10, a0, l_26
	l_27:
	li a0, 0
	l_28:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_26:
	mv a0, s10
	call _spfa__myfunc 
	mv s9, a0
	li s7, 0
	l_29:
	la tp, n
	lw a0, 0(tp)
	blt s7, a0, l_30
	l_31:
	la t2, g_2
	mv a0, t2
	call puts 
	l_32:
	addi s10, s10, 1
	j l_25
	l_30:
	la tp, INF
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s7
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s9
	addi tp, tp, 4
	lw t0, 0(tp)
	beq t0, a0, l_33
	l_34:
	mv tp, zero
	add tp, tp, s7
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s9
	addi tp, tp, 4
	lw a0, 0(tp)
	call toString 
	call print 
	j l_35
	l_33:
	la t2, g_0
	mv a0, t2
	call print 
	l_35:
	la t2, g_1
	mv a0, t2
	call print 
	l_36:
	addi s7, s7, 1
	j l_29

.globl	_Queue_int.Queue_int__myfunc
.p2align	1
.type	_Queue_int.Queue_int__myfunc,@function

_Queue_int.Queue_int__myfunc:
	l_37:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s7, a0
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
	li s10, 16
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s10
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s10, 0(tp)
	l_38:
	bgtz s10, l_39
	l_40:
	mv tp, zero
	add tp, tp, s7
	sw a0, 0(tp)
	l_41:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_39:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s10, s10, -1
	j l_38

.globl	_Queue_int.push__myfunc
.p2align	1
.type	_Queue_int.push__myfunc,@function

_Queue_int.push__myfunc:
	l_42:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s9, a0
	mv s10, a1
	mv a0, s9
	call _Queue_int.size__myfunc 
	mv s7, a0
	mv tp, zero
	add tp, tp, s9
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	mv a1, a0
	li t1, 1
	sub a1, a1, t1
	bne s7, a1, l_43
	l_44:
	mv a0, s9
	call _Queue_int.doubleStorage__myfunc 
	l_43:
	mv tp, zero
	add tp, tp, s9
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, s9
	addi tp, tp, 8
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a2
	addi tp, tp, 4
	sw s10, 0(tp)
	mv tp, zero
	add tp, tp, s9
	addi tp, tp, 8
	lw s7, 0(tp)
	addi s7, s7, 1
	mv tp, zero
	add tp, tp, s9
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	mv a1, a0
	mv a0, s7
	rem a0, a0, a1
	mv tp, zero
	add tp, tp, s9
	addi tp, tp, 8
	sw a0, 0(tp)
	l_45:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.top__myfunc
.p2align	1
.type	_Queue_int.top__myfunc,@function

_Queue_int.top__myfunc:
	l_46:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a1, a0
	mv tp, zero
	add tp, tp, a1
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	l_47:
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
	l_48:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s10, a0
	mv a0, s10
	call _Queue_int.size__myfunc 
	bnez a0, l_49
	l_50:
	la t2, g_3
	mv a0, t2
	call puts 
	l_49:
	mv a0, s10
	call _Queue_int.top__myfunc 
	mv a1, a0
	mv s7, a1
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	lw s9, 0(tp)
	addi s9, s9, 1
	mv tp, zero
	add tp, tp, s10
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	mv a1, a0
	mv a0, s9
	rem a0, a0, a1
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	sw a0, 0(tp)
	mv a0, s7
	l_51:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.size__myfunc
.p2align	1
.type	_Queue_int.size__myfunc,@function

_Queue_int.size__myfunc:
	l_52:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s7, a0
	mv tp, zero
	add tp, tp, s7
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	mv a2, a0
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 8
	lw a1, 0(tp)
	add a1, a1, a2
	mv s10, a1
	mv tp, zero
	add tp, tp, s7
	addi tp, tp, 4
	lw t0, 0(tp)
	sub s10, s10, t0
	mv tp, zero
	add tp, tp, s7
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	mv a1, a0
	mv a0, s10
	rem a0, a0, a1
	l_53:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_Queue_int.doubleStorage__myfunc
.p2align	1
.type	_Queue_int.doubleStorage__myfunc,@function

_Queue_int.doubleStorage__myfunc:
	l_54:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s10, a0
	mv tp, zero
	add tp, tp, s10
	lw s7, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	lw gp, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw s9, 0(tp)
	mv a2, s7
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	li t1, 2
	mul a0, a0, t1
	mv s6, a0
	sub s3, s3, s3
	addi s3, s3, 4
	mul s3, s3, s6
	addi s3, s3, 4
	mv a0, s3
	call malloc 
	mv a1, a0
	mv tp, zero
	add tp, tp, a1
	sw s6, 0(tp)
	l_55:
	bgtz s6, l_56
	l_57:
	mv tp, zero
	add tp, tp, s10
	sw a1, 0(tp)
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
	mv a3, gp
	l_58:
	bne a3, s9, l_59
	l_60:
	l_61:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_59:
	mv tp, zero
	add tp, tp, s10
	lw a4, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	addi tp, tp, 4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a4
	addi tp, tp, 4
	sw a1, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	addi t0, t0, 1
	sw t0, 0(tp)
	mv s6, a3
	addi s6, s6, 1
	mv a2, s7
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	mv a2, a0
	mv a1, s6
	rem a1, a1, a2
	mv a3, a1
	j l_58
	l_56:
	mv tp, zero
	add tp, tp, s6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	li t1, 0
	sw t1, 0(tp)
	addi s6, s6, -1
	j l_55

.globl	_Edge.Edge__myfunc
.p2align	1
.type	_Edge.Edge__myfunc,@function

_Edge.Edge__myfunc:
	l_62:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	l_63:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_EdgeList.EdgeList__myfunc
.p2align	1
.type	_EdgeList.EdgeList__myfunc,@function

_EdgeList.EdgeList__myfunc:
	l_64:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	l_65:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_EdgeList.init__myfunc
.p2align	1
.type	_EdgeList.init__myfunc,@function

_EdgeList.init__myfunc:
	l_66:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s11, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s10, a0
	mv s7, a1
	mv gp, a2
	mv s3, gp
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s3
	addi a0, a0, 4
	call malloc 
	mv s9, a0
	mv tp, zero
	add tp, tp, s9
	sw s3, 0(tp)
	l_67:
	bgtz s3, l_68
	l_69:
	mv tp, zero
	add tp, tp, s10
	sw s9, 0(tp)
	mv s9, gp
	sub s6, s6, s6
	addi s6, s6, 4
	mul s6, s6, s9
	addi s6, s6, 4
	mv a0, s6
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s9, 0(tp)
	l_70:
	bgtz s9, l_71
	l_72:
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	sw a0, 0(tp)
	mv s9, s7
	sub s11, s11, s11
	addi s11, s11, 4
	mul s11, s11, s9
	addi s11, s11, 4
	mv a0, s11
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s9, 0(tp)
	l_73:
	bgtz s9, l_74
	l_75:
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	sw a0, 0(tp)
	li a2, 0
	l_76:
	blt a2, gp, l_77
	l_78:
	li a2, 0
	l_79:
	blt a2, s7, l_80
	l_81:
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 12
	li t1, 0
	sw t1, 0(tp)
	l_82:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s11, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_80:
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 8
	lw a1, 0(tp)
	li a0, 1
	neg a0, a0
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	sw a0, 0(tp)
	l_83:
	addi a2, a2, 1
	j l_79
	l_77:
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	lw a0, 0(tp)
	li a1, 1
	neg a1, a1
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw a1, 0(tp)
	l_84:
	addi a2, a2, 1
	j l_76
	l_74:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s9, s9, -1
	j l_73
	l_71:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s9, s9, -1
	j l_70
	l_68:
	li a0, 4
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s9
	sw a0, 0(tp)
	addi s3, s3, -1
	j l_67

.globl	_EdgeList.addEdge__myfunc
.p2align	1
.type	_EdgeList.addEdge__myfunc,@function

_EdgeList.addEdge__myfunc:
	l_85:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv s6, a0
	mv s10, a1
	mv s9, a2
	mv s7, a3
	li a0, 12
	call malloc 
	mv gp, a0
	mv a0, gp
	call _Edge.Edge__myfunc 
	mv a2, gp
	mv a0, a2
	mv tp, zero
	add tp, tp, a0
	sw s10, 0(tp)
	mv a0, a2
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	sw s9, 0(tp)
	mv a0, a2
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 8
	sw s7, 0(tp)
	mv tp, zero
	add tp, tp, s6
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 12
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	sw a2, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 4
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 12
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a2
	addi tp, tp, 4
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 12
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw a1, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 12
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 12
	lw t0, 0(tp)
	mv tp, zero
	add tp, tp, s6
	addi tp, tp, 12
	addi t0, t0, 1
	sw t0, 0(tp)
	l_86:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_EdgeList.nVertices__myfunc
.p2align	1
.type	_EdgeList.nVertices__myfunc,@function

_EdgeList.nVertices__myfunc:
	l_87:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a1, a0
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 8
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	l_88:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_EdgeList.nEdges__myfunc
.p2align	1
.type	_EdgeList.nEdges__myfunc,@function

_EdgeList.nEdges__myfunc:
	l_89:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a1, a0
	mv tp, zero
	add tp, tp, a1
	lw a2, 0(tp)
	mv a1, a0
	mv a0, a2
	call __lib_array_size 
	l_90:
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
	l_91:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	li a0, 10000000
	la tp, INF
	sw a0, 0(tp)
	call _main__myfunc 
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

