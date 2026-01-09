# Hexagonal Architecture - Customer API

Aplicação de exemplo demonstrando a implementação da **Arquitetura Hexagonal** (Ports and Adapters) com Spring Boot.

## 🏗️ Arquitetura

Este projeto segue os princípios da Arquitetura Hexagonal:

```
┌─────────────────────────────────────────────────────────────────┐
│                         ADAPTERS                                │
│  ┌─────────────────────┐         ┌─────────────────────────┐   │
│  │    Adapters IN      │         │     Adapters OUT        │   │
│  │  ┌───────────────┐  │         │  ┌─────────────────┐    │   │
│  │  │  Controller   │  │         │  │   Repository    │    │   │
│  │  │  (REST API)   │  │         │  │   (MongoDB)     │    │   │
│  │  └───────────────┘  │         │  └─────────────────┘    │   │
│  │  ┌───────────────┐  │         │  ┌─────────────────┐    │   │
│  │  │   Consumer    │  │         │  │  Kafka Producer │    │   │
│  │  │   (Kafka)     │  │         │  └─────────────────┘    │   │
│  │  └───────────────┘  │         │  ┌─────────────────┐    │   │
│  └─────────┬───────────┘         │  │  Feign Client   │    │   │
│            │                     │  │  (Address API)  │    │   │
│            │                     │  └─────────────────┘    │   │
│            │                     └───────────┬─────────────┘   │
│            ▼                                 ▲                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                    PORTS (Interfaces)                   │   │
│  │   ┌───────────────────┐    ┌───────────────────────┐    │   │
│  │   │   Input Ports     │    │    Output Ports       │    │   │
│  │   └─────────┬─────────┘    └───────────┬───────────┘    │   │
│  └─────────────┼──────────────────────────┼────────────────┘   │
│                ▼                          ▲                    │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                   APPLICATION CORE                      │   │
│  │   ┌─────────────────┐      ┌─────────────────────┐      │   │
│  │   │    Use Cases    │      │      Domain         │      │   │
│  │   │                 │      │   (Customer,        │      │   │
│  │   │ InsertCustomer  │      │    Address)         │      │   │
│  │   │ FindCustomer    │      │                     │      │   │
│  │   │ UpdateCustomer  │      │                     │      │   │
│  │   │ DeleteCustomer  │      │                     │      │   │
│  │   └─────────────────┘      └─────────────────────┘      │   │
│  └─────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

## 🛠️ Tecnologias

| Tecnologia | Versão |
|------------|--------|
| Java | 21 |
| Spring Boot | 3.4.1 |
| Spring Data MongoDB | - |
| Spring Kafka | - |
| Spring Cloud OpenFeign | 2024.0.0 |
| MapStruct | 1.5.5 |
| Lombok | - |
| Gradle | - |

## 📋 Pré-requisitos

- Java 21+
- Docker e Docker Compose
- Gradle (ou use o wrapper `./gradlew`)

## 🚀 Como Executar

### 1. Subir a infraestrutura (MongoDB, Kafka, Zookeeper, Kafdrop)

```bash
cd docker-local
docker-compose up -d
```

Isso irá iniciar:
- **Zookeeper**: porta 2181
- **Kafka**: porta 9092
- **Kafdrop** (UI para Kafka): http://localhost:9000
- **MongoDB**: porta 27017
- **Mongo Express** (UI para MongoDB): http://localhost:8083

### 2. Executar a aplicação

```bash
./gradlew bootRun
```

A aplicação estará disponível em: **http://localhost:8081**

## 📡 API Endpoints

### Customer

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/v1/customers` | Criar um novo customer |
| `GET` | `/api/v1/customers/{id}` | Buscar customer por ID |
| `PUT` | `/api/v1/customers/{id}` | Atualizar customer |
| `DELETE` | `/api/v1/customers/{id}` | Deletar customer |

### Exemplo de Request

**POST /api/v1/customers**

```json
{
  "name": "João Silva",
  "cpf": "12345678900",
  "zipCode": "38400000"
}
```

**Response GET /api/v1/customers/{id}**

```json
{
  "id": "6960400d26c42f56c451a763",
  "name": "João Silva",
  "address": {
    "street": "Rua Exemplo",
    "city": "Uberlândia",
    "state": "MG"
  },
  "cpf": "12345678900",
  "isValidCpf": true
}
```

## 📨 Kafka

### Tópicos

| Tópico | Descrição |
|--------|-----------|
| `tp-cpf-validation` | Envia CPF para validação (Producer) |
| `tp-cpf-validated` | Recebe resultado da validação de CPF (Consumer) |

### Fluxo de Validação de CPF

1. Ao criar um customer, a aplicação envia uma mensagem para o tópico `tp-cpf-validation`
2. Um serviço externo (simulado) valida o CPF
3. O resultado é enviado para o tópico `tp-cpf-validated`
4. A aplicação consome a mensagem e atualiza o customer com `isValidCpf = true/false`

### Produzir Mensagem no Kafka (Teste Manual)

Para simular o retorno da validação de CPF, execute o comando abaixo:

```bash
echo '{"id":"ID_DO_CUSTOMER","name":"teste","zipCode":"38400000","cpf":"12345678900","isValidCpf":true}' | docker exec -i docker-local-kafka-1 /bin/kafka-console-producer --broker-list localhost:29092 --topic tp-cpf-validated
```

**Estrutura da mensagem:**

```json
{
  "id": "6960400d26c42f56c451a763",
  "name": "teste",
  "zipCode": "38400000",
  "cpf": "12345678900",
  "isValidCpf": true
}
```

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | String | ID do customer existente no MongoDB |
| `name` | String | Nome do customer |
| `zipCode` | String | CEP para buscar endereço |
| `cpf` | String | CPF do customer |
| `isValidCpf` | Boolean | Resultado da validação do CPF |

### Acessar Kafdrop

Acesse http://localhost:9000 para visualizar os tópicos e mensagens do Kafka.

## 🗄️ MongoDB

### Configuração

```yaml
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      authentication-database: admin
      username: root
      password: example
      database: hexagonal
```

### Acessar Mongo Express

Acesse http://localhost:8083 para gerenciar o MongoDB via interface web.

- **Usuário**: root
- **Senha**: example

## 📁 Estrutura do Projeto

```
src/main/java/dev/felipeazsantos/hexagonal/
├── adapters/
│   ├── in/
│   │   ├── consumer/          # Kafka Consumer
│   │   │   ├── mapper/
│   │   │   └── message/
│   │   └── controller/        # REST Controllers
│   │       ├── mapper/
│   │       ├── request/
│   │       └── response/
│   └── out/
│       ├── client/            # Feign Clients
│       │   ├── mapper/
│       │   └── response/
│       └── repository/        # MongoDB Repository
│           ├── entity/
│           └── mapper/
├── application/
│   ├── core/
│   │   ├── domain/            # Entidades de domínio
│   │   └── usecase/           # Casos de uso
│   └── ports/
│       ├── in/                # Input Ports (interfaces)
│       └── out/               # Output Ports (interfaces)
├── config/                    # Configurações Spring
└── HexagonalApplication.java
```

## 🧪 Testes

```bash
./gradlew test
```

## 📝 Licença

Este projeto é apenas para fins de estudo e demonstração da Arquitetura Hexagonal.
