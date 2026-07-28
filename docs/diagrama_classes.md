classDiagram
    class Cliente {
        +Long id
        +String nome
        +String email
        +String cpf
    }

    class Produto {
        +Long id
        +String nome
        +String descricao
        +BigDecimal preco
        +Integer quantidadeEstoque
    }

    class StatusPedido {
        <<enumeration>>
        PENDENTE
        PAGO
        ENVIADO
        CANCELADO
    }

    class Pedido {
        +Long id
        +LocalDateTime dataPedido
        +StatusPedido status
        +BigDecimal valorTotal
        +calcularTotal()
    }

    class ItemPedido {
        +Long id
        +Integer quantidade
        +BigDecimal precoUnitario
        +calcularSubtotal()
    }

    Cliente "1" -- "0..*" Pedido : faz >
    Pedido "1" *-- "1..*" ItemPedido : contem >
    Produto "1" -- "0..*" ItemPedido : pertence a >
    Pedido --> StatusPedido : possui >