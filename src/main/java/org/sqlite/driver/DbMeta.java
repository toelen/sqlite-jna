/*
 * The author disclaims copyright to this source code.  In place of
 * a legal notice, here is a blessing:
 *
 *    May you do good and not evil.
 *    May you find forgiveness for yourself and forgive others.
 *    May you share freely, never taking more than you give.
 */
package org.sqlite.driver;

import org.sqlite.ColAffinities;
import org.sqlite.SQLite;

import java.sql.*;
import java.util.*;

public class DbMeta implements DatabaseMetaData {
  private Conn c;

  public DbMeta(Conn c) {
    this.c = c;
  }

  private void checkOpen() throws SQLException {
    if (c == null) throw new SQLException("connection closed");
  }

  private org.sqlite.Conn getConn() throws SQLException {
    checkOpen();
    return c.getConn();
  }

  @Override
  public boolean allProceduresAreCallable() throws SQLException {
    return false;
  }
  @Override
  public boolean allTablesAreSelectable() throws SQLException {
    return true;
  }
  @Override
  public String getURL() throws SQLException {
    return getConn().getFilename();
  }
  @Override
  public String getUserName() throws SQLException {
    return null;
  }
  @Override
  public boolean isReadOnly() throws SQLException {
    return getConn().isReadOnly();
  }
  @Override
  public boolean nullsAreSortedHigh() throws SQLException {
    return false;
  }
  @Override
  public boolean nullsAreSortedLow() throws SQLException {
    return true;
  }
  @Override
  public boolean nullsAreSortedAtStart() throws SQLException {
    return false;
  }
  @Override
  public boolean nullsAreSortedAtEnd() throws SQLException {
    return false;
  }
  @Override
  public String getDatabaseProductName() throws SQLException {
    return "SQLite 3";
  }
  @Override
  public String getDatabaseProductVersion() throws SQLException {
    return org.sqlite.Conn.libversion();
  }
  @Override
  public String getDriverName() throws SQLException {
    return "SQLiteJNA";
  }
  @Override
  public String getDriverVersion() throws SQLException {
    return "1.0"; // FIXME
  }
  @Override
  public int getDriverMajorVersion() {
    return 1; // FIXME Keep in sync with Driver
  }
  @Override
  public int getDriverMinorVersion() {
    return 0; // FIXME Keep in sync with Driver
  }
  @Override
  public boolean usesLocalFiles() throws SQLException {
    return true;
  }
  @Override
  public boolean usesLocalFilePerTable() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsMixedCaseIdentifiers() throws SQLException {
    return true;
  }
  @Override
  public boolean storesUpperCaseIdentifiers() throws SQLException {
    return false;
  }
  @Override
  public boolean storesLowerCaseIdentifiers() throws SQLException {
    return false;
  }
  @Override
  public boolean storesMixedCaseIdentifiers() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
    return true;
  }
  @Override
  public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
    return false;
  }
  @Override
  public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
    return false;
  }
  @Override
  public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
    return true;
  }
  @Override
  public String getIdentifierQuoteString() throws SQLException {
    return "\""; // http://sqlite.org/lang_keywords.html
  }
  @Override
  public String getSQLKeywords() throws SQLException {
    return "ABORT,ANALYZE,ATTACH,AUTOINCREMENT,CONFLICT,DATABASE,DETACH,EXCLUSIVE,EXPLAIN,FAIL,GLOB,IF,IGNORE,INDEX,INDEXED,INSTEAD,ISNULL,LIMIT,NOTNULL,OFFSET,PLAN,PRAGMA,QUERY,RAISE,REGEXP,REINDEX,RENAME,REPLACE,RESTRICT,TEMP,VACUUM,VIRTUAL";
  }
  @Override
  public String getNumericFunctions() throws SQLException {
    return "abs,max,min,round,random";
  }
  @Override
  public String getStringFunctions() throws SQLException {
    return "glob,length,like,lower,ltrim,replace,rtrim,soundex,substr,trim,upper";
  }
  @Override
  public String getSystemFunctions() throws SQLException {
    return "last_insert_rowid,load_extension,sqlite_version";
  }
  @Override
  public String getTimeDateFunctions() throws SQLException {
    return "date,time,datetime,julianday,strftime";
  }
  @Override
  public String getSearchStringEscape() throws SQLException {
    Util.trace("DatabaseMetaData.getSearchStringEscape");
    return null; // TODO Validate (Y LIKE X [ESCAPE Z])
  }
  @Override
  public String getExtraNameCharacters() throws SQLException {
    return "";
  }
  @Override
  public boolean supportsAlterTableWithAddColumn() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsAlterTableWithDropColumn() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsColumnAliasing() throws SQLException {
    return true;
  }
  @Override
  public boolean nullPlusNonNullIsNull() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsConvert() throws SQLException {
    Util.trace("DatabaseMetaData.supportsConvert");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsConvert(int fromType, int toType) throws SQLException {
    Util.trace("DatabaseMetaData.supportsConvert");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsTableCorrelationNames() throws SQLException {
    Util.trace("DatabaseMetaData.supportsTableCorrelationNames");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsDifferentTableCorrelationNames() throws SQLException {
    Util.trace("DatabaseMetaData.supportsDifferentTableCorrelationNames");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsExpressionsInOrderBy() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsOrderByUnrelated() throws SQLException {
    return false; // TODO Validate
  }
  @Override
  public boolean supportsGroupBy() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsGroupByUnrelated() throws SQLException {
    return false; // TODO Validate
  }
  @Override
  public boolean supportsGroupByBeyondSelect() throws SQLException {
    return false; // TODO Validate
  }
  @Override
  public boolean supportsLikeEscapeClause() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsMultipleResultSets() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsMultipleTransactions() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsNonNullableColumns() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsMinimumSQLGrammar() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsCoreSQLGrammar() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsExtendedSQLGrammar() throws SQLException {
    Util.trace("DatabaseMetaData.supportsExtendedSQLGrammar");
    return false;
  }
  @Override
  public boolean supportsANSI92EntryLevelSQL() throws SQLException {
    Util.trace("DatabaseMetaData.supportsANSI92EntryLevelSQL");
    return false;
  }
  @Override
  public boolean supportsANSI92IntermediateSQL() throws SQLException {
    Util.trace("DatabaseMetaData.supportsANSI92IntermediateSQL");
    return false;
  }
  @Override
  public boolean supportsANSI92FullSQL() throws SQLException {
    Util.trace("DatabaseMetaData.supportsANSI92FullSQL");
    return false;
  }
  @Override
  public boolean supportsIntegrityEnhancementFacility() throws SQLException {
    return false; // TODO Validate
  }
  @Override
  public boolean supportsOuterJoins() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsFullOuterJoins() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsLimitedOuterJoins() throws SQLException {
    return true;
  }
  @Override
  public String getSchemaTerm() throws SQLException {
    return "dbName";
  }
  @Override
  public String getProcedureTerm() throws SQLException {
    return null;
  }
  @Override
  public String getCatalogTerm() throws SQLException {
    return null;
  }
  @Override
  public boolean isCatalogAtStart() throws SQLException {
    return true;
  }
  @Override
  public String getCatalogSeparator() throws SQLException {
    return ".";
  }
  @Override
  public boolean supportsSchemasInDataManipulation() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsSchemasInProcedureCalls() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsSchemasInTableDefinitions() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsSchemasInIndexDefinitions() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsCatalogsInDataManipulation() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsCatalogsInProcedureCalls() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsCatalogsInTableDefinitions() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsPositionedDelete() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsPositionedUpdate() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsSelectForUpdate() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsStoredProcedures() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsSubqueriesInComparisons() throws SQLException {
    Util.trace("DatabaseMetaData.supportsSubqueriesInComparisons");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsSubqueriesInExists() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsSubqueriesInIns() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsSubqueriesInQuantifieds() throws SQLException {
    Util.trace("DatabaseMetaData.supportsSubqueriesInQuantifieds");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsCorrelatedSubqueries() throws SQLException {
    Util.trace("DatabaseMetaData.supportsCorrelatedSubqueries");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsUnion() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsUnionAll() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
    Util.trace("DatabaseMetaData.supportsOpenCursorsAcrossCommit");
    return false;
  }
  @Override
  public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
    Util.trace("DatabaseMetaData.supportsOpenCursorsAcrossRollback");
    return false;
  }
  @Override
  public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
    Util.trace("DatabaseMetaData.supportsOpenStatementsAcrossCommit");
    return false; // TODO Validate
  }
  @Override
  public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
    Util.trace("DatabaseMetaData.supportsOpenStatementsAcrossRollback");
    return false; // TODO Validate
  }
  @Override
  public int getMaxBinaryLiteralLength() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_length
  }
  @Override
  public int getMaxCharLiteralLength() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_length
  }
  @Override
  public int getMaxColumnNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxColumnsInGroupBy() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_column
  }
  @Override
  public int getMaxColumnsInIndex() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_column
  }
  @Override
  public int getMaxColumnsInOrderBy() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_column
  }
  @Override
  public int getMaxColumnsInSelect() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_column
  }
  @Override
  public int getMaxColumnsInTable() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_column
  }
  @Override
  public int getMaxConnections() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxCursorNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxIndexLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxSchemaNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxProcedureNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxCatalogNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxRowSize() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_length
  }
  @Override
  public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
    return true;
  }
  @Override
  public int getMaxStatementLength() throws SQLException {
    return 0; // TODO http://sqlite.org/limits.html#max_sql_length
  }
  @Override
  public int getMaxStatements() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxTableNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getMaxTablesInSelect() throws SQLException {
    return 64; // ~ http://www.sqlite.org/limits.html: Maximum Number Of Tables In A Join
  }
  @Override
  public int getMaxUserNameLength() throws SQLException {
    return 0;
  }
  @Override
  public int getDefaultTransactionIsolation() throws SQLException {
    return Connection.TRANSACTION_SERIALIZABLE;
  }
  @Override
  public boolean supportsTransactions() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
    return level == Connection.TRANSACTION_SERIALIZABLE; // TODO TRANSACTION_READ_UNCOMMITTED
  }
  @Override
  public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
    return true; // tested
  }
  @Override
  public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
    return false;
  }
  @Override
  public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
    return false; // tested
  }
  @Override
  public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
    return false; // tested
  }
  @Override
  public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as PROCEDURE_CAT, "
            + "null as PROCEDURE_SCHEM, "
            + "null as PROCEDURE_NAME, "
            + "null as UNDEF1, "
            + "null as UNDEF2, "
            + "null as UNDEF3, "
            + "null as REMARKS, "
            + "null as PROCEDURE_TYPE, "
            + "null as SPECIFIC_NAME limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as PROCEDURE_CAT, "
            + "null as PROCEDURE_SCHEM, "
            + "null as PROCEDURE_NAME, "
            + "null as COLUMN_NAME, "
            + "null as COLUMN_TYPE, "
            + "null as DATA_TYPE, "
            + "null as TYPE_NAME, "
            + "null as PRECISION, "
            + "null as LENGTH, "
            + "null as SCALE, "
            + "null as RADIX, "
            + "null as NULLABLE, "
            + "null as REMARKS, "
            + "null as COLUMN_DEF, "
            + "null as SQL_DATA_TYPE, "
            + "null as SQL_DATETIME_SUB, "
            + "null as CHAR_OCTET_LENGTH, "
            + "null as ORDINAL_POSITION, "
            + "null as IS_NULLABLE, "
            + "null as SPECIFIC_NAME limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
    checkOpen();
    tableNamePattern = (tableNamePattern == null || "".equals(tableNamePattern)) ? "%" : tableNamePattern;

    final StringBuilder sql = new StringBuilder().append("select").
        append(" null as TABLE_CAT,").
        append(" null as TABLE_SCHEM,"). // TODO
        append(" name as TABLE_NAME,").
        append(" upper(type) as TABLE_TYPE,").
        append(" null as REMARKS,").
        append(" null as TYPE_CAT,").
        append(" null as TYPE_SCHEM,").
        append(" null as TYPE_NAME,").
        append(" null as SELF_REFERENCING_COL_NAME,").
        append(" null as REF_GENERATION").
        append(" from (select name, type from sqlite_master union all").
        append("       select name, type from sqlite_temp_master)").
        append(" where TABLE_NAME like ").append(quote(tableNamePattern));

    if (types != null) {
      sql.append(" and TABLE_TYPE in (");
      for (int i = 0; i < types.length; i++) {
        if (i > 0) sql.append(", ");
        sql.append(quote(types[i].toUpperCase()));
      }
      sql.append(")");
    } else {
      sql.append(" and TABLE_TYPE in ('TABLE', 'VIEW')");
    }

    sql.append(" order by TABLE_TYPE, TABLE_SCHEM, TABLE_NAME");

    final PreparedStatement stmt = c.prepareStatement(sql.toString());
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getSchemas() throws SQLException { // TODO main, temp, attached dbs (pragma database_list)
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TABLE_SCHEM, "
            + "null as TABLE_CATALOG "
            + "limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getCatalogs() throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select null as TABLE_CAT limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getTableTypes() throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement("select 'TABLE' as TABLE_TYPE " +
        "union select 'VIEW' as TABLE_TYPE");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }

  @Override
  public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
    checkOpen();
    final StringBuilder sql = new StringBuilder();

    final Set<String> tbls = getExactTableNames(tableNamePattern);

    sql.append("select ").
        append("null as TABLE_CAT, ").
        append("null as TABLE_SCHEM, ").
        append("tbl as TABLE_NAME, ").
        append("cn as COLUMN_NAME, ").
        append("ct as DATA_TYPE, ").
        append("tn as TYPE_NAME, ").
        append("10 as COLUMN_SIZE, "). // FIXME
        append("10 as BUFFER_LENGTH, ").
        append("10 as DECIMAL_DIGITS, ").
        append("10 as NUM_PREC_RADIX, ").
        append("colnullable as NULLABLE, ").
        append("null as REMARKS, ").
        append("cdflt as COLUMN_DEF, ").
        append("0 as SQL_DATA_TYPE, ").
        append("0 as SQL_DATETIME_SUB, ").
        append("10 as CHAR_OCTET_LENGTH, "). // FIXME
        append("ordpos as ORDINAL_POSITION, ").
        append("(case colnullable when 0 then 'N' when 1 then 'Y' else '' end)").
        append(" as IS_NULLABLE, ").
        append("null as SCOPE_CATLOG, ").
        append("null as SCOPE_SCHEMA, ").
        append("null as SCOPE_TABLE, ").
        append("null as SOURCE_DATA_TYPE, ").
        append("null as IS_AUTOINCREMENT, "). // TODO
        append("null as IS_GENERATEDCOLUMN from (");

    boolean colFound = false;
    for (String tbl : tbls) {
      // Pragma cannot be used as subquery...
      PreparedStatement table_info = null;
      ResultSet rs = null;
      try {
        table_info = c.prepareStatement("PRAGMA table_info(" + quote(tbl) + ")");
        rs = table_info.executeQuery();

        while (rs.next()) {
          if (colFound) sql.append(" UNION ALL ");
          colFound = true;

          String colType = getSQLiteType(rs.getString(3));
          int colJavaType = getJavaType(colType);

          sql.append("SELECT ").
              append(quote(tbl)).append(" AS tbl, ").
              append(rs.getInt(1)).append(" AS ordpos, ").
              append(rs.getBoolean(4) ? columnNoNulls : columnNullable).append(" AS colnullable, ").
              append(colJavaType).append(" AS ct, ").
              append(quote(rs.getString(2))).append(" AS cn, ").
              append(quote(colType)).append(" AS tn, ").
              append(quote(rs.getString(5))).append(" AS cdflt");

          if (columnNamePattern != null && !"%".equals(columnNamePattern)) {
            sql.append(" where cn like ").append(quote(columnNamePattern));
          }
        }
      } catch (SQLException e) { // query does not return ResultSet
      } finally {
        if (rs != null) {
          rs.close();
        }
        if (table_info != null) {
          table_info.close();
        }
      }
    }

    sql.append(colFound ? ") order by TABLE_SCHEM, TABLE_NAME, ORDINAL_POSITION" :
        "SELECT NULL AS ordpos, NULL AS colnullable, NULL AS ct, "
            + "NULL AS cn, NULL AS tn, NULL AS cdflt) LIMIT 0");
    final PreparedStatement columns = c.prepareStatement(sql.toString());
    columns.closeOnCompletion();
    return columns.executeQuery();
  }

  private Set<String> getExactTableNames(String tableNamePattern) throws SQLException {
    tableNamePattern = (tableNamePattern == null || "".equals(tableNamePattern)) ? "%" : tableNamePattern;
    if (tableNamePattern.contains("%") || tableNamePattern.contains("?")) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
        ps = c.prepareStatement("SELECT tbl_name FROM sqlite_master WHERE tbl_name LIKE ?");
        // determine exact table name
        ps.setString(1, tableNamePattern);
        rs = ps.executeQuery();
        if (!rs.next()) {
          return Collections.emptySet();
        }
        final Set<String> tbls = new HashSet<String>();
        tbls.add(rs.getString(1));
        while (rs.next()) {
          tbls.add(rs.getString(1));
        }
        return tbls;
      } finally {
        if (rs != null) {
          rs.close();
        }
        if (ps != null) {
          ps.close();
        }
      }
    } else {
      return Collections.singleton(tableNamePattern);
    }
  }

  private String getSQLiteType(String colType) {
    return colType == null ? "" : colType.toUpperCase();
  }
  // TODO Validate affinity vs java type
  public static int getJavaType(String colType) {
    final int affinity = SQLite.getAffinity(colType);
    switch (affinity) {
      case ColAffinities.TEXT:
        return Types.VARCHAR;
      case ColAffinities.INTEGER:
        return Types.INTEGER;
      case ColAffinities.REAL:
        return Types.REAL;
      case ColAffinities.NONE:
        return Types.BLOB;
      default:
        return Types.NUMERIC;
    }
  }

  @Override
  public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TABLE_CAT, "
            + "null as TABLE_SCHEM, "
            + "null as TABLE_NAME, "
            + "null as COLUMN_NAME, "
            + "null as GRANTOR, "
            + "null as GRANTEE, "
            + "null as PRIVILEGE, "
            + "null as IS_GRANTABLE limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TABLE_CAT, "
            + "null as TABLE_SCHEM, "
            + "null as TABLE_NAME, "
            + "null as GRANTOR, "
            + "null as GRANTEE, "
            + "null as PRIVILEGE, "
            + "null as IS_GRANTABLE limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
    checkOpen();
    final StringBuilder sql = new StringBuilder();

    sql.append("select ").
        append(scope).append(" as SCOPE, ").
        append("cn as COLUMN_NAME, ").
        append("ct as DATA_TYPE, ").
        append("tn as TYPE_NAME, ").
        append("10 as COLUMN_SIZE, "). // FIXME
        append("0 as BUFFER_LENGTH, ").
        append("0 as DECIMAL_DIGITS, ").
        append("pc as PSEUDO_COLUMN from (");

    // Pragma cannot be used as subquery...
    int count = 0;
    String colName = null;
    String colType = null;
    PreparedStatement table_info = null;
    ResultSet rs = null;
    try {
      table_info = c.prepareStatement("PRAGMA table_info(" + quote(table) + ")");
      rs = table_info.executeQuery();

      while (count < 2 && rs.next()) {
        if (rs.getBoolean(6) && (nullable || rs.getBoolean(4))) { // FIXME
          colName = rs.getString(2);
          colType = getSQLiteType(rs.getString(3));
          count++;
        }
      }
    } catch (SQLException e) { // query does not return ResultSet
      count = -1;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (table_info != null) {
        table_info.close();
      }
    }

    if (count == 1) {
      sql.append("SELECT ").
          append(quote(colName)).append(" as cn, ").
          append(getJavaType(colType)).append(" as ct, ").
          append("'").append(colType).append("' as tn, ").
          append(bestRowNotPseudo).append(" as pc) order by SCOPE");
    } else {
      sql.append("SELECT ").
          append("'ROWID' AS cn, ").
          append(Types.INTEGER).append(" AS ct, ").
          append("'INTEGER' AS tn, ").
          append(bestRowPseudo).append(" AS pc) order by SCOPE");
      if (count < 0) {
        sql.append(" limit 0");
      }
    }
    final PreparedStatement columns = c.prepareStatement(sql.toString());
    columns.closeOnCompletion();
    return columns.executeQuery();
  }
  @Override
  public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as SCOPE, "
            + "null as COLUMN_NAME, "
            + "null as DATA_TYPE, "
            + "null as TYPE_NAME, "
            + "null as COLUMN_SIZE, "
            + "null as BUFFER_LENGTH, "
            + "null as DECIMAL_DIGITS, "
            + "null as PSEUDO_COLUMN limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
    checkOpen();
    final StringBuilder sql = new StringBuilder();

    sql.append("select ").
        append("null as TABLE_CAT, ").
        append("null as TABLE_SCHEM, ").
        append(quote(table)).append(" as TABLE_NAME, ").
        append("cn as COLUMN_NAME, ").
        append("seqno as KEY_SEQ, ").
        append("pk as PK_NAME from (");

    // Pragma cannot be used as subquery...
    final List<String> colNames = new ArrayList<String>();
    PreparedStatement table_info = null;
    ResultSet rs = null;
    try {
      table_info = c.prepareStatement("PRAGMA table_info(" + quote(table) + ")");
      rs = table_info.executeQuery();

      while (rs.next()) {
        if (rs.getBoolean(6)) { // FIXME
          colNames.add(rs.getString(2));
        }
      }
    } catch (SQLException e) { // query does not return ResultSet
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (table_info != null) {
        table_info.close();
      }
    }

    if (colNames.isEmpty()) {
      sql.append("SELECT NULL as pk, NULL AS cn, NULL AS seqno) LIMIT 0");
    } else if (colNames.size() == 1) {
      sql.append("SELECT ").
          append(quote(colNames.get(0))).append(" AS pk, ").
          append(quote(colNames.get(0))).append(" AS cn, ").
          append(0).append(" AS seqno)");
    } else {
      final List<String> indexNames = new ArrayList<String>();
      PreparedStatement index_list = null;
      try {
        index_list = c.prepareStatement("PRAGMA index_list(" + quote(table) + ")");
        rs = index_list.executeQuery();

        while (rs.next()) {
          if (rs.getBoolean(3) && rs.getString(2).startsWith("sqlite_autoindex_")) {
            indexNames.add(rs.getString(2));
          }
        }
      } catch (SQLException e) { // query does not return ResultSet
      } finally {
        if (rs != null) {
          rs.close();
        }
        if (index_list != null) {
          index_list.close();
        }
      }

      boolean indexFound = false;
      for (String indexName : indexNames) {
        final List<String> columns = new ArrayList<String>();
        PreparedStatement index_info = null;
        try {
          index_info = c.prepareStatement("PRAGMA index_info(" + quote(indexName) + ")");
          rs = index_info.executeQuery();

          while (rs.next()) {
            columns.add(rs.getInt(1), rs.getString(3));
          }
          //} catch(SQLException e) { // query does not return ResultSet
        } finally {
          if (rs != null) {
            rs.close();
          }
          if (index_info != null) {
            index_info.close();
          }
        }

        if (areEquals(colNames, columns)) {
          int i = 0;
          for (String column : columns) {
            if (i > 0) sql.append(" UNION ALL ");
            sql.append("SELECT ").
                append(quote(indexName)).append(" AS pk, ").
                append(quote(column)).append(" AS cn, ").
                append(i).append(" AS seqno");
            i++;
          }
          indexFound = true;
          break;
        }
      }
      sql.append(indexFound ? ") order by COLUMN_NAME" :
          "SELECT NULL as pk, NULL AS cn, NULL AS seqno) LIMIT 0");
    }
    final PreparedStatement columns = c.prepareStatement(sql.toString());
    columns.closeOnCompletion();
    return columns.executeQuery();
  }

  private static boolean areEquals(List<String> pkColumns, List<String> idxColumns) {
    if (pkColumns.size() != idxColumns.size()) return false;
    for (String idxColumn : idxColumns) {
      if (!pkColumns.contains(idxColumn)) { // TODO ignore case
        return false;
      }
    }
    return true;
  }

  @Override
  public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
    return getForeignKeys(null, table, false);
  }

  private ResultSet getForeignKeys(String primaryTable, String foreignTable, boolean cross) throws SQLException {
    checkOpen();
    final StringBuilder sql = new StringBuilder();

    sql.append("select ").
        append("null as PKTABLE_CAT, ").
        append("null as PKTABLE_SCHEM, ").
        append("pt as PKTABLE_NAME, ").
        append("pc as PKCOLUMN_NAME, ").
        append("null as FKTABLE_CAT, ").
        append("null as FKTABLE_SCHEM, ").
        append(quote(foreignTable)).append(" as FKTABLE_NAME, ").
        append("fc as FKCOLUMN_NAME, ").
        append("seq as KEY_SEQ, ").
        append(importedKeyNoAction).append(" as UPDATE_RULE, "). // FIXME on_update (6) SET NULL (importedKeySetNull), SET DEFAULT (importedKeySetDefault), CASCADE (importedKeyCascade), RESTRICT (importedKeyRestrict), NO ACTION (importedKeyNoAction)
        append(importedKeyNoAction).append(" as DELETE_RULE, "). // FIXME on_delete (7)
        append("id as FK_NAME, ").
        append("null as PK_NAME, ").
        append(importedKeyNotDeferrable).append(" as DEFERRABILITY "). // FIXME
        append("from (");

    // Pragma cannot be used as subquery...
    int count = 0;
    PreparedStatement foreign_key_list = null;
    ResultSet rs = null;
    try {
      foreign_key_list = c.prepareStatement("pragma foreign_key_list(" + quote(foreignTable) + ");");
      rs = foreign_key_list.executeQuery();
      while (rs.next()) {
        if (cross && !primaryTable.equalsIgnoreCase(rs.getString(3))) {
          continue;
        }
        if (count > 0) {
          sql.append(" union all ");
        }
        sql.append("select ").
            append(quote(rs.getString(3) + '_' + rs.getString(1))).append(" as id, ").
            append(quote(rs.getString(3))).append(" as pt, ").
            append(quote(rs.getString(5))).append(" as pc, ").
            append(quote(rs.getString(4))).append(" as fc, ").
            append(rs.getShort(2) + 1).append(" as seq");
        count++;
      }
    } catch (SQLException e) { // query does not return ResultSet
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (foreign_key_list != null) {
        foreign_key_list.close();
      }
    }

    if (count == 0) {
      sql.append("select null as id, null as pt, null as pc, null as fc, null as seq) LIMIT 0");
    } else {
      if (cross) {
        sql.append(") order by FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, KEY_SEQ");
      } else {
        sql.append(") order by PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME, KEY_SEQ");
      }
    }
    final PreparedStatement fks = c.prepareStatement(sql.toString());
    fks.closeOnCompletion();
    return fks.executeQuery();
  }
  @Override
  public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
    checkOpen();
    final StringBuilder sql = new StringBuilder();

    sql.append("select ").
        append("null as PKTABLE_CAT, ").
        append("null as PKTABLE_SCHEM, ").
        append(quote(table)).append(" as PKTABLE_NAME, ").
        append("pc as PKCOLUMN_NAME, ").
        append("null as FKTABLE_CAT, ").
        append("null as FKTABLE_SCHEM, ").
        append("ft as FKTABLE_NAME, ").
        append("fc as FKCOLUMN_NAME, ").
        append("seq as KEY_SEQ, ").
        append(importedKeyNoAction).append(" as UPDATE_RULE, "). // FIXME on_update (6) NO ACTION, CASCADE
        append(importedKeyNoAction).append(" as DELETE_RULE, "). // FIXME on_delete (7) NO ACTION, CASCADE
        append("fk as FK_NAME, ").
        append("null as PK_NAME, ").
        append(importedKeyNotDeferrable).append(" as DEFERRABILITY "). // FIXME
        append("from (");

    final List<String> fkTables = new ArrayList<String>();
    PreparedStatement fks = null;
    ResultSet rs = null;
    try {
      fks = c.prepareStatement("select distinct tbl_name from sqlite_master where type = 'table' and tbl_name <> ? and sql like ?");
      fks.setString(1, table);
      fks.setString(2, "%REFERENCES%" + table + "%(%");
      rs = fks.executeQuery();
      while (rs.next()) {
        fkTables.add(rs.getString(1));
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (fks != null) {
        fks.close();
      }
    }

    int count = 0;
    if (!fkTables.isEmpty()) {
      for (String fkTable : fkTables) {
        // Pragma cannot be used as subquery...
        PreparedStatement foreign_key_list = null;
        try {
          foreign_key_list = c.prepareStatement("pragma foreign_key_list(" + quote(fkTable) + ");");
          rs = foreign_key_list.executeQuery();
          while (rs.next()) {
            if (!rs.getString(3).equalsIgnoreCase(table)) {
              continue;
            }
            if (count > 0) {
              sql.append(" union all ");
            }
            sql.append("select ").
                append(quote(fkTable + '_' + rs.getString(1))).append(" as fk, ").
                append(quote(fkTable)).append(" as ft, ").
                append(quote(rs.getString(5))).append(" as pc, ").
                append(quote(rs.getString(4))).append(" as fc, ").
                append(rs.getShort(2) + 1).append(" as seq");
            count++;
          }
        } catch (SQLException e) { // query does not return ResultSet
        } finally {
          if (rs != null) {
            rs.close();
          }
          if (foreign_key_list != null) {
            foreign_key_list.close();
          }
        }
      }
    }

    if (count == 0) {
      sql.append("select null as fk, null as ft, null as pc, null as fc, null as seq) LIMIT 0");
    } else {
      sql.append(") order by FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, KEY_SEQ");
    }
    final PreparedStatement eks = c.prepareStatement(sql.toString());
    eks.closeOnCompletion();
    return eks.executeQuery();
  }
  @Override
  public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
    return getForeignKeys(parentTable, foreignTable, true);
  }
  @Override
  public ResultSet getTypeInfo() throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "tn as TYPE_NAME, "
            + "dt as DATA_TYPE, "
            + "0 as PRECISION, "
            + "null as LITERAL_PREFIX, "
            + "null as LITERAL_SUFFIX, "
            + "null as CREATE_PARAMS, "
            + typeNullable + " as NULLABLE, "
            + "1 as CASE_SENSITIVE, "
            + typeSearchable + " as SEARCHABLE, "
            + "0 as UNSIGNED_ATTRIBUTE, "
            + "0 as FIXED_PREC_SCALE, "
            + "0 as AUTO_INCREMENT, "
            + "null as LOCAL_TYPE_NAME, "
            + "0 as MINIMUM_SCALE, "
            + "0 as MAXIMUM_SCALE, "
            + "0 as SQL_DATA_TYPE, "
            + "0 as SQL_DATETIME_SUB, "
            + "10 as NUM_PREC_RADIX from ("
            + "    select 'BLOB' as tn, " + Types.BLOB + " as dt union"
            + "    select 'NULL' as tn, " + Types.NULL + " as dt union"
            + "    select 'REAL' as tn, " + Types.REAL + " as dt union"
            + "    select 'TEXT' as tn, " + Types.VARCHAR + " as dt union"
            + "    select 'INTEGER' as tn, " + Types.INTEGER + " as dt"
            + ") order by DATA_TYPE, TYPE_NAME");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
    checkOpen();
    final StringBuilder sql = new StringBuilder();
    sql.append("select ").
        append("null as TABLE_CAT, ").
        append("null as TABLE_SCHEM, ").
        append(quote(table)).append(" as TABLE_NAME, ").
        append("nu as NON_UNIQUE, ").
        append("null as INDEX_QUALIFIER, ").
        append("idx as INDEX_NAME, ").
        append(tableIndexOther).append(" as TYPE, ").
        append("seqno as ORDINAL_POSITION, ").
        append("cn as COLUMN_NAME, ").
        append("'A' as ASC_OR_DESC, ").
        append("0 as CARDINALITY, ").
        append("0 as PAGES, ").
        append("null as FILTER_CONDITION ").
        append("from (");

    Map<String, Boolean> indexes = new HashMap<String, Boolean>();
    PreparedStatement index_list = null;
    ResultSet rs = null;
    try {
      index_list = c.prepareStatement("PRAGMA index_list(" + quote(table) + ")");
      rs = index_list.executeQuery();
      while (rs.next()) {
        final boolean notuniq = !rs.getBoolean(3);
        if (unique && notuniq) {
          continue;
        }
        indexes.put(rs.getString(2), notuniq);
      }
    } catch (SQLException e) { // query does not return ResultSet
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (index_list != null) {
        index_list.close();
      }
    }

    if (indexes.isEmpty()) {
      sql.append("SELECT NULL AS nu, NULL AS idx, NULL AS seqno, NULL AS cn) LIMIT 0");
    } else {
      boolean found = false;
      for (final Map.Entry<String, Boolean> index : indexes.entrySet()) {
        PreparedStatement index_info = null;
        try {
          index_info = c.prepareStatement("PRAGMA index_info(" + quote(index.getKey()) + ")");
          rs = index_info.executeQuery();
          while (rs.next()) {
            if (found) {
              sql.append(" UNION ALL ");
            }
            sql.append("SELECT ").
                append(index.getValue() ? 1 : 0).append(" AS nu, ").
                append(quote(index.getKey())).append(" AS idx, ").
                append(rs.getInt(1)).append(" AS seqno, ").
                append(quote(rs.getString(3))).append(" AS cn");
            found = true;
          }
          //} catch(SQLException e) { // query does not return ResultSet
        } finally {
          if (rs != null) {
            rs.close();
          }
          if (index_info != null) {
            index_info.close();
          }
        }
      }
      if (found) {
        sql.append(") order by NON_UNIQUE, TYPE, INDEX_NAME, ORDINAL_POSITION");
      } else {
        sql.append("SELECT NULL AS nu, NULL AS idx, NULL AS seqno, NULL AS cn) LIMIT 0");
      }
    }

    final PreparedStatement idx = c.prepareStatement(sql.toString());
    idx.closeOnCompletion();
    return idx.executeQuery();
  }
  @Override
  public boolean supportsResultSetType(int type) throws SQLException {
    return type == ResultSet.TYPE_FORWARD_ONLY;
  }
  @Override
  public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
    return type == ResultSet.TYPE_FORWARD_ONLY && concurrency == ResultSet.CONCUR_READ_ONLY;
  }
  @Override
  public boolean ownUpdatesAreVisible(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean ownDeletesAreVisible(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean ownInsertsAreVisible(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean othersUpdatesAreVisible(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean othersDeletesAreVisible(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean othersInsertsAreVisible(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean updatesAreDetected(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean deletesAreDetected(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean insertsAreDetected(int type) throws SQLException {
    return false;
  }
  @Override
  public boolean supportsBatchUpdates() throws SQLException {
    return true;
  }
  @Override
  public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TYPE_CAT, "
            + "null as TYPE_SCHEM, "
            + "null as TYPE_NAME, "
            + "null as CLASS_NAME, "
            + "null as DATA_TYPE, "
            + "null as REMARKS, "
            + "null as BASE_TYPE "
            + "limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public Connection getConnection() throws SQLException {
    return c;
  }
  @Override
  public boolean supportsSavepoints() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsNamedParameters() throws SQLException {
    return true;
  }
  @Override
  public boolean supportsMultipleOpenResults() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsGetGeneratedKeys() throws SQLException { // Used by Hibernate
    return true;
  }
  @Override
  public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TYPE_CAT, "
            + "null as TYPE_SCHEM, "
            + "null as TYPE_NAME, "
            + "null as SUPERTYPE_CAT, "
            + "null as SUPERTYPE_SCHEM, "
            + "null as SUPERTYPE_NAME limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TABLE_CAT, "
            + "null as TABLE_SCHEM, "
            + "null as TABLE_NAME, "
            + "null as SUPERTABLE_NAME limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as TYPE_CAT, "
            + "null as TYPE_SCHEM, "
            + "null as TYPE_NAME, "
            + "null as ATTR_NAME, "
            + "null as DATA_TYPE, "
            + "null as ATTR_TYPE_NAME, "
            + "null as ATTR_SIZE, "
            + "null as DECIMAL_DIGITS, "
            + "null as NUM_PREC_RADIX, "
            + "null as NULLABLE, "
            + "null as REMARKS, "
            + "null as ATTR_DEF, "
            + "null as SQL_DATA_TYPE, "
            + "null as SQL_DATETIME_SUB, "
            + "null as CHAR_OCTET_LENGTH, "
            + "null as ORDINAL_POSITION, "
            + "null as IS_NULLABLE, "
            + "null as SCOPE_CATALOG, "
            + "null as SCOPE_SCHEMA, "
            + "null as SCOPE_TABLE, "
            + "null as SOURCE_DATA_TYPE limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public boolean supportsResultSetHoldability(int holdability) throws SQLException {
    return holdability == ResultSet.CLOSE_CURSORS_AT_COMMIT;
  }
  @Override
  public int getResultSetHoldability() throws SQLException {
    return ResultSet.CLOSE_CURSORS_AT_COMMIT;
  }
  @Override
  public int getDatabaseMajorVersion() throws SQLException {
    return 3; // FIXME
  }
  @Override
  public int getDatabaseMinorVersion() throws SQLException {
    return 0; // FIXME
  }
  @Override
  public int getJDBCMajorVersion() throws SQLException {
    return 4;
  }
  @Override
  public int getJDBCMinorVersion() throws SQLException {
    return 1;
  }
  @Override
  public int getSQLStateType() throws SQLException {
    return sqlStateSQL99;
  }
  @Override
  public boolean locatorsUpdateCopy() throws SQLException {
    return false;
  }
  @Override
  public boolean supportsStatementPooling() throws SQLException {
    return false;
  }
  @Override
  public RowIdLifetime getRowIdLifetime() throws SQLException {
    Util.trace("DatabaseMetaData.getRowIdLifetime");
    return RowIdLifetime.ROWID_VALID_FOREVER; // TODO http://www.sqlite.org/autoinc.html
  }
  @Override
  public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
    return getSchemas(); // TODO
  }
  @Override
  public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
    return false;
  }
  @Override
  public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
    return false;
  }
  @Override
  public ResultSet getClientInfoProperties() throws SQLException { // TODO
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement(
        "select "
            + "null as NAME, "
            + "0 as MAX_LEN, "
            + "null as DEFAULT_VALUE, "
            + "null as DESCRIPTION "
            + "limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
    Util.trace("DatabaseMetaData.getFunctions");
    return null;
  }
  @Override
  public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
    Util.trace("DatabaseMetaData.getFunctionColumns");
    return null;
  }
  @Override
  public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
    checkOpen();
    final PreparedStatement stmt = c.prepareStatement( // TODO rowId?
        "select "
            + "null as TABLE_CAT, "
            + "null as TABLE_SCHEM, "
            + "null as TABLE_NAME, "
            + "null as COLUMN_NAME, "
            + "null as DATA_TYPE, "
            + "null as COLUMN_SIZE, "
            + "null as DECIMAL_DIGITS, "
            + "null as NUM_PREC_RADIX, "
            + "null as COLUMN_USAGE, "
            + "null as REMARKS, "
            + "null as CHAR_OCTET_LENGTH, "
            + "null as IS_NULLABLE limit 0");
    stmt.closeOnCompletion();
    return stmt.executeQuery();
  }
  @Override
  public boolean generatedKeyAlwaysReturned() throws SQLException {
    return false;
  }
  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    throw Util.error("not a wrapper");
  }
  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false;
  }

  private String quote(String data) {
    //if (data == null) return data;
    return Conn.mprintf("%Q", data);
  }
}
