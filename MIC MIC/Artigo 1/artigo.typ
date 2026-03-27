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

#outline()

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
    #text(size: 10pt)[Circuito NAND de um circuito TTL. Fonte: #cite(<sistemas-digitais>,form: "prose")]
  ]
)<figura-nand>

Uma característica notável dos circuitos TTL, é o fato de que portas desconectadas possuem o valor lógico 1, devido a natureza do transistor bipolar, e nestes
casos, diz-se que a porta em questão está *flutuando* @sistemas-digitais.

A família TTL possui duas séries principais, a série 74 e a série 54. Segundo #cite(<sistemas-digitais>,form: "prose"), ambas as séries suportam uma
tensão de 4,5 a 5,5 V, porém, a série 74 é mais sensível a temperatura, suportando de 0 a 70º C, enquanto a série 54 suporta de -55º a 125º C. As tensões máximas
suportadas são +7 V e -0,5 V.

Conforme mostra na @figura-tabela-ttl, o _fan-out_ da série TTL é baixo, sendo de apenas 10 para a série 74, e de no máximo 40 na série 74AS. Também é possível
observar que a série 74ALS é a que possui as melhores estatísticas, com um dos menores atrasos e menor dissipação de potência, mas com um _fan-out_ e taxa de clock razoáveis em comparação com as demais séries.

#figure(
  image("images/tabela-ttl.png"),
  caption: [
    #text(size: 10pt)[Recorte de uma tabela de #cite(<sistemas-digitais>,form: "prose")]
  ]
)<figura-tabela-ttl>

= Família lógica CMOS

A família CMOS é a base da eletrônica digital moderna, com algumas de suas séries compatíveis com a família TTL, mas apresentando melhor possibilidade de compressão, menor dissipação de potência e fabricação mais barata @sistemas-digitais.

Quanto a dissipação de potência, os circuitos CMOS possuem um consumo despresível quando não há comutação, ou seja, quando o estado lógico das portas não muda,
o que é um dos motivos para apresentarem uma boa eficiência energética. Por outro lado, quanto maior a frequência do circuito, mais comutações ele faz, e
consequentemente, mais ele consome potência @sistemas-digitais.

De acordo com #cite(<sistemas-digitais>, form: "prose"), o _fan-out_ dos circuitos CMOS é limitado pela atraso de propagação máximo, o que significa que, quanto
maior o fan-out, menor é a velocidade da porta. Por este motivo, na prática, o _fan-out_ costuma ser limitado a 50 para circutos com frequência menor que 50 MHz.

Porém, também é importante notar que os chips CMOS são extremamente sensíveis, em comparação com a família TTL. Na família CMOS, entradas não conectadas são
suscetíveis a interferências e ruído, podendo resultar em um pequeno curto-circuito. Também é importante notar que os circuitos CMOS são mais vulneráveis
a eletrecidade estática, uma vez que a camada de óxido semicondutor pode não suportar a diferença de potencial que uma carga estática consegue gerar
@sistemas-digitais.

A partir da família CMOS, surgiu também a família BicMOS, uma família de chips que combinam a alta velocidade dos transistores bipolares com a baixa dissipação
da família BicMOS, obtendo o melhor dos dois mundos. Além disso, a família BicMOS é compatível pino a pino com a família TTL padrão, aumentando as possibilidades
de projetos de circuitos @sistemas-digitais.

= Conclusão

As diferentes famílias lógicas atendem a diferentes mercados, porém, é inegável as vantagens que a família CMOS e sua filha, BicMOS, trouxeram para o mercado.
A possibilidade de criar chips com menor consumo, em menos espaço, e de forma mais barata é simplesmente muito tentadora para o mercado, que abraçou a família
CMOS em todo lugar que ela pode ser inserida. Ainda assim, a tecnologia de transistores bipolares e a família TTL não perderam seu valor, ainda sendo
largamente utilizadas em contextos que requerem mais segurança, maior velocidade ou maior robustez. Cada tecnologia eletrônica atende ao seu mercado.

#pagebreak()

#bibliography(
  "referencias.yaml",
  style: "associacao-brasileira-de-normas-tecnicas"
)