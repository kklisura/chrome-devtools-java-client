package com.github.kklisura.cdtp.protocol.events.database;

import com.github.kklisura.cdtp.protocol.types.database.Database;

public class AddDatabase {

  private Database database;

  public Database getDatabase() {
    return database;
  }

  public void setDatabase(Database database) {
    this.database = database;
  }
}
