.section .data
.global x
.global y
.global z
.global position_data_ptr
.global MAX_Y
.global MAX_Z
.global ID_CONTAINER_SIZE

.section .text
.global checkContainer
checkContainer:
        pushq %r10
        movslq ID_CONTAINER_SIZE(%rip), %r8
        movslq MAX_Z(%rip), %r9
        movslq MAX_Y(%rip), %r10

        movslq z(%rip), %rax
        mulq %r8
        movq %rax, %rdi
        movslq y(%rip), %rax
        mulq %r8
        mulq %r9
        addq %rax, %rdi
        movslq x(%rip), %rax
        mulq %r8
        mulq %r9
        mulq %r10
        addq %rax, %rdi

        movq position_data_ptr(%rip), %rax
        addq %rdi, %rax
        cmpb $0, (%rax)
        je zero
        movb $1, %al
        jmp end
zero:
        movb $0, %al
end:
        popq %r10
        ret
