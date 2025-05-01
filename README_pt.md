# Sistema de Gestão Imobiliária

Uma aplicação Java abrangente para agências imobiliárias gerirem propriedades, clientes, anúncios e operações comerciais.

## Visão Geral do Projeto

Este sistema de gestão imobiliária oferece uma solução completa para agências imobiliárias gerirem as suas operações diárias. O sistema suporta múltiplos perfis de utilizador, incluindo administradores, funcionários (agentes), clientes, gerentes de loja e gerentes de rede, cada um com permissões de acesso e funcionalidades específicas.

### Principais Funcionalidades

- **Gestão de Propriedades**: Adicionar, editar e navegar por vários tipos de propriedades (casas, apartamentos, terrenos)
- **Sistema de Anúncios**: Criar, publicar e gerir listagens de propriedades
- **Autenticação de Utilizadores**: Controlo de acesso baseado em perfis com login seguro
- **Gestão de Clientes**: Acompanhar informações e interações com clientes
- **Agendamento de Visitas**: Marcar e gerir visitas a propriedades
- **Processamento de Encomendas**: Gerir ordens de compra e arrendamento
- **Gestão de Agências**: Organizar agências, lojas e funcionários
- **Notificações**: Notificações por email e SMS para eventos importantes

## Arquitetura

A aplicação segue um padrão de arquitetura em camadas com:

- **Camada de Domínio**: Entidades e lógica de negócio principais
- **Camada de Repositório**: Persistência e recuperação de dados
- **Camada de Aplicação**: Controladores que coordenam operações
- **Camada de UI**: Componentes de interface do utilizador

O sistema utiliza um padrão de repositório para acesso a dados, facilitando a mudança entre diferentes mecanismos de armazenamento.

## Começar

### Pré-requisitos

- Java JDK 11 ou superior
- Maven 3.6 ou superior

### Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seunomedeutilizador/gestao-imobiliaria.git
   cd gestao-imobiliaria
   ```

2. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   java -jar target/real-estate-app.jar
   ```

### Utilizadores Predefinidos

O sistema vem pré-configurado com os seguintes utilizadores:

| Nome de Utilizador | Email | Palavra-passe | Perfil |
|----------|-------|----------|------|
| MainAdministrator | admin@this.app | admin | Administrador |
| Employee | employee@this.app | pwd | Funcionário |
| Client | client@this.app | pwd | Cliente |
| Store Manager | storemanager@this.app | pwd | Gerente de Loja |
| Network Manager | networkmanager@this.app | pwd | Gerente de Rede |

## Desenvolvimento

### Estrutura do Projeto

- `src/main/java/pt/ipp/isep/dei/esoft/project/`
    - `domain/` - Entidades de negócio e objetos de valor
    - `repository/` - Objetos de acesso a dados
    - `application/controller/` - Lógica de aplicação
    - `ui/` - Componentes de interface do utilizador
    - `ui/console/` - Implementação da UI de consola
    - `ui/gui/` - Implementação da GUI (se aplicável)

### Executar Testes

```bash
# Executar todos os testes
mvn clean test

# Gerar relatório de cobertura de testes
mvn test jacoco:report

# Verificar se os limites de cobertura são atingidos
mvn test jacoco:check

# Gerar relatório de testes de mutação
mvn org.pitest:pitest-maven:mutationCoverage
```

### Gerar Documentação

```bash
# Gerar documentação do código fonte
mvn javadoc:javadoc

# Gerar documentação do código de teste
mvn javadoc:test-javadoc
```

## Persistência de Dados

A aplicação usa serialização Java para persistência de dados. Os dados são guardados num ficheiro chamado `RealEstateUSA.ser`. No arranque, a aplicação verifica se este ficheiro existe:

- Se existir, desserializa os dados e executa apenas o bootstrap de utilizadores
- Se não existir, executa o bootstrap completo e cria um novo ficheiro serializado

## Diagramas UML

Os diagramas UML podem ser gerados usando PlantUML. Execute o seguinte script a partir da raiz do projeto:

```bash
bin/generate-plantuml-diagrams.sh
```

## Licença

Este projeto está licenciado sob a Licença MIT - consulte o ficheiro LICENSE para obter detalhes.

## Agradecimentos

* Instituto Superior de Engenharia do Porto (ISEP)
* Licenciatura em Engenharia Informática (LEI)
* Ricardo Gonçalves