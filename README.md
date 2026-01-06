# IPWA02-01_Ghost_Net_Fishing

Prototyp einer Webanwendung zur Meldung und Bergung von Geisternetzen (herrenlose Fischernetze im Meer). Das Projekt basiert auf der Aufgabenstellung „Ghost Net Fishing“ und setzt die fachlichen Anforderungen prototypisch um.


## 🧭 Funktionen

- Geisternetze melden (auch anonym)
- Meldende und bergende Personen verwalten
- Anzeige aller noch zu bergenden Geisternetze
- Bergung einem Geisternetz zuordnen
- Netze als **geborgen** oder **verschollen** markieren
- Weltkartenansicht offener Geisternetze

## ⚙️ Features

- Web-Frontend mit **Thymeleaf**
- Backend mit **Spring Boot** (Java 17)
- Speicherung der Daten in einer **MySQL-Datenbank**
- Nutzung von **JPA-Entitäten** für `GhostNet` und `Person`
- Datenzugriffe über **Spring Data JPA**
- Automatische DDL-Erstellung über Hibernate (`ddl-auto=update`)

## 🚀 Tech-Stack

- Java 17
- Spring Boot 4
- Maven
- Thymeleaf
- MySQL
- Hibernate / JPA
