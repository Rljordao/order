# Order

Serviço responsável pelo processamento de pedidos. Este projeto é baseado no Spring Boot, utilizando práticas modernas de desenvolvimento, como APIs RESTful, OpenAPI, integração com Kafka e persistência de dados no SQL Server.

---

## 📚 Tecnologias Utilizadas

- **Java 21**: Linguagem principal para a construção do projeto.
- **Spring Boot 3.3.5**: Framework para desenvolvimento de aplicações Java com configuração mínima.
    - **Spring Boot Starter Web**: Para construção de APIs RESTful.
    - **Spring Boot Starter Data JPA**: Integração com o banco de dados SQL Server.
    - **Spring Boot Starter Validation**: Validação de dados de entrada.
    - **Spring Cloud Stream Kafka**: Comunicação assíncrona via Apache Kafka.
    - **Spring Retry**: Implementação de lógica de tentativas automáticas.
    - **Springdoc OpenAPI**: Geração de documentação interativa (Swagger UI).
- **Microsoft SQL Server**: Banco de dados utilizado para persistência.
- **MapStruct 1.5.5**: Para conversão entre DTOs e entidades.
- **Jacoco**: Ferramenta de análise de cobertura de testes.
- **Mockito e JUnit 5**: Frameworks para criação de testes unitários.
- **Docker**: Para criar ambientes isolados de execução.
- **Maven**: Gerenciador de dependências e build.

---

## 🏗️ Configuração e Execução

### Pré-requisitos

1. **Docker**: Para rodar o banco de dados e kafka localmente.
2. **Java 21**: Para compilar e executar o projeto.
3. **Maven**: Para build e gerenciamento de dependências.
4**Docker Compose**: Para gerenciar a configuração de múltiplos containers.

---


## Executando o Projeto com Docker
### 🚀 Passo a Passo
1. **Clone o repositório**:

   git clone https://github.com/Rljordao/order.git
   cd order-service

### Construa as imagens Docker e suba os contêineres:
   docker-compose up --build

### Acesse a aplicação:
1. **API:** http://localhost:8081/swagger-ui/index.html#
2. **Kafdrop (Administração Kafka)::** http://localhost:19000

### Documentação da API
A documentação da API está disponível na interface Swagger gerada automaticamente pelo **Springdoc OpenAPI**. Você pode acessá-la em:

- [Swagger UI](http://localhost:8081/swagger-ui/index.html#)

O Swagger permite explorar todos os endpoints da API de forma interativa.

### Docker Compose
O `docker-compose.yml` define a configuração de múltiplos serviços necessários para o projeto, incluindo:

- **api-order**: O serviço principal, onde o código do Order será executado.
- **sqlserver**: O banco de dados Microsoft SQL Server que armazena os dados do pedido.
- **kafka e zookeeper**: A infraestrutura de mensageria que permite a comunicação assíncrona entre os microsserviços.
- **kafdrop**: A interface gráfica para visualização e gerenciamento de tópicos no Kafka.

Ele também gerencia a configuração das redes, volumes e dependências entre os serviços.


### Dockerfile
O Dockerfile é utilizado para criar a imagem do Order. Ele realiza os seguintes passos:

- **Imagem Base**: Utiliza uma imagem otimizada do Java 21.
- **Copia o código-fonte**: Os arquivos do código são copiados para dentro do container.
- **Instala as dependências**: Executa o Maven para instalar as dependências do projeto.
- **Compila e Executa**: Executa a aplicação usando o Maven, configurando o ambiente para rodar no container.

O arquivo está configurado para expor a aplicação na porta 8081.

### Testes
O projeto inclui testes unitários e de integração utilizando **JUnit 5** e **Mockito**. Para rodar os testes, basta executar o comando:

`mvn test`

---

## 📡 Integração com Kafka

### 📌 Visão Geral do Fluxo de Mensagens

1. **Producer**: O Order envia eventos relacionados ao processamento de pedidos para o Kafka, utilizando o produtor configurado.
2. **Consumer**: O Order consome os eventos de pedidos de outros sistemas, processando-os de forma assíncrona. Se o processamento falhar, a mensagem será colocada na **Dead Letter Queue (DLQ)**.
3. **Retries e Backoff**: Caso o consumidor não consiga processar a mensagem inicialmente, ele tentará novamente de acordo com a configuração de **retry** e **backoff**.
