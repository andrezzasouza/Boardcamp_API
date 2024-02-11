# Boardcamp

Boardcamp is an API for managing board game rental services.

## Motivation

In the digital age, many people use phones, computers and video games to play. Although technology has brought many kinds of game that you can play in your favorite device, the love for board games is still quite common among people of all ages. However, these games tend to be somewhat expensive and as you need people around to play with you, they aren't used as often as other online and/or solo games.

For this reason, many stores in Brazil have started offering their space for customers to play together for a smaller price than it would cost to buy a game you would rarely play. Another option some of these stores offer is the rental of said games, much like the old Blockbuster stores where we would rent films to watch and then return them.

Boardcamp is the solution for this kind of store! It's a management system designed for board game rental services. It allows the store to keep record of its customers, its games and its rentals. No more renting a game when you're out of stock, losing track of how much the customers owe you when they return a game after the deadline or not knowing which customer has which game. Boardcamp allows you to easily input these data and retrieve them when necessary.

We may love games that don't require modern technology to be played, but we can still use technology to help us spread the love for old-fashioned board games!

<br/>

## Technologies used in this project

### Main technologies

- Java 17
- Maven
- Spring
- PostgreSQL

### Dependencies

- Lombok
- Validation I/O
- Spring Web
- Spring Boot DevTools
- Spring Data JPA
- PostgreSQL Driver

<br/>

## Test using the deployed version

You can test this API online using the following URL:

https://boardcamp-api-6oob.onrender.com

It is recommended that you use a tool that allows you to test APIs, such as [Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/) or [Thunder Client](https://www.thunderclient.com/). You can also use [curl](https://curl.se/docs/manpage.html) to make requests using the terminal.

You can create requests using the tools above and the information in the section ["Endpoints and expected inputs and outputs"](#endpoints-and-expected-inputs-and-outputs) of this README file.

## How to run the app locally

In order to run the app locally, you will need to have some tools installed and follow the instructions below.

### What you need to run the app locally

- Java 17
- PostgreSQL 15 or 16
- A terminal, such as bash
- Git (optional)

### How can you run the app locally

**1. Get this repository**

If you have Git installed in your terminal, you can clone this project to run locally. To do so, you should copy the link above, using the button `Code`, or copy the URL below:

```
https://github.com/andrezzasouza/Boardcamp_API.git
```

In your terminal, you should type the git command to clone and paste the URL above:

```bash
git clone https://github.com/andrezzasouza/Boardcamp_API.git
```

Alternatively, you can download the zip file of this repository using the button `Code` above and extract the folder containing the project.

<br/>

**2. Configure the database and .env file**

This project needs a created PostgreSQL database to run. You should create an empty database.

After a new database has been created, you should create a .env file in the root of the project. You can use the [.env.example](.env.example) file to know which variables should be declared. You should fill out the environment variables with the data from the database you have just created.

`DB_URL` should receive the URL of the database, `DB_USERNAME` should receive the username you use to access PostgreSQL and `DB_PASSWORD` should receive the password you use to access PostgreSQL.

<br/>

**3. Ensure that your computer has what it needs**

Check that you have successfully installed and configured everything listed in the section ["What you need to run the app locally"](#what-you-need-to-run-the-app-locally).

You may also need to configure or alter the JAVA_HOME environment variable. You can refer to [this article](https://www.baeldung.com/java-home-on-windows-mac-os-x-linux) for more information on how to do that depending on your operating system.

<br/>

**4. Run the project**

As this project contains a Maven Wrapper script, the mvnw file, it's not necessary to have Maven installed globally to run the project in you machine. The Maven wrapper script will be used to build the app using the correct version of Maven.

All you should need to do is open a terminal in the root directory of the project and run the command below:

```bash
./mvnw spring-boot:run
```

If you're using an IDE that has integration with Java, you may be able to skip running the command above. In Visual Studio Code (VS Code), for example, you can install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) so that you can build and run the project straight from the ApiApplication.java file.

After installing the extensions above, you'll be able to click on the word "Run" that will appear above the function `public static void main(String[] args)` in the ApiApplication.java file or the play button that will appear in the tab bar when this file is opened.

If you use another IDE, check if it supports Java and/or has tools to run it.

<br/>

## Endpoints and expected inputs and outputs

### Endpoints

The existing endpoints of this application are listed below:

| Method | URL                 | Description                                                                                           |
| ------ | ------------------- | ----------------------------------------------------------------------------------------------------- |
| POST   | /games              | It registers a new board game.                                                                        |
| GET    | /games              | It shows all registered board games.                                                                  |
| POST   | /customers          | It registers a new customer.                                                                          |
| GET    | /customers/:id      | It shows the data belonging to the customer whose ID number is passed as param in this route          |
| POST   | /rentals            | It creates a new rental of a game by a customer.                                                      |
| GET    | /rentals            | It shows all registered rentals.                                                                      |
| PUT    | /rentals/:id/return | It finalizes the rental which has the ID number passed as a param and calculates if delay fees apply. |

<br/>

### What each route expects and what it returns

<br/>
<details>
<summary><b>POST /games</b></summary>

- Param

  No param is expected in this route.

- Request body

  ```json
  {
    "name": "Monopoly",
    "image": "http://image-example.com/monopoly.jpg",
    "stockTotal": 3,
    "pricePerDay": 1500
  }
  ```

- Response

  `201 (CREATED)`

  ```json
  {
    "id": 1,
    "name": "Monopoly",
    "image": "http://image-example.com/monopoly.jpg",
    "stockTotal": 3,
    "pricePerDay": 1500
  }
  ```

</details>
<br/>
<details>
<summary><b>GET /games</b></summary>

- Param

  No param is expected in this route.

- Request body

  No body is expected in this route.

- Response

  `200 (OK)`

  ```json
  [
    {
      "id": 1,
      "name": "Monopoly",
      "image": "http://image-example.com/monopoly.jpg",
      "stockTotal": 3,
      "pricePerDay": 1500
    },
    {
      "id": 2,
      "name": "Clue",
      "image": "http://image-example.com/clue.png",
      "stockTotal": 1,
      "pricePerDay": 2500
    }
  ]
  ```

</details>
<br/>
<details>
<summary><b>POST /customers</b></summary>

- Param

  No param is expected in this route.

- Request body

  ```json
  {
    "name": "João Alfredo",
    "cpf": "01234567890"
  }
  ```

- Response

  `201 (CREATED)`

  ```json
  {
    "id": 1,
    "name": "João Alfredo",
    "cpf": "01234567890"
  }
  ```

</details>
<br/>
<details>
<summary><b>GET /customers/:id</b></summary>

- Param

  The param `id` is expected to be added to the end of this route. It represents the ID of the customer whose information you want to access.

- Request body

  No body is expected in this route.

- Response

  `200 (OK)`

  ```json
  {
    "id": 1,
    "name": "João Alfredo",
    "cpf": "01234567890"
  }
  ```

</details>
<br/>
<details>
<summary><b>POST /rentals</b></summary>

- Param

  No param is expected in this route.

- Request body

  ```json
  {
    "customerId": 1,
    "gameId": 1,
    "daysRented": 3
  }
  ```

- Response

  `201 (CREATED)`

  ```json
  {
    "id": 1,
    "rentDate": "2021-06-20",
    "daysRented": 3,
    "returnDate": null,
    "originalPrice": 4500,
    "delayFee": 0,
    "customer": {
      "id": 1,
      "name": "João Alfredo",
      "cpf": "01234567890"
    },
    "game": {
      "id": 1,
      "name": "Monopoly",
      "image": "http://image-example.com/monopoly.jpg",
      "stockTotal": 3,
      "pricePerDay": 1500
    }
  }
  ```

</details>
<br/>
<details>
<summary><b>GET /rentals</b></summary>

- Param

  No param is expected in this route.

- Request body

  No body is expected in this route.

- Response

  `200 (OK)`

  ```json
  [
    {
      "id": 1,
      "rentDate": "2021-06-20",
      "daysRented": 3,
      "returnDate": null,
      "originalPrice": 4500,
      "delayFee": 0,
      "customer": {
        "id": 1,
        "name": "João Alfredo",
        "cpf": "01234567890"
      },
      "game": {
        "id": 1,
        "name": "Monopoly",
        "image": "http://image-example.com/monopoly.jpg",
        "stockTotal": 3,
        "pricePerDay": 1500
      }
    }
  ]
  ```

</details>
<br/>
<details>
<summary><b>PUT /rentals/:id/return</b></summary>

- Param

  The param `id` is expected to be added to this route, between `/rentals/` and `/return`, as shown above. It represents the ID of the rental that should be finalized when the customer returns the game they had rented.

Example: `http://localhost:8080/rentals/1/return`

- Request body

  No body is expected in this route.

- Response

  `200 (OK)`

  ```json
  {
    "id": 1,
    "rentDate": "2021-06-20",
    "daysRented": 3,
    "returnDate": "2021-06-25",
    "originalPrice": 4500,
    "delayFee": 3000,
    "customer": {
      "id": 1,
      "name": "João Alfredo",
      "cpf": "01234567890"
    },
    "game": {
      "id": 1,
      "name": "Monopoly",
      "image": "http://image-example.com/monopoly.jpg",
      "stockTotal": 3,
      "pricePerDay": 1500
    }
  }
  ```

</details>
