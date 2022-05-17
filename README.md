# ATM APP
## How To Start
1. import ``atm-app-db.sql`` to mariadb
2. mariadb config hardcoded in ``com.atm.app.db.DB`` class
3. run ``gradlew :gui:shadowJar`` to compile
## How To Run
```
java -jar app/gui/build/libs/atm-app-1.0.0-all.jar
```