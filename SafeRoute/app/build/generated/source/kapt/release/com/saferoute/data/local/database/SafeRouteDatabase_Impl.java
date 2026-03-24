package com.saferoute.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.saferoute.data.local.dao.EmergencyContactDao;
import com.saferoute.data.local.dao.EmergencyContactDao_Impl;
import com.saferoute.data.local.dao.FallEventDao;
import com.saferoute.data.local.dao.FallEventDao_Impl;
import com.saferoute.data.local.dao.SOSAlertDao;
import com.saferoute.data.local.dao.SOSAlertDao_Impl;
import com.saferoute.data.local.dao.SafeZoneDao;
import com.saferoute.data.local.dao.SafeZoneDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SafeRouteDatabase_Impl extends SafeRouteDatabase {
  private volatile EmergencyContactDao _emergencyContactDao;

  private volatile SafeZoneDao _safeZoneDao;

  private volatile FallEventDao _fallEventDao;

  private volatile SOSAlertDao _sOSAlertDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `emergency_contacts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `name` TEXT NOT NULL, `phoneNumber` TEXT NOT NULL, `email` TEXT, `relationship` TEXT NOT NULL, `priority` INTEGER NOT NULL, `isEnabled` INTEGER NOT NULL, `notifyOnFall` INTEGER NOT NULL, `notifyOnSOS` INTEGER NOT NULL, `notifyOnSafeZoneExit` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_emergency_contacts_userId` ON `emergency_contacts` (`userId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `safe_zones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `name` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `radius` REAL NOT NULL, `type` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `alertOnExit` INTEGER NOT NULL, `alertOnEnter` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_safe_zones_userId` ON `safe_zones` (`userId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `fall_events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `location` TEXT, `impactForce` REAL NOT NULL, `fallDuration` INTEGER NOT NULL, `isConfirmed` INTEGER, `responseTime` INTEGER, `alertSent` INTEGER NOT NULL, `alertSentAt` INTEGER, `notes` TEXT)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_fall_events_userId` ON `fall_events` (`userId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_fall_events_timestamp` ON `fall_events` (`timestamp`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `sos_alerts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `location` TEXT, `triggerType` TEXT NOT NULL, `message` TEXT NOT NULL, `recipients` TEXT NOT NULL, `status` TEXT NOT NULL, `sentAt` INTEGER, `deliveredCount` INTEGER NOT NULL, `failedCount` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sos_alerts_userId` ON `sos_alerts` (`userId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sos_alerts_timestamp` ON `sos_alerts` (`timestamp`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c2d5864d1e554bdd96137b45e7c85812')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `emergency_contacts`");
        db.execSQL("DROP TABLE IF EXISTS `safe_zones`");
        db.execSQL("DROP TABLE IF EXISTS `fall_events`");
        db.execSQL("DROP TABLE IF EXISTS `sos_alerts`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsEmergencyContacts = new HashMap<String, TableInfo.Column>(12);
        _columnsEmergencyContacts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("relationship", new TableInfo.Column("relationship", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("notifyOnFall", new TableInfo.Column("notifyOnFall", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("notifyOnSOS", new TableInfo.Column("notifyOnSOS", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("notifyOnSafeZoneExit", new TableInfo.Column("notifyOnSafeZoneExit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmergencyContacts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEmergencyContacts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEmergencyContacts = new HashSet<TableInfo.Index>(1);
        _indicesEmergencyContacts.add(new TableInfo.Index("index_emergency_contacts_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        final TableInfo _infoEmergencyContacts = new TableInfo("emergency_contacts", _columnsEmergencyContacts, _foreignKeysEmergencyContacts, _indicesEmergencyContacts);
        final TableInfo _existingEmergencyContacts = TableInfo.read(db, "emergency_contacts");
        if (!_infoEmergencyContacts.equals(_existingEmergencyContacts)) {
          return new RoomOpenHelper.ValidationResult(false, "emergency_contacts(com.saferoute.data.local.entity.EmergencyContactEntity).\n"
                  + " Expected:\n" + _infoEmergencyContacts + "\n"
                  + " Found:\n" + _existingEmergencyContacts);
        }
        final HashMap<String, TableInfo.Column> _columnsSafeZones = new HashMap<String, TableInfo.Column>(11);
        _columnsSafeZones.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("radius", new TableInfo.Column("radius", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("alertOnExit", new TableInfo.Column("alertOnExit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("alertOnEnter", new TableInfo.Column("alertOnEnter", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSafeZones.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSafeZones = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSafeZones = new HashSet<TableInfo.Index>(1);
        _indicesSafeZones.add(new TableInfo.Index("index_safe_zones_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        final TableInfo _infoSafeZones = new TableInfo("safe_zones", _columnsSafeZones, _foreignKeysSafeZones, _indicesSafeZones);
        final TableInfo _existingSafeZones = TableInfo.read(db, "safe_zones");
        if (!_infoSafeZones.equals(_existingSafeZones)) {
          return new RoomOpenHelper.ValidationResult(false, "safe_zones(com.saferoute.data.local.entity.SafeZoneEntity).\n"
                  + " Expected:\n" + _infoSafeZones + "\n"
                  + " Found:\n" + _existingSafeZones);
        }
        final HashMap<String, TableInfo.Column> _columnsFallEvents = new HashMap<String, TableInfo.Column>(11);
        _columnsFallEvents.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("impactForce", new TableInfo.Column("impactForce", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("fallDuration", new TableInfo.Column("fallDuration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("isConfirmed", new TableInfo.Column("isConfirmed", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("responseTime", new TableInfo.Column("responseTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("alertSent", new TableInfo.Column("alertSent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("alertSentAt", new TableInfo.Column("alertSentAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFallEvents.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFallEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFallEvents = new HashSet<TableInfo.Index>(2);
        _indicesFallEvents.add(new TableInfo.Index("index_fall_events_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        _indicesFallEvents.add(new TableInfo.Index("index_fall_events_timestamp", false, Arrays.asList("timestamp"), Arrays.asList("ASC")));
        final TableInfo _infoFallEvents = new TableInfo("fall_events", _columnsFallEvents, _foreignKeysFallEvents, _indicesFallEvents);
        final TableInfo _existingFallEvents = TableInfo.read(db, "fall_events");
        if (!_infoFallEvents.equals(_existingFallEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "fall_events(com.saferoute.data.local.entity.FallEventEntity).\n"
                  + " Expected:\n" + _infoFallEvents + "\n"
                  + " Found:\n" + _existingFallEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsSosAlerts = new HashMap<String, TableInfo.Column>(11);
        _columnsSosAlerts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("triggerType", new TableInfo.Column("triggerType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("recipients", new TableInfo.Column("recipients", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("sentAt", new TableInfo.Column("sentAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("deliveredCount", new TableInfo.Column("deliveredCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSosAlerts.put("failedCount", new TableInfo.Column("failedCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSosAlerts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSosAlerts = new HashSet<TableInfo.Index>(2);
        _indicesSosAlerts.add(new TableInfo.Index("index_sos_alerts_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        _indicesSosAlerts.add(new TableInfo.Index("index_sos_alerts_timestamp", false, Arrays.asList("timestamp"), Arrays.asList("ASC")));
        final TableInfo _infoSosAlerts = new TableInfo("sos_alerts", _columnsSosAlerts, _foreignKeysSosAlerts, _indicesSosAlerts);
        final TableInfo _existingSosAlerts = TableInfo.read(db, "sos_alerts");
        if (!_infoSosAlerts.equals(_existingSosAlerts)) {
          return new RoomOpenHelper.ValidationResult(false, "sos_alerts(com.saferoute.data.local.entity.SOSAlertEntity).\n"
                  + " Expected:\n" + _infoSosAlerts + "\n"
                  + " Found:\n" + _existingSosAlerts);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c2d5864d1e554bdd96137b45e7c85812", "4a2b4ea090d7aa38ec9b6640b5692cd8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "emergency_contacts","safe_zones","fall_events","sos_alerts");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `emergency_contacts`");
      _db.execSQL("DELETE FROM `safe_zones`");
      _db.execSQL("DELETE FROM `fall_events`");
      _db.execSQL("DELETE FROM `sos_alerts`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(EmergencyContactDao.class, EmergencyContactDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SafeZoneDao.class, SafeZoneDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FallEventDao.class, FallEventDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SOSAlertDao.class, SOSAlertDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public EmergencyContactDao emergencyContactDao() {
    if (_emergencyContactDao != null) {
      return _emergencyContactDao;
    } else {
      synchronized(this) {
        if(_emergencyContactDao == null) {
          _emergencyContactDao = new EmergencyContactDao_Impl(this);
        }
        return _emergencyContactDao;
      }
    }
  }

  @Override
  public SafeZoneDao safeZoneDao() {
    if (_safeZoneDao != null) {
      return _safeZoneDao;
    } else {
      synchronized(this) {
        if(_safeZoneDao == null) {
          _safeZoneDao = new SafeZoneDao_Impl(this);
        }
        return _safeZoneDao;
      }
    }
  }

  @Override
  public FallEventDao fallEventDao() {
    if (_fallEventDao != null) {
      return _fallEventDao;
    } else {
      synchronized(this) {
        if(_fallEventDao == null) {
          _fallEventDao = new FallEventDao_Impl(this);
        }
        return _fallEventDao;
      }
    }
  }

  @Override
  public SOSAlertDao sosAlertDao() {
    if (_sOSAlertDao != null) {
      return _sOSAlertDao;
    } else {
      synchronized(this) {
        if(_sOSAlertDao == null) {
          _sOSAlertDao = new SOSAlertDao_Impl(this);
        }
        return _sOSAlertDao;
      }
    }
  }
}
