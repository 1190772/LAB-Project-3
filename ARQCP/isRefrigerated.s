.section .data

.global position_data_ptr
.global ID_CONTAINER_SIZE 

.equ DATA_SIZE, 56
.equ X_OFFSET, 36
.equ Y_OFFSET, 37
.equ Z_OFFSET, 38
.equ TEMPERATURE_OFFSET, 39

.section .text
.global isRefrigerated
isRefrigerated:
	# container_ptr in rdi, x in sil, y in dl, z in cl, length in r8d, ptr_ptr int %r9
	
	movl $0, %eax
	
position_loop:
	cmpl $0, %r8d # see if there are more positions to check
	je end
	
	cmpb %sil, X_OFFSET(%rdi) # test x coordinate
	jne next
	cmpb %dl, Y_OFFSET(%rdi) # test y coordinate
	jne next
	cmpb %cl, Z_OFFSET(%rdi) # test z coordinate
	jne next
	
	# Container Found
	cmpb $0, TEMPERATURE_OFFSET(%rdi) # test if refrigerated
	je end
	movq %rdi, (%r9) # set caller container_ptr to the found container
	movl $1, %eax
	jmp end
next:
	decl %r8d # decrement number of positions left
	addq $DATA_SIZE, %rdi # move to next position
	jmp position_loop
       
end:
	ret
