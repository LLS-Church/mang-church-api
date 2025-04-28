# Documentação - mang-church-api

## Sobre o Projeto
O **mang-church-api** é uma API desenvolvida para gerenciar dados relacionados a igrejas, suas entidades e integrações.

## Como executar o projeto localmente

### 1. Requisitos
- Java 17 ou superior instalado
- Docker
- IDE ou terminal configurado para executar aplicações Java Spring Boot

### 2. Configurações necessárias

Antes de rodar a aplicação, é necessário:

- Adicionar a seguinte **VM option** na sua execução:

  ```
  -Dspring.profiles.active=local
  ```

Essa configuração garante que o projeto utilizará o arquivo de propriedades locais específico (`application-local.properties`) para rodar em ambiente de desenvolvimento.

### 3. Subir os serviços de dependência (Docker)

Antes de iniciar a aplicação, é obrigatório subir os containers Docker para que o ambiente de observabilidade esteja disponível.

Execute o comando:

```bash
docker-compose up -d
```

Isso irá iniciar os serviços necessários, incluindo:
- **Prometheus** (monitoramento) na porta **9090**
- **Grafana** (dashboards e métricas) na porta **3000**

---

## Acesso aos serviços

- **Grafana**: [http://localhost:3000](http://localhost:3000)
- **Prometheus**: [http://localhost:9090](http://localhost:9090)

Usuário e senha padrão do Grafana (caso necessário):
- **Usuário:** admin
- **Senha:** admin

---

## Observações importantes
- Certifique-se que as portas 3000 e 9090 estão liberadas no seu ambiente local antes de subir o Docker.
- Caso precise alterar configurações adicionais, ajuste os arquivos `docker-compose.yml` ou os arquivos de configuração do Spring Boot conforme sua necessidade.

