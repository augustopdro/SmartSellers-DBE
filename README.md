# Smartsellers: Elevando a Experiência do Cliente e Aumentando a Conversão de Vendas 

Bem-vindo à Smartsellers, a sua parceira em soluções avançadas de chatbot baseadas na poderosa API do ChatGPT. Nosso objetivo é fornecer às empresas um chatbot inteligente e personalizado, focado em empresas que oferecem soluções, com o propósito de aprimorar a experiência do cliente e impulsionar a conversão de vendas.
Integrantes do grupo:

-  RM 93442 - Gabriel Hitoshi Furone Yokogawa
-  RM 93150 - Guilherme Martins Nascimento
-  RM 93205 - Luis Fernando Nascimento de Oliveira
-  RM 94990 - Pedro Augusto Pereira Viana   
-  RM 95800 - Yasmin Cabral Dias


# Visão Geral

Na Smartsellers, acreditamos que a chave para o sucesso nas vendas está na interação eficaz entre as empresas e seus clientes. Com base nessa premissa, desenvolvemos uma solução inovadora que combina a inteligência artificial do ChatGPT com a análise de dados de navegação do usuário. Dessa forma, somos capazes de criar um chatbot altamente inteligente e automatizado, que aborda proativamente os clientes em potencial, oferecendo uma experiência personalizada e relevante.

## Deploy

Para executar a aplicação, basta clonar o repositório em sua máquina e executar o SmartSellersApplication

## Teste os endpoints por aqui
- Postman: https://www.postman.com/altimetry-astronaut-37339768/workspace/alimentech/collection/23202593-1f7b59a0-b414-465c-abaa-9aaa09dabb14?action=share&creator=23202593

# Inteligência Artificial a Serviço das Vendas

Nosso chatbot vai além de simplesmente responder perguntas e fornecer informações básicas. Ele é capaz de iniciar e manter conversas envolventes com os clientes, compreendendo suas necessidades e explicando como a solução oferecida pela empresa pode atender a essas necessidades específicas.

Através da análise dos dados de navegação do usuário, nossa inteligência artificial identifica padrões e comportamentos que indicam um maior interesse ou intenção de compra por parte do cliente. Assim, nosso modelo de inteligência artificial ativará o chatbot no momento oportuno, permitindo que ele inicie uma conversa significativa e ofereça informações relevantes sobre a solução.

# Benefícios da Solução Smartsellers

1. **Melhoria na Experiência do Cliente:** Nosso chatbot inteligente é capaz de compreender e responder às perguntas do cliente de forma ágil e precisa. Isso resulta em interações mais satisfatórias e personalizadas, proporcionando uma experiência agradável e fortalecendo o relacionamento com a empresa.

2. **Aumento na Conversão de Vendas:** Ao abordar proativamente os clientes com informações relevantes sobre a solução, nosso chatbot aumenta as chances de conversão de vendas. Ao explicar como a solução pode ajudar o cliente em seu contexto específico, o chatbot oferece uma abordagem personalizada, auxiliando na tomada de decisão do cliente.

3. **Automatização de Vendas:** Com nosso chatbot, as empresas podem contar com um vendedor virtual automatizado, disponível 24 horas por dia, 7 dias por semana. Isso permite que a empresa atenda aos clientes em tempo real, mesmo fora do horário comercial, maximizando as oportunidades de vendas e fornecendo suporte contínuo.

4. **Integração Perfeita:** A solução Smartsellers pode ser facilmente integrada aos sistemas existentes da empresa, garantindo uma implementação rápida e eficiente. Além disso, nosso chatbot pode ser personalizado de acordo com as necessidades e a identidade visual da empresa, oferecendo uma experiência de marca consistente.

# Entre em contato
Entre em contato conosco pelo e-mail **smartsellers.contato@gmail.com** para saber mais sobre como podemos ajudar a impulsionar seu negócio rumo ao sucesso!

# Arquitetura

<img align="center" alt="Diagrama-UML" src="https://github.com/augustopdro/SmartSellers-DBE/blob/master/ArquiteturaRecursos.jpg?raw=true">

---

## Endpoints
- Usuário
    - [Cadastrar](#cadastrar)
    - [Login](#login)
- Produto
    - [Registrar](#registrar)
    - [Atualizar registro](#atualizar-registro)
    - [Deletar Registro](#deletar-registro)


---

## Cadastrar
`POST` /api/usuario/cadastrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | sim | é o nome do usuário, deve respeitar o Regex(^[a-zA-Z]{3,}$)
| email | string | sim | é o email empresarial, deve respeitar o ReGex(^[A-Za-z0-9+_.-]+@(.+)$)
| telefone | string | sim | é o telefone do usuário, deve ter 11 caracteres


**Exemplo de corpo do request**
```js
{
	"nome": "Pedro Augusto",
	"email": "ibm@gmail.com",
	"telefone": "1140028922"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 400 | Erro na requisição

---

---

## Login
`POST` /api/usuario/login

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| codigoAcesso | string | sim | é o código enviado ao email do usuário, que permite logar na plataforma

**Exemplo de corpo do request**
```js
{
	"codigoAcesso": "A29d23XX"
}
```

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
|Id | string | Id do usuario que identifica o usuário no sistema

```js
1010
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario validado com sucesso
| 401 | Código de acesso incorreto

---

---

## Registrar 
`POST` /api/produto/{userId}/registrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | String | sim | é o nome do produto
| descricao | String | sim | é uma descrição que explica exatamente como funciona o produto
| preco | Double | sim | é o preço do produto


**Exemplo de corpo do request**
```js
{
	"nome": "godfather action figure",
	"descricao": "Em homenagem ao icônico filme americano de máfia, The GodFather, a Action Figure lançou um colecionável especial em homenagem a Vito Corleone.",
    "preco": "2500.0"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Produto cadastrado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

`GET` /api/produto/{userId}/produtos

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
| nome | String | é o nome do produto
| descricao | String | é uma descrição que explica exatamente como funciona o produto
| preco | Double | é o preço do produto


```js
{
	"nome": "godfather action figure",
	"descricao": "Em homenagem ao icônico filme americano de máfia, The GodFather, a Action Figure lançou um colecionável especial em homenagem a Vito Corleone.",
    "preco": "2500.0"

}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Produtos recuperados com sucesso
| 404 | Usuario não encontrado
| 404 | Produtos não encontrados
| 400 | Erro na requisição

---

---

## Atualizar Registro
`PUT` /api/produto/{registroId}

**Exemplo de corpo do request**
```js
{
	"nome": "godfather action figure",
	"descricao": "Em homenagem ao icônico filme americano de máfia, The GodFather, a Action Figure lançou um colecionável especial em homenagem a Vito Corleone.",
    "preco": "4500.0",
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Registro atualizado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

## Deletar Registro
`DELETE` /api/sono/{userId}/deletar/{registroId}

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 204 | Objeto deletado com sucesso
| 404 | Usuario não encontrado
| 404 | Objeto não encontrado

---
