-- Consulta para gerar o relatório do pedido com todos os detalhes
SELECT 
    c.nome AS cliente,
    p.id AS numero_pedido,
    prod.nome AS produto,
    ip.quantidade,
    ip.preco_unitario,
    (ip.quantidade * ip.preco_unitario) AS subtotal
FROM pedido p
INNER JOIN cliente c ON p.cliente_id = c.id
INNER JOIN item_pedido ip ON p.id = ip.pedido_id
INNER JOIN produto prod ON ip.produto_id = prod.id;
