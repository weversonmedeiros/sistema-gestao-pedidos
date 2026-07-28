INSERT INTO cliente (nome, email, cpf) VALUES ('Ana Silva', 'ana@email.com', '111.222.333-44');

INSERT INTO produto (nome, descricao, preco, quantidade_estoque) VALUES 
('Teclado Mecânico', 'Teclado RGB switch blue', 350.00, 10),
('Mouse Gamer', 'Mouse 10000 DPI', 150.00, 25);

INSERT INTO pedido (cliente_id, status, valor_total) VALUES (1, 'PENDENTE', 850.00);

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES 
(1, 1, 2, 350.00), 
(1, 2, 1, 150.00);
