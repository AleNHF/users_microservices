# Documentación de la API

Esta API proporciona endpoints para gestionar usuarios y clientes. A continuación, se detallan los endpoints disponibles y los parámetros necesarios para cada solicitud.

## Base URL

La URL base de la API es: `https://api.example.com`

## Ejemplo para Usuarios

## Get users
{
  users {
    id
    username
    email
  }
}

## Get user by id
{
  user(id: "ID") {
    id
    username
    email
  }
}

## Create user
mutation {
  createUser(username: "USERNAME", email: "username@username.com", password: "PASSWORD") {
    id
    username
    email
  }
}

## Update user
mutation {
    updateUser(id: "ID", username: "USERNAME", email: "username@username.com") {
        id
      	username
        email
    }
}

## Delete user
mutation {
    deleteUser(id: "ID")

