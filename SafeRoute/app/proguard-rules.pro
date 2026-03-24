# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep model classes
-keep class com.saferoute.domain.model.** { *; }
-keep class com.saferoute.data.local.entity.** { *; }

# Keep serialization
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Kotlin serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class kotlinx.serialization.** { *; }

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# Hilt
-keep class * extends dagger.hilt.** { *; }
-keep class * extends javax.inject.** { *; }

# Google Play Services
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# Biometric
-keep class androidx.biometric.** { *; }

# Timber
-dontwarn timber.log.Timber

# General
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
