package com.saferoute.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.saferoute.data.local.database.Converters;
import com.saferoute.data.local.entity.FallEventEntity;
import com.saferoute.domain.model.LocationData;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FallEventDao_Impl implements FallEventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FallEventEntity> __insertionAdapterOfFallEventEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<FallEventEntity> __deletionAdapterOfFallEventEntity;

  private final EntityDeletionOrUpdateAdapter<FallEventEntity> __updateAdapterOfFallEventEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateConfirmation;

  private final SharedSQLiteStatement __preparedStmtOfMarkAlertSent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFallEvents;

  public FallEventDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFallEventEntity = new EntityInsertionAdapter<FallEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `fall_events` (`id`,`userId`,`timestamp`,`location`,`impactForce`,`fallDuration`,`isConfirmed`,`responseTime`,`alertSent`,`alertSentAt`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FallEventEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        statement.bindLong(3, entity.getTimestamp());
        final String _tmp = __converters.fromLocationData(entity.getLocation());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getImpactForce());
        statement.bindLong(6, entity.getFallDuration());
        final Integer _tmp_1 = entity.isConfirmed() == null ? null : (entity.isConfirmed() ? 1 : 0);
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_1);
        }
        if (entity.getResponseTime() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getResponseTime());
        }
        final int _tmp_2 = entity.getAlertSent() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        if (entity.getAlertSentAt() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getAlertSentAt());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfFallEventEntity = new EntityDeletionOrUpdateAdapter<FallEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `fall_events` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FallEventEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfFallEventEntity = new EntityDeletionOrUpdateAdapter<FallEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `fall_events` SET `id` = ?,`userId` = ?,`timestamp` = ?,`location` = ?,`impactForce` = ?,`fallDuration` = ?,`isConfirmed` = ?,`responseTime` = ?,`alertSent` = ?,`alertSentAt` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FallEventEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        statement.bindLong(3, entity.getTimestamp());
        final String _tmp = __converters.fromLocationData(entity.getLocation());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getImpactForce());
        statement.bindLong(6, entity.getFallDuration());
        final Integer _tmp_1 = entity.isConfirmed() == null ? null : (entity.isConfirmed() ? 1 : 0);
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp_1);
        }
        if (entity.getResponseTime() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getResponseTime());
        }
        final int _tmp_2 = entity.getAlertSent() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        if (entity.getAlertSentAt() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getAlertSentAt());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNotes());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateConfirmation = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE fall_events SET isConfirmed = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAlertSent = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE fall_events SET alertSent = 1, alertSentAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllFallEvents = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM fall_events WHERE userId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertFallEvent(final FallEventEntity event,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFallEventEntity.insertAndReturnId(event);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFallEvent(final FallEventEntity event,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFallEventEntity.handle(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateFallEvent(final FallEventEntity event,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFallEventEntity.handle(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateConfirmation(final long eventId, final boolean isConfirmed,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateConfirmation.acquire();
        int _argIndex = 1;
        final int _tmp = isConfirmed ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, eventId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateConfirmation.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAlertSent(final long eventId, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAlertSent.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, eventId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkAlertSent.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllFallEvents(final String userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllFallEvents.acquire();
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllFallEvents.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getFallHistory(final String userId, final int limit,
      final Continuation<? super List<FallEventEntity>> $completion) {
    final String _sql = "SELECT * FROM fall_events WHERE userId = ? ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FallEventEntity>>() {
      @Override
      @NonNull
      public List<FallEventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfImpactForce = CursorUtil.getColumnIndexOrThrow(_cursor, "impactForce");
          final int _cursorIndexOfFallDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "fallDuration");
          final int _cursorIndexOfIsConfirmed = CursorUtil.getColumnIndexOrThrow(_cursor, "isConfirmed");
          final int _cursorIndexOfResponseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "responseTime");
          final int _cursorIndexOfAlertSent = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSent");
          final int _cursorIndexOfAlertSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSentAt");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<FallEventEntity> _result = new ArrayList<FallEventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FallEventEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final LocationData _tmpLocation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfLocation);
            }
            _tmpLocation = __converters.toLocationData(_tmp);
            final double _tmpImpactForce;
            _tmpImpactForce = _cursor.getDouble(_cursorIndexOfImpactForce);
            final long _tmpFallDuration;
            _tmpFallDuration = _cursor.getLong(_cursorIndexOfFallDuration);
            final Boolean _tmpIsConfirmed;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfIsConfirmed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsConfirmed);
            }
            _tmpIsConfirmed = _tmp_1 == null ? null : _tmp_1 != 0;
            final Long _tmpResponseTime;
            if (_cursor.isNull(_cursorIndexOfResponseTime)) {
              _tmpResponseTime = null;
            } else {
              _tmpResponseTime = _cursor.getLong(_cursorIndexOfResponseTime);
            }
            final boolean _tmpAlertSent;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAlertSent);
            _tmpAlertSent = _tmp_2 != 0;
            final Long _tmpAlertSentAt;
            if (_cursor.isNull(_cursorIndexOfAlertSentAt)) {
              _tmpAlertSentAt = null;
            } else {
              _tmpAlertSentAt = _cursor.getLong(_cursorIndexOfAlertSentAt);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new FallEventEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpImpactForce,_tmpFallDuration,_tmpIsConfirmed,_tmpResponseTime,_tmpAlertSent,_tmpAlertSentAt,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<FallEventEntity>> getFallHistoryFlow(final String userId) {
    final String _sql = "SELECT * FROM fall_events WHERE userId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"fall_events"}, new Callable<List<FallEventEntity>>() {
      @Override
      @NonNull
      public List<FallEventEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfImpactForce = CursorUtil.getColumnIndexOrThrow(_cursor, "impactForce");
          final int _cursorIndexOfFallDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "fallDuration");
          final int _cursorIndexOfIsConfirmed = CursorUtil.getColumnIndexOrThrow(_cursor, "isConfirmed");
          final int _cursorIndexOfResponseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "responseTime");
          final int _cursorIndexOfAlertSent = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSent");
          final int _cursorIndexOfAlertSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSentAt");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<FallEventEntity> _result = new ArrayList<FallEventEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FallEventEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final LocationData _tmpLocation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfLocation);
            }
            _tmpLocation = __converters.toLocationData(_tmp);
            final double _tmpImpactForce;
            _tmpImpactForce = _cursor.getDouble(_cursorIndexOfImpactForce);
            final long _tmpFallDuration;
            _tmpFallDuration = _cursor.getLong(_cursorIndexOfFallDuration);
            final Boolean _tmpIsConfirmed;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfIsConfirmed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsConfirmed);
            }
            _tmpIsConfirmed = _tmp_1 == null ? null : _tmp_1 != 0;
            final Long _tmpResponseTime;
            if (_cursor.isNull(_cursorIndexOfResponseTime)) {
              _tmpResponseTime = null;
            } else {
              _tmpResponseTime = _cursor.getLong(_cursorIndexOfResponseTime);
            }
            final boolean _tmpAlertSent;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAlertSent);
            _tmpAlertSent = _tmp_2 != 0;
            final Long _tmpAlertSentAt;
            if (_cursor.isNull(_cursorIndexOfAlertSentAt)) {
              _tmpAlertSentAt = null;
            } else {
              _tmpAlertSentAt = _cursor.getLong(_cursorIndexOfAlertSentAt);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new FallEventEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpImpactForce,_tmpFallDuration,_tmpIsConfirmed,_tmpResponseTime,_tmpAlertSent,_tmpAlertSentAt,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getFallEventById(final long eventId,
      final Continuation<? super FallEventEntity> $completion) {
    final String _sql = "SELECT * FROM fall_events WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FallEventEntity>() {
      @Override
      @Nullable
      public FallEventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfImpactForce = CursorUtil.getColumnIndexOrThrow(_cursor, "impactForce");
          final int _cursorIndexOfFallDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "fallDuration");
          final int _cursorIndexOfIsConfirmed = CursorUtil.getColumnIndexOrThrow(_cursor, "isConfirmed");
          final int _cursorIndexOfResponseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "responseTime");
          final int _cursorIndexOfAlertSent = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSent");
          final int _cursorIndexOfAlertSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSentAt");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final FallEventEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final LocationData _tmpLocation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfLocation);
            }
            _tmpLocation = __converters.toLocationData(_tmp);
            final double _tmpImpactForce;
            _tmpImpactForce = _cursor.getDouble(_cursorIndexOfImpactForce);
            final long _tmpFallDuration;
            _tmpFallDuration = _cursor.getLong(_cursorIndexOfFallDuration);
            final Boolean _tmpIsConfirmed;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfIsConfirmed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsConfirmed);
            }
            _tmpIsConfirmed = _tmp_1 == null ? null : _tmp_1 != 0;
            final Long _tmpResponseTime;
            if (_cursor.isNull(_cursorIndexOfResponseTime)) {
              _tmpResponseTime = null;
            } else {
              _tmpResponseTime = _cursor.getLong(_cursorIndexOfResponseTime);
            }
            final boolean _tmpAlertSent;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAlertSent);
            _tmpAlertSent = _tmp_2 != 0;
            final Long _tmpAlertSentAt;
            if (_cursor.isNull(_cursorIndexOfAlertSentAt)) {
              _tmpAlertSentAt = null;
            } else {
              _tmpAlertSentAt = _cursor.getLong(_cursorIndexOfAlertSentAt);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new FallEventEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpImpactForce,_tmpFallDuration,_tmpIsConfirmed,_tmpResponseTime,_tmpAlertSent,_tmpAlertSentAt,_tmpNotes);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPendingFallEvent(final String userId,
      final Continuation<? super FallEventEntity> $completion) {
    final String _sql = "SELECT * FROM fall_events WHERE userId = ? AND alertSent = 0 AND isConfirmed IS NULL ORDER BY timestamp DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FallEventEntity>() {
      @Override
      @Nullable
      public FallEventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfImpactForce = CursorUtil.getColumnIndexOrThrow(_cursor, "impactForce");
          final int _cursorIndexOfFallDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "fallDuration");
          final int _cursorIndexOfIsConfirmed = CursorUtil.getColumnIndexOrThrow(_cursor, "isConfirmed");
          final int _cursorIndexOfResponseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "responseTime");
          final int _cursorIndexOfAlertSent = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSent");
          final int _cursorIndexOfAlertSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "alertSentAt");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final FallEventEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final LocationData _tmpLocation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfLocation);
            }
            _tmpLocation = __converters.toLocationData(_tmp);
            final double _tmpImpactForce;
            _tmpImpactForce = _cursor.getDouble(_cursorIndexOfImpactForce);
            final long _tmpFallDuration;
            _tmpFallDuration = _cursor.getLong(_cursorIndexOfFallDuration);
            final Boolean _tmpIsConfirmed;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfIsConfirmed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsConfirmed);
            }
            _tmpIsConfirmed = _tmp_1 == null ? null : _tmp_1 != 0;
            final Long _tmpResponseTime;
            if (_cursor.isNull(_cursorIndexOfResponseTime)) {
              _tmpResponseTime = null;
            } else {
              _tmpResponseTime = _cursor.getLong(_cursorIndexOfResponseTime);
            }
            final boolean _tmpAlertSent;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfAlertSent);
            _tmpAlertSent = _tmp_2 != 0;
            final Long _tmpAlertSentAt;
            if (_cursor.isNull(_cursorIndexOfAlertSentAt)) {
              _tmpAlertSentAt = null;
            } else {
              _tmpAlertSentAt = _cursor.getLong(_cursorIndexOfAlertSentAt);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new FallEventEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpImpactForce,_tmpFallDuration,_tmpIsConfirmed,_tmpResponseTime,_tmpAlertSent,_tmpAlertSentAt,_tmpNotes);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
