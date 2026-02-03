# Docker Setup for Real Estate USA System

Este guia explica como executar a aplicação Real Estate USA usando Docker com interface gráfica acessível pelo browser.

## Pré-requisitos

- Docker instalado (versão 20.10+)
- Docker Compose instalado (versão 1.29+)
- **Nenhuma configuração adicional necessária!** ✅

## Como funciona

A aplicação corre dentro do container com:
- **Xvfb**: Display virtual (X11)
- **x11vnc**: VNC server
- **noVNC**: Interface web acessível no browser

**Não precisa instalar nada no PC!** Acede à GUI pelo browser em `http://localhost:6080`

## Build e Execução

### Passo 1: Build

```bash
docker-compose build
```

### Passo 2: Executar

```bash
docker-compose up
```

### Passo 3: Aceder à GUI

Abre o browser e vai a:

```
http://localhost:6080
```

Vai aparecer a interface da aplicação! 🎉

### Parar a aplicação

```bash
docker-compose down
```

Ou simplesmente `Ctrl+C` no terminal.

## Persistência de Dados

Os dados da aplicação (ficheiro `RealEstateUSA.ser`) são guardados no diretório `./data` do host através de um volume Docker. Isto garante que os dados persistem entre execuções do container.

## Configurações Opcionais

### Alterar Resolução

Edita o `docker-compose.yml` e muda a variável `RESOLUTION`:

```yaml
environment:
  - RESOLUTION=1920x1080  # Full HD
```

### Aceder via VNC Client

Se preferires usar um cliente VNC em vez do browser:

```
Host: localhost
Port: 5900
```

## Troubleshooting

### Porta 6080 já está em uso

Muda a porta no `docker-compose.yml`:

```yaml
ports:
  - "8080:6080"  # Usa porta 8080 localmente
```

Depois acede em `http://localhost:8080`

### Aplicação demora a arrancar

É normal. A primeira vez demora ~30 segundos porque:
1. Inicia o X11 virtual
2. Inicia o VNC server
3. Inicia o noVNC
4. Carrega a aplicação JavaFX

Podes ver o progresso nos logs:
```bash
docker-compose logs -f
```

### Ecrã preto no browser

Aguarda alguns segundos. Se continuar, verifica os logs:
```bash
docker-compose logs real-estate-app
```

## Desenvolvimento Local vs Docker

### Build Local (macOS/Windows/Linux)

O ficheiro `pom.xml` foi configurado com profiles Maven que detetam automaticamente o sistema operativo e usam as dependências JavaFX corretas:

```bash
mvn clean install
mvn javafx:run
```

### Build no Docker

O profile Linux é automaticamente ativado quando o Maven deteta que está a correr num sistema Linux (como dentro do container Docker).

## Notas

- A aplicação JavaFX requer um display X11 para funcionar
- Para ambientes totalmente headless, considerar criar uma versão console-only da aplicação
- O X11 forwarding pode ter problemas de segurança em ambientes de produção
