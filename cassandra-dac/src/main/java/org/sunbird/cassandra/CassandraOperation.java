/** */
package org.sunbird.cassandra;

import java.util.List;
import java.util.Map;
import org.sunbird.common.models.response.Response;

/**
 * @desc this interface will hold functions for cassandra db interaction
 * @author Amit Kumar
 */
public interface CassandraOperation {

  /**
   * @desc This method is used to insert/update record in cassandra db (if primary key exist in
   *     request ,it will update else will insert the record in cassandra db. By default cassandra
   *     insert operation does upsert operation. Upsert means that Cassandra will insert a row if a
   *     primary key does not exist already otherwise if primary key already exists, it will update
   *     that row.)
   * @param keyspaceName String (data base keyspace name)
   * @param tableName String
   * @param request Map<String,Object>(i.e map of column name and their value)
   * @return Response Response
   */
  public Response upsertRecord(String keyspaceName, String tableName, Map<String, Object> request);

  /**
   * @desc This method is used to insert record in cassandra db
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param request Map<String,Object>(i.e map of column name and their value)
   * @return Response Response
   */
  public Response insertRecord(String keyspaceName, String tableName, Map<String, Object> request);

  /**
   * @desc This method is used to update record in cassandra db
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param request Map<String,Object>(i.e map of column name and their value)
   * @return Response Response
   */
  public Response updateRecord(String keyspaceName, String tableName, Map<String, Object> request);

  /**
   * @desc This method is used to delete record in cassandra db by their primary key(identifier)
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param identifier String
   * @return Response Response
   */
  public Response deleteRecord(String keyspaceName, String tableName, String identifier);

  /**
   * @desc This method is used to delete record in cassandra db by their primary composite key
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param compositeKeyMap Column map for composite primary key
   */
  public void deleteRecord(
      String keyspaceName, String tableName, Map<String, String> compositeKeyMap);

  /**
   * @desc This method is used to delete one or more records from Cassandra DB corresponding to
   *     given list of primary keys
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param identifierList List of primary keys of records to be deleted
   * @return Status of delete records operation
   */
  public boolean deleteRecords(String keyspaceName, String tableName, List<String> identifierList);

  /**
   * @desc This method is used to fetch record based on given parameter and it's value (it only
   *     fetch the record on indexed property or column or it will throw exception.)
   * @param keyspaceName String (data base keyspace name)
   * @param tableName String
   * @param propertyName String
   * @param propertyValue Value to be used for matching in select query
   * @return Response Response
   */
  public Response getRecordsByProperty(
      String keyspaceName, String tableName, String propertyName, Object propertyValue);

  /**
   * Fetch records with specified columns (select all if null) for given column name and value.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param propertyName Column name
   * @param propertyValue Column value
   * @param fields List of columns to be returned in each record
   * @return Response consisting of fetched records
   */
  Response getRecordsByProperty(
      String keyspaceName,
      String tableName,
      String propertyName,
      Object propertyValue,
      List<String> fields);

  /**
   * @desc This method is used to fetch record based on given parameter and it's list of value (for
   *     In Query , for example : SELECT * FROM mykeyspace.mytable WHERE id IN (‘A’,’B’,C’) )
   * @param keyspaceName String (data base keyspace name)
   * @param tableName String
   * @param propertyName String
   * @param propertyValueList List<Object>
   * @return Response Response
   */
  public Response getRecordsByProperty(
      String keyspaceName, String tableName, String propertyName, List<Object> propertyValueList);

  /**
   * Fetch records with specified columns (select all if null) for given column name with matching
   * value in the list.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param propertyName Column name
   * @param propertyValueList List of values to be used for matching in select query
   * @param fields List of columns to be returned in each record
   * @return Response consisting of fetched records
   */
  Response getRecordsByProperty(
      String keyspaceName,
      String tableName,
      String propertyName,
      List<Object> propertyValueList,
      List<String> fields);

  /**
   * Fetch records with specified indexed column
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param propertyName Indexed Column name
   * @param propertyValue Value to be used for matching in select query
   * @return Response consisting of fetched records
   */
  Response getRecordsByIndexedProperty(
      String keyspaceName, String tableName, String propertyName, Object propertyValue);

  /**
   * @desc This method is used to fetch record based on given parameter list and their values
   * @param keyspaceName String (data base keyspace name)
   * @param tableName String
   * @param propertyMap Map<String,Object> propertyMap)(i.e map of column name and their value)
   * @return Response Response
   */
  public Response getRecordsByProperties(
      String keyspaceName, String tableName, Map<String, Object> propertyMap);

  /**
   * Fetch records with specified columns (select all if null) for given column map (name, value
   * pairs).
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param propertyMap Map describing columns to be used in where clause of select query.
   * @param fields List of columns to be returned in each record
   * @return Response consisting of fetched records
   */
  Response getRecordsByProperties(
      String keyspaceName, String tableName, Map<String, Object> propertyMap, List<String> fields);

  /**
   * @desc This method is used to fetch properties value based on id
   * @param keyspaceName String (data base keyspace name)
   * @param tableName String
   * @param id String
   * @param properties String varargs
   * @return Response.
   */
  public Response getPropertiesValueById(
      String keyspaceName, String tableName, String id, String... properties);

  /**
   * @desc This method is used to fetch all records for table(i.e Select * from tableName)
   * @param keyspaceName String (data base keyspace name)
   * @param tableName String
   * @return Response Response
   */
  public Response getAllRecords(String keyspaceName, String tableName);

  /**
   * Method to update the record on basis of composite primary key.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param updateAttributes Column map to be used in set clause of update query
   * @param compositeKey Column map for composite primary key
   * @return Response consisting of update query status
   */
  Response updateRecord(
      String keyspaceName,
      String tableName,
      Map<String, Object> updateAttributes,
      Map<String, Object> compositeKey);

  /**
   * Method to get record by primary key.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param key Primary key
   * @return Response consisting of matched record
   */
  Response getRecordById(String keyspaceName, String tableName, String key);

  /**
   * Method to get record by composite primary key.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param key Column map representing composite primary key
   * @return Response consisting of matched record
   */
  Response getRecordById(String keyspaceName, String tableName, Map<String, Object> key);

  /**
   * Method to get record by primary key consisting of only specified fields (return all if null).
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param key Primary key
   * @param fields List of columns to be returned in each record
   * @return Response consisting of matched record
   */
  Response getRecordById(String keyspaceName, String tableName, String key, List<String> fields);

  /**
   * Method to get record by composity primary key consisting of only specified fields (return all
   * if null).
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param key Column map representing composite primary key
   * @param fields List of columns to be returned in each record
   * @return Response consisting of matched record
   */
  Response getRecordById(
      String keyspaceName, String tableName, Map<String, Object> key, List<String> fields);

  /**
   * Method to perform batch insert operation.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param records List of records in the batch insert operation
   * @return Response indicating status of operation
   */
  Response batchInsert(String keyspaceName, String tableName, List<Map<String, Object>> records);

  /**
   * Method to perform batch update operation.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param records List of map consisting of two maps with exactly two keys: PK: Column map for
   *     primary key, NonPK: Column map for properties with new values to be updated
   * @return Response indicating status of operation
   */
  Response batchUpdate(
      String keyspaceName, String tableName, List<Map<String, Map<String, Object>>> records);

  /**
   * Fetch records with composite key.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param compositeKeyMap Column map for composite primary key
   * @return Response consisting of fetched records
   */
  Response getRecordsByCompositeKey(
      String keyspaceName, String tableName, Map<String, Object> compositeKeyMap);

  /**
   * Fetch records with specified columns for given identifiers.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param properties List of columns to be returned in each record
   * @param ids List of identifiers
   * @return Response consisting of fetched records
   */
  Response getRecordsByIdsWithSpecifiedColumns(
      String keyspaceName, String tableName, List<String> properties, List<String> ids);

  /**
   * Fetch records for given primary keys.
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param primaryKeys List of primary key values
   * @param primaryKeyColumnName Name of the primary key column
   * @return Response consisting of fetched records
   */
  Response getRecordsByPrimaryKeys(
      String keyspaceName, String tableName, List<String> primaryKeys, String primaryKeyColumnName);

  /**
   * Insert record with TTL expiration
   *
   * @param keyspaceName Keyspace name
   * @param tableName Table name
   * @param request Map consisting of column name and value
   * @param ttl Time to live after which inserted record will be auto deleted
   * @return Response indicating status of operation
   */
  public Response insertRecordWithTTL(
      String keyspaceName, String tableName, Map<String, Object> request, int ttl);

  /**
   * Utility method to fetch records with alias and ttl info
   *
   * @param keyspaceName
   * @param tableName
   * @param primaryKeys key as column and value as value to be applied in where clause
   * @param propertiesWithAlias map of key as column and column as alias. <i> if alias is null it's
   *     not applied </i>
   * @param ttlPropertiesWithAlias map of ttl column as key and alias as value <i> if alias is null
   *     it's not applied </i>
   * @return
   */
  Response getRecordsByIdsWithSpecifiedColumnsAndTTL(
      String keyspaceName,
      String tableName,
      Map<String, Object> primaryKeys,
      Map<String, String> propertiesWithAlias,
      Map<String, String> ttlPropertiesWithAlias);

  /**
   * Makes a batch insert with different ttl values.
   *
   * @param keyspaceName
   * @param tableName
   * @param records entries to be inserted
   * @param ttls corresponding ttl needs to be provided on each entry. <i>Any ttl will be ignored
   *     for values null, 0 or negative numbers</i>
   */
  Response batchInsertWithTTL(
      String keyspaceName, String tableName, List<Map<String, Object>> records, List<Integer> ttls);
}
