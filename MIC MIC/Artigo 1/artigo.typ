#set text(
  font: "New Computer Modern",
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
#show heading: self => [
  #self
  #v(15pt)
]

#align(
  center,
  [
    #text(size: 16pt)[
      #box(
        image("images/brasao_republica.png"),
        height: 4cm,
      )

      UNIVERSIDADE FEDERAL DO SUL E DO SUDESTE DO PARÁ
    ]
  ]

)

#v(5cm)

#align(center)[
  #text(size: 18pt)[
    Famílias Lógicas e Circuitos Integrados
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
  #v(2cm)
  2026

  Marabá-PA
]
#pagebreak()

= Introdução

Os dispositovos #lorem(50)