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

O objetivo da avaliação era criar um sistema de semáforo, onde iria ter a parte dos veículos e a parte dos pedestres. O semáforo para os pedestres possui apenas um led verde e um led vermelho, e o semáforo para os veículos possui três leds, um vermlho, um verde e um amarelo. O componente central é o PIC16F628A.

#v(1em)

#figure(
  image("images/circuito.png"),
  caption: [
    Circuto do semáforo
  ]
)<circuito>

#v(1em)

Na @circuito, é possível ver que os terminais utilizados foram RB0, RB1, RB2, RB4 e RB5, de acordo com a tabela:

#v(1em)

#align(center)[
  #table(
    columns: 2,
    table.header([*Terminal*],[*LED*]),
    [RB0], [Verde veículos],
    [RB1], [Amarelo veículos],
    [RB2], [Vermelho veículos],
    [RB4], [Verde pedestre],
    [RB5], [Vermelho pedestre],
  )
]

#v(1em)

O vídeo de uso do firmware em uma placa está #underline( link("https://drive.google.com/file/d/1xj_5_Yc66wp1IuUSOuouQDe5xQ2BX2s9/view?usp=drive_link")[aqui] ).

O código fonte da aplicação está #underline(link("https://drive.google.com/file/d/1DHHrWtvRzwvvpPqU4Sl-NSnzUClNBUvF/view?usp=drive_link")[aqui]).

O binário está #underline(link("https://drive.google.com/file/d/1TnboQpOKVUumWzDqFNtmVxZGnOcve1t9/view?usp=drive_link")[aqui]).