# TCC-Monolitico


## Como realizar os testes do kafka com microsservice e monolitico

	Para realizar os testes é necessário ter o Docker instalado, a partir disso devemos seguir os seguintes passos : 


	Subir instancia do mysql e kafka no docker utilizando o seguinte docker-compose.
		Docker compose mysql + kafka + kafdrop + zookeper :
		 - https://gist.github.com/fabiofsilva92/2141a7a7e67743ce8d7035b964fcd97a
		Para realizar o procedimento, devemos colocar o arquivo docker-compose.yml em uma pasta e abrir o cmd no caminho da pasta, após isso executar o comando : docker-compose up
		Após esse procedimento o docker irá conter o banco de dados necessário para rodar a aplicação e também o kafka e suas configurações.
		Certifique-se que os containeres estão rodando e então siga  com a execução dos serviços na seguinte ordem : 
		
	Iniciar microsservice naming-service
	Iniciar microsservice book-crud (porta 7000)
	Iniciar microsservice cambio-crud (porta 7080)
	Iniciar microsservice kafka consumer
	Iniciar microsservice kafka producer (porta 8087)
	Iniciar monolitico
	
	Após isso devemos realizar o teste no endpoint do producer.
	Para realizar o teste com a mensageria deveremos enviar um POST para um endpoint do nosso producer
		- endpoint : localhost:8087/message
		
		- body: Devemos enviar no body o serviço que queremos testar e a operação, até o momento só implementamos o metodo GET onde trará todos os registros das tabelas do respectivo serviço escolhido
		podemos utilizar o service "BOOK" ou "CAMBIO"
		Exemplo:
				{
					"service": "CAMBIO",
					"type": "GET"
				}
	
	Ao enviar o post, o consumer dos microsserviços e o consumer do monolitico irá receber a mensagem e realizar as mesmas operações ao mesmo tempo, o console dos consumers irão logar o tempo percorrido em cada uma das arquiteturas.
 

