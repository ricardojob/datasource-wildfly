## Informações

Este projeto cria um datasource e persiste algumas informações no banco de dados.

Neste exemplo será utilizado o SGBD PostgreSQL e o servidor de aplicação WildFly 10.

Para criação do datasource e implantação do projeto foram utilizadas as ferramentas de linha de comando disponíveis no Wildfly. A sequência de passos foi executada em um ambiente Unix, por isso o uso dos comando '.sh', caso esteja em um ambiente Windows execute os comandos '.bat'.

### Passos
1. *Copiar o arquivo '.jar' do banco para a pasta `../wildfly/customization`* . Neste exemplo, o arquivo copiado foi o *postgresql-9.4-1200-jdbc41.jar*;

2. *Executar `../wildfly/bin/standalone.sh`*;

3. *Em outra aba do terminal, ou instância do cmd, executar `../wildfly/bin/jboss-cli.sh --connect --command="module add --name=org.postgresql --resources=/Users/job/Documents/dev/servers/wildfly-10.1.0.Final/customization/postgresql-9.4-1200-jdbc41.jar --dependencies=javax.api,javax.transaction.api,javax.servlet.api"`*;

  a. O valor do parâmetro `resources` deve informar o caminho absoluto para o arquivo `.jar` que foi copiado. `--resources=/Users/job/Documents/dev/servers/wildfly-10.1.0.Final/customization/postgresql-9.4-1200-jdbc41.jar`;
4. *Executar `../wildfly/bin/jboss-cli.sh  --connect --command="/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-xa-datasource-class-name=org.postgresql.ds.PGPoolingDataSource)"`*;
  a. a saída deve ser algo como: `{"outcome" => "success"}`;

5. *Executar `../wildfly/bin/jboss-cli.sh  --connect --command="data-source add --name=postgresqlDS --driver-name=postgresql --jndi-name=java:jboss/jdbc/datasourceDS --connection-url=jdbc:postgresql://127.0.0.1:5432/exemplo-dac --user-name=postgres --password=12345 --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true"`*;

  a. deve ser informadas as configurações da conexão com o banco de dados;

  b. o nome *jndi* configurado no `--jndi-name=java:jboss/jdbc/datasourceDS` será utilizado para recupear o datasource na aplicação;

  c. a saída deve ser algo como:  `WFLYCTL0212: Duplicate resource [
    ("subsystem" => "datasources"),
    ("data-source" => "postgresqlDS")]`;

6. *Para fazer o 'deploy' da aplicação, executar `../wildfly/bin/jboss-cli.sh --connect --command="deploy --force /Users/job/Documents/dev/testes/exemplo-wildfly-datasource/target/exemplo.war"`*;

  a. deve ser informado o caminho absoluto para o arquivo a ser implantado `/Users/job/Documents/dev/testes/exemplo-wildfly-datasource/target/exemplo.war`;

7. *Caso seja necessário fazer o `undeploy`, executar `../wildfly/bin/jboss-cli.sh --connect --command="undeploy /Users/job/Documents/dev/testes/exemplo-wildfly-datasource/target/exemplo.war.war"`*;

  a. deve ser informado o caminho absoluto para o arquivo a ser desimplantado `/Users/job/Documents/dev/testes/exemplo-wildfly-datasource/target/exemplo.war`;
