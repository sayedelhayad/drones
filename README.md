# drones-server



## Getting started
There are 5 API's:

1- registerDrone (POST: "/drones")

        to register new drone
		
2- loadDrone (POST: "/{droneSN}/load")  
    
        to load drone by medications
		
3- getMedications (GET: "/{droneSN}/medications")

        get list of medications for a specific drone

4- getAvailableDrones (GET: "/state/IDLE")

        get available drone for loadable

5- getDronesByState (GET: "/state/{droneState}")

        get drone by state and can be used to get loadable drone by this path "/state/IDLE"

6- getDronesBatteryLevel (GET: "/{droneSN}/battery-level")

        get battery level for a specific drone
		

and you can check the attached postman collection (/files/drones.postman_collection.json)

to run unit test cases run:

mvn clean install

```
cd existing_repo
git clone https://github.com/sayedelhayad/drones.git
```

## Description
initial description
