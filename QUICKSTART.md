# Quick Start Guide - FundMe

## Schnellstart für Entwickler

### 1. Backend starten (Terminal 1)

```bash
cd backend
mvn spring-boot:run
```

Der Backend-Server läuft auf: http://localhost:8080

### 2. Frontend starten (Terminal 2)

```bash
cd frontend
npm install  # Nur beim ersten Mal
npm start
```

Die Anwendung ist verfügbar unter: http://localhost:4200

### 3. Erste Schritte

1. **Registrierung**: Öffnen Sie http://localhost:4200 und klicken Sie auf "Sign Up"
2. **Login**: Melden Sie sich mit Ihren erstellten Anmeldedaten an
3. **Projekt erstellen**: Klicken Sie auf "Create Project" in der Navigation
4. **Projekte durchsuchen**: Alle aktiven Projekte werden auf der Hauptseite angezeigt
5. **Projekt unterstützen**: Klicken Sie auf ein Projekt und nutzen Sie das Support-Formular

## H2-Datenbank-Konsole (optional)

Zum Anzeigen der Datenbank während der Entwicklung:

URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:fundme`
- Username: `sa`
- Password: (leer lassen)

## API testen mit curl

### Benutzer registrieren
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

### Projekte abrufen
```bash
curl http://localhost:8080/api/projects
```

### Projekt erstellen (mit JWT Token)
```bash
curl -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "title": "Mein Projekt",
    "description": "Eine tolle Projektidee",
    "fundingGoal": 1000
  }'
```

## Fehlerbehebung

### Backend startet nicht
- Prüfen Sie, ob Java 17+ installiert ist: `java -version`
- Prüfen Sie, ob Port 8080 verfügbar ist

### Frontend startet nicht
- Prüfen Sie, ob Node.js installiert ist: `node -v`
- Installieren Sie Dependencies neu: `rm -rf node_modules && npm install`
- Prüfen Sie, ob Port 4200 verfügbar ist

### CORS-Fehler
- Stellen Sie sicher, dass das Backend auf Port 8080 läuft
- Die CORS-Konfiguration erlaubt Anfragen von http://localhost:4200

## Nächste Schritte

- Erkunden Sie die API-Endpunkte in `README.md`
- Passen Sie das Design in den CSS-Dateien an
- Fügen Sie weitere Funktionen hinzu (z.B. Kommentare, Likes)
- Implementieren Sie Video-Upload-Funktionalität
