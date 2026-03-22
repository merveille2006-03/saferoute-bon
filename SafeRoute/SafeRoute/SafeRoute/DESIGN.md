# SafeRoute - Design Documentation

## Table des matières
1. [Vue d'ensemble](#vue-densemble)
2. [Identité visuelle](#identité-visuelle)
3. [Palette de couleurs](#palette-de-couleurs)
4. [Typographie](#typographie)
5. [Icônes](#icônes)
6. [Composants UI](#composants-ui)
7. [Écrans](#écrans)
8. [Animations](#animations)

---

## Vue d'ensemble

SafeRoute est une application de sécurité personnelle avec une interface moderne, épurée et accessible. Le design priorise la clarté et la rapidité d'action en situation d'urgence.

### Principes de design
- **Clarté** : Interface intuitive et facile à comprendre
- **Rapidité** : Actions d'urgence accessibles en un minimum de taps
- **Accessibilité** : Contraste élevé, tailles de texte adaptables
- **Rassurante** : Couleurs et ton rassurants malgré la nature sérieuse de l'application

---

## Identité visuelle

### Logo
Le logo SafeRoute combine un bouclier de protection avec un élément de localisation, symbolisant la sécurité et le suivi GPS.

### Nom de l'application
**SafeRoute** - Écrit en Police Bold, couleur Primary Blue (#2563EB)

---

## Palette de couleurs

### Couleurs principales

| Nom | Hex | Usage |
|-----|-----|-------|
| Primary Blue | `#2563EB` | Boutons principaux, liens, header |
| Primary Blue Dark | `#1D4ED8` | États hover/pressed |
| Primary Blue Light | `#60A5FA` | Survols, accents |

### Couleurs secondaires

| Nom | Hex | Usage |
|-----|-----|-------|
| Secondary Teal | `#14B8A6` | Zones sécurisées, succès |
| Secondary Teal Dark | `#0D9488` | États actifs |

### Couleurs d'alerte

| Nom | Hex | Usage |
|-----|-----|-------|
| Alert Red | `#DC2626` | Bouton SOS, alertes critiques |
| Alert Red Dark | `#B91C1C` | SOS pressed |
| Alert Orange | `#F97316` | Avertissements |
| Warning Yellow | `#FBBF24` | Notifications de précaution |

### Couleurs de succès

| Nom | Hex | Usage |
|-----|-----|-------|
| Success Green | `#22C55E` | Statuts actifs, confirmations |
| Success Green Dark | `#16A34A` | États validés |

### Nuances de gris

| Nom | Hex | Usage |
|-----|-----|-------|
| Gray 50 | `#F9FAFB` | Fonds clairs |
| Gray 100 | `#F3F4F6` | Cartes, séparations |
| Gray 200 | `#E5E7EB` | Bordures |
| Gray 300 | `#D1D5DB` | Icônes inactives |
| Gray 400 | `#9CA3AF` | Texte secondaire |
| Gray 500 | `#6B7280` | Labels |
| Gray 600 | `#4B5563` | Texte principal |
| Gray 700 | `#374151` | Titres |
| Gray 800 | `#1F2937` | Texte foncé |
| Gray 900 | `#111827` | Titres foncés |

### Couleurs spécifiques

| Nom | Hex | Usage |
|-----|-----|-------|
| Safe Zone Green | `#10B981` | Indicateurs de zone sécurisée |
| Safe Zone Border | `#34D399` | Bordures de zones |

---

## Typographie

### Police
- **Système** : Roboto (Android par défaut)
- **Fallback** : Sans-serif

### Hiérarchie

| Style | Taille | Poids | Usage |
|-------|--------|-------|-------|
| Display Large | 57sp | Bold | Grands titres |
| Display Medium | 45sp | Bold | Titres importants |
| Headline Large | 32sp | SemiBold | Titres de section |
| Headline Medium | 28sp | SemiBold | Titres de page |
| Title Large | 22sp | Medium | Titres de cartes |
| Title Medium | 16sp | Medium | Sous-titres |
| Body Large | 16sp | Regular | Texte principal |
| Body Medium | 14sp | Regular | Texte secondaire |
| Label Large | 14sp | Medium | Boutons |
| Label Medium | 12sp | Medium | Tags |
| Label Small | 11sp | Medium | Légendes |

---

## Icônes

### Bibliothèque d'icônes
**RemixIcon** - https://remixicon.com/

Alternative : **Font Awesome** - https://fontawesome.com/

### Icônes principales

| Icône | Nom RemixIcon | Usage |
|-------|---------------|-------|
| 🏠 | `ri-home-line` / `ri-home-fill` | Zones - Domicile |
| 🏢 | `ri-building-line` / `ri-building-fill` | Zones - Travail |
| 🎓 | `ri-school-line` / `ri-school-fill` | Zones - École |
| 📍 | `ri-map-pin-line` / `ri-map-pin-fill` | Localisation |
| 🔔 | `ri-notification-line` / `ri-notification-fill` | Notifications |
| ⚠️ | `ri-alert-line` / `ri-alert-fill` | Alertes |
| 🚨 | `ri-alarm-warning-line` / `ri-alarm-warning-fill` | SOS |
| 👤 | `ri-user-line` / `ri-user-fill` | Contacts |
| 👥 | `ri-team-line` / `ri-team-fill` | Liste de contacts |
| ⚙️ | `ri-settings-line` / `ri-settings-fill` | Paramètres |
| 📊 | `ri-bar-chart-line` / `ri-bar-chart-fill` | Statistiques |
| 📜 | `ri-history-line` / `ri-history-fill` | Historique |
| 🔒 | `ri-lock-line` / `ri-lock-fill` | Sécurité |
| 🔓 | `ri-lock-unlock-line` / `ri-lock-unlock-fill` | Déverrouiller |
| 👆 | `ri-fingerprint-line` / `ri-fingerprint-fill` | Biométrie |
| 📶 | `ri-bluetooth-line` / `ri-bluetooth-fill` | Bluetooth |
| 📡 | `ri-wifi-line` / `ri-wifi-fill` | Connexion |
| ✅ | `ri-check-line` / `ri-check-fill` | Validation |
| ❌ | `ri-close-line` / `ri-close-fill` | Fermer/Annuler |
| 🗑️ | `ri-delete-bin-line` / `ri-delete-bin-fill` | Supprimer |
| ✏️ | `ri-edit-line` / `ri-edit-fill` | Modifier |
| ➕ | `ri-add-line` / `ri-add-fill` | Ajouter |
| ⬅️ | `ri-arrow-left-line` / `ri-arrow-left-fill` | Retour |
| ↻ | `ri-refresh-line` / `ri-refresh-fill` | Rafraîchir |
| 🔍 | `ri-search-line` / `ri-search-fill` | Rechercher |
| 📞 | `ri-phone-line` / `ri-phone-fill` | Téléphone |
| ✉️ | `ri-mail-line` / `ri-mail-fill` | Email |
| 💬 | `ri-message-line` / `ri-message-fill` | Messages |
| 🎯 | `ri-focus-3-line` / `ri-focus-3-fill` | Ciblage |
| 🛡️ | `ri-shield-check-line` / `ri-shield-check-fill` | Protection |
| ⚡ | `ri-flashlight-line` / `ri-flashlight-fill` | Flash |
| 🔊 | `ri-volume-up-line` / `ri-volume-up-fill` | Son |
| 📳 | `ri-vibration-line` / `ri-vibration-fill` | Vibration |
| 🏃 | `ri-run-line` / `ri-run-fill` | Détection de mouvement |
| 💥 | `ri-meteor-line` / `ri-meteor-fill` | Impact/Chute |
| 📵 | `ri-spam-line` / `ri-spam-fill` | Fausse alerte |
| 🌍 | `ri-earth-line` / `ri-earth-fill` | GPS/Localisation |

### Icônes personnalisées (Vector Drawable)

Les icônes suivantes sont créées en tant que Vector Drawables dans `res/drawable/`:

1. **ic_fall_detection.xml** - Silhouette de personne avec indicateur de chute
2. **ic_sos.xml** - Bouton SOS stylisé
3. **ic_safe_zone.xml** - Zone circulaire avec icône de maison
4. **ic_location_tracking.xml** - Point GPS avec cercle de pulsation
5. **ic_bluetooth_alert.xml** - Bluetooth avec symbole d'alerte

---

## Composants UI

### Bouton SOS
- **Forme** : Cercle parfait
- **Taille** : 200dp x 200dp
- **Couleur** : Dégradé radial (Alert Red → Alert Red Dark)
- **Animation** : Pulsation continue quand inactif
- **Interaction** : Appui long avec compte à rebours
- **Texte** : "SOS" en gras + "Maintenez pour alerter"

### Cartes de statut
- **Forme** : Rectangle arrondi (12dp)
- **Padding** : 12dp
- **Ombre** : Élévation 2dp
- **État actif** : Fond vert clair, icône verte
- **État inactif** : Fond gris, icône grise

### Cartes de contact
- **Forme** : Rectangle arrondi (12dp)
- **Structure** : 
  - Indicateur de priorité (cercle numéroté)
  - Avatar/Initiales
  - Nom + Téléphone
  - Switch d'activation
  - Bouton suppression

### Cartes de zone sécurisée
- **Forme** : Rectangle arrondi (12dp)
- **Structure** :
  - Icône de type (maison/travail/école)
  - Nom de la zone
  - Rayon en mètres
  - Badge "À l'intérieur" (si applicable)
  - Switch d'activation

### Dialogues
- **Forme** : Rectangle arrondi (16dp)
- **Padding** : 24dp
- **Titre** : Headline Small
- **Contenu** : Body Medium
- **Boutons** : Alignés à droite, espacés de 8dp

---

## Écrans

### Écran de connexion
- **Fond** : Blanc/Gris très clair
- **Logo** : Centré, 120dp
- **Titre** : "SafeRoute" - Display Medium, Primary Blue
- **Sous-titre** : "Votre sécurité, notre priorité" - Body Large, Gray 500
- **Champs** : 
  - PIN (4 chiffres, masqué)
- **Boutons** :
  - "Se connecter" - Primary, pleine largeur
  - "Empreinte digitale" - Outlined (si disponible)

### Écran d'accueil
- **Header** : Logo + Bouton paramètres
- **Section statut** : 3 cartes horizontales (Protection, GPS, Zones)
- **Alerte chute** : Carte rouge animée (conditionnelle)
- **Bouton SOS** : Centré, grande taille
- **Actions rapides** : Contacts, Zones, Historique

### Écran Contacts
- **Header** : Flèche retour + Titre + Actions
- **Liste** : Cartes de contact empilées
- **FAB** : Bouton "+" en bas à droite, Primary Blue
- **État vide** : Icône + Texte explicatif

### Écran Zones sécurisées
- **Header** : Flèche retour + Titre + Rafraîchir
- **Carte** : Aperçu Google Maps (optionnel)
- **Liste** : Cartes de zones
- **FAB** : Bouton "+" en bas à droite, Safe Zone Green

### Écran Paramètres
- **Header** : Flèche retour + Titre
- **Sections** :
  - Protection (switches)
  - Détection de chute (sensibilité)
  - SOS (compte à rebours)
  - Sécurité (biométrie)
  - Notifications (sons/vibrations)
  - Communication (Bluetooth)
- **Bouton reset** : En bas, couleur Alert Red

### Écran Historique
- **Header** : Flèche retour + Titre + Rafraîchir
- **Stats** : Carte avec statistiques
- **Liste** : Événements chronologiques
- **Filtres** : Tous / Chutes / SOS / Zones (segmented control)

---

## Animations

### Transitions
- **Navigation** : Slide horizontal (300ms, ease-out)
- **Apparition** : Fade + slide up (200ms)
- **Disparition** : Fade out (150ms)

### Micro-interactions
- **Boutons** : Scale 0.95 on press (100ms)
- **Switches** : Slide avec rebond
- **Cartes** : Élévation augmente on press
- **FAB** : Rotation 45° on press

### Animations spéciales
- **Bouton SOS** : 
  - Anneau pulsant (scale 1.0 → 1.1, 1s, infinite)
  - Compte à rebours (chiffres qui changent)
- **Alerte chute** :
  - Slide down + fade in
  - Vibration du téléphone
  - Son d'alerte
- **Notification** :
  - Slide from top
  - Badge avec nombre

### Durées standard
- **Rapide** : 100ms (micro-interactions)
- **Normal** : 200ms (transitions)
- **Lent** : 300ms (navigation)
- **Compte à rebours** : 1000ms par seconde

---

## Accessibilité

### Contraste
- Ratio minimum : 4.5:1 pour le texte normal
- Ratio minimum : 3:1 pour le texte grand (18sp+)
- Ratio minimum : 3:1 pour les composants UI

### Tailles tactiles
- Minimum : 48dp x 48dp
- Bouton SOS : 200dp x 200dp (exception)

### Contenu
- Labels descriptifs pour tous les éléments interactifs
- Ordre de focus logique
- Support TalkBack complet

### Réduction de mouvement
- Respecter `prefers-reduced-motion`
- Désactiver les animations pulsantes
- Conserver les transitions simples

---

## Ressources

### Fichiers
- `colors.xml` : Toutes les couleurs définies
- `themes.xml` : Thèmes clair et sombre
- `strings.xml` : Tous les textes (français)
- `Typography.kt` : Styles de texte Compose

### Assets
- Logo : `ic_launcher_foreground.xml`
- Icônes : Vector drawables dans `res/drawable/`
- Sons : `res/raw/` (alerte, confirmation)

---

## Notes d'implémentation

### Jetpack Compose
- Utiliser `MaterialTheme` comme base
- Créer un `SafeRouteTheme` personnalisé
- Définir les couleurs dans `Color.kt`
- Définir la typographie dans `Type.kt`

### Icônes
- Préférer les Vector Drawables
- Utiliser `Icon` de Compose avec `ImageVector`
- Créer des `ImageVector` personnalisés si nécessaire

### Responsive
- Utiliser `Modifier.fillMaxWidth()` avec des `padding`
- Éviter les tailles fixes sauf pour les icônes
- Tester sur différentes tailles d'écran

---

*Document créé pour SafeRoute - Version 1.0*
