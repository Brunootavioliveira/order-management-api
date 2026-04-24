# CheckPoint1 - Order Management API

REST API para gerenciamento de pedidos, desenvolvida com Spring Boot e arquitetura em camadas.

---

## Tecnologias

| Tecnologia | Versao |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.5 |
| Spring Data JPA | - |
| Spring Web MVC | - |
| Spring Validation | - |
| Lombok | - |
| H2 Database | runtime |
| Maven | wrapper incluso |

---

## Estrutura do Projeto

```
src/
└── main/
    ├── java/br/com/fiap/CheckPoint1/
    │   ├── CheckPoint1Application.java   # Classe principal
    │   ├── controller/
    │   │   └── OrderController.java      # Endpoints REST
    │   ├── model/
    │   │   └── OrderModel.java           # Entidade JPA
    │   ├── repository/
    │   │   └── OrderRepository.java      # Acesso a dados
    │   └── service/
    │       └── OrderService.java         # Regras de negocio
    └── resources/
        └── application.yaml              # Configuracoes da aplicacao
```

---

## Configuracao

A aplicacao utiliza banco de dados H2 em modo arquivo, sem necessidade de instalacao de banco externo.

**Porta:** `8085`  
**Banco:** H2 File (`~/testedb`)  
**Console H2:** `http://localhost:8085/h2-console`

Credenciais do H2:
- **URL JDBC:** `jdbc:h2:file:~/testedb`
- **Usuario:** `sa`
- **Senha:** `password`

---

## Como Executar

**Pre-requisitos:** Java 21 instalado.

```bash
# Clonar o repositorio
git clone <url-do-repositorio>
cd CheckPoint1

# Executar com Maven Wrapper
./mvnw spring-boot:run        # Linux / macOS
mvnw.cmd spring-boot:run      # Windows
```

A aplicacao estara disponivel em `http://localhost:8085`.

---

## Endpoints

Base URL: `/orders`

### Criar Pedido

```
POST /orders
Content-Type: application/json
```

Corpo da requisicao:

```json
{
  "clientName": "Joao Silva",
  "totalValue": 150.00
}
```

Respostas: `201 Created` | `400 Bad Request`

---

### Listar Todos os Pedidos

```
GET /orders
```

Resposta: `200 OK` com lista de pedidos.

---

### Buscar Pedido por ID

```
GET /orders/{id}
```

Respostas: `200 OK` | `404 Not Found`

---

### Atualizar Pedido

```
PUT /orders/{id}
Content-Type: application/json
```

Corpo da requisicao:

```json
{
  "clientName": "Maria Souza",
  "totalValue": 200.00
}
```

Respostas: `200 OK` | `404 Not Found`

---

### Excluir Pedido

```
DELETE /orders/{id}
```

Respostas: `204 No Content` | `404 Not Found`

---

## Modelo de Dados

**Entidade:** `OrderModel` | **Tabela:** `Pedidos`

| Campo | Tipo | Restricoes |
|---|---|---|
| `id` | Long | PK, auto-incremento |
| `clientName` | String | Obrigatorio |
| `orderDate` | LocalDate | Preenchido automaticamente na criacao |
| `totalValue` | BigDecimal | Nao negativo, positivo |

---

## Validacoes

- `clientName`: campo obrigatorio; retorna `400` se ausente ou vazio.
- `totalValue`: deve ser um valor positivo e nao negativo; retorna `400` se invalido.
- `orderDate`: preenchido automaticamente com a data atual via `@PrePersist` caso nao seja informado.

---

## Arquitetura

O projeto segue o padrao em tres camadas:

**Controller** recebe as requisicoes HTTP, delega ao Service e retorna as respostas adequadas com os status codes apropriados.

**Service** centraliza as regras de negocio, lanca `EntityNotFoundException` para recursos nao encontrados e `IllegalArgumentException` para entradas invalidas.

**Repository** extende `JpaRepository`, fornecendo as operacoes CRUD sem implementacao adicional.

---

## Instituicao

Desenvolvido como atividade avaliativa para a **FIAP - Faculdade de Informatica e Administracao Paulista**.

## Integrantes

Bruno Otavio Oliveira  
Software Engineering Student - FIAP - RM:556196

Guilherme Flores Pereira de Almeida  
Software Engineering Student - FIAP - RM:554948

Luiz Fernando de Aragão  
Software Engineering Student - FIAP - RM:555561

Marcello Moreira  
Software Engineering Student - FIAP - RM:557531

Leonardo Gonçalves Novaes  
Software Engineering Student - FIAP - RM:554807
