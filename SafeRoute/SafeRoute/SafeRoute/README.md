# SafeRoute

SafeRoute est une application mobile Android de sécurité personnelle conçue pour alerter vos proches en cas de danger, même lorsque vous n'êtes plus en mesure de le faire vous-même.

## Fonctionnalités

### Détection de chute
- Surveillance en arrière-plan via l'accéléromètre
- Détection intelligente des chutes avec analyse de l'impact
- 30 secondes pour répondre avant l'envoi automatique d'alerte
- Possibilité d'annuler la fausse alerte

### Suivi GPS et Zones sécurisées
- Suivi de position en temps réel
- Définition de zones habituelles (domicile, travail, école)
- Alerte automatique en cas de sortie anormale d'une zone
- Partage de position précise avec les contacts d'urgence

### Bouton SOS
- Alerte immédiate en un seul tap
- Compte à rebours configurable (3-10 secondes)
- Envoi de SMS avec position GPS aux contacts d'urgence
- Fonctionne même sans connexion Internet (via Bluetooth)

### Sécurité
- Authentification par empreinte digitale
- Protection des données sensibles
- Chiffrement des informations personnelles

### Communication hors ligne
- Fonctionnalité Bluetooth pour les zones sans réseau
- Transmission directe entre appareils SafeRoute
- Mode dégradé garantissant les fonctionnalités essentielles

## Architecture

### MVVM (Model-View-ViewModel)
- **Model** : Entités Room, Data classes Kotlin
- **View** : Écrans Jetpack Compose
- **ViewModel** : Gestion de l'état et logique métier

### Couches de données
- **Domain** : Modèles, interfaces de repository, use cases
- **Data** : Implémentations des repositories, DAOs, API
- **Presentation** : UI, ViewModels, thèmes

### Injection de dépendances
- **Hilt** pour l'injection de dépendances
- Modules pour Database, Location, Repositories

## Technologies utilisées

- **Kotlin** - Langage principal
- **Jetpack Compose** - UI moderne et déclarative
- **Hilt** - Injection de dépendances
- **Room** - Base de données locale
- **DataStore** - Préférences utilisateur
- **Coroutines & Flow** - Programmation asynchrone
- **Google Play Services Location** - Localisation GPS
- **Biometric API** - Authentification biométrique
- **Bluetooth LE** - Communication hors ligne

## Tests

### Tests unitaires (JUnit 5)
- `FallDetectionTest` - Tests de détection de chute
- `SafeZoneTest` - Tests des zones sécurisées
- `SOSAlertTest` - Tests des alertes SOS
- `SettingsTest` - Tests des paramètres
- `EmergencyContactTest` - Tests des contacts

### Tests instrumentés (Espresso)
- `LoginScreenTest` - Tests de l'écran de connexion
- `HomeScreenTest` - Tests de l'écran d'accueil
- `ContactsScreenTest` - Tests de l'écran contacts

## Permissions requises

- `ACCESS_FINE_LOCATION` - Localisation précise
- `ACCESS_BACKGROUND_LOCATION` - Localisation en arrière-plan
- `SEND_SMS` - Envoi d'alertes SMS
- `FOREGROUND_SERVICE` - Services en premier plan
- `USE_BIOMETRIC` - Authentification biométrique
- `BLUETOOTH` / `BLUETOOTH_ADMIN` - Communication Bluetooth
- `POST_NOTIFICATIONS` - Notifications (Android 13+)

## Installation

1. Cloner le repository
2. Ouvrir dans Android Studio
3. Synchroniser le projet avec Gradle
4. Configurer votre clé API Google Maps dans `AndroidManifest.xml`
5. Lancer l'application sur un appareil ou émulateur

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="VOTRE_CLE_API" />
```

## Configuration

### PIN par défaut
Le code PIN par défaut est `1234`. Changez-le dans les paramètres après la première connexion.

### Contacts d'urgence
Ajoutez au moins un contact d'urgence pour que les alertes fonctionnent correctement.

### Zones sécurisées
Définissez vos zones habituelles (domicile, travail) pour activer la détection de sortie anormale.

## Design

Le dossier `DESIGN.md` contient la documentation complète du design :
- Palette de couleurs
- Typographie
- Icônes (RemixIcon / Font Awesome)
- Composants UI
- Écrans
- Animations

## Structure du projet

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/saferoute/
│   │   │   ├── data/
│   │   │   │   ├── local/          # Database, DAOs, Entities
│   │   │   │   └── repository/     # Repository implementations
│   │   │   ├── domain/
│   │   │   │   ├── model/          # Data models
│   │   │   │   └── repository/     # Repository interfaces
│   │   │   ├── presentation/
│   │   │   │   ├── components/     # Reusable UI components
│   │   │   │   ├── screens/        # Screen composables
│   │   │   │   ├── theme/          # Colors, Typography, Theme
│   │   │   │   └── viewmodels/     # ViewModels
│   │   │   ├── service/            # Background services
│   │   │   ├── di/                 # Hilt modules
│   │   │   ├── SafeRouteApplication.kt
│   │   │   └── MainActivity.kt
│   │   └── res/                    # Resources
│   ├── test/                       # Unit tests
│   └── androidTest/                # Instrumented tests
└── build.gradle.kts
```

## Licence

Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.

## Auteur

Développé avec ❤️ pour la sécurité de tous.

## Remerciements

- [RemixIcon](https://remixicon.com/) pour les icônes
- [Font Awesome](https://fontawesome.com/) pour les icônes alternatives
- Material Design 3 pour les composants UI
