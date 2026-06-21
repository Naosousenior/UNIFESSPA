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

      Alana Brenda Pantoja dos Santos

      Lucas de Oliveira Alves
    ]
  ]
)

#align(center)[
  #v(3cm)
  2026

  Marabá-PA
]
#pagebreak()

= Atividades Avaliativa

O objetivo da avaliação era criar um sistema de semáforo para veículos, com um botão para os pedestres apertarem para fechar o sinal para os veículos por 9 segundos. O componente central é o PIC16F628A.

#v(1em)

#figure(
  image("images/circuito.png"),
  caption: [
    Circuto do semáforo
  ]
)<circuito>

#v(1em)

De acordo com o esquema do @circuito, todo o *PORTB* foi utilizado para o display de 7 seguimentos, enquanto no *PORTA* tivemos a seguinte configuração:

#v(1em)

#align(center)[
  #table(
    columns: 2,
    table.header([*Terminal*],[*LED*]),
    [RA2], [Sinal verde],
    [RA6], [Sinal amarelo],
    [RA7], [Sinal vermelho],
    [RA1], [Botão para os pedestres]
  )
]

#v(1em)

O vídeo de uso do firmware em uma placa está #underline( link("https://drive.google.com/file/d/1GArJg7vzrpE9DuX3YNM5pPUiSMNI1xas/view?usp=drive_link")[aqui] ).

O código fonte da aplicação está #underline(link("https://drive.google.com/file/d/1qkjyHyxGN87YWHTjrOp8odjpNwBgIMXe/view?usp=drive_link")[aqui]).

O binário está #underline(link("https://drive.google.com/file/d/1NCYC3de7tU72owC7BgP6RquUpO2l-px8/view?usp=drive_link")[aqui]).