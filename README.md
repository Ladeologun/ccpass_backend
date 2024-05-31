Finclusion CCPPAS application
===

To run this and see tables generated
* Create a database called **ccppass** on postgresql
* Go to `application.yml` and input your database `username` and `password`
* Make sure your maven dependencies are updated. You can do this in intelliJ my reloading maven
* Then Run the application.
* After running the application a default super Admin with the `password: 00000000` and `fmoj_id: FMOJ11111111` is
  created in the system.
* You can use this information to login and proceed testing the endpoints available
  in the swagger documentation available on this URL
  `localhost:8080/swagger-ui/index.html#/`