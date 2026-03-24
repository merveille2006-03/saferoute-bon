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
import com.saferoute.data.local.entity.SOSAlertEntity;
import com.saferoute.domain.model.LocationData;
import java.lang.Class;
import java.lang.Exception;
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
public final class SOSAlertDao_Impl implements SOSAlertDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SOSAlertEntity> __insertionAdapterOfSOSAlertEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<SOSAlertEntity> __deletionAdapterOfSOSAlertEntity;

  private final EntityDeletionOrUpdateAdapter<SOSAlertEntity> __updateAdapterOfSOSAlertEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatus;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDeliveredCount;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAlerts;

  public SOSAlertDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSOSAlertEntity = new EntityInsertionAdapter<SOSAlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `sos_alerts` (`id`,`userId`,`timestamp`,`location`,`triggerType`,`message`,`recipients`,`status`,`sentAt`,`deliveredCount`,`failedCount`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SOSAlertEntity entity) {
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
        if (entity.getTriggerType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTriggerType());
        }
        if (entity.getMessage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getMessage());
        }
        final String _tmp_1 = __converters.fromStringList(entity.getRecipients());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        if (entity.getStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStatus());
        }
        if (entity.getSentAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getSentAt());
        }
        statement.bindLong(10, entity.getDeliveredCount());
        statement.bindLong(11, entity.getFailedCount());
      }
    };
    this.__deletionAdapterOfSOSAlertEntity = new EntityDeletionOrUpdateAdapter<SOSAlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `sos_alerts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SOSAlertEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSOSAlertEntity = new EntityDeletionOrUpdateAdapter<SOSAlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `sos_alerts` SET `id` = ?,`userId` = ?,`timestamp` = ?,`location` = ?,`triggerType` = ?,`message` = ?,`recipients` = ?,`status` = ?,`sentAt` = ?,`deliveredCount` = ?,`failedCount` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SOSAlertEntity entity) {
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
        if (entity.getTriggerType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTriggerType());
        }
        if (entity.getMessage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getMessage());
        }
        final String _tmp_1 = __converters.fromStringList(entity.getRecipients());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        if (entity.getStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStatus());
        }
        if (entity.getSentAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getSentAt());
        }
        statement.bindLong(10, entity.getDeliveredCount());
        statement.bindLong(11, entity.getFailedCount());
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE sos_alerts SET status = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDeliveredCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE sos_alerts SET deliveredCount = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllAlerts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM sos_alerts WHERE userId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAlert(final SOSAlertEntity alert,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSOSAlertEntity.insertAndReturnId(alert);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAlert(final SOSAlertEntity alert,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSOSAlertEntity.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAlert(final SOSAlertEntity alert,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSOSAlertEntity.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStatus(final long alertId, final String status,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatus.acquire();
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, status);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfUpdateStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDeliveredCount(final long alertId, final int count,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDeliveredCount.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, count);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfUpdateDeliveredCount.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllAlerts(final String userId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAlerts.acquire();
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
          __preparedStmtOfDeleteAllAlerts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllAlerts(final String userId,
      final Continuation<? super List<SOSAlertEntity>> $completion) {
    final String _sql = "SELECT * FROM sos_alerts WHERE userId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SOSAlertEntity>>() {
      @Override
      @NonNull
      public List<SOSAlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfTriggerType = CursorUtil.getColumnIndexOrThrow(_cursor, "triggerType");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfRecipients = CursorUtil.getColumnIndexOrThrow(_cursor, "recipients");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "sentAt");
          final int _cursorIndexOfDeliveredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final List<SOSAlertEntity> _result = new ArrayList<SOSAlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SOSAlertEntity _item;
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
            final String _tmpTriggerType;
            if (_cursor.isNull(_cursorIndexOfTriggerType)) {
              _tmpTriggerType = null;
            } else {
              _tmpTriggerType = _cursor.getString(_cursorIndexOfTriggerType);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final List<String> _tmpRecipients;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRecipients)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRecipients);
            }
            _tmpRecipients = __converters.toStringList(_tmp_1);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Long _tmpSentAt;
            if (_cursor.isNull(_cursorIndexOfSentAt)) {
              _tmpSentAt = null;
            } else {
              _tmpSentAt = _cursor.getLong(_cursorIndexOfSentAt);
            }
            final int _tmpDeliveredCount;
            _tmpDeliveredCount = _cursor.getInt(_cursorIndexOfDeliveredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            _item = new SOSAlertEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpTriggerType,_tmpMessage,_tmpRecipients,_tmpStatus,_tmpSentAt,_tmpDeliveredCount,_tmpFailedCount);
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
  public Flow<List<SOSAlertEntity>> getAllAlertsFlow(final String userId) {
    final String _sql = "SELECT * FROM sos_alerts WHERE userId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sos_alerts"}, new Callable<List<SOSAlertEntity>>() {
      @Override
      @NonNull
      public List<SOSAlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfTriggerType = CursorUtil.getColumnIndexOrThrow(_cursor, "triggerType");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfRecipients = CursorUtil.getColumnIndexOrThrow(_cursor, "recipients");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "sentAt");
          final int _cursorIndexOfDeliveredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final List<SOSAlertEntity> _result = new ArrayList<SOSAlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SOSAlertEntity _item;
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
            final String _tmpTriggerType;
            if (_cursor.isNull(_cursorIndexOfTriggerType)) {
              _tmpTriggerType = null;
            } else {
              _tmpTriggerType = _cursor.getString(_cursorIndexOfTriggerType);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final List<String> _tmpRecipients;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRecipients)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRecipients);
            }
            _tmpRecipients = __converters.toStringList(_tmp_1);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Long _tmpSentAt;
            if (_cursor.isNull(_cursorIndexOfSentAt)) {
              _tmpSentAt = null;
            } else {
              _tmpSentAt = _cursor.getLong(_cursorIndexOfSentAt);
            }
            final int _tmpDeliveredCount;
            _tmpDeliveredCount = _cursor.getInt(_cursorIndexOfDeliveredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            _item = new SOSAlertEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpTriggerType,_tmpMessage,_tmpRecipients,_tmpStatus,_tmpSentAt,_tmpDeliveredCount,_tmpFailedCount);
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
  public Object getAlertById(final long alertId,
      final Continuation<? super SOSAlertEntity> $completion) {
    final String _sql = "SELECT * FROM sos_alerts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, alertId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SOSAlertEntity>() {
      @Override
      @Nullable
      public SOSAlertEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfTriggerType = CursorUtil.getColumnIndexOrThrow(_cursor, "triggerType");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfRecipients = CursorUtil.getColumnIndexOrThrow(_cursor, "recipients");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "sentAt");
          final int _cursorIndexOfDeliveredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final SOSAlertEntity _result;
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
            final String _tmpTriggerType;
            if (_cursor.isNull(_cursorIndexOfTriggerType)) {
              _tmpTriggerType = null;
            } else {
              _tmpTriggerType = _cursor.getString(_cursorIndexOfTriggerType);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final List<String> _tmpRecipients;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRecipients)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRecipients);
            }
            _tmpRecipients = __converters.toStringList(_tmp_1);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Long _tmpSentAt;
            if (_cursor.isNull(_cursorIndexOfSentAt)) {
              _tmpSentAt = null;
            } else {
              _tmpSentAt = _cursor.getLong(_cursorIndexOfSentAt);
            }
            final int _tmpDeliveredCount;
            _tmpDeliveredCount = _cursor.getInt(_cursorIndexOfDeliveredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            _result = new SOSAlertEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpTriggerType,_tmpMessage,_tmpRecipients,_tmpStatus,_tmpSentAt,_tmpDeliveredCount,_tmpFailedCount);
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
  public Object getAlertsByStatus(final String userId, final String status,
      final Continuation<? super List<SOSAlertEntity>> $completion) {
    final String _sql = "SELECT * FROM sos_alerts WHERE userId = ? AND status = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SOSAlertEntity>>() {
      @Override
      @NonNull
      public List<SOSAlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfTriggerType = CursorUtil.getColumnIndexOrThrow(_cursor, "triggerType");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfRecipients = CursorUtil.getColumnIndexOrThrow(_cursor, "recipients");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSentAt = CursorUtil.getColumnIndexOrThrow(_cursor, "sentAt");
          final int _cursorIndexOfDeliveredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredCount");
          final int _cursorIndexOfFailedCount = CursorUtil.getColumnIndexOrThrow(_cursor, "failedCount");
          final List<SOSAlertEntity> _result = new ArrayList<SOSAlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SOSAlertEntity _item;
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
            final String _tmpTriggerType;
            if (_cursor.isNull(_cursorIndexOfTriggerType)) {
              _tmpTriggerType = null;
            } else {
              _tmpTriggerType = _cursor.getString(_cursorIndexOfTriggerType);
            }
            final String _tmpMessage;
            if (_cursor.isNull(_cursorIndexOfMessage)) {
              _tmpMessage = null;
            } else {
              _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            }
            final List<String> _tmpRecipients;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRecipients)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRecipients);
            }
            _tmpRecipients = __converters.toStringList(_tmp_1);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final Long _tmpSentAt;
            if (_cursor.isNull(_cursorIndexOfSentAt)) {
              _tmpSentAt = null;
            } else {
              _tmpSentAt = _cursor.getLong(_cursorIndexOfSentAt);
            }
            final int _tmpDeliveredCount;
            _tmpDeliveredCount = _cursor.getInt(_cursorIndexOfDeliveredCount);
            final int _tmpFailedCount;
            _tmpFailedCount = _cursor.getInt(_cursorIndexOfFailedCount);
            _item = new SOSAlertEntity(_tmpId,_tmpUserId,_tmpTimestamp,_tmpLocation,_tmpTriggerType,_tmpMessage,_tmpRecipients,_tmpStatus,_tmpSentAt,_tmpDeliveredCount,_tmpFailedCount);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
