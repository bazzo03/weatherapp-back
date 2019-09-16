# Readme File

### Objective of the application

The application will handle requests to find weather conditions for a specific city. 


### Run the application

The application is built as a SpringBoot application and uses Maven.

Deploy the application with Maven Install setting in goals:
```bash
spring-boot:run
```  

### Technologies Used

Technology	|	Version
----------	|	-------
Java		|	1.8
Maven		|	3.5.0
SpringBoot	|   2.1.4	

### Docker
The application has a Dockerfile in order to make it possible to deploy it on containers.
  
### Endpoints

```bash
 * http://{server}:{port}/api/weather/{cityName}?apiServer=weatherUnlocked
 * http://{server}:{port}/api/weather/{cityName}?apiServer=apixuWeather
```

### Layers
The project has been built with the following architecture layers:
```bash
project
    ````client              -------->           Fiegn connections to the 2 weather apis
    ````controller          -------->           Rest controller of the application
    ````converter           -------->           Converters from dto to entity and viceversa
    ````dto                 -------->           dto to communicate with other services
    ````entity              -------->           entity representation for weather
    ````exception           -------->           project's exceptions
    ````util                -------->           utility classes of the project
    ````service             -------->           services definition
        ````impl            -------->           services implementation
```