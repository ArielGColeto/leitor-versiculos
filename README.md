"# 📖 Leitor de Versículos

Aplicação desktop desenvolvida em JavaFX para projeção de versículos bíblicos em igrejas, com suporte a múltiplas telas (projetor).

---

## ✨ Funcionalidades

- 🔍 Busca de versículos por livro, capítulo e versículo
- 📚 Suporte a múltiplas traduções (NVI, ACF, NAA)
- 🎯 Autocomplete de livros bíblicos
- 🖥️ Modo apresentação em tela cheia
- 📺 Suporte a múltiplos monitores (modo estendido)
- ⚡ Interface moderna e responsiva

---

## 🛠️ Tecnologias utilizadas

- Java 17
- JavaFX
- Maven
- JSON

---

## 📂 Estrutura do projeto

src/
 ├── app
 ├── ui
 │   ├── components
 │   └── telas
 ├── service
 ├── repository
 └── model

---

## ▶️ Como executar o projeto

### 1. Clonar o repositório

git clone https://github.com/ArielGColeto/leitor-versiculos.git

### 2. Entrar na pasta

cd leitor-versiculos

### 3. Compilar com Maven

mvn clean package

### 4. Executar

java --module-path \"CAMINHO_DO_JAVAFX/lib\" --add-modules javafx.controls,javafx.fxml -jar target/Sistema_Igreja-1.0-jar-with-dependencies.jar

---

## 🎯 Objetivo

Sistema desenvolvido para facilitar a projeção de versículos em igrejas, permitindo uso com projetor em modo estendido.

---

## 👨‍💻 Autor

Desenvolvido por Ariel Grassi Coleto
