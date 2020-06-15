	.type	countA,@object
	.section	.bss
	.global	countA
	.p2align	2
countA:
	.LcountA$local:
	.word	0
	.size	countA, 4

	.type	countB,@object
	.section	.bss
	.global	countB
	.p2align	2
countB:
	.LcountB$local:
	.word	0
	.size	countB, 4

	.type	countC,@object
	.section	.bss
	.global	countC
	.p2align	2
countC:
	.LcountC$local:
	.word	0
	.size	countC, 4

	.type	something,@object
	.section	.bss
	.global	something
	.p2align	2
something:
	.Lsomething$local:
	.word	0
	.size	something, 4

	.type	g_0,@object
	.section	.rodata
	g_0:
	.asciz	" "
	.size	g_0, 2

	.type	g_1,@object
	.section	.rodata
	g_1:
	.asciz	" "
	.size	g_1, 2

	.type	g_2,@object
	.section	.rodata
	g_2:
	.asciz	" "
	.size	g_2, 2

	.type	g_3,@object
	.section	.rodata
	g_3:
	.asciz	" "
	.size	g_3, 2

	.type	g_4,@object
	.section	.rodata
	g_4:
	.asciz	"\n"
	.size	g_4, 2

	.type	g_5,@object
	.section	.rodata
	g_5:
	.asciz	">"
	.size	g_5, 2

	.type	g_6,@object
	.section	.rodata
	g_6:
	.asciz	"<="
	.size	g_6, 3

	.type	g_7,@object
	.section	.rodata
	g_7:
	.asciz	","
	.size	g_7, 2

	.type	g_8,@object
	.section	.rodata
	g_8:
	.asciz	""
	.size	g_8, 1

	.type	g_9,@object
	.section	.rodata
	g_9:
	.asciz	"Oops!"
	.size	g_9, 6

.text

.globl	_count__myfunc
.p2align	1
.type	_count__myfunc,@function

_count__myfunc:
	l_0:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s2, 0(sp)
	li a0, 0
	la tp, countA
	sw a0, 0(tp)
	li a0, 0
	la tp, countB
	sw a0, 0(tp)
	li a0, 0
	la tp, countC
	sw a0, 0(tp)
	li a0, 8
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	la tp, countA
	lw a0, 0(tp)
	call toString 
	la t2, g_0
	mv a1, t2
	call string_add 
	mv s2, a0
	la tp, countB
	lw a0, 0(tp)
	call toString 
	mv a1, a0
	mv a0, s2
	call string_add 
	la t2, g_1
	mv a1, t2
	call string_add 
	mv s2, a0
	la tp, countC
	lw a0, 0(tp)
	call toString 
	mv a1, a0
	mv a0, s2
	call string_add 
	call puts 
	li a0, 1
	la tp, countA
	sw a0, 0(tp)
	li a0, 1
	la tp, countB
	sw a0, 0(tp)
	li a0, 1
	la tp, countC
	sw a0, 0(tp)
	li a0, 8
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	la tp, countA
	lw a0, 0(tp)
	li t1, 1
	sub a0, a0, t1
	call toString 
	la t2, g_2
	mv a1, t2
	call string_add 
	mv s2, a0
	la tp, countB
	lw a0, 0(tp)
	li t1, 1
	sub a0, a0, t1
	call toString 
	mv a1, a0
	mv a0, s2
	call string_add 
	la t2, g_3
	mv a1, t2
	call string_add 
	mv s2, a0
	la tp, countC
	lw a0, 0(tp)
	li t1, 1
	sub a0, a0, t1
	call toString 
	mv a1, a0
	mv a0, s2
	call string_add 
	call print 
	la t2, g_4
	mv a0, t2
	call print 
	la tp, something
	lw a0, 0(tp)
	call _C.Me__myfunc 
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw s2, 0(tp)
	la tp, something
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	call string_length 
	li t1, 1
	sub a0, a0, t1
	mv a2, a0
	li a1, 1
	mv a0, s2
	call string_substring 
	call string_parseInt 
	call toString 
	call puts 
	la tp, something
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	li a1, 42
	li t1, 21
	and a1, a1, t1
	call string_ord 
	call toString 
	mv s2, a0
	la tp, something
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a1, 0(tp)
	mv a0, s2
	call string_lt 
	bltz a0, l_1
	l_2:
	la tp, something
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	la t2, g_6
	mv a1, t2
	call string_add 
	mv a1, s2
	call string_add 
	call puts 
	j l_3
	l_1:
	la tp, something
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	la t2, g_5
	mv a1, t2
	call string_add 
	mv a1, s2
	call string_add 
	call puts 
	l_3:
	l_4:
	lw s2, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_main__myfunc
.p2align	1
.type	_main__myfunc,@function

_main__myfunc:
	l_5:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	li a0, 0
	l_6:
	li a1, 891
	li t1, 759
	and a1, a1, t1
	xor a0, a0, a1
	li t1, 666
	beq a0, t1, l_7
	l_8:
	li a0, 0
	j l_9
	l_7:
	li a0, 1
	l_9:
	li a1, 0
	not a1, a1
	beqz a0, l_10
	l_11:
	l_12:
	mv a1, a0
	addi a0, a0, 1
	j l_6
	l_10:
	call toString 
	call puts 
	li a0, 0
	call toString 
	call puts 
	li a0, 1
	call toString 
	call puts 
	call _count__myfunc 
	l_13:
	l_14:
	l_15:
	li t1, 2
	rem a0, a0, t1
	beqz a0, l_16
	l_17:
	call toString 
	la t2, g_7
	mv a1, t2
	call string_add 
	call print 
	j l_14
	l_16:
	j l_14

.globl	_A.A__myfunc
.p2align	1
.type	_A.A__myfunc,@function

_A.A__myfunc:
	l_18:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	li t1, 4
	sub sp, sp, t1
	addi sp, sp, -4
	sw s1, 0(sp)
	addi sp, sp, -4
	sw s11, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s5, 0(sp)
	addi sp, sp, -4
	sw s2, 0(sp)
	addi sp, sp, -4
	sw s8, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s11, a0
	la tp, countA
	lw a0, 0(tp)
	mv a1, a0
	la tp, countA
	lw a0, 0(tp)
	addi a0, a0, 1
	la tp, countA
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 12
	sw a1, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 12
	lw a0, 0(tp)
	li t1, 2
	rem a0, a0, t1
	beqz a0, l_19
	l_20:
	mv tp, zero
	add tp, tp, s11
	li t1, 0
	sw t1, 0(tp)
	j l_21
	l_19:
	li a0, 16
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	mv tp, zero
	add tp, tp, s11
	sw a0, 0(tp)
	la tp, countB
	lw a0, 0(tp)
	li t1, 2
	rem a0, a0, t1
	beqz a0, l_22
	l_23:
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	j l_24
	l_22:
	li a0, 8
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 4
	sw a0, 0(tp)
	l_24:
	l_21:
	li s10, 2
	sub s2, s2, s2
	addi s2, s2, 4
	mul s2, s2, s10
	addi s2, s2, 4
	mv a0, s2
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s10, 0(tp)
	l_25:
	bgtz s10, l_26
	l_27:
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	lw s10, 0(tp)
	li s9, 6
	sub s5, s5, s5
	addi s5, s5, 4
	mul s5, s5, s9
	addi s5, s5, 4
	mv a0, s5
	call malloc 
	mv s3, a0
	mv tp, zero
	add tp, tp, s3
	sw s9, 0(tp)
	l_28:
	bgtz s9, l_29
	l_30:
	mv tp, zero
	add tp, tp, s3
	addi tp, tp, 12
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 16
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 16
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s10
	addi tp, tp, 4
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 8
	li t1, 0
	sw t1, 0(tp)
	mv tp, zero
	add tp, tp, s11
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	lw t0, 0(tp)
	li t1, 2
	beq t0, t1, l_31
	l_32:
	la t2, g_9
	mv a0, t2
	call puts 
	l_31:
	l_33:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s8, 0(sp)
	addi sp, sp, 4
	lw s2, 0(sp)
	addi sp, sp, 4
	lw s5, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s11, 0(sp)
	addi sp, sp, 4
	lw s1, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_29:
	li a0, 6
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	sw a0, 0(tp)
	sub s4, s4, s4
	addi s4, s4, 4
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	mul s4, s4, a0
	addi s4, s4, 4
	mv a0, s4
	call malloc 
	mv s8, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s8
	sw a0, 0(tp)
	l_34:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	bgtz a0, l_35
	l_36:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	sw s8, 0(tp)
	addi s9, s9, -1
	j l_28
	l_35:
	li s5, 6
	sub s1, s1, s1
	addi s1, s1, 4
	mul s1, s1, s5
	addi s1, s1, 4
	mv a0, s1
	call malloc 
	mv s2, a0
	mv tp, zero
	add tp, tp, s2
	sw s5, 0(tp)
	l_37:
	bgtz s5, l_38
	l_39:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s8
	sw s2, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	addi a0, a0, -1
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	sw a0, 0(tp)
	j l_34
	l_38:
	li s6, 6
	sub gp, gp, gp
	addi gp, gp, 4
	mul gp, gp, s6
	addi gp, gp, 4
	mv a0, gp
	call malloc 
	mv s7, a0
	mv tp, zero
	add tp, tp, s7
	sw s6, 0(tp)
	l_40:
	bgtz s6, l_41
	l_42:
	mv tp, zero
	add tp, tp, s5
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s2
	sw s7, 0(tp)
	addi s5, s5, -1
	j l_37
	l_41:
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
	add tp, tp, s6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s7
	sw a0, 0(tp)
	addi s6, s6, -1
	j l_40
	l_26:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s10, s10, -1
	j l_25

.globl	_A.getc0__myfunc
.p2align	1
.type	_A.getc0__myfunc,@function

_A.getc0__myfunc:
	l_43:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	l_44:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_B.B__myfunc
.p2align	1
.type	_B.B__myfunc,@function

_B.B__myfunc:
	l_45:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s2, 0(sp)
	mv s2, a0
	la tp, countB
	lw a0, 0(tp)
	mv a1, a0
	la tp, countB
	lw a0, 0(tp)
	addi a0, a0, 1
	la tp, countB
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s2
	sw a1, 0(tp)
	li a0, 16
	call malloc 
	addi a0, a0, 4
	mv tp, zero
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	li t1, 4
	sub a0, a0, t1
	call _A.getc0__myfunc 
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	call _C.Me__myfunc 
	call _C.Me__myfunc 
	mv tp, zero
	add tp, tp, s2
	addi tp, tp, 4
	sw a0, 0(tp)
	l_46:
	lw s2, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_C.C__myfunc
.p2align	1
.type	_C.C__myfunc,@function

_C.C__myfunc:
	l_47:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s2, 0(sp)
	mv s2, a0
	mv a1, s2
	la tp, countC
	lw a0, 0(tp)
	la tp, countC
	lw a2, 0(tp)
	addi a2, a2, 1
	la tp, countC
	sw a2, 0(tp)
	mv tp, zero
	add tp, tp, a1
	sw a0, 0(tp)
	mv tp, zero
	add tp, tp, s2
	lw a0, 0(tp)
	call toString 
	mv tp, zero
	add tp, tp, s2
	addi tp, tp, 4
	sw a0, 0(tp)
	mv a0, s2
	call _C.Me__myfunc 
	la tp, something
	sw a0, 0(tp)
	l_48:
	lw s2, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_C.Me__myfunc
.p2align	1
.type	_C.Me__myfunc,@function

_C.Me__myfunc:
	l_49:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	l_50:
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
	l_51:
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

