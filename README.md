# TCC-Monolitico



## Como realizar os testes do kafka com microsservice e monolitico


	Subir instancia do mysql no docker utilizando o seguinte docker-compose.
		Docker compose mysql - https://gist.github.com/fabiofsilva92/5d38d9b9980e96140cd3bf7dcdf7511d
		
		
	Subir instancia kafka no docker utilizando o seguinte docker-compose. 
		Docker compose kafka + kafdrop + zookeper - https://gist.github.com/fabiofsilva92/2141a7a7e67743ce8d7035b964fcd97a
		
	Iniciar microsservice naming-service
	Iniciar microsservice book-crud (porta 7000)
	Iniciar microsservice cambio-crud (porta 7080)
	Iniciar microsservice kafka consumer
	Iniciar microsservice kafka producer (porta 8087)
	Iniciar monolitico
	
	Para realizar o teste com a mensageria deveremos enviar um POST para um endpoint do nosso producer
		- endpoint : localhost:8087/message
		
		- body: Devemos enviar no body o serviço que queremos testar e a operação, até o momento só implementamos o metodo GET onde trará todos os registros das tabelas do respectivo serviço escolhido
		podemos utilizar o service "BOOK" ou "CAMBIO"
		Exemplo:
				{
					"service": "CAMBIO",
					"type": "GET"
				}
	
	Ao enviar o post, o consumer dos microsserviços e o consumer do monolitico irá receber a mensagem e realizar as mesmas operações ao mesmo tempo, o console ira logar o tempo percorrido em cada uma das arquiteturas.
 

