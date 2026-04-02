# WEG Workshop API

API RESTful desenvolvida para a gestão de Ordens de Serviço (OS) e Usuários nas oficinas do Centro WEG / SENAI.

# Postura Crítica: Análise do Código Legado e Mitigações Arquiteturais

A avaliação da arquitetura atual baseia-se no contraste direto com a primeira versão do sistema (`SistemaOficinaWegCompleto.java`). O código original operava sob um paradigma procedural estrito, contido em um único arquivo de classe. Abaixo, detalhamos as falhas críticas dessa abordagem legada e as soluções definitivas implementadas na nova API RESTful.

* **De Script Monolítico para Arquitetura em Camadas (Clean Architecture):** * **Falha Original:** Toda a lógica de negócio, entrada de dados (Scanner) e armazenamento dividiam o mesmo escopo (`main` e estruturas condicional `switch-case`).
    * **Mitigação:** Segregação rigorosa de responsabilidades. A lógica foi distribuída entre pacotes estruturais: `infrastructure` (entrada/saída via web e banco), `application` (serviços e DTOs) e `domain` (modelos e regras). O código tornou-se modular, testável e de baixo acoplamento.

* **De Memória Estática para Persistência Desacoplada (Padrão Repository):**
    * **Falha Original:** O banco de dados era simulado através de vetores estáticos e matrizes (`String[] osEquipamento`, `int[][] osAlunosEscalados`) com tamanho fixo (limite de 100 itens), impossibilitando persistência real e escalabilidade.
    * **Mitigação:** Implementação de interfaces de repositório e adaptadores (`persistency.repository`). A infraestrutura de persistência agora delega a responsabilidade para um banco de dados real, rompendo as limitações de memória e garantindo a integridade transacional.

* **De Arrays Paralelos para Modelagem de Domínio Ricos e DTOs:**
    * **Falha Original:** Dados de uma mesma entidade eram fragmentados em múltiplos vetores paralelos. Uma Ordem de Serviço não existia como objeto, mas como índices correspondentes em diferentes arrays de tipos primitivos.
    * **Mitigação:** Criação de entidades ricas no pacote `domain.model` (`ServiceOrder`, `User`, `SchoolClass`). A transferência de dados para o exterior da API foi blindada pelo padrão DTO (`application.dto`), mapeados estritamente via classes utilitárias no pacote `mapper`.

* **Gestão de Fluxo e Resiliência (Tratamento Global de Erros):**
    * **Falha Original:** Validações limitadas a condicionais `if/else`, sem padronização ou prevenção de quebra do sistema por inputs inválidos.
    * **Mitigação:** Introdução de um `GlobalExceptionHandler` (`infrastructure.web.common`) e de classes como `BusinessException` (`domain.exception`). Erros de negócio e validação agora são capturados globalmente, retornando respostas HTTP padronizadas e seguras.

---

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 4** (Web, Data JPA)
* **Jakarta Validation** (Validação de DTOs)
* **Jackson** (Serialização JSON)

---

## Rotas da API

### Usuários (`/api/users`)
* `POST /`: Cria usuário (Professor ou Aluno).
* `GET /`: Lista todos os usuários.
* `GET /{id}`: Busca um usuário por ID.

### Ordens de Serviço (`/api/service-orders`)
* `POST /`: Professor abre uma nova OS.
* `PUT /execute`: Aluno executa a OS (informa materiais e conclusão).
* `PUT /approve`: Professor aprova a OS finalizada.