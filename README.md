# FundMe - Social Media Crowdfunding Platform

Eine Social-Media-Crowdfunding-Plattform, bei der Projekte sich per Videos oder Texten vorstellen und finanzielle Unterstützer finden können.

## Technologie-Stack

### Backend
- **Spring Boot 3.2.0** - Java-Framework
- **Spring Security** - Authentifizierung und Autorisierung
- **Spring Data JPA** - Datenbankzugriff
- **H2 Database** - In-Memory-Datenbank (Entwicklung)
- **JWT** - JSON Web Token für sichere Authentifizierung
- **Maven** - Build-Tool

### Frontend
- **Angular 17** - Frontend-Framework
- **TypeScript** - Programmiersprache
- **RxJS** - Reactive Programming
- **Angular Material** - UI-Komponenten
- **CSS3** - Styling

## Funktionen

### Benutzer
- ✅ Registrierung und Login
- ✅ JWT-basierte Authentifizierung
- ✅ Benutzerprofilverwaltung

### Projekte
- ✅ Projekte erstellen mit Titel, Beschreibung und Finanzierungsziel
- ✅ Projekte anzeigen (Liste und Details)
- ✅ Projektstatus verfolgen (ACTIVE, FUNDED, CLOSED, CANCELLED)
- ✅ Fortschrittsbalken für Finanzierung
- ✅ Deadline-Unterstützung

### Finanzierung
- ✅ Projekte finanziell unterstützen
- ✅ Nachricht an Projektersteller senden
- ✅ Unterstützer-Liste anzeigen
- ✅ Finanzierungsfortschritt in Echtzeit

### Medien
- ✅ Video- und Bild-Upload für Projekte
- ✅ Medienverwaltung

## Projektstruktur

```
fund_me/
├── backend/                    # Spring Boot Backend
│   ├── src/main/java/com/fundme/app/
│   │   ├── controller/        # REST Controllers
│   │   ├── model/             # Entity-Klassen
│   │   ├── repository/        # JPA Repositories
│   │   ├── service/           # Business Logic
│   │   ├── security/          # Security-Konfiguration
│   │   └── dto/               # Data Transfer Objects
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── frontend/                   # Angular Frontend
│   ├── src/app/
│   │   ├── components/        # UI-Komponenten
│   │   ├── services/          # API-Services
│   │   └── models/            # TypeScript-Interfaces
│   ├── angular.json
│   └── package.json
│
└── README.md
```

## Installation und Setup

### Voraussetzungen
- Java 17 oder höher
- Node.js 18 oder höher
- Maven 3.6 oder höher
- npm 8 oder höher

### Backend starten

1. In das Backend-Verzeichnis wechseln:
```bash
cd backend
```

2. Dependencies installieren und Projekt bauen:
```bash
mvn clean install
```

3. Anwendung starten:
```bash
mvn spring-boot:run
```

Das Backend läuft auf: `http://localhost:8080`

### Frontend starten

1. In das Frontend-Verzeichnis wechseln:
```bash
cd frontend
```

2. Dependencies installieren:
```bash
npm install
```

3. Development-Server starten:
```bash
npm start
```

Das Frontend läuft auf: `http://localhost:4200`

## API-Endpunkte

### Authentifizierung
- `POST /api/auth/signup` - Neuen Benutzer registrieren
- `POST /api/auth/login` - Benutzer anmelden

### Projekte
- `GET /api/projects` - Alle Projekte abrufen
- `GET /api/projects/active` - Aktive Projekte abrufen
- `GET /api/projects/{id}` - Projekt-Details abrufen
- `POST /api/projects` - Neues Projekt erstellen (Auth erforderlich)
- `PUT /api/projects/{id}` - Projekt aktualisieren (Auth erforderlich)
- `DELETE /api/projects/{id}` - Projekt löschen (Auth erforderlich)

### Finanzierung
- `GET /api/fundings/project/{projectId}` - Finanzierungen eines Projekts abrufen
- `POST /api/fundings/project/{projectId}` - Projekt unterstützen (Auth erforderlich)

### Medien
- `GET /api/media/project/{projectId}` - Medien eines Projekts abrufen
- `POST /api/media/project/{projectId}/upload` - Datei hochladen
- `DELETE /api/media/{id}` - Medium löschen

## H2-Konsole (Entwicklung)

Die H2-Datenbankkonsole ist unter folgender URL verfügbar:
```
http://localhost:8080/h2-console
```

**Verbindungsdetails:**
- JDBC URL: `jdbc:h2:mem:fundme`
- Benutzername: `sa`
- Passwort: (leer lassen)

## Verwendung

1. **Registrierung:** Erstellen Sie ein neues Konto über die Signup-Seite
2. **Login:** Melden Sie sich mit Ihren Anmeldedaten an
3. **Projekt erstellen:** Klicken Sie auf "Create Project" und füllen Sie das Formular aus
4. **Projekte durchsuchen:** Sehen Sie sich aktive Projekte auf der Projektseite an
5. **Projekt unterstützen:** Klicken Sie auf ein Projekt, um Details anzuzeigen und es zu unterstützen

## Sicherheit

- JWT-basierte Authentifizierung
- Password-Hashing mit BCrypt
- CORS-Konfiguration für Frontend-Backend-Kommunikation
- Input-Validierung auf Backend- und Frontend-Seite
- Sichere Session-Verwaltung

## Lizenz

MIT License - siehe [LICENSE](LICENSE) Datei

## Autor

MarcelDev - MarcelM264

## Hinweis

Dies ist ein Entwicklungsprojekt. Für den Produktionseinsatz sollten folgende Punkte beachtet werden:
- H2-Datenbank durch PostgreSQL/MySQL ersetzen
- Umgebungsvariablen für sensible Daten verwenden
- SSL/TLS für HTTPS aktivieren
- Rate Limiting implementieren
- File-Upload-Größenbeschränkungen konfigurieren
- Production-Build für Angular erstellen
