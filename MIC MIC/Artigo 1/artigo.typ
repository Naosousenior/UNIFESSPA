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
  #v(3.4cm)
  2026

  Marabá-PA
]
#pagebreak()

= Introdução

Circuitos integrados são mecanismos com complexos circuitos eletrônicos compostos por um substrato semicondutor (geralmente silício),
e encapsulados por um material isolante, com alguns contatos externos @rosaneguse.
CIs, também comumente chamados de chips ou microchips @rosaneguse, estão atualmente presentes em todo tipo de dispositivo com qualquer
resquício de energia elétrica, como brinquedos infantis, carros, computadores, telefones celulares, naves espaciais,
trens do metrô, aviões, videogames, escovas de dente e etc. @ci-synopsis.

Devido a sua natureza de alta complexidade, emerge a necessidade da classificação técnica de tais componentes com objetivo de realizar estudos e análises
sobre a eficácia e eficiência de cada tipo de chip para determinados contextos. Assim sendo, existem muitas formas de classificar os CIs. Neste artigo,
abordaremos a tipologia de circuitos integrados por meio de _famílias lógicas_, que leva em consideração a tecnologia de fabricação dos circuitos @rosaneguse.

= Famílias lógicas

Existem muitos tipos de famílias lógicas, cada família atende a um ramo específico de tecnologia. A seguir, listamos alguns tipos de famílias lógicas
segundo @curso-eletronica:

Famílias lógicas que usam transistores bipolares:
- DTL
- DCTL
- RTL
- RCTL
- HTL
- TTL
- ECL

Famílias lógicas que usam tecnologia MOS:

- pMOS
- nMOS
- CMOS

== Transistores bipolares

Segundo #cite(<microeletronica>,form: "prose"), transistor bipolar de junção, ou simplesmente transistor, são componentes eletrônicos de 3 terminais com duas
junções _pn_, conectadas em série e em oposição. O motivo de se chamar transistor bipolar se deve ao fato de que a corrente pode fluir tanto por elétrons como por lacunas, no primeiro caso, o transistor é do tipo _npn_, e no segundo caso, o transistor é do tipo _pnp_.

Em ambos os casos, os três terminais do transistor são @microeletronica:

+ *Coletor*: O terminal por onde a corrente entra no transistor.
+ *Emissor*: O terminal por onde a corrente sai do transistor.
+ *Base*: O terminal que funciona como chave, abrindo ou fechando o fluxo de corrente entre coletor e emissor.

Entretanto, o funcionamento dos terminais difere do transistor _npn_ para o _pnp_. No caso do _npn_, para que haja fluxo de corrente, deve haver uma injeção de corrente na base do transistor, fazendo com que a energia flua do coletor para o emissor. No transistor _pnp_, por outro lado, que funciona com lacunas, a
corrente flui do emissor para o coletor, e para que haja essa corrente, deve haver uma drenagem de corrente na base @microeletronica.

== Tecnologia MOS



= Família lógica TTL




#bibliography(
  "referencias.yaml",
  style: "associacao-brasileira-de-normas-tecnicas"
)