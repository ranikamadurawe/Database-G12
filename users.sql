GRANT ALL PRIVILEGES ON *.* TO 'administrator'@'localhost' IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441' WITH GRANT OPTION;

GRANT USAGE ON *.* TO 'emp'@'localhost' IDENTIFIED BY PASSWORD '*8531F7C0C119E82E3764B969DA287264F47C36FD';

GRANT SELECT ON `welfare`.`emppay` TO 'emp'@'localhost';

GRANT SELECT ON `welfare`.`empcontact` TO 'emp'@'localhost';

GRANT SELECT ON `welfare`.`employeepersonal` TO 'emp'@'localhost';

GRANT SELECT ON `welfare`.`leavesubmissions` TO 'emp'@'localhost';

GRANT SELECT, INSERT ON `welfare`.`empemergenct` TO 'emp'@'localhost';

GRANT SELECT ON `welfare`.`leaveleft` TO 'emp'@'localhost';

GRANT SELECT ON `welfare`.`useraccount` TO 'emp'@'localhost';

GRANT USAGE ON *.* TO 'hr'@'localhost' IDENTIFIED BY PASSWORD '*0179FD6366890BF591858A797A4CBC8218D37BA7';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE VIEW, SHOW VIEW ON `welfare`.* TO 'hr'@'localhost';

GRANT USAGE ON *.* TO 'manager'@'localhost' IDENTIFIED BY PASSWORD '*A8CF0E1E5E44B6CA473F382BDBCCD754DEED97EA';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`employeedetails` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`estatus` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`employeeadditional` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`emppay` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`leaveleft` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`leavesubmissions` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`employeepersonal` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`empcontact` TO 'manager'@'localhost';

GRANT SELECT, INSERT, UPDATE ON `welfare`.`empemergenct` TO 'manager'@'localhost';

GRANT SELECT, INSERT ON `welfare`.`useraccount` TO 'manager'@'localhost';

GRANT USAGE ON *.* TO 'loggingin'@'localhost' IDENTIFIED BY PASSWORD '*DA3A26F09A9747BD86CFB21D5873484D2C4B9728';

GRANT SELECT (title, eid) ON `welfare`.`employeedetails` TO 'loggingin'@'localhost';

GRANT SELECT ON `welfare`.`jobtitiles` TO 'loggingin'@'localhost';

GRANT SELECT (cpass, cuname, name) ON `welfare`.`userroles` TO 'loggingin'@'localhost';

GRANT SELECT, INSERT ON `welfare`.`useraccount` TO 'loggingin'@'localhost';

