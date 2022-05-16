# ATM APP
## HOW TO USE
1. import ``atm-app-db.sql`` to mariadb
2. mariadb config hardcoded in ``com.atm.app.db.DB`` class
3. run ``gradle :gui:shadowJar`` to compile
4. run ``java -jar app/gui/build/libs/atm-app-1.0.0-all.jar``