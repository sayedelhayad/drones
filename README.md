# anti-fraud-server



## Getting started
There are 5 API's:
1- registerDrone (POST: "/drones")
        to register new drone
2- loadDrone (POST: "/{droneSN}/load")      
        to load drone by medications
3- getMedications (POST: "/{droneSN}/medications")
        get list of medications for a specific drone
4- getDronesByState (POST: "/state/{droneState}")
        get drone by state and can be used to get loadable drone by this path "/state/IDLE"
5- getDronesBatteryLevel (POST: "/{droneSN}/battery-level")
        get battery level for a specific drone

and you can check the attached postman collection

```
cd existing_repo
git remote add origin https://github.com/sayedelhayad/drones.git
git branch -M master
git push -uf origin master
```

## Description
initial description
