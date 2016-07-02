CREATE USER 'prm'@'localhost' IDENTIFIED BY 'prm123456';

GRANT all ON prm.* TO 'prm'@'localhost';

flush privileges;

CREATE DATABASE `prm` DEFAULT CHARACTER SET utf8;

use prm;
