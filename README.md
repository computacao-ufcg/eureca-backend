# pdc-backend

## Instruções para instalação de dependências

**Obs:** é necessário ter o pip (instalador de pacotes python), juntamente com o python3 instalados em sua máquina.

Após isso, para instalar todas as dependências do projeto basta executar o seguinte comando:
```
pip install -r requirements.txt
```

## Execução da aplicação

Após instalar todas as dependências, para executar a aplicação basta executar o seguinte comando na raiz do projeto
```
python run.py
```

## Execução da aplicação com Docker

Fazer o build da imagem a partir da raiz do projeto:
```
docker build -t pdc-back:dev .
```

Criando o container da aplicação a partir da imagem:
```
docker run -it --name pdc-back-cont -p 5000:5000 pdc-back:dev
```

Após o procedimento anterior, já é possível acessar a aplicação que estará em execução em http://localhost:5000

## Enviando a aplicação para o DockerHub

- A partir da raiz do projeto, digite:

    `docker build -t eureca-backend:dev .`

- Após o sucesso do build, esteja logado com sua conta do DockerHub para enviar a imagem. Para se conectar ao Docker:

    `docker login`

- Insira suas credenciais e faça o login.

**Criando a tag para a imagem.**

- Com a imagem montada, e o login efetuado, execute:

    `docker images`

- Recupere o id da imagem eureca-backend, pois iremos utilizar no próximo passo.

    `docker tag "id_imagem" eureca/eureca-backend:dev`

    `docker push eureca/eureca-backend:dev`

- **"eureca/"** é o nome da organização que o docker enviará/atualizará a imagem.
- **eureca-backend:dev** é o nome da imagem.