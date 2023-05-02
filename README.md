# SmartSellers

Uma API para o sistema de chatbots inteligentes.

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
| quantidade | Integer | sim | é a quantidade disponível do produto

**Exemplo de corpo do request**
```js
{
	"nome": "godfather action figure",
	"descricao": "Em homenagem ao icônico filme americano de máfia, The GodFather, a Action Figure lançou um colecionável especial em homenagem a Vito Corleone.",
  "preco": "2500.0",
  "quantidade": "12"
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
| quantidade | Integer | é a quantidade disponível do produto

```js
{
	"nome": "godfather action figure",
	"descricao": "Em homenagem ao icônico filme americano de máfia, The GodFather, a Action Figure lançou um colecionável especial em homenagem a Vito Corleone.",
  "preco": "2500.0",
  "quantidade": "12"
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
  "quantidade": "8"
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
