	.type	asciiTable,@object
	.section	.bss
	.global	asciiTable
	.p2align	2
asciiTable:
	.LasciiTable$local:
	.word	0
	.size	asciiTable, 4

	.type	MAXCHUNK,@object
	.section	.bss
	.global	MAXCHUNK
	.p2align	2
MAXCHUNK:
	.LMAXCHUNK$local:
	.word	0
	.size	MAXCHUNK, 4

	.type	MAXLENGTH,@object
	.section	.bss
	.global	MAXLENGTH
	.p2align	2
MAXLENGTH:
	.LMAXLENGTH$local:
	.word	0
	.size	MAXLENGTH, 4

	.type	chunks,@object
	.section	.bss
	.global	chunks
	.p2align	2
chunks:
	.Lchunks$local:
	.word	0
	.size	chunks, 4

	.type	inputBuffer,@object
	.section	.bss
	.global	inputBuffer
	.p2align	2
inputBuffer:
	.LinputBuffer$local:
	.word	0
	.size	inputBuffer, 4

	.type	outputBuffer,@object
	.section	.bss
	.global	outputBuffer
	.p2align	2
outputBuffer:
	.LoutputBuffer$local:
	.word	0
	.size	outputBuffer, 4

	.type	g_0,@object
	.section	.rodata
	g_0:
	.asciz	""
	.size	g_0, 1

	.type	g_1,@object
	.section	.rodata
	g_1:
	.asciz	""
	.size	g_1, 1

	.type	g_2,@object
	.section	.rodata
	g_2:
	.asciz	"nChunk > MAXCHUNK!"
	.size	g_2, 19

	.type	g_3,@object
	.section	.rodata
	g_3:
	.asciz	""
	.size	g_3, 1

	.type	g_4,@object
	.section	.rodata
	g_4:
	.asciz	"Invalid input"
	.size	g_4, 14

	.type	g_5,@object
	.section	.rodata
	g_5:
	.asciz	""
	.size	g_5, 1

	.type	g_6,@object
	.section	.rodata
	g_6:
	.asciz	"Not Found!"
	.size	g_6, 11

	.type	g_7,@object
	.section	.rodata
	g_7:
	.asciz	" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"
	.size	g_7, 96

.text

.globl	_hex2int__myfunc
.p2align	1
.type	_hex2int__myfunc,@function

_hex2int__myfunc:
	l_0:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s9, a0
	li s10, 0
	li s3, 0
	l_1:
	mv a0, s9
	call string_length 
	blt s3, a0, l_2
	l_3:
	mv a0, s10
	j l_4
	l_2:
	mv a0, s9
	mv a1, s3
	call string_ord 
	mv a1, a0
	li t1, 48
	blt a1, t1, l_5
	l_6:
	li t1, 57
	bgt a1, t1, l_5
	j l_7
	l_5:
	li t1, 65
	blt a1, t1, l_8
	l_9:
	li t1, 70
	bgt a1, t1, l_8
	j l_10
	l_8:
	li t1, 97
	blt a1, t1, l_11
	l_12:
	li t1, 102
	bgt a1, t1, l_11
	j l_13
	l_11:
	li a0, 0
	l_4:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_13:
	mv a0, s10
	li t1, 16
	mul a0, a0, t1
	add a0, a0, a1
	li t1, 97
	sub a0, a0, t1
	addi a0, a0, 10
	mv s10, a0
	l_14:
	j l_15
	l_10:
	mv a0, s10
	li t1, 16
	mul a0, a0, t1
	add a0, a0, a1
	li t1, 65
	sub a0, a0, t1
	addi a0, a0, 10
	mv s10, a0
	l_15:
	j l_16
	l_7:
	mv a0, s10
	li t1, 16
	mul a0, a0, t1
	add a0, a0, a1
	li t1, 48
	sub a0, a0, t1
	mv s10, a0
	l_16:
	l_17:
	mv a0, s3
	addi s3, s3, 1
	j l_1

.globl	_int2chr__myfunc
.p2align	1
.type	_int2chr__myfunc,@function

_int2chr__myfunc:
	l_18:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a1, a0
	li t1, 32
	blt a1, t1, l_19
	l_20:
	li t1, 126
	bgt a1, t1, l_19
	j l_21
	l_19:
	la t2, g_0
	mv a0, t2
	j l_22
	l_21:
	la tp, asciiTable
	lw a0, 0(tp)
	mv a3, a1
	li t1, 32
	sub a3, a3, t1
	li t1, 31
	sub a1, a1, t1
	mv a2, a1
	mv a1, a3
	call string_substring 
	l_22:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_toStringHex__myfunc
.p2align	1
.type	_toStringHex__myfunc,@function

_toStringHex__myfunc:
	l_23:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s9, a0
	la t2, g_1
	mv s10, t2
	li s3, 28
	l_24:
	bgez s3, l_25
	l_26:
	mv a0, s10
	l_27:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_25:
	mv a0, s9
	mv t4, s3
	srl a0, a0, t4
	li t1, 15
	and a0, a0, t1
	mv a1, a0
	li t1, 10
	blt a1, t1, l_28
	l_29:
	li a0, 65
	add a0, a0, a1
	li t1, 10
	sub a0, a0, t1
	call _int2chr__myfunc 
	mv a1, a0
	mv a0, s10
	call string_add 
	mv s10, a0
	j l_30
	l_28:
	li a0, 48
	add a0, a0, a1
	call _int2chr__myfunc 
	mv a1, a0
	mv a0, s10
	call string_add 
	mv s10, a0
	l_30:
	l_31:
	li t1, 4
	sub s3, s3, t1
	j l_24

.globl	_rotate_left__myfunc
.p2align	1
.type	_rotate_left__myfunc,@function

_rotate_left__myfunc:
	l_32:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a3, a0
	mv a2, a1
	li t1, 1
	beq a2, t1, l_33
	l_34:
	li t1, 31
	beq a2, t1, l_35
	l_36:
	li a1, 32
	sub a1, a1, a2
	li a0, 1
	mv t4, a1
	sll a0, a0, t4
	mv a1, a0
	li t1, 1
	sub a1, a1, t1
	mv a0, a3
	and a0, a0, a1
	mv t4, a2
	sll a0, a0, t4
	li a1, 32
	sub a1, a1, a2
	mv t4, a1
	srl a3, a3, t4
	li a1, 1
	mv t4, a2
	sll a1, a1, t4
	mv a2, a1
	li t1, 1
	sub a2, a2, t1
	mv a1, a3
	and a1, a1, a2
	or a0, a0, a1
	j l_37
	l_35:
	mv a0, a3
	li t1, 1
	and a0, a0, t1
	mv a2, a0
	li t4, 31
	sll a2, a2, t4
	mv a0, a3
	li t4, 1
	srl a0, a0, t4
	mv a1, a0
	li t1, 2147483647
	and a1, a1, t1
	mv a0, a2
	or a0, a0, a1
	j l_37
	l_33:
	mv a0, a3
	li t1, 2147483647
	and a0, a0, t1
	li t4, 1
	sll a0, a0, t4
	mv a1, a3
	li t4, 31
	srl a1, a1, t4
	li t1, 1
	and a1, a1, t1
	or a0, a0, a1
	l_37:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_add__myfunc
.p2align	1
.type	_add__myfunc,@function

_add__myfunc:
	l_38:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a3, a0
	mv a2, a1
	mv a0, a3
	li t1, 65535
	and a0, a0, t1
	mv a1, a2
	li t1, 65535
	and a1, a1, t1
	add a0, a0, a1
	mv a1, a0
	mv a0, a3
	li t4, 16
	srl a0, a0, t4
	mv a3, a0
	li t1, 65535
	and a3, a3, t1
	mv a0, a2
	li t4, 16
	srl a0, a0, t4
	li t1, 65535
	and a0, a0, t1
	mv a2, a3
	add a2, a2, a0
	mv a0, a1
	li t4, 16
	srl a0, a0, t4
	add a2, a2, a0
	mv a0, a2
	li t1, 65535
	and a0, a0, t1
	li t4, 16
	sll a0, a0, t4
	li t1, 65535
	and a1, a1, t1
	or a0, a0, a1
	l_39:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_lohi__myfunc
.p2align	1
.type	_lohi__myfunc,@function

_lohi__myfunc:
	l_40:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a2, a0
	mv a0, a1
	mv a1, a0
	li t4, 16
	sll a1, a1, t4
	mv a0, a2
	or a0, a0, a1
	l_41:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_sha1__myfunc
.p2align	1
.type	_sha1__myfunc,@function

_sha1__myfunc:
	l_42:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	li t1, 20
	sub sp, sp, t1
	addi sp, sp, -4
	sw s2, 0(sp)
	addi sp, sp, -4
	sw s4, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	addi sp, sp, -4
	sw s11, 0(sp)
	addi sp, sp, -4
	sw s1, 0(sp)
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s8, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	mv a2, a0
	mv a4, a1
	mv a0, a4
	addi a0, a0, 64
	li t1, 56
	sub a0, a0, t1
	li t1, 64
	div a0, a0, t1
	addi a0, a0, 1
	mv s9, a0
	la tp, MAXCHUNK
	lw a0, 0(tp)
	bgt s9, a0, l_43
	l_44:
	li s3, 0
	l_45:
	blt s3, s9, l_46
	l_47:
	li s3, 0
	l_48:
	blt s3, a4, l_49
	l_50:
	mv a2, s3
	li t1, 64
	div a2, a2, t1
	mv a0, s3
	li t1, 64
	rem a0, a0, t1
	mv a1, a0
	li t1, 4
	div a1, a1, t1
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	mv a3, s3
	li t1, 64
	div a3, a3, t1
	mv a2, s3
	li t1, 64
	rem a2, a2, t1
	mv a6, a2
	li t1, 4
	div a6, a6, t1
	la tp, chunks
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a2
	addi tp, tp, 4
	lw a3, 0(tp)
	mv a5, s3
	li t1, 4
	rem a5, a5, t1
	li a2, 3
	sub a2, a2, a5
	li t1, 8
	mul a2, a2, t1
	li a5, 128
	mv t4, a2
	sll a5, a5, t4
	mv tp, zero
	add tp, tp, a6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a3
	addi tp, tp, 4
	lw a2, 0(tp)
	or a2, a2, a5
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw a2, 0(tp)
	mv a1, s9
	li t1, 1
	sub a1, a1, t1
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a1, 0(tp)
	mv a0, a4
	li t4, 3
	sll a0, a0, t4
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 64
	sw a0, 0(tp)
	mv a1, s9
	li t1, 1
	sub a1, a1, t1
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a1, 0(tp)
	mv a0, a4
	li t4, 29
	srl a0, a0, t4
	li t1, 7
	and a0, a0, t1
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 60
	sw a0, 0(tp)
	li s6, 1732584193
	li a1, 61389
	li a0, 43913
	call _lohi__myfunc 
	mv s7, a0
	li a1, 39098
	li a0, 56574
	call _lohi__myfunc 
	mv s8, a0
	li a0, 271733878
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	sw a0, 0(tp)
	li a1, 50130
	li a0, 57840
	call _lohi__myfunc 
	mv s1, a0
	li s3, 0
	l_51:
	blt s3, s9, l_52
	l_53:
	la tp, outputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 4
	sw s6, 0(tp)
	la tp, outputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 8
	sw s7, 0(tp)
	la tp, outputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 12
	sw s8, 0(tp)
	la tp, outputBuffer
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	addi tp, tp, 16
	sw a0, 0(tp)
	la tp, outputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	addi tp, tp, 20
	sw s1, 0(tp)
	la tp, outputBuffer
	lw a0, 0(tp)
	j l_54
	l_52:
	li a0, 16
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	sw a0, 0(tp)
	l_55:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 80
	blt a0, t1, l_56
	l_57:
	mv a0, s6
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -12
	sw a0, 0(tp)
	mv s11, s7
	mv s4, s8
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	sw a0, 0(tp)
	mv s2, s1
	li a0, 0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	sw a0, 0(tp)
	l_58:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 80
	blt a0, t1, l_59
	l_60:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -12
	lw a0, 0(tp)
	mv a1, a0
	mv a0, s6
	call _add__myfunc 
	mv s6, a0
	mv a1, s11
	mv a0, s7
	call _add__myfunc 
	mv s7, a0
	mv a1, s4
	mv a0, s8
	call _add__myfunc 
	mv s8, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a0, 0(tp)
	mv a1, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	lw a0, 0(tp)
	call _add__myfunc 
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -4
	sw a0, 0(tp)
	mv a1, s2
	mv a0, s1
	call _add__myfunc 
	mv s1, a0
	l_61:
	mv a0, s3
	addi s3, s3, 1
	j l_51
	l_59:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 20
	blt a0, t1, l_62
	l_63:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 40
	blt a0, t1, l_64
	l_65:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 60
	blt a0, t1, l_66
	l_67:
	mv a0, s11
	xor a0, a0, s4
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a1, 0(tp)
	xor a0, a0, a1
	mv s10, a0
	li a1, 51810
	li a0, 49622
	call _lohi__myfunc 
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -20
	sw a0, 0(tp)
	j l_68
	l_66:
	mv a0, s11
	and a0, a0, s4
	mv a1, s11
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a2, 0(tp)
	and a1, a1, a2
	or a0, a0, a1
	mv a2, s4
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a1, 0(tp)
	and a2, a2, a1
	or a0, a0, a2
	mv s10, a0
	li a1, 36635
	li a0, 48348
	call _lohi__myfunc 
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -20
	sw a0, 0(tp)
	l_68:
	j l_69
	l_64:
	mv a0, s11
	xor a0, a0, s4
	mv a1, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a0, 0(tp)
	xor a1, a1, a0
	mv s10, a1
	li a0, 1859775393
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -20
	sw a0, 0(tp)
	l_69:
	j l_70
	l_62:
	mv a1, s11
	and a1, a1, s4
	mv a0, s11
	not a0, a0
	mv a2, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a0, 0(tp)
	and a2, a2, a0
	mv a0, a1
	or a0, a0, a2
	mv s10, a0
	li a0, 1518500249
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -20
	sw a0, 0(tp)
	l_70:
	li a1, 5
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -12
	lw a0, 0(tp)
	call _rotate_left__myfunc 
	mv a1, s2
	call _add__myfunc 
	mv s2, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -20
	lw a0, 0(tp)
	mv a1, a0
	mv a0, s10
	call _add__myfunc 
	mv a1, a0
	mv a0, s2
	call _add__myfunc 
	la tp, chunks
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw a1, 0(tp)
	call _add__myfunc 
	mv s10, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	lw a0, 0(tp)
	mv s2, a0
	mv a0, s4
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -16
	sw a0, 0(tp)
	li a1, 30
	mv a0, s11
	call _rotate_left__myfunc 
	mv s4, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -12
	lw a0, 0(tp)
	mv s11, a0
	mv a0, s10
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -12
	sw a0, 0(tp)
	l_71:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	addi a0, a0, 1
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	sw a0, 0(tp)
	j l_58
	l_56:
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw s10, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv a2, a0
	li t1, 3
	sub a2, a2, t1
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a3, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 8
	sub a0, a0, t1
	la tp, chunks
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a2
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a3
	addi tp, tp, 4
	lw a2, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw t0, 0(tp)
	xor a2, a2, t0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv a1, a0
	li t1, 14
	sub a1, a1, t1
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw t0, 0(tp)
	xor a2, a2, t0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 16
	sub a0, a0, t1
	la tp, chunks
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	lw t0, 0(tp)
	xor a2, a2, t0
	li a1, 1
	mv a0, a2
	call _rotate_left__myfunc 
	mv a1, a0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s10
	addi tp, tp, 4
	sw a1, 0(tp)
	l_72:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	addi a0, a0, 1
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	sw a0, 0(tp)
	j l_55
	l_49:
	mv a1, s3
	li t1, 64
	div a1, a1, t1
	mv a0, s3
	li t1, 64
	rem a0, a0, t1
	mv a6, a0
	li t1, 4
	div a6, a6, t1
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a7, 0(tp)
	mv a0, s3
	li t1, 64
	div a0, a0, t1
	mv a1, s3
	li t1, 64
	rem a1, a1, t1
	li t1, 4
	div a1, a1, t1
	la tp, chunks
	lw a3, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a3
	addi tp, tp, 4
	lw a0, 0(tp)
	mv a5, s3
	li t1, 4
	rem a5, a5, t1
	li a3, 3
	sub a3, a3, a5
	li t1, 8
	mul a3, a3, t1
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a2
	addi tp, tp, 4
	lw a5, 0(tp)
	mv t4, a3
	sll a5, a5, t4
	mv tp, zero
	add tp, tp, a1
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	or a0, a0, a5
	mv tp, zero
	add tp, tp, a6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a7
	addi tp, tp, 4
	sw a0, 0(tp)
	l_73:
	mv a0, s3
	addi s3, s3, 1
	j l_48
	l_46:
	li a0, 0
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	sw a0, 0(tp)
	l_74:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	li t1, 80
	blt a0, t1, l_75
	l_76:
	l_77:
	mv a0, s3
	addi s3, s3, 1
	j l_45
	l_75:
	la tp, chunks
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a1, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, a0
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a1
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	l_78:
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	lw a0, 0(tp)
	addi a0, a0, 1
	mv tp, zero
	add tp, tp, s0
	addi tp, tp, -8
	sw a0, 0(tp)
	j l_74
	l_43:
	la t2, g_2
	mv a0, t2
	call puts 
	li a0, 0
	l_54:
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s8, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	lw s1, 0(sp)
	addi sp, sp, 4
	lw s11, 0(sp)
	addi sp, sp, 4
	lw s10, 0(sp)
	addi sp, sp, 4
	lw s4, 0(sp)
	addi sp, sp, 4
	lw s2, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_computeSHA1__myfunc
.p2align	1
.type	_computeSHA1__myfunc,@function

_computeSHA1__myfunc:
	l_79:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s10, a0
	li s3, 0
	l_80:
	mv a0, s10
	call string_length 
	blt s3, a0, l_81
	l_82:
	mv a0, s10
	call string_length 
	mv a1, a0
	la tp, inputBuffer
	lw a0, 0(tp)
	call _sha1__myfunc 
	mv s10, a0
	li s3, 0
	l_83:
	mv a0, s10
	mv a0, s10
	call __lib_array_size 
	blt s3, a0, l_84
	l_85:
	la t2, g_3
	mv a0, t2
	call puts 
	l_86:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_84:
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s10
	addi tp, tp, 4
	lw a0, 0(tp)
	call _toStringHex__myfunc 
	call print 
	l_87:
	mv a0, s3
	addi s3, s3, 1
	j l_83
	l_81:
	mv a0, s10
	mv a1, s3
	call string_ord 
	mv a1, a0
	la tp, inputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	sw a1, 0(tp)
	l_88:
	mv a0, s3
	addi s3, s3, 1
	j l_80

.globl	_nextLetter__myfunc
.p2align	1
.type	_nextLetter__myfunc,@function

_nextLetter__myfunc:
	l_89:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	li t1, 122
	beq a0, t1, l_90
	l_91:
	li t1, 90
	beq a0, t1, l_92
	l_93:
	li t1, 57
	beq a0, t1, l_94
	l_95:
	addi a0, a0, 1
	j l_96
	l_94:
	li a0, 65
	j l_96
	l_92:
	li a0, 97
	j l_96
	l_90:
	li a0, 1
	neg a0, a0
	l_96:
	lw ra, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_nextText__myfunc
.p2align	1
.type	_nextText__myfunc,@function

_nextText__myfunc:
	l_97:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s3, a0
	mv a0, a1
	li t1, 1
	sub a0, a0, t1
	mv s10, a0
	l_98:
	bgez s10, l_99
	l_100:
	l_101:
	li a0, 0
	l_102:
	j l_103
	l_99:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	addi tp, tp, 4
	lw a0, 0(tp)
	call _nextLetter__myfunc 
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	addi tp, tp, 4
	sw a0, 0(tp)
	li a0, 1
	neg a0, a0
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	addi tp, tp, 4
	lw t0, 0(tp)
	beq t0, a0, l_104
	l_105:
	l_106:
	li a0, 1
	l_107:
	l_103:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_104:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	addi tp, tp, 4
	li t1, 48
	sw t1, 0(tp)
	l_108:
	l_109:
	mv a0, s10
	addi s10, s10, -1
	j l_98

.globl	_array_equal__myfunc
.p2align	1
.type	_array_equal__myfunc,@function

_array_equal__myfunc:
	l_110:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s10, a0
	mv s3, a1
	mv a0, s10
	mv a0, s10
	call __lib_array_size 
	mv s9, a0
	mv a0, s3
	mv a0, s3
	call __lib_array_size 
	bne s9, a0, l_111
	l_112:
	li s9, 0
	l_113:
	mv a0, s10
	mv a0, s10
	call __lib_array_size 
	blt s9, a0, l_114
	l_115:
	l_116:
	li a0, 1
	l_117:
	j l_118
	l_114:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s10
	addi tp, tp, 4
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s3
	addi tp, tp, 4
	lw t0, 0(tp)
	bne a0, t0, l_119
	l_120:
	l_121:
	mv a0, s9
	addi s9, s9, 1
	j l_113
	l_119:
	l_122:
	li a0, 0
	l_123:
	j l_118
	l_111:
	l_124:
	li a0, 0
	l_125:
	l_118:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret

.globl	_crackSHA1__myfunc
.p2align	1
.type	_crackSHA1__myfunc,@function

_crackSHA1__myfunc:
	l_126:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s8, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	mv s8, a0
	li s10, 5
	sub a0, a0, a0
	addi a0, a0, 4
	mul a0, a0, s10
	addi a0, a0, 4
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s10, 0(tp)
	l_127:
	bgtz s10, l_128
	l_129:
	mv s10, a0
	mv a0, s8
	call string_length 
	li t1, 40
	bne a0, t1, l_130
	l_131:
	li s9, 0
	l_132:
	li t1, 5
	blt s9, t1, l_133
	l_134:
	li s9, 0
	l_135:
	li t1, 40
	blt s9, t1, l_136
	l_137:
	li s3, 4
	li s6, 1
	l_138:
	ble s6, s3, l_139
	l_140:
	la t2, g_6
	mv a0, t2
	call puts 
	j l_141
	l_139:
	li s9, 0
	l_142:
	blt s9, s6, l_143
	l_144:
	l_145:
	l_146:
	mv a1, s6
	la tp, inputBuffer
	lw a0, 0(tp)
	call _sha1__myfunc 
	mv a1, s10
	call _array_equal__myfunc 
	bnez a0, l_147
	l_148:
	mv a1, s6
	la tp, inputBuffer
	lw a0, 0(tp)
	call _nextText__myfunc 
	bnez a0, l_149
	l_150:
	l_151:
	l_152:
	mv a0, s6
	addi s6, s6, 1
	j l_138
	l_149:
	j l_145
	l_147:
	li s9, 0
	l_153:
	blt s9, s6, l_154
	l_155:
	la t2, g_5
	mv a0, t2
	call puts 
	j l_141
	l_154:
	la tp, inputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	lw a0, 0(tp)
	call _int2chr__myfunc 
	call print 
	l_156:
	mv a0, s9
	addi s9, s9, 1
	j l_153
	l_143:
	la tp, inputBuffer
	lw a0, 0(tp)
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	addi tp, tp, 4
	li t1, 48
	sw t1, 0(tp)
	l_157:
	mv a0, s9
	addi s9, s9, 1
	j l_142
	l_136:
	mv s3, s9
	li t1, 8
	div s3, s3, t1
	mv s6, s9
	li t1, 8
	div s6, s6, t1
	mv a0, s8
	mv a1, s9
	addi a1, a1, 4
	mv a2, a1
	mv a1, s9
	call string_substring 
	call _hex2int__myfunc 
	mv a2, a0
	mv a0, s9
	li t1, 4
	div a0, a0, t1
	mv a1, a0
	li t1, 2
	rem a1, a1, t1
	li a0, 1
	sub a0, a0, a1
	li t1, 16
	mul a0, a0, t1
	mv a1, a2
	mv t4, a0
	sll a1, a1, t4
	mv tp, zero
	add tp, tp, s6
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s10
	addi tp, tp, 4
	lw a0, 0(tp)
	or a0, a0, a1
	mv tp, zero
	add tp, tp, s3
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s10
	addi tp, tp, 4
	sw a0, 0(tp)
	l_158:
	addi s9, s9, 4
	j l_135
	l_133:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s10
	addi tp, tp, 4
	li t1, 0
	sw t1, 0(tp)
	l_159:
	mv a0, s9
	addi s9, s9, 1
	j l_132
	l_130:
	la t2, g_4
	mv a0, t2
	call puts 
	l_141:
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s8, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_128:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s10, s10, -1
	j l_127

.globl	_main__myfunc
.p2align	1
.type	_main__myfunc,@function

_main__myfunc:
	l_160:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw ra, 0(sp)
	l_161:
	l_162:
	call getInt 
	beqz a0, l_163
	l_164:
	li t1, 1
	beq a0, t1, l_165
	l_166:
	li t1, 2
	bne a0, t1, l_167
	l_168:
	call getString 
	call _crackSHA1__myfunc 
	l_167:
	j l_169
	l_165:
	call getString 
	call _computeSHA1__myfunc 
	l_169:
	j l_161
	l_163:
	l_170:
	li a0, 0
	l_171:
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
	l_172:
	addi sp, sp, -4
	sw s0, 0(sp)
	mv s0, sp
	addi sp, sp, -4
	sw s7, 0(sp)
	addi sp, sp, -4
	sw s8, 0(sp)
	addi sp, sp, -4
	sw s6, 0(sp)
	addi sp, sp, -4
	sw s9, 0(sp)
	addi sp, sp, -4
	sw s3, 0(sp)
	addi sp, sp, -4
	sw ra, 0(sp)
	addi sp, sp, -4
	sw s10, 0(sp)
	la t2, g_7
	mv a0, t2
	la tp, asciiTable
	sw a0, 0(tp)
	li a0, 100
	la tp, MAXCHUNK
	sw a0, 0(tp)
	la tp, MAXCHUNK
	lw a0, 0(tp)
	li t1, 1
	sub a0, a0, t1
	li t1, 64
	mul a0, a0, t1
	li t1, 16
	sub a0, a0, t1
	la tp, MAXLENGTH
	sw a0, 0(tp)
	la tp, MAXCHUNK
	lw a0, 0(tp)
	mv s9, a0
	sub a1, a1, a1
	addi a1, a1, 4
	mul a1, a1, s9
	addi a1, a1, 4
	mv a0, a1
	call malloc 
	mv s8, a0
	mv tp, zero
	add tp, tp, s8
	sw s9, 0(tp)
	l_173:
	bgtz s9, l_174
	l_175:
	mv a0, s8
	la tp, chunks
	sw a0, 0(tp)
	la tp, MAXLENGTH
	lw a0, 0(tp)
	mv s10, a0
	sub s3, s3, s3
	addi s3, s3, 4
	mul s3, s3, s10
	addi s3, s3, 4
	mv a0, s3
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s10, 0(tp)
	l_176:
	bgtz s10, l_177
	l_178:
	la tp, inputBuffer
	sw a0, 0(tp)
	li s10, 5
	sub s6, s6, s6
	addi s6, s6, 4
	mul s6, s6, s10
	addi s6, s6, 4
	mv a0, s6
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s10, 0(tp)
	l_179:
	bgtz s10, l_180
	l_181:
	la tp, outputBuffer
	sw a0, 0(tp)
	call _main__myfunc 
	lw s10, 0(sp)
	addi sp, sp, 4
	lw ra, 0(sp)
	addi sp, sp, 4
	lw s3, 0(sp)
	addi sp, sp, 4
	lw s9, 0(sp)
	addi sp, sp, 4
	lw s6, 0(sp)
	addi sp, sp, 4
	lw s8, 0(sp)
	addi sp, sp, 4
	lw s7, 0(sp)
	addi sp, sp, 4
	mv sp, s0
	lw s0, 0(sp)
	addi sp, sp, 4
	ret
	l_180:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s10, s10, -1
	j l_179
	l_177:
	mv tp, zero
	add tp, tp, s10
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s10, s10, -1
	j l_176
	l_174:
	li s7, 80
	sub s10, s10, s10
	addi s10, s10, 4
	mul s10, s10, s7
	addi s10, s10, 4
	mv a0, s10
	call malloc 
	mv tp, zero
	add tp, tp, a0
	sw s7, 0(tp)
	l_182:
	bgtz s7, l_183
	l_184:
	mv tp, zero
	add tp, tp, s9
	li t2, 4
	mul tp, tp, t2
	add tp, tp, s8
	sw a0, 0(tp)
	addi s9, s9, -1
	j l_173
	l_183:
	mv tp, zero
	add tp, tp, s7
	li t2, 4
	mul tp, tp, t2
	add tp, tp, a0
	li t1, 0
	sw t1, 0(tp)
	addi s7, s7, -1
	j l_182

