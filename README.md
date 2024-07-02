# Reserva lá

Reserva lá é uma API REST desenvolvida em Java utilizando Spring Boot e JDBC Template. O projeto segue padrões de desenvolvimento como DTO (Data Transfer Object) para uma melhor organização e manutenibilidade do código. Testes unitários e de integração foram implementados utilizando JUnit e Mockito, e as métricas de cobertura de testes foram geradas com Jacoco.

## Tecnologias Utilizadas
[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,jenkins)](https://skillicons.dev) 

Algumas tecnologias específicas que também foram utilizadas:
> JDBC Template <br>
> JUnit <br>
> Mockito <br>
> Jacoco (Java Code Coverage)

## Funcionalidades
> CRUD dos quartos do hotel <br>
> Obtenção da receita do hotel <br>
> Obtenção da ocupação total dos quartos do hotel <br>
> Validação de dados <br>
> Exceções customizadas <br>
> Integração com H2 Database usando JDBC Template <br>

## Estrutura do projeto
````css
reserva-room-hotel
│ README.md
│ pom.xml
└───src
│    └───main
│          └───java
│                └───br
│                    └───com
│                         └───ada
│                              └───reservala
│                                     │ ReservaApplication.java
│                                     └───controller
│                                     │ RoomController.java
│                                     └───dto
│                                     │ RoomDtoRequest.java
│                                     └───exception
│                                     │ ErrorHandler.java
│                                     │ RoomNotFoundException.java
│                                     └───domain
│                                     │ Room.java
│                                     └───repository
│                                     │ RoomRepository.java
│                                     └───service
│                                     │ RoomService.java
└───test
│     └───java
│           └───br
│               └───com
│                    └───ada
│                         └───reservala
│                         │ ReservaApplicationTests.java
│                         └───controller
│                         │ RoomControllerTest.java
│                         └───domain
│                         │ RoomTest.java
│                         └───service
│                         │ RoomServiceTest.java
│                         └───repository
│                         │ RoomRepositoryTest.java
````

## Configuração do Ambiente

### Pré-requisitos

- JDK 11+
- Maven

### Configuração do Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/gb-alves03/reserva-room-hotel.git
   cd reserva-room-hotel

2. Compile o projeto:
   ````bash
   mvn clean install

3. Execute a aplicação:
   ````bash
   mvn spring-boot:run

## Testes

### Executar Testes Unitários e de Integração

Para executar os testes unitários e de integração, utilize o comando:
  ````bash
  mvn test
  ````
## Cobertura de Testes com Jacoco

Para gerar o relatório de cobertura de testes, utilize o comando:

  ````bash
  mvn jacoco:report
  ````

O relatório será gerado no diretório "target/site/jacoco".


