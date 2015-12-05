# Popula tabela com cpf e cnpj válidos
Programa feito em Java
por Vinicius Vale 01/12/2015

Blog: www.viniciusvale.com <br />
Empresa: www.valeconsultoria.com <br />
Linkedin: linkedin.com/in/aquinovale 

### ARQUIVO OFUSCADOR
O arquivo ofuscador é basicamente um arquivo com tabela.campo <br/>
Ex.: clientes->cpf <br/>

Caso a tabela esteja definida com Maiuscula, então deve se usar aspas duplas <br/>
Ex.: "Pessoas"->cpf <br/>
Ex.: "Pessoas"->"CPF" <br/>

Havendo a necessidade de definir um gerador diferente do default (cpf).
Ex.: pessoas->campo->cnpj <br />
Ex.: pessoas->campo = pessoas->campo->cpf

##### EXECUÇAO DO JAR                 
Faça a compilação do fonte, ou utilize o pacote já pronto.
```
Ex.:  java -jar ofusca.jar /conexao.properties
```

#### ARQUIVO conexao.properties
Arquivo onde se encontra as configurações para acesso ao postgresql.

obs.: Lembrando que pode haver necessidade de liberação do usuário no pg_hba.conf <br />
obs.: Não deixe espaços entro campos

```
host=localhost
port=5432
user=vinicius
senha=123456
database=ofuscador
password=123456
```

###### TABELAS FILHAS
Sendo as alterações feitas em campos com PK, o mesmo deve ser feito em tabela pai. As FK's que estão apontadas para o campo que será modificado, devem ser excluídas e recriadas com a opçao UPDATE CASCATE.

```
---- EXCLUSÃO DA FK

ALTER TABLE filha DROP CONSTRAINT fk_names; 

----  CRIAÇÂO DA FK

ALTER TABLE filha ADD CONSTRAINT fk_chaves FOREIGN KEY (campoFilha) REFERENCES pai(campoPai) on UPDATE CASCADE ;

```

















