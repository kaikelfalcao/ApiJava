<h1 align="center" style="font-weight: bold;">API Java 💻</h1>

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#started">Preparando</a> •
 <a href="#routes">APP Routes</a> • 
 <a href="#colab">Colaboladores</a> •
</p>

<p align="center">
    <b>Uma API Crud escrita em Java</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

- Java
- Maven
- Wildfky
- Docker
- PostGres

<h2 id="started">🚀 Preparando</h2>

Para iniciar essa aplicação siga esses passos

Na sua maquina para rodar é preciso de **Docker** e **Java** 
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Java](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)

<h3>Cloning</h3>

Para clonar basta rodar

```bash
git clone https://github.com/kaikelfalcao/ApiJava.git
```

<h3>Rodando Projeto </h3>

Para começar siga esses Passos:

```bash
cd ApiJava
docker build -t api .
docker-compose up -d
```

Com isso: 

- o Wildfly está  exposto na URL: http://localhost:8080

- o Postgres está  exposto na URL: http://localhost:5432

 - o PgAdmin está  exposto na URL: http://localhost:8082
-- Email = kaike@gmail.com
-- Senha = password

<h2 id="routes">📍 Application Routes</h2>

As chamadas as API estarão na http://localhost:8080/JavaAPI/'''{rota}'''


<h2> Paciente </h2>

| route               | request    | response                                        
|----------------------|-----------------------------------------------------|---------------------------
| <kbd>GET /paciente </kbd>  | None | Lista de todos os pacientes 
| <kbd>GET /paciente/{cpf} </kbd>   | CPF na rota | `json` dos dados do paciente
| <kbd>POST /paciente/ </kbd> | `json` com os campos cpf, nome, dtnascimento, endereco, email, planosaude, numcarteirinhaplanosaude| `json` `message`
| <kbd>PUT /paciente/{cpf} </kbd>  | CPF na rota| `json` `message`
| <kbd>DELETE /paciente/{cpf} </kbd> | CPF na rota | `json` `message`

<h2> Usuário </h2>

| route               | request    | response                                        
|----------------------|-----------------------------------------------------|---------------------------
| <kbd>GET /usuario/{cpf} </kbd>   | CPF na rota | `json` dos dados do usuario
| <kbd>POST /usuario </kbd> | `json` com os campos cpf, login, senha| `json` `message`
| <kbd>PUT /usuario/{cpf} </kbd>  | CPF na rota| `json` `message`
| <kbd>DELETE /usuario/{cpf} </kbd> | CPF na rota | `json` `message`

<h2> Atendimento </h2>

| route               | request    | response                                        
|----------------------|-----------------------------------------------------|---------------------------
| <kbd>GET /atendimento/paciente/{cpf} </kbd>| CPF na rota | A lista de atendimentos do paciente
| <kbd>GET /atendimento/tipo/{tipo}</kbd> | Tipo do atendimento na rota| A lista de atendimentos desse tipo
| <kbd>POST /atendimento </kbd>  | `json` com cpfpaciente, nomepaciente, cpfusuario, descricaoatendimento, dataatendimento, valorcobrado, tipoatendimento| `json` `message`
| <kbd>DELETE  /atendimento/{numero} </kbd> | Numero do Atendimento na Rota | `json` `message`


<h2 id="colab">🤝 Collaborators</h2>

Grupo responsável por elaborar o projeto.

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/LimeHawk">
        <img src="https://avatars.githubusercontent.com/u/96706378?s=400&u=c9ec291bbbb7ff2f92b39ba6350b6eb6894e7036&v=4" width="100px;" alt="Kaike Profile Picture"/><br>
        <sub>
          <b>Kaíke Falcão</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/BisNeT0">
        <img src="https://avatars.githubusercontent.com/u/111530921?v=4" width="100px;" alt="Henrique Profile Picture"/><br>
        <sub>
          <b>Henrique Bisneto</b>
        </sub>
      </a>
    </td>
    </td>

    
    
  </tr>
</table>


