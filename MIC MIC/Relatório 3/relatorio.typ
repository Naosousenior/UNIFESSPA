#set text(
  font: "Times New Roman",
  size: 12pt,lang: "pt",
  region: "br",
)
#set par(
  first-line-indent: (amount: 2em,all: true),
  leading: 0.6em,
  spacing: 1em,
  justify: true
)
#set page(paper: "a4")
#set list(indent: 3em)
#set heading(numbering: "1.")

#show link: set text(hyphenate: true)

#show heading: self => [
  #self
  #v(15pt)
]

#align(
  center,
  [
    #text(size: 16pt)[
      #box(
        image("images/unifesspa_logo.png"),
        height: 4cm,
      )

      UNIVERSIDADE FEDERAL DO SUL E DO SUDESTE DO PARÁ
    ]
  ]

)

#v(5cm)

#align(center)[
  #text(size: 18pt)[
    Relatório de Experimentos com Microcontroladores

    PIC16F628A
  ]
]

#v(5cm)

#align(
  right,[
    Alunos:

    #upper[
      Adolfo Fernando Silva Araújo

      Lucas de Oliveira Alves
    ]
  ]
)

#align(center)[
  #v(3.4cm)
  2026

  Marabá-PA
]
#pagebreak()

= Atividades Realizadas

*Na primeira simulação*, o objetivo era fazer com que o led acendesse ao apertar o botão conectado ao terminal AO do chip. O binário executado está disponível
#link("https://drive.google.com/file/d/1ZtoX7ac6fzXFps1kuLKd3dbKPHOCsaR2/view?usp=drive_link")[ #underline("aqui,") ] e o vídeo com a simulação está disponível
#link("https://drive.google.com/file/d/1qMQlIilUkb_f4QgaNoXZdOZcT-zd2cfB/view?usp=drive_link")[ #underline("aqui.")]

Por sua vez, na segunda simulação o objetivo era fazer com que o LED piscasse periódicamente. O binário executado está
#link("https://drive.google.com/file/d/1M8NZahAGNhXjpko2iseTGxaCPmHlpiBe/view?usp=drive_link")[#underline("aqui"),] e o vídeo com a simulação está
#link("https://drive.google.com/file/d/1SWpr0IB6BL1AAx6Ri6oR9uEaCHpZsGsN/view?usp=drive_link")[#underline("aqui.")]

= Avaliações

Acerca das tarefas realizadas, as seguintes atividades avaliativas foram respondidas, com base nos conhecimentos adquiridos  nas aulas de Microcontroladores e Microprocessadores.

== Atividade Avaliativa 1
1. Considerando o código do experimento 2, utilize o osciloscópio para:

- Meça o tempo em que o LED fica ligado.

- Meça o tempo em que o LED fica desligado. 

R= Tempo Ligado = Tempo Desligado = 150 ms (em média). Tempo total = 300 ms (em média).

2. Modifique o código de `movl .4` $->$ `movl .8`. O que acontece com o período?

R= O período entre piscadas do led aumentou, diminuindo a frequência do led.

== Atividade Avaliativa 2

1. Considerando o experimento do código que trata sub-rotina:

- Utilizando um Osciloscópio, observe o sinal do LED. O uso de CALL altera o período do sinal? Explique.

R= Sim, uma vez que o `call` consome alguns ciclos a mais de CPU.

- Crie uma sub-rotina (pisca_led), que: liga o LED, espera (delay), desliga e espera (delay). 

R= A sob-rotina foi criada, e ela é executada sempre que o botão é apertado:

```yasm
;Microcontroladores e Microprocessadores
;Aula 03
;Prof. Elton Alves
;Sub-Rotina e criação de variáveis em memória
;Botão ligado em pull-up;
;0 - botão acionado
;1 - botão desligado

#include <P16f628a.inc>

;---FUSE bits---
__config _XT_OSC & _WDT_OFF & _PWRTE_ON & _CP_OFF & _MCLRE_OFF & _LVP_OFF &_BODEN_OFF

;---Paginação de Memória
#define bank0 bcf STATUS, RP0
#define bank1 bsf STATUS, RP0

; Define as Entradas
#define botao2 PORTA,0

; Defina Saídas
#define led2 PORTB,3

;---Vetor de RESET---
org H'000'
goto inicio

;---Vetor de Interrupção----
org H'0004'
retfie

inicio
    CLRF PORTA
    CLRF PORTB

    bank1
    movlw H'FF'
    movwf TRISA ;TRISA entrada

    movlw H'F7' ; RB3 saída
    movwf TRISB

    movlw b'00000000' ; habilita pull-up
    movwf OPTION_REG

    movlw b'00000000'
    movwf INTCON ;interrupções desligadas

    bank0
    movlw b'00000111'
    movwf CMCON ; comparadores off

loop
    call trata_but
    goto loop

;--- Sub-rotina ---
trata_but
    btfsc botao2 ; se 0 (pressionado), pula próxima
    goto apaga_led2

    call pisca_led ; pisca LED
    return

apaga_led2
    bcf led2 ; apaga LED
    return
    
pisca_led
    bsf led2
    call delay
    bcf led2
    call delay
    return
    
delay
    call delay4
    call delay4
    call delay4
    call delay4
    call delay4
    return

delay4
    call delay3
    call delay3
    call delay3
    call delay3
    call delay3
    call delay3
    call delay3
    call delay3
    call delay3
    call delay3
    return
    
delay3
    call delay2
    call delay2
    call delay2
    call delay2
    call delay2
    call delay2
    call delay2
    call delay2
    call delay2
    call delay2
    return
    
delay2
    call delay1
    call delay1
    call delay1
    call delay1
    call delay1
    call delay1
    call delay1
    call delay1
    call delay1
    call delay1
    return

delay1
    nop ;nop não faz nada, apenas gasta 1 ciclo de máquina (1 micro s)
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop
    return

end

```

O binário para o programa acima está disponível em #link("https://drive.google.com/file/d/1XQu2LRTACgPDMr6U9RP9TbPpnTg9l3at/view?usp=drive_link")[
  #underline("aqui,")
]
e o vídeo de simulação está disponível #link("https://drive.google.com/file/d/1XEy_5LlhfYdSoYyUGoar7KpTx6V1QCl9/view?usp=drive_link")[#underline("aqui.")]

= Conclusão

O uso de sub-rotinas para o desenvolvimento de firmwares é essencial, uma vez que aumenta a flexibilidade dos programas e elas podem ser tratadas por vezes como
funções de baixo nível. Neste experimento, foi demonstrado que no baixo nível, o uso da instrução goto é essencial, tornando possível chamar sub-rotinas que vão finalizar outras sub-rotinas, mostrando como um diagrama de fluxo é útil e necessário para o desenvolvimento de softwares de baixo nível.