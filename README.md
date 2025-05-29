# Projeto Integrador – Cloud Developing 2025/1

> CRUD simples + API Gateway + Lambda /report + RDS

**Grupo**:

1. 10418084 - Andrea Cecilia Garcia - Aws Academy
2. 10419181 - Caroline Begiato Cabral - Front-end, documentação
3. 10418069 - Mariana Okamoto - Aws Academy
4. 10417520 - Renata Freire Ardito - Back-end, documentação

## 1. Visão geral
Imagine ter sua própria estante virtual, acessível de qualquer lugar. Inspirado no Skoob, nosso sistema CRUD na AWS permite:

 - Inserir livros com título, editora, número de páginas e gênero

 - Navegar entre os que você já leu e os que ainda quer ler

 - Atualizar o status conforme avança na leitura

 - Remover da lista títulos que não fazem mais sentido pra você

Tudo isso sem complicação: um fluxo simples para gerenciar sua coleção de livros na nuvem.

## 2. Arquitetura

| Camada  |            Serviço            |                   Descrição                   |
|---------|-------------------------------|-----------------------------------------------|
| Backend | EC2 + Docker                  | Spring Boot                                   |
| Banco   | Amazon RDS                    | PostgreSQL                                    |
| Gateway | Amazon API Gateway            | Rotas CRUD → ECS · `/report` → Lambda         |
| Função  | AWS Lambda                    | Consome a API gateway, gera estatísticas JSON |
 
## 3. Como rodar localmente

```bash
# docker logs -f bookcloud-container (envia informações do container)
# docker ps -a (mostra lista dos containers em execução e parados)
# docker start bookcloud-container (reiniciar docker parado)

# Lambda JSON https://y5il6o92ya.execute-api.us-east-1.amazonaws.com/prod/report
# Pagina Home http://<IP publico da EC2>:8080/home
