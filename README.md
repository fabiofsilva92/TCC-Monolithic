# TCC-Monolitico


## Como realizar os testes do kafka com microsservice e monolitico

	Para realizar os testes é necessário ter o Docker desktop e Postman instalado na sua maquina, a partir disso devemos seguir os seguintes passos : 


	Subir instancia do mysql e kafka no docker utilizando o seguinte docker-compose.
		Docker compose mysql + kafka + kafdrop + zookeper :
		 - https://gist.github.com/fabiofsilva92/2141a7a7e67743ce8d7035b964fcd97a
		Para realizar o procedimento, devemos copiar as configurações que se encontram no link acima,
		colar em um arquivo nomeado como  docker-compose.yml, após isso abrir o cmd no caminho onde se encontra este arquivo,
		após isso executar o comando : docker-compose up
		Após esse procedimento o docker irá conter o banco de dados necessário para rodar a aplicação e também o kafka e suas configurações.
		Certifique-se que os containeres estão rodando(é possivel conferir olhando o docker desktop os containers que estão ativos).
		
		Agora deve ser iniciado a execução das aplicações de Microserviços e do monolitco na seguinte ordem : 
	
	MICROSERVIÇOS (INICIAR EXATAMENTE NESTA ORDEM ABAIXO):
	
	Iniciar microsservice naming-service
	Iniciar microsservice book-crud (porta 7000)
	Iniciar microsservice cambio-crud (porta 7080)
	Iniciar microsservice kafka consumer
	Iniciar microsservice kafka producer (porta 8087)
	
	MONOLITICO(É APENAS UM ENTÃO, SÓ ESTARTAR):
	Iniciar monolitico
	
	Após isso devemos realizar o teste no endpoint do producer aonselhavel usar o Postman.
	
	Para realizar o teste com a mensageria deveremos enviar um POST para um endpoint do nosso producer
		- endpoint : localhost:8087/message
		
		- body: Devemos enviar no body o serviço que queremos testar e a operação,
		até o momento só implementamos o metodo GET onde trará todos os registros das tabelas do respectivo serviço escolhido
		podemos utilizar o service "BOOK" ou "CAMBIO"
		Exemplo:
				{
					"service": "CAMBIO",
					"type": "GET"
				}
	
	Ao enviar o post, o consumer dos microsserviços e o consumer do monolitico irá receber a mensagem e realizar as mesmas operações ao mesmo tempo,
	e no console(terminal) dos consumers de cada aplicação ira aparecer o tempo percorrido em cada uma das arquiteturas.
 

