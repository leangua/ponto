# Exercicio de revisao ponto eletronico

Declaracao dos endpoints:


(GET) /usuarios/listar
-> Lista todos os usuarios cadastrados previamente


(POST)
/usuarios/cadastrar
-> Cadastra um novo usuario
	-> campos de entrada (body)
		-> nome (String)
		-> cpf (String)
		-> email (String)

(GET)
/usuarios/{id}
-> Consulta informacoes do usuario definido em {id}
	-> URI Parameter
		-> id (String)

(PUT)
/usuarios/{id}/atualizar
-> Atualiza os dados do usuario definido em {id}
	-> Campos de entrada (body)
		-> nome (String)
		-> cpf (String)
		-> email (Sttring)
	-> URI Parameter
		-> id (String)

------------------------------------------------
(GET)
/usuarios/{idUsuario}/ponto/listar
-> Lista os registros de ponto de um determinado usuario definido em {idUsuario}
	-> URI Parameter
		- idUsuario (String)

(POST)
/usuarios/{idUsuario}/ponto/registrar
-> Grava registro de ponto do usuario definido em {idUsuario}
	-> URI Parameter
		- idUsuario (String)
	-> Campos de Entrada
		- tipoBatida (ENUM: ENTRADA, SAIDA)
