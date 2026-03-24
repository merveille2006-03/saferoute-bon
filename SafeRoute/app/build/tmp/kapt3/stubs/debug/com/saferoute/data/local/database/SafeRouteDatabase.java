package com.saferoute.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.saferoute.data.local.dao.EmergencyContactDao;
import com.saferoute.data.local.dao.FallEventDao;
import com.saferoute.data.local.dao.SafeZoneDao;
import com.saferoute.data.local.dao.SOSAlertDao;
import com.saferoute.data.local.entity.EmergencyContactEntity;
import com.saferoute.data.local.entity.FallEventEntity;
import com.saferoute.data.local.entity.SafeZoneEntity;
import com.saferoute.data.local.entity.SOSAlertEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/saferoute/data/local/database/SafeRouteDatabase;", "Landroidx/room/RoomDatabase;", "()V", "emergencyContactDao", "Lcom/saferoute/data/local/dao/EmergencyContactDao;", "fallEventDao", "Lcom/saferoute/data/local/dao/FallEventDao;", "safeZoneDao", "Lcom/saferoute/data/local/dao/SafeZoneDao;", "sosAlertDao", "Lcom/saferoute/data/local/dao/SOSAlertDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.saferoute.data.local.entity.EmergencyContactEntity.class, com.saferoute.data.local.entity.SafeZoneEntity.class, com.saferoute.data.local.entity.FallEventEntity.class, com.saferoute.data.local.entity.SOSAlertEntity.class}, version = 1, exportSchema = true)
@androidx.room.TypeConverters(value = {com.saferoute.data.local.database.Converters.class})
public abstract class SafeRouteDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATABASE_NAME = "saferoute_database";
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.data.local.database.SafeRouteDatabase.Companion Companion = null;
    
    public SafeRouteDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.data.local.dao.EmergencyContactDao emergencyContactDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.data.local.dao.SafeZoneDao safeZoneDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.data.local.dao.FallEventDao fallEventDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.saferoute.data.local.dao.SOSAlertDao sosAlertDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/data/local/database/SafeRouteDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}