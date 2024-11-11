# Projeto OMS

`Microserviços responsáveis pelo gerenciamento de pedidos`

# Pré Requisitos

Para que seja possível rodar essa aplicação é necessário atender alguns requisitos básicos.

- JDK 17 LTS
- Gradle 8.8+
- PostgreSQL 14+
- Apache Kafka

# Compilando para IDEAs como IntelliJ

Assim como todo projeto *Gradle*, é necessário primeiramente realizarmos a geração dos fontes (Para cada projeto). Conforme o exemplo abaixo:

```bash
./gradlew clean build
```

# Compilando e inicializando com Docker

Executar os comandos docker abaixo (Executar no diretorio raiz, antes dos 2 projetos)

```bash
docker compose build
docker compose up
```
# Documentação Swagger

É possivel acessar os endpoints disponiveis para visualização no seguinte endereço, depois que o container estiver rodando

http://localhost:8080/swagger-ui/index.html
http://localhost:8081/swagger-ui/index.html

# Executando e testando com Postman

Existe um arquivo postman 'OMS.postman_collection.json' na raiz do projeto com todas as operações disponiveis
Além disso, há um diagrama com a arquitetura do projeto

# Arquivos Uteis Na raiz

- OMS.postman_collection.json
- Arquitetura.drawio
- Arquitetura.png

# cURLs de exemplo

- Importar Pedido
```bash
curl --location 'http://localhost:8080/order/import' \
--header 'Content-Type: application/json' \
--data '{
  "order-id": "38d954d9-fe35-4531-9ec9-809282aee963",
  "store": "Boteco do Denis",
  "status": "sended",
  "origin_system": "application",
  "pickup": "delivery",
  "items": [
    {
      "id": 1,
      "quantity": 2
    },
    {
      "id": 2,
      "quantity": 1
    },
    {
      "id": 16,
      "quantity": 2
    },
    {
      "id": 21,
      "quantity": 3
    }
  ]
}'
```
- Consultar Pedido importado
```bash
curl --location 'http://localhost:8081/order/38d954d9-fe35-4531-9ec9-809282aee963'
```