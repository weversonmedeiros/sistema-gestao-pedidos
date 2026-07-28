# 📋 Documentação de Requisitos e Regras de Negócio

## Épicos e Histórias de Usuário (User Stories)

**Épico 1: Gestão de Clientes e Produtos**
* **US01:** Como administrador, quero cadastrar clientes com Nome, Email e CPF únicos, para evitar duplicidade na base.
* **US02:** Como administrador, quero cadastrar produtos com Nome, Preço e Quantidade em Estoque, para disponibilizá-los para venda.

**Épico 2: Processamento de Pedidos**
* **US03:** Como sistema, devo permitir que um pedido contenha um ou mais itens (produtos).
* **US04:** Como sistema, ao adicionar um item ao pedido, devo capturar o preço atual do produto e salvar no item, para que alterações futuras no preço do produto não afetem pedidos antigos.
* **US05:** Como sistema, devo calcular o valor total do pedido somando o subtotal de todos os seus itens.

## Regras de Restrição
1. Não é possível deletar um cliente que possua pedidos atrelados ao seu histórico.
2. Um pedido recém-criado deve nascer obrigatoriamente com o status `PENDENTE`.
