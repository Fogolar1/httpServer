# Instruções para a execução do código

## Servidor
- Antes da execução do código é necessário alterar a constante *PATH* da classe *FileSenderThread* que é responsável por salvar o arquivo HTML e a imagem no computador do servidor.
- Para acessar o código em opção de servidor, basta executar a classe *Main* sem passar nenhum argumento.
- O servidor ficará de pé até que o código deixe de ser executado, podendo responder a 3 tipos diferentes de request : 
  - html : retorna uma página HTML
  - img : retorna uma imagem
  - exit : finaliza a conexão com o cliente
- Para o manuseio de requests, o servidor cria uma nova *thread* com uma nova *Socket*, enviando os arquivos através dessa nova *thread*.


## Cliente
- Antes da execução do código, é necessário alterar o IP do servidor na criação da *Socket* no método *start()* da classe *Client* para o IP do computador que está executando o código da classe *Server*.
- Também é necessário alterar a constante *PATH* da classe *Client* que é responsável por disponibilizar o caminho absoluto do arquivo HTML e a imagem no computador do cliente.
- Para acessar o código em opção de cliente, basta executar a classe *Main* com o argumento *"client"*.
- Depois da execução do código de cliente, o código mandará 3 mensagens para o servidor, uma solicitando uma página HTML, outra solicitando uma imagem e outra finalizando o código e a conexão com o servidor.
- A página HTML e o arquivo de imagem se encontraram salvos na pasta da constante *PATH* da classe *FileSenderThread*.