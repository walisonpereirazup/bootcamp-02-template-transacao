CREATE DATABASE keycloak;
CREATE USER keycloak WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak;

CREATE DATABASE transaction;
CREATE USER transaction WITH ENCRYPTED PASSWORD 'dHJhbnNhY3Rpb24K';
GRANT ALL PRIVILEGES ON DATABASE transaction TO transaction;