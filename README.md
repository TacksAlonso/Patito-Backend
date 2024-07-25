# Patito-Backend
El sistema cuenta con 5 microservicios y una lib
 - [CUSTOMERS-SERVICES](https://github.com/TacksAlonso/Patito-Backend/tree/main/customers)
    - Servicio encargado de consultar, crear y eliminar clientes
 - [USERS-SERVICES](https://github.com/TacksAlonso/Patito-Backend/tree/main/users)
    - Servicio encargado de consultar, crear, eliminar y generar token para el consumir los demas servicios  
 - [ORDERS-SERVICES](https://github.com/TacksAlonso/Patito-Backend/tree/main/orders)
     - Servicio encargado de generar los pedidos, en este servicio se comunica con los servicios de INVENTORY-SERVICES y CUSTOMERS-SERVICES
 - [INVENTORY-SERVICES](https://github.com/TacksAlonso/Patito-Backend/tree/main/inventory)
     - Servicio encargado de gestionar la consulta de productos e inventario
 - [eureka.server](https://github.com/TacksAlonso/Patito-Backend/tree/main/eureka.server)
     - Servicio gestionador de descubrir las instancias de los demas servicios
 - [manager.token](https://github.com/TacksAlonso/Patito-Backend/tree/main/manager.token)
     - Libreria encargada de generar el token y es compartida con todos los servicios a expecion de eureka.server, en todo los demas permite validar que el acceso de token sea valido

***

Instalacion se debe correr el script que se anexa en el repositorio para poder crear las bases de datos, los servicios cada uno en automatico genera las tablas al conectarse a la base de datos

Se debe configurar los accesos a la base de datos en los archivos properties de cada servicio

Se anexa un archivo con la coleccion de peticiones que se tiene para los servicios