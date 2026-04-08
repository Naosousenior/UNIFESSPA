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

*Na primeira atividade*, o objetivo era piscar uma LED. Para isso, o seguinto código foi compilado e executado:

```yasm
list      p=16F628
#include <P16f628a.inc>
    
;---FUSE bits---    
;Cristal oscilador externo 4MHZ
;Sem watchdog time
;Com powe up time
__config _XT_OSC & _WDT_OFF & _PWRTE_ON & _CP_OFF & _MCLRE_OFF & _LVP_OFF & _BODEN_OFF
    
;---Paginação de Memória
#define  bank0   bcf STATUS, RP0
#define  bank1   bsf STATUS, RP0
    
;---Saídas---
 #define led1 PORTB,RB0
 
;---Vetor de RESET---
     org H'000'
     goto inicio
     
;---Vetor de Interrupção----
     org H'0004'
     retfie
     
;---Incio do Programa----
 
inicio 
     
CLRF PORTB
CLRF PORTA
     
bank1
     movlw B'00000000'
     movwf TRISB
     movlw b'10000000'
     movwf OPTION_REG
     
     movlw b'00000000'
     movwf INTCON
 bank0
     movlw b'00000111'
     movwf CMCON
     
  
loop 
bsf     led1

goto loop
    
END
```

A seguir, a simulação do código no SimulIDE: #link("https://drive.google.com/file/d/1WHWGx2Ul2ANDnmkOlDwhrWRYf95hj_31/view?usp=drive_link")[
  #underline("Simulação do projeto 1")
]

O arquivo binário do código está em: #link("https://drive.google.com/file/d/1wKGgw95DZZQVEXk4P-rDsncAfksLH4WX/view?usp=drive_link")[
  #underline("Binário do projeto 1")
]

*Na segunda atividade*, o objetivo era piscar dois LEDs, o código utilizado foi o seguinte:

```yasm
list      p=16F628
#include <P16f628a.inc>

;---FUSE bits---
__config _XT_OSC & _WDT_OFF & _PWRTE_ON & _CP_OFF & _MCLRE_OFF & _LVP_OFF & _BODEN_OFF

;---Paginação de Memória
#define  bank0   bcf STATUS, RP0
#define  bank1   bsf STATUS, RP0

;---Saídas---
#define led1 PORTB,0
#define led2 PORTB,1

;---Vetor de RESET---
org H'000'
goto inicio

;---Vetor de Interrupção----
org H'0004'
retfie

;---Inicio do Programa----
inicio 

    CLRF PORTB
    CLRF PORTA

    bank1
    movlw B'00000000'
    movwf TRISB
    movlw b'10000000'
    movwf OPTION_REG
    movlw b'00000000'
    movwf INTCON

    bank0
    movlw b'00000111'
    movwf CMCON

;---Loop principal---
loop
    bsf led1
    bcf led2

;---Delay (NOP)---
d1
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

    ; LED1 OFF / LED2 ON
    bcf led1
    bsf led2

;---Delay (NOP)---
d2
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

    goto loop

END
```

A simulação realizada no SimulIDE pode ser encontrada em: #link("https://drive.google.com/file/d/1-lwSkYwZAehXQlHJgfpahbBMN5fnw-QU/view?usp=drive_link")[
  #underline("Simulação do projeto 2")
]

O arquivo binário usado nesta simulação está em: #link("https://drive.google.com/file/d/1eT3OSHI39lm_nf6Q2_2w2TQifd89tw7W/view?usp=drive_link")[
  #underline("Bináio do projeto2")
]

= Avaliações

Acerca das tarefas realizadas, as seguintes atividades avaliativas foram respondidas, com base nos conhecimentos adquiridos  nas aulas de Microcontroladores e Microprocessadores.

== Atividade Avaliativa 1
Considerando o projeto 2, responda as seguintes perguntas:

1. Por que o LED parece não piscar?
R= O tempo gasto é muito curto, dado que cada nop consome apenas 1 microssegundo, totalizando 10 microssegundos de delay para acender e apagar os LEDs, tempo imperceptível a olho nu.

2. Qual a frequência aproximada?
R= De acordo com a @figura_circuito, o osciloscópio indica uma frequência de 38.46 kHz. A percepção humana é incapaz de captar algo acima de 60 Hz, portanto, será impossível percebermos que o LED está piscando de forma alternada. 

#figure(
  caption: [Captura de tela da simulação do circuito do experimento 2, durante a execução],
  image("images/circuito_1.png")
)<figura_circuito>

3. O duty cycle é 50%? Por quê?
R= Conforme observável na @figura_circuito, o Duty Cycle é de 50%.

== Atividade Avaliativa 2

Uma abordagem possível para resolução desse problema serial literalmente adicionar mais instruções `nop` para aumentar a quantidade de ciclos consumidos pelo microcontrolador. Para chegar a uma troca de 1 Hz, deveriam haver 4 milhões de instruções `nop` para que a frequência ser de 1 Hz, fazendo cada LED ficar aceso por 0,5 segundos. Nesse caso, deveriam haver 2 milhões de `nop`s no primeiro delay e mais dois milhões no segundo delay, algo mais ou menos assim:

```
; ---Restante do código---
;---Delay (NOP)---
d1
    nop ;nop não faz nada, apenas gasta 1 ciclo de máquina (1 micro s)
    nop
    nop
    nop
    nop
    ; --- Mais 1999990 de instruções nop ---
    nop
    nop
    nop
    nop
    nop

    ; LED1 OFF / LED2 ON
    bcf led1
    bsf led2

;---Delay (NOP)---
d2
    nop
    nop
    nop
    nop
    nop
    ; --- Mais 1999990 de instruções nop ---
    nop
    nop
    nop
    nop
    nop

    goto loop

```

Porém, tal programa seria grande demais para caber na memória de um PIC16F628A, que possui como limite de memória apenas 2048 palavras, ou seja, um máximo de 2048 instruções. Para resolver o problema, foram criadas funções que chamam funções de delay, multiplicando o delay até ser perceptível a troca de luz entre os LEDs. O código final implementado foi:

```
;Microntroladores e Microprocessadores;
;Aula 01
;Prof. Elton Alves
; Código para piscar dois LEDs

list      p=16F628
#include <P16f628a.inc>

;---FUSE bits---
__config _XT_OSC & _WDT_OFF & _PWRTE_ON & _CP_OFF & _MCLRE_OFF & _LVP_OFF & _BODEN_OFF

;---Paginação de Memória
#define  bank0   bcf STATUS, RP0
#define  bank1   bsf STATUS, RP0

;---Saídas---
#define led1 PORTB,0
#define led2 PORTB,1

;---Vetor de RESET---
org H'000'
goto inicio

;---Vetor de Interrupção----
org H'0004'
retfie

;---Inicio do Programa----
inicio

    CLRF PORTB
    CLRF PORTA

    bank1
    movlw B'00000000'
    movwf TRISB
    movlw b'10000000'
    movwf OPTION_REG
    movlw b'00000000'
    movwf INTCON

    bank0
    movlw b'00000111'
    movwf CMCON

;---Loop principal---
loop

    ; LED1 ON / LED2 OFF
    bsf led1
    bcf led2

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
    
    ; LED1 OFF / LED2 ON
    bcf led1
    bsf led2
    
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

    goto loop

;--Deley ainda mais demorado
delay2
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    call delay
    retfie

;---Delay (NOP)---
delay
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
    retfie
    
END
```

O link para o binário do firmware está disponível em #link("https://drive.google.com/file/d/1_VDzIuPAy8w9BH4R9yBDGI8aQUyThrUa/view?usp=sharing")[ #underline("Binário do firmware do exercício 2")], enquanto o vídeo para a simulação rodando está disponível em #link("https://drive.google.com/file/d/1k9exDqhxxPBbsKQ-j9EAytnvKvjGh5ZL/view?usp=sharing")[#underline("Vídeo de demonstração da simulação")].

= Conclusão

O chip PIC16F628A pode ser utilizado com muita facilidade para temporizar ações através de seu clock por meio de instruções `nop`, embora esse não seja exatamente a forma mais eficiente de fazer as coisas. Além disso, foi possível observar a utilidade da instrução `nop` no processo de temporização de tarefas e instruções.