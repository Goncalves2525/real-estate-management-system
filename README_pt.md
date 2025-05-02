# Sistema Real Estate USA

## Visão Geral do Projeto
Este projeto é uma solução integrativa desenvolvida para o curso de Licenciatura em Engenharia Informática (LEI) no Instituto Superior de Engenharia do Porto (ISEP). Representa uma aplicação abrangente de gestão imobiliária para uma empresa fictícia chamada "Real Estate USA".

O projeto integra conhecimentos e competências de múltiplas Unidades Curriculares (UC) do 2º semestre:
- ESOFT - Processo de desenvolvimento de software
- PPROG - Programação orientada a objetos em Java
- MDISC - Complexidade temporal no pior caso de algoritmos de ordenação e algoritmo de Partição Equilibrada
- MATCP - Regressão linear e tarefas de previsão
- LAPR2 - Gestão de equipas, metodologia de trabalho, integração de módulos

## Contexto de Negócio
A Real Estate USA é uma empresa com uma rede de agências imobiliárias nos Estados Unidos que necessita de uma aplicação para gerir as suas operações de negócio de arrendamento e venda de imóveis.

### Funcionalidades Principais
- Gestão de anúncios de imóveis (arrendamento e venda)
- Registo de negócio (arrendamento ou venda)
- Agendamento de visitas a imóveis
- Consulta de imóveis por clientes
- Gestão de operações de funcionários
- Cálculo de comissões
- Gestão de lojas e da rede
- Análise de desempenho

## Utilizadores do Sistema
- **Proprietários**: Registam imóveis para venda/arrendamento
- **Clientes/Compradores**: Consultam imóveis e agendam visitas
- **Agentes Imobiliários**: Gerem anúncios e visitas dos clientes
- **Gerentes de Loja**: Monitorizam operações da agência e desempenho dos funcionários
- **Gestores de Rede**: Analisam o desempenho entre agências
- **Administrador do Sistema**: Gere funcionários e agências

## Requisitos Técnicos
- Desenvolvido em Java
- IDE: IntelliJ ou NetBeans
- Interface Gráfica: JavaFX 11
- Autenticação: Baseada em palavra-passe (7+ alfanuméricos com 3 letras maiúsculas e 2 dígitos)
- Suporte de Idioma: Inglês
- Documentação: Javadoc
- Testes: Framework JUnit 5 com JaCoCo para relatórios de cobertura
- Formato de imagem: SVG
- Persistência de dados: Serialização de objetos

## Arquitetura

A aplicação segue um padrão de arquitetura em camadas:

- **Camada de Domínio**: Entidades de negócio e lógica principal
- **Camada de Repositório**: Persistência e recuperação de dados
- **Camada de Aplicação**: Controladores que coordenam operações
- **Camada de UI**: Componentes de interface do utilizador

O sistema utiliza um padrão de repositório para acesso a dados, facilitando a alternância entre diferentes mecanismos de armazenamento.

## Começar

### Pré-requisitos

- Java JDK 11 ou superior
- Maven 3.6 ou superior
- IntelliJ IDE ou NetBeans
- JavaFX 11

### Instalação

1. Clonar o repositório:
   ```bash
   git clone https://github.com/seuutilizador/real-estate-usa.git
   cd real-estate-usa
   ```

2. Compilar o projeto com Maven:
   ```bash
   mvn clean install
   ```

3. Executar a aplicação:
   ```bash
   java -jar target/real-estate-app.jar
   ```

### Utilizadores Predefinidos

O sistema vem pré-configurado com os seguintes utilizadores:

| Nome de Utilizador | Email | Palavra-passe | Função |
|----------|-------|----------|------|
| MainAdministrator | admin@realestate.app | Admin123 | Administrador |
| Agent1 | agent1@realestate.app | Agent123 | Funcionário |
| Client1 | client1@realestate.app | Client123 | Cliente |
| StoreManager1 | manager1@realestate.app | Manager123 | Gerente de Loja |
| NetworkManager | network@realestate.app | Network123 | Gestor de Rede |

## Desenvolvimento

### Estrutura do Projeto

- `src/main/java/pt/ipp/isep/dei/esoft/project/`
    - `domain/` - Entidades de negócio e objetos de valor
        - `model/` - Objetos de negócio principais (Imóvel, Agente, Cliente, etc.)
        - `shared/` - Objetos de valor e componentes partilhados
    - `repository/` - Objetos de acesso a dados
    - `application/controller/` - Lógica de aplicação
    - `ui/` - Componentes de interface do utilizador
        - `console/` - Implementação da UI de consola
        - `gui/` - Implementação da UI gráfica com JavaFX
    - `utils/` - Classes utilitárias

### Estrutura de Sprints

O projeto segue um processo de desenvolvimento baseado em sprints:
- **Sprint A (Semanas 4-6)**: Competências básicas de ER e OO, Testes de Software, Integração Contínua
- **Sprint B (Semanas 7-9)**: Competências avançadas de ER/OO/DOO/COO, desenvolvimento de UI de consola
- **Sprint C (Semanas 10-12)**: Continuação do desenvolvimento de módulos, refinamento da UI de consola
- **Sprint D (Semanas 13-15)**: Conclusão de todos os módulos, implementação da UI gráfica com JavaFX

### Execução de Testes

```bash
# Executar todos os testes
mvn clean test

# Gerar relatório de cobertura de testes
mvn test jacoco:report

# Verificar se os limiares de cobertura são atingidos
mvn test jacoco:check

# Gerar relatório de teste de mutação
mvn org.pitest:pitest-maven:mutationCoverage
```

### Geração de Documentação

```bash
# Gerar documentação do código fonte
mvn javadoc:javadoc

# Gerar documentação do código de teste
mvn javadoc:test-javadoc
```

## Persistência de Dados

A aplicação utiliza serialização Java para persistência de dados. Os dados são guardados num ficheiro denominado `RealEstateUSA.ser`. No arranque, a aplicação verifica se este ficheiro existe:

- Se existir, deserializa os dados e executa apenas o bootstrap de utilizadores
- Se não existir, executa o bootstrap completo e cria um novo ficheiro serializado

## Diagramas UML

Os diagramas UML podem ser gerados utilizando PlantUML. Execute o seguinte script a partir da raiz do projeto:

```bash
bin/generate-plantuml-diagrams.sh
```

Todas as imagens e figuras produzidas durante o desenvolvimento devem ser registadas em formato SVG, conforme especificado nos requisitos técnicos.

## Equipa de Projeto
Este projeto foi concebido para ser realizado por equipas de 4 alunos, trabalhando em todas as UCs do 2º semestre.

## Agradecimentos

* Instituto Superior de Engenharia do Porto (ISEP)
* Licenciatura em Engenharia Informática (LEI)
* Coordenadores do Projeto e Professores das UCs