# SGM-LECC21-G1
Pretende-se com este trabalho especificar e modelar o comportamento de algo que conhecemos na realidade, um Sistema de Gestão. Resultando assim em uma solução que possa gerir até três componentes dentro do sistema interligados entre si. É objectivo deste trabalho a criação de um Sistema de Gestão de uma mercearia

Objectivos
É objectivo deste trabalho a criação de um Sistema de Gestão que permita efectuar as seguintes
operações:
• Autenticação;
• Criar, Editar e Remover Clientes;
• Criar, Editar e Remover Produtos;
• Criar, Editar e Remover Armazéns;
• Criar, Editar e Remover Fornecedores;
• Emitir Relatórios Produtos mais vendidos
• Filtros
• Efectuar Vendas de Produtos.
• Stock mínimo = 5

**Politica de Funcionamento**
Pretende-se com este ponto definir algumas regras de funcionamento deste programa e também definir alguns requisitos funcionais que o sistema deve poder executar. Cada operação que é relevante a ser recuperada durante a execução do programa, neste caso vai ser usado uma estrutura de dados do tipo Vector e Leitura e Gravação em ficheiros de Objectos.

**Requisitos Funcionais:**
Os requisitos listados nesta sessão foram feitos como forma de ilustrar ao aluno uma forma de iniciar o trabalho. Não se espera que no final alguém tenha uma interface idêntica ao listado aqui, mas sim ideias próprias baseado no tema por vos escolhido.

**RF01**: O sistema deve estar protegido para que pessoas não autorizadas façam introdução ou alteração dos dados.
![image](https://github.com/Mauroreis100/SGM-LECC23-G1/assets/69197927/99ad8680-b595-4481-8b20-cf4b9a5e63b6)

RF02: O sistema deve ter um menu amigável facilitando o utilizador a achar as funções de forma rápida.
![image](https://github.com/Mauroreis100/SGM-LECC23-G1/assets/69197927/0d21509f-b08e-411c-8376-098103b882c4)

RF03: Na Tela de Registo dos componentes quando regista-se um componente uma nova entrada deve ser gravado em um Vector e também deve ser populado na tabela abaixo ficando da seguinte forma, veja a imagem mais a abaixo.

RF04: Deve ser possível procurar os componentes através de dois campos diferentes, por exemplo: do Nome ou Numero, veja no 2º separador abaixo.
![image](https://github.com/Mauroreis100/SGM-LECC23-G1/assets/69197927/b64f1934-51c1-4746-bd64-0d6b30a4339a)

RF05: Outra tela, idêntico ao RF03
![image](https://github.com/Mauroreis100/SGM-LECC23-G1/assets/69197927/2eb10f0b-2f1a-455e-882e-b2a9f208aa74)

RF06: Ultima tela.
![image](https://github.com/Mauroreis100/SGM-LECC23-G1/assets/69197927/7e8a0361-90dd-47d8-9136-83acc0c135f0)

RF07: Um filtro que permite listar todos produtos que estão em todos armazéns cujo Stock Mínimo esta abaixo do previsto para o Produto e também possibilidade de listar produtos que estejam quase no seu prazo de validade para expirar.
![image](https://github.com/Mauroreis100/SGM-LECC23-G1/assets/69197927/e79c597d-fba0-4e28-8dae-785fcd9d2f89)

RF08: A Tela principal deve conter o horário actual actualizando a cada um segundo
Horas: Minutos: Segundos

RF09: O programa deve estar protegido de erros usando Excepções.

RF10: O programa deve poder vender os Produtos que temos em Stock, caso o mesmo não esteja em stock/Armazém Incorreto deve informar. Não deve ser possível vender em stock negativo ou igual a zero. Qualquer venda deve ser acrescido do IVA 17% e o mesmo deve ser ilustrado. As vendas devem ser debitadas directamente no Stock e o programa não permite devoluções. 

A tela de vendas fica a critério de cada grupo. Mas deve-se no mínimo indicar Quantidade do Produto e Produto para que o sistema tenha uma base de calculo. O produto deve estar listado e o funcionário deve escolher, e não digitar o nome do produto que pretende comprar. O que sera permitido digital manualmente será a quantidade, pois isto dependera de cada cliente.

Quando fala-se de interligação, entenda como um sistema que tem uma sequência de ações para serem feitas, veja a tela de Produtos tem um campo designado como Fornecedor que é uma caixa de escolha, a única maneira desta caixa de escolha ter informações é primeiro ser preenchido um Fornecedor ao menos.







