# graphql-auth-dummy
An experiment with authentication model in springboot

Sample project for GraphQL authentication post

1. [Dev.to](https://dev.to/mahendranv/graphql-backend-authorization-authentication-4goe)

2. [My Blog](https://mahendranv.github.io/blog/2021-05-31-gql-auth/)


To run this project

```

./gradlew bootRun

```


GraphiQL playground

```

http://localhost:8080/graphiql

```

Sample query

```graphql


mutation { 
    addProduct 
    placeOrder 
}

### header
"x-auth-token":"token-seller"
"x-auth-token":"token-buyer"


```
