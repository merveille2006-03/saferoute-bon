# SafeRoute - Structure du projet

## Vue d'ensemble
Ce document décrit la structure complète du projet SafeRoute Android.

## Arborescence

```
SafeRoute/
├── app/
│   ├── build.gradle.kts              # Configuration du module app
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml   # Manifeste de l'application
│       │   ├── java/com/saferoute/
│       │   │   ├── SafeRouteApplication.kt
│       │   │   ├── MainActivity.kt
│       │   │   ├── data/
│       │   │   │   ├── local/
│       │   │   │   │   ├── database/
│       │   │   │   │   │   ├── SafeRouteDatabase.kt
│       │   │   │   │   │   └── Converters.kt
│       │   │   │   │   ├── dao/
│       │   │   │   │   │   ├── EmergencyContactDao.kt
│       │   │   │   │   │   ├── FallEventDao.kt
│       │   │   │   │   │   ├── SafeZoneDao.kt
│       │   │   │   │   │   └── SOSAlertDao.kt
│       │   │   │   │   └── entity/
│       │   │   │   │       ├── EmergencyContactEntity.kt
│       │   │   │   │       ├── FallEventEntity.kt
│       │   │   │   │       ├── SafeZoneEntity.kt
│       │   │   │   │       └── SOSAlertEntity.kt
│       │   │   │   └── repository/
│       │   │   │       ├── AlertRepositoryImpl.kt
│       │   │   │       ├── EmergencyContactRepositoryImpl.kt
│       │   │   │       ├── FallDetectionRepositoryImpl.kt
│       │   │   │       ├── LocationRepositoryImpl.kt
│       │   │   │       ├── SafeZoneRepositoryImpl.kt
│       │   │   │       └── SettingsRepositoryImpl.kt
│       │   │   ├── di/
│       │   │   │   └── AppModule.kt
│       │   │   ├── domain/
│       │   │   │   ├── model/
│       │   │   │   │   ├── AppSettings.kt
│       │   │   │   │   ├── EmergencyContact.kt
│       │   │   │   │   ├── FallEvent.kt
│       │   │   │   │   ├── LocationData.kt
│       │   │   │   │   ├── SafeZone.kt
│       │   │   │   │   ├── SOSAlert.kt
│       │   │   │   │   └── User.kt
│       │   │   │   └── repository/
│       │   │   │       ├── AlertRepository.kt
│       │   │   │       ├── EmergencyContactRepository.kt
│       │   │   │       ├── FallDetectionRepository.kt
│       │   │   │       ├── LocationRepository.kt
│       │   │   │       ├── SafeZoneRepository.kt
│       │   │   │       └── SettingsRepository.kt
│       │   │   ├── presentation/
│       │   │   │   ├── components/
│       │   │   │   │   ├── SOSButton.kt
│       │   │   │   │   └── StatusCard.kt
│       │   │   │   ├── screens/
│       │   │   │   │   ├── AlertHistoryScreen.kt
│       │   │   │   │   ├── ContactsScreen.kt
│       │   │   │   │   ├── FallHistoryScreen.kt
│       │   │   │   │   ├── HomeScreen.kt
│       │   │   │   │   ├── LoginScreen.kt
│       │   │   │   │   ├── PermissionsScreen.kt
│       │   │   │   │   ├── SafeZonesScreen.kt
│       │   │   │   │   └── SettingsScreen.kt
│       │   │   │   ├── theme/
│       │   │   │   │   ├── Color.kt
│       │   │   │   │   ├── Theme.kt
│       │   │   │   │   └── Type.kt
│       │   │   │   └── viewmodels/
│       │   │   │       ├── AlertHistoryViewModel.kt
│       │   │   │       ├── AuthViewModel.kt
│       │   │   │       ├── ContactsViewModel.kt
│       │   │   │       ├── FallHistoryViewModel.kt
│       │   │   │       ├── MainViewModel.kt
│       │   │   │       ├── SafeZonesViewModel.kt
│       │   │   │       └── SettingsViewModel.kt
│       │   │   └── service/
│       │   │       ├── BluetoothService.kt
│       │   │       ├── BootReceiver.kt
│       │   │       ├── FallDetectionService.kt
│       │   │       ├── LocationTrackingService.kt
│       │   │       └── SOSAlertService.kt
│       │   └── res/
│       │       ├── drawable/
│       │       │   ├── ic_alert.xml
│       │       │   ├── ic_cancel.xml
│       │       │   ├── ic_check.xml
│       │       │   ├── ic_error.xml
│       │       │   ├── ic_fall_detection.xml
│       │       │   ├── ic_location.xml
│       │       │   ├── ic_notification_important.xml
│       │       │   ├── ic_safe_zone.xml
│       │       │   ├── ic_sos.xml
│       │       │   └── ic_warning.xml
│       │       ├── values/
│       │       │   ├── colors.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       └── xml/
│       │           ├── backup_rules.xml
│       │           └── data_extraction_rules.xml
│       ├── test/
│       │   └── java/com/saferoute/
│       │       ├── EmergencyContactTest.kt
│       │       ├── ExampleUnitTest.kt
│       │       ├── FallDetectionTest.kt
│       │       ├── SafeZoneTest.kt
│       │       ├── SettingsTest.kt
│       │       └── SOSAlertTest.kt
│       └── androidTest/
│           └── java/com/saferoute/
│               ├── ContactsScreenTest.kt
│               ├── ExampleInstrumentedTest.kt
│               ├── HomeScreenTest.kt
│               └── LoginScreenTest.kt
├── build.gradle.kts                  # Configuration racine
├── settings.gradle.kts               # Paramètres du projet
├── gradle.properties                 # Propriétés Gradle
├── local.properties                  # Propriétés locales (SDK)
├── proguard-rules.pro                # Règles ProGuard
├── DESIGN.md                         # Documentation design
├── README.md                         # Documentation principale
└── PROJECT_STRUCTURE.md              # Ce fichier

```

## Statistiques

- **Fichiers Kotlin** : 50+
- **Fichiers XML** : 20+
- **Tests unitaires** : 6 fichiers
- **Tests instrumentés** : 4 fichiers
- **Lignes de code estimées** : 5000+

## Modules principaux

### 1. Domain (Clean Architecture)
- **Models** : Classes de données pures
- **Repository Interfaces** : Contrats pour l'accès aux données

### 2. Data
- **Local** : Room Database, DAOs, Entities
- **Repository Implementations** : Implémentations concrètes

### 3. Presentation (MVVM)
- **Screens** : Composables Jetpack Compose
- **ViewModels** : Gestion d'état
- **Components** : Composables réutilisables
- **Theme** : Couleurs, typographie, thèmes

### 4. Service
- **FallDetectionService** : Service de détection de chute
- **LocationTrackingService** : Suivi GPS en arrière-plan
- **SOSAlertService** : Gestion des alertes SOS
- **BluetoothService** : Communication hors ligne
- **BootReceiver** : Démarrage automatique

### 5. DI (Dependency Injection)
- **AppModule** : Modules Hilt pour l'injection

## Dépendances principales

- AndroidX Core & Compose
- Hilt (DI)
- Room (Database)
- DataStore (Preferences)
- Google Play Services Location
- Biometric API
- Accompanist (Permissions)
- JUnit 5 & Espresso (Tests)

## Configuration requise

- **minSdk** : 26 (Android 8.0)
- **targetSdk** : 34 (Android 14)
- **compileSdk** : 34
- **Java/Kotlin** : 17

## Prochaines étapes

1. Configurer la clé API Google Maps
2. Tester sur un appareil physique
3. Configurer les signatures pour release
4. Publier sur Google Play Store
