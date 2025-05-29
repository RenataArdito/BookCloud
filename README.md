# Projeto Integrador – Cloud Developing 2025/1

> CRUD simples + API Gateway + Lambda /report + RDS

**Grupo**:

1. 10418084 - Andrea Cecilia Garcia - Aws Academy
2. 10419181 - Caroline Begiato Cabral - Front-end, documentação
3. 10418069 - Mariana Okamoto - Aws Academy
4. 10417520 - Renata Freire Ardito - Back-end, documentação

## 1. Visão geral
Optamos pelo universo dos livros porque não há nada como mergulhar em novas narrativas e viajar sem sair do lugar. Imagine sua própria estante virtual, disponível e sustentada pela segurança e escalabilidade da AWS. Inspirado no Skoob, nosso sistema CRUD na AWS permite:

 - Inserir livros com título, editora, número de páginas e gênero.

 - Navegar entre os livros já adicionados, os que você já leu e os que ainda quer ler.

 - Atualizar o status conforme avança na leitura.

 - Remover da lista títulos que não fazem mais sentido pra você.


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
