# Projeto Integrador – Cloud Developing 2025/1

> CRUD simples + API Gateway + Lambda /report + RDS

**Grupo**:

1. 10418084 - Andrea Cecilia Garcia - Aws Academy
2. 10419181 - Caroline Begiato Cabral - Front-end, documentação
3. 10418069 - Mariana Okamoto - Aws Academy
4. 10417520 - Renata Freire Ardito - Back-end, documentação

## 1. Visão geral
O objetivo deste projeto é desenvolver um sistema CRUD simples hospedado na AWS, com foco na correta configuração e integração de serviços em nuvem. 

O tema escolhido para esse projeto foi um site para catalogar livros, onde é possível adicionar um livro, editora, quantidade de páginas e gênero, atualizar status do livro (lido ou quero ler), também é possivel apagar o livro do historico. 

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
