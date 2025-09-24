#!/bin/bash

# Script para executar o projeto Synergia com Java 17
echo "🚀 Iniciando Synergia com Java 17..."

export JAVA_HOME=/usr/local/opt/openjdk@17

# Limpa e compila
mvn clean compile

# Executa a aplicação
mvn exec:java -Dexec.mainClass="br.com.synergia.Main"