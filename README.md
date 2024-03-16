# HackathonFIAP2024TurmaA1DJT

**#######| Projeto do hackathon 2024, turma FIAP 1ADJT |############################################**

* Fiap turma: 1ADJT
* GRUPO: 60
    * Lucca Brito Gesteira - RM 349111
    *  Guilherme Franco - RM 350562
    *  Vinícius Miranda de Menezes - RM 348870

Desenvolvimento de um sistema WEB, com interfaces e APIs, para a startup de Sistemas de Hospitalidade.

# Estrutura do projeto
O sistema possui 3 módulos:
- Gestão de Quartos: src/main/java/br/com/fiap/postech/hackathon2024/gestaoquarto
- Gestão de Serviços e Opcionais: src/main/java/br/com/fiap/postech/hackathon2024/gestaoclientes
- Gestão de Reservas: src/main/java/br/com/fiap/postech/hackathon2024/gestaoreservas

Adicionalmente possui os seguintes serviços corporativos:
- Serviço de mensageria de e-mail: src/main/java/br/com/fiap/postech/hackathon2024/emailsender
- Controle de eventos de sistema: src/main/java/br/com/fiap/postech/hackathon2024/handlers

## Modelagem das entidades

A modelagem de negócio foi primeiramente toda desenvolvida na camada de entities, de cada módulo. A visão integrada
pode ser observada no diagrama de classes, divido por módulo. Conforme a seguir:
Diagrama de Classe - Entities x Modulos
![Diagrama de Classe - Entities x Modulos](img.png)

--- 
Modelo entidade relacionamento do banco
![saída - Cópia-1](https://github.com/paravinicius/HackathonFIAP2024TurmaA1DJT/assets/94026036/34330474-865f-4058-abff-3799ba73d39d)
