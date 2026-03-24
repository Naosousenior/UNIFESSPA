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

Circuitos integrados são mecanismos eletrônicos complexos compostos por um substrato semicondutor (geralmente silício),
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

#figure(
  image(
    "images/transistor_bipolar.png",
    height: 10em
  ),
  
  caption: [
    #text(size: 10pt)[Estrutura de um transistor bipolar de junção _npn_. Fonte: #cite(<microeletronica>,form: "prose")]
  ]
)

Entretanto, o funcionamento dos terminais difere do transistor _npn_ para o _pnp_. No caso do _npn_, para que haja fluxo de corrente, deve haver uma injeção de corrente na base do transistor, fazendo com que a energia flua do coletor para o emissor. No transistor _pnp_, por outro lado, que funciona com lacunas, a
corrente flui do emissor para o coletor, e para que haja essa corrente, deve haver uma drenagem de corrente na base @microeletronica.

== Tecnologia MOS

De acordo com #cite(<sistemas-digitais>, form: "prose"), a sigla MOS significa _metal-oxide-semicondutor_, ou metal óxido semicondutor, uma tecnologia que
consiste em um eletrodo de metal que produz um _campo elétrico_ que influência na resistência de um substrato semicondutor, que permite ou não a passagem de energia, o que permite a construção de transistores chamados *MOSFETs*, Transistores de Efeito de Campo Semicondutor de Óxido Metálico @emily-johnson.

#figure(
  image(
    "images/transistor-mosfet.webp",
    height: 10em
  ),
  caption: [
    #text(size: 10pt)[Estrutura de um transistor MOSFET de canal N. Fonte: #cite(<emily-johnson>, form: "prose")]
  ]
) <figura-mosfet>

Conforme vemos na @figura-mosfet, o MOSFET possui uma _porta_ (Gate), um _dreno_ (Drain) e uma _fonte_ (Source). Observe que a fonte e o dreno estão conectados
por uma linha tracejada, indicando que normalmente não há passagem de corrente direta entre eles, e observe também que a porta está separada  dos demais
terminais, indicando que há uma alta resistência entre ela e o substrato @sistemas-digitais.

Aplicando uma tensão positiva da porta para a fonte, o campo elétrico produzido pela porta reduz a resistência do substrato semicondutor, permitindo assim que
haja uma passagem de corrente entre o dreno e a fonte @sistemas-digitais.

Ainda de acordo com #cite(<sistemas-digitais>, form: "prose"), a tecnologia MOS possui vantagem em relação a transistores bipolares nos quesitos custo de
fabricação, custo de potência e miniaturização, ou seja, chips feitos com a tecnologia MOS são mais baratos, consomem menos energia, dissipam menos calor e
possuem uma maior quantidade de componentes por espaço. Por outro lado, chips MOS são extremamente sensíveis a eletrecidade estática, o que os torna menos duráveis e confiáveis.

= Família lógica TTL

A família _Transistor-Transistor Logic_ é fabricada a mais de 60 anos, estando presente no mercado eletrônico até os dias de hoje, apesar de ter entrado em
declinio e atualmente ser mais utilizada para fins acadêmicos. Seu circuito digital principal consiste na porta NAND, demonstrado em @figura-nand.

#figure(
  image(
    "images/nand-ttl.png",
    height: 20em
  ),
  caption: [
    #text(size: 10pt)[Circuito NAND de um circuito TTL]
  ]
)<figura-nand>

Algumas características dos circuitos TTL 

#pagebreak()

#bibliography(
  "referencias.yaml",
  style: "associacao-brasileira-de-normas-tecnicas"
)