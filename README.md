# Gerenciador de Downloads e Compactação ZIP
Este projeto tem como objetivo baixar arquivos PDF de um site especificado, compactá-los em um arquivo ZIP e limpar os arquivos temporários criados durante o processo.

## Funcionalidades
Baixar anexos PDF de uma página web.

Compactar arquivos em um único arquivo ZIP.

Limpeza de arquivos temporários após a execução.

## Estrutura do Projeto
O projeto é dividido em quatro classes principais:

### CompactadorZIP: Responsável pela compactação dos arquivos PDF em um arquivo ZIP.

## GerenciadorDownloads: Gerencia o processo de download dos arquivos PDF a partir de um URL.

## GerenciadorArquivos: Utilitário para criação de diretórios e limpeza de arquivos temporários.

## Main: Classe principal que executa o processo de download e compactação.

## Dependências
Este projeto utiliza a biblioteca Jsoup para fazer o scraping do site e coletar os links dos arquivos PDF.

Adicione a dependência no seu arquivo pom.xml:
