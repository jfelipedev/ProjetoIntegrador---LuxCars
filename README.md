# Certified Tech Developer - Projeto Integrador

[Template TC/API](https://docs.google.com/spreadsheets/d/102YXhAx5eYccNrHzfOaBldmsKHMqhsJ-gkHUi6vrOlI/edit?usp=sharing)

## Sprint 3
> ### Épica: Gestão de usuários

<details>
<summary>Ver</summary>
<table>
<tr>
<th colspan="2"><b>Dados usuários</b></th>
<th>Requisito</th>
</tr>
<tr>
<td>
<b>Criar a tabela de funções na base de dados
</b>
</td>
<td>
Deve conter como atributos:
<ul>
<li>
id
</li>
<li>
nome
</li>
</ul>
</td>
<td>
"Como administrador, quero conseguir gerir funções no site para poder conceder diferentes permissões aos usuários".
</td>
</tr>
<tr>
<td>
<b>Criar tabela de "usuários" na base de dados</b>
</td>
<td>
Deve conter como atributos:
<ul>
<li>
id
</li>
<li>
nome
</li>
<li>
sobrenome
</li>
<li>
e-mail
</li>
<li>
senha
</li>
</ul>
Crie um relacionamento Um para um com a tabela “funções”
</td>
</tr>
<tr>
<td>
<b>Criar API para registro de usuários</b>
</td>
<td>
<ul>
<li>
Criar endpoint que nos permita registrar novos usuários, utilizando um codificador para encriptar a senha.
</li>
<li>
Devolver um código 201 em caso de sucesso.
</li>
</ul>
</td>
<td>
"Como usuário anônimo, quero poder registrar-me no site para ter acesso a funcionalidades extras".
</td>
</tr>
<tr>
<td>
<b>Conectar o formulário de login com API de usuários</b>
</td>
<td>
<ul>
<li>
Ao clicar no botão de login no formulário, enviar um request com os dados do formulário para a API Usuário
</li>
<li>
Se o endpoint retornar um status 200, deve-se guardar o token globalmente para utilização no momento da reserva.
</li>
<li>
Se o endpoint retornar um status 200, o usuário deve estar logado.
</li>
<li>
Se o endpoint não retornar status 201, o bloco de login deve exibir uma mensagem de erro no formulário dizendo ao usuário: <b>"Infelizmente não foi possível iniciar a sessão. Por favor, tente novamente mais tarde"</b>
</li>
<li>
<b>Header</b>: Ao estar logado, deve aparecer à direita do header um Olá, [nome de usuário], um avatar do usuário e um link para fechar a sessão devem aparecer alinhados à direita do cabeçalho.
</li>
</ul>
</td>
<td>
"Como usuário registrado, quero poder iniciar sessão para ter acesso a funcionalidades extras".
</td>
</tr>
<tr>
<td>
<b>Acrescentar autenticação com Spring Security à API de usuários</b>
</td>
<td>
<ul>
<li>
Acrescentar um método de autenticação que devolve um JWT.
</li>
<li>
Adicionar e configurar a Spring Security em conjunto com a biblioteca io.jsonwebtoken para implementar a autenticação JWT.
</li>
</ul>
</td>
</tr>
<tr>
<td>
<b>Conectar o botão "Logout" com API dos usuários</b>
</td>
<td>
<ul>
<li>
Ao encerrar a sessão, apagar o token armazenado.
</li>
<li>
No header deverá aparecer <b>Olá, [nome do usuário], avatar do usuário link para fechar a sessão</b> devem desaparecer no cabeçalho.
</li>
<li>
Os botões de login e de registo devem reaparecer também no header.
</li>
</ul>
</td>
<td>
"Como usuário autenticado, quero encerrar a sessão para poder navegar anonimamente no site".
</td>
</tr>
</table>
</details>

> ### Épica: Lista e busca de produtos

<details>
<summary>Ver</summary>
<table>
<tr>
<th colspan="2">Filtro</th>
<th>Requisito</th>
</tr>
<tr>
<td>
<b>
Implementar pesquisa por data na página principal
</b>
</td>
<td>
<ul>
<li>
Adicionar no buscador a possibilidade de pesquisar produtos por gama de datas. Desta forma, as buscas possíveis serão por data e/ou cidade.
</li>
</ul>
</td>
<td>
"Como usuário, quero pesquisar por data para encontrar o produto que corresponda aos meus interesses".
</td>
</tr>
<tr>
<td>
<b>
Implementar filtro por Cidade e intervalo de datas em API de Produtos
</b>
</td>
<td>
<ul>
<li>
Adicionar um método que nos permita filtrar os produtos por cidade e duas datas.
</li>
</ul>
</td>
<td>
"Como usuário, quero pesquisar por cidade e data para encontrar o produto que corresponda aos meus interesses".
</td>
</tr>
</table>
</details>

>### Épica: Reserva de produtos

<details>
<summary>Ver</summary>
<table>
<tr>
<th colspan="2">Funcionalidades</th>
<th>Requisito</th>
</tr>
<tr>
<td>
<b>
Dar funcionalidade ao botão de reserva na página de detalhes do produto
</b>
</td>
<td>
<ul>
<li>
Ao clicar no botão de reserva na página de detalhes do produto, deve verificar se o usuário está logado.
</li>
<b>Não logado</b>: Redirecionar para o bloco de login. O login deve conter um texto no topo indicando que o login é obrigatório, e que em caso de não estar registrado, o usuário deve registrar-se.
<li>
<b>Logado</b>: Redirecionar para a página de reserva do produto a ser reservado.
</li>
</ul>
</td>
<td>
"Como usuário, quero pesquisar por data para encontrar o produto que corresponda aos meus interesses".
</td>
</tr>
<tr>
<td>
<b>
Criar tabela de "reservas" na base de dados
</b>
</td>
<td>
Deve conter como atributos (mínimo):
<ul>
<li>
Id
</li>
<li>
Hora de início da reserva
</li>
<li>
Data inicial da reserva
</li>
<li>
Data final da reserva
</li>
</ul>
Criar uma relação de <i>"muitos para um"</i> com a tabela "produtos".
<br>
Criar a relação de <i>"muitos para um"</i> com a tabela "usuários".
</td>
<td>
"Como usuário autenticado, quero poder fazer reservas para utilizar os produtos".
</td>
</tr>
<tr>
<td>
<b>
Mapear as tabelas de "função", "usuários" e "reservas" para classes do nosso modelo.
</b>
</td>
<td>
<ul>
<li>
Para mapear a relação "muitos para um" das reservas aos usuários, podemos criar uma nova classe, chamada cliente, que herda dos usuários e utiliza esta classe como um atributo na classe Reserva.
</li>
</ul>
</td>
</tr>
<tr>
<td>
<b>
Criar a API de reserva com o JPARepository
</b>
</td>
<td>
Criar API de reserva, implementar JPARepository e criar serviços que nos permita:
<ul>
<li>
Criar uma nova reserva para um produto.
</li>
<li>
Consultar reservas por id do produto.
</li>
</ul>
</td>
</tr>
<tr>
<td>
<b>
Acrescentar segurança com token ao endpoint de criação de reservas
</b>
</td>
<td>
Apenas os pedidos com um token válido poderão criar novas reservas.
</td>
</tr>
<tr>
<td>
<b>
Implementar o template da tela de reserva
</b>
</td>
<td>
<b>Roteamento</b>:
<ul>
<li>
Adicionar a rota (relacionada com o produto) de reserva no router. Por exemplo: /produto/:id/reserva
</li>
</ul>
<b>Template</b>:
<ul>
<li>
<b>Container</b>: Esta página deve ter como container o componente template geral criado na história do usuário do sprint I (HU: Implementação do template geral responsivo).
</li>
<li>
<b>Bloco de título</b>: deve conter o componente do título do produto utilizado na página do produto.
<ul>
<li>Deve exibir o título e a categoria do produto.</li>
<li>À direita deve incluir um ícone para voltar à página do produto quando clicado.</li>
</ul>
</li>
<li>
<b>Bloco de detalhes de reserva</b> (alinhado à direita do formulário) incluindo:
<ul>
<li>Título: "Detalhe de reserva"</li>
<li>Imagem principal do produto</li>
<li>Categoria do produto</li>
<li>Título do produto</li>
<li>Localização do produto</li>
<li>Check-in</li>
<li>Confira</li>
<li>Botão de confirmação da reserva a partir do formulário de reserva.</li>
</ul>
</li>
<li>Deve conter o bloco de política do produto utilizado na página do produto.</li>
</ul>
</td>
<td>
"Como usuário autenticado, gostaria de ver uma página de reserva com os detalhes do produto para poder reservá-lo".
</td>
</tr>
<tr>
<td>
<b>
Implementar o formulário de Reserva
</b>
</td>
<td>
Implementação do formulário no template da tela de reserva
<ul>
<li>
Bloco de inputs:
<ul>
<li>Nome (do tipo="text" e disabled) (dado obtido da API Usuários)</li>
<li>Sobrenome (do tipo="text" e disabled) (dado obtido da API Usuários)</li>
<li>Email (do tipo="email" e disabled) (dado obtido da API Usuários)</li>
<li>Cidade (do tipo="text" / required) (dados introduzidos pelo usuário).</li>
</ul>
</li>
<li>
Bloco horário de chegada:
<ul>
<li>
Um label que diga "Indique a sua hora prevista de chegada".
</li>
<li>
Select com 25 opções. 24 opções de tempo, de 0 a 23 e um default value que diga "Selecionar hora" / obrigatório).
</li>
</ul>
</li>
<li>
Botão submit:
<ul>
<li>
Ver botão em task (Implementação básica do template de tela de reserva).
</li>
</ul>
</li>
</ul>
</td>
<td>
"Como usuário autenticado, quero poder introduzir os meus dados de reserva na página de reservas, a fim de confirmar a minha reserva".
</td>
<tr>
<td>
<b>
Implementar submit do formulário de reservas
</b>
</td>
<td>
<ul>
<li>
<b>Validações</b>
<ul>
<li>O select deve ter um valor de hora selecionado (obrigatório).</li>
<li>O calendário deve ter um intervalo de datas selecionado (obrigatório).</li>
</ul>
</li>
<li>
<b>Submit</b>
<ul>
<li>
Ao dar submit, os dados do formulário de reserva devem ser enviados para a API de reserva.
</li>
<li>
No caso da API retornar status 201, redirecionar para uma página que apresente uma mensagem de reserva bem sucedida.
</li>
<li>No caso da API não retornar o status 201, deve exibir uma mensagem de erro no formulário que diga ao usuário: "Infelizmente, a reserva não pôde ser completada. Por favor, tente novamente mais tarde".
</li>
</ul>
</li>
</ul>
</td>
</tr>
<tr>
<td>
<b>Implementar um calendário interativo na página de reservas</b>
</td>
<td>
Bloco calendário:
<ul>
<li>Adicionar um calendário duplo no qual se possa selecionar um intervalo de reserva.</li>
</ul>
Eventos do calendário:
<ul>
<li>
O calendário duplo deve permitir a movimentação entre meses diferentes.
</li>
<li>Datas anteriores devem ser desabilitadas.</li>
<li>Datas já reservadas devem ser desabilitadas.</li>
<li>Você deve ser capaz de selecionar um intervalo de datas para a reserva.</li>
<li>Ao selecionar um intervalo, o mesmo deve ser impresso no texto do formulário referente à data.</li>
</ul>
</td>
<td>"Como usuário autenticado, quero poder ver e selecionar as datas disponíveis na página de reservas para definir o meu período de reserva".</td>
</tr>
<tr>
<td><b>Implementar o template de reserva bem sucedida</b></td>
<td>
<b>Contêiner</b>
<ul>
<li>Esta página deve conter o componente de template geral criado na tarefa Sprint 1: Implementação do template geral responsivo.</li>
</ul>
<b>Bloco com conteúdo específico:</b>
<ul>
<li>
Deve conter um ícone.
</li>
<li>
Um link de retorno à página home.
</li>
<li>
Uma mensagem de sucesso.
</li>
</ul>
</td>
<td>"Como usuário autenticado, quero ver uma confirmação de reserva logo após reservá-la, para ter a certeza de ter completado o processo".</td>
</tr>
</table>
</details>

>### Épica: Outros tarefas

<details>
<summary>Ver</summary>
<table>
<tr>
<th colspan="2"><b>Infra/Testing</b></th>
</tr>

<tr>
<td>
<b>Infraestrutura - Deploy na AWS</b>
</td>
<td>
Implementação do template geral responsivo
<ul>
<li>
Crie a implantação manual de front-end e back-end no servidor web EC2 na AWS.
</li>
<li>
(Opcional - Modo A) Implante em uma instância do Docker no EC2.
</li>
<li>
(Opcional - Modo B) Use o CodeDeploy na AWS conectado ao Gitlab para fazer a implantação automatizada.
</li>
</ul>
Crie tabelas no RDS na AWS.
<ul>
<li>
Acesse o banco de dados na AWS através do Workbench e crie as tabelas e relacionamentos.
</li>
<li>
Faça a conexão do banco de dados ao servidor web (EC2).
</li>
</ul>
Adicione imagens no AWS Bucket para gerar o URL
<ul>
<li>
Carregue as imagens no bucket, que irá gerar uma URL, adicione essas URLs ao banco de dados.
</li>
</ul>
</td>
</tr>
<tr>
<td>
<b>Testing - Implementar testes manuais</b>
</td>
<td>
<ul>
<li>
Realizar testes exploratórios nas páginas de reserva e confirmação de reserva. Verifique e valide as funcionalidades e a interface do usuário. 
</li>
<li>
Adicionar casos de teste manuais nas funcionalidades a serem entregues neste sprint e executar um ciclo de teste selecionando os casos de teste necessários, levando em consideração para não duplicar o esforço (evitando executar aqueles que já estão automatizados).
</li>
</ul>
</td>
</tr>
<tr>
<td>
<b>Testing - Implementar testes automatizados</b>
</td>
<td>
<ul>
<li>
Adicionar os testes necessários para a API de produtos, reservas e usuários à coleção previamente criada no Postman (scripts de testes automatizados). 
</li>
<li>
Criar e executar os testes unitários das páginas de reserva e confirmação de reserva através do JUnit.
</li>
<li>
Opcional: Adicionar/criar um script relacionado ao processo de reserva nos testes de regressão automatizados criados com Selenium Web Driver.
</li>
</ul>
</td>
</tr>
</table>
</details>
