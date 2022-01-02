.section .data

.global position_data_ptr
.global MAX_X
.global MAX_Y
.global MAX_Z
.global ID_CONTAINER_SIZE 

.section .text
.global occupation
occupation:
	movq position_data_ptr(%rip), %rcx # place position_data_ptr in rcx
	movl MAX_X(%rip), %edx # place MAX_X in edx
	movl MAX_Y(%rip), %esi # place MAX_Y in esi
	movl MAX_Z(%rip), %edi # place MAX_Z in edi
	movl ID_CONTAINER_SIZE(%rip), %eax # place ID_CONTAINER_SIZE in eax
		
       imull %esi, %edx # multiply x with y
       imull %edi, %edx # multiply (x*y) with z, total room in edx
        
	movl $0, %esi # set free slots to 0
       movl $0, %edi # set occupied slots to 0
       
my_loop:
	cmpb $0, (%rcx) # check if position is empty
	je free
	incl %edi # increment occupied slots
	jmp next
free:
	incl %esi # increment free slots
next:
	decl %edx # decrement slots left
	cmpl $0, %edx # check if there are no more slots left
	je end
	addq %rax, %rcx # advance to next slot
	jmp my_loop
	
end:
	movl %esi, %eax # copy free slots to eax
	SAL $32, %rax # shift free slots to the 4 most significant bytes
	addq %rdi, %rax # copy occupied slots to eax
	ret
