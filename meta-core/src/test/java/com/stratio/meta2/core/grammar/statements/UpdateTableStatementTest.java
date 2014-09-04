/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.stratio.meta2.core.grammar.statements;

import com.stratio.meta2.core.grammar.ParsingTest;
import org.testng.annotations.Test;

public class UpdateTableStatementTest extends ParsingTest {

  @Test
  public void updateBasic() {
    String inputText = "UPDATE demo.table1 SET field1 = 'value1' WHERE catalog1.table1.field3 = 'value3' AND table2.name = 'stratio';";
    String expectedText = "UPDATE demo.table1 SET demo.table1.field1 = 'value1' WHERE catalog1.table1.field3 = 'value3' AND demo.table2.name = 'stratio';";
    testRegularStatement(inputText, expectedText, "updateBasic");
  }

  @Test
  public void updateTablename() {
    String inputText = "[demo], UPDATE myTable USING 'prop1' = 342 SET ident1 = 'term1', ident2 = 'term2'"
                       + " WHERE ident3 > 25 IF 'replication' = 2;";
    String expectedText = "UPDATE demo.myTable USING 'prop1' = 342 SET demo.myTable.ident1 = 'term1', demo.myTable.ident2 = 'term2'"
                          + " WHERE demo.myTable.ident3 > 25 IF 'replication' = 2;";
    testRegularStatement(inputText, expectedText, "updateTablename");
  }

  @Test
  public void updateWhere() {
    String inputText = "UPDATE table1 USING 'TTL' = 400 SET field1 = 'value1',"
                       + " field2 = 'value2' WHERE field3 = 'value3' AND field4 = 'value4';";
    String expectedText = "UPDATE demo.table1 USING 'TTL' = 400 SET demo.table1.field1 = 'value1',"
                          + " demo.table1.field2 = 'value2' WHERE demo.table1.field3 = 'value3' AND demo.table1.field4 = 'value4';";
    testRegularStatementSession("demo", inputText, expectedText, "updateWhere");
  }

  @Test
  public void updateFull() {
    String inputText = "UPDATE table1 USING 'TTL' = 400 SET field1 = 'value1',"
                       + " field2 = 'value2' WHERE field3 = 'value3' AND field4 = 'value4'"
                       + " IF 'class' = 'transaction_value5';";
    String expectedText = "UPDATE <unknown_name>.table1 USING 'TTL' = 400 SET <unknown_name>.table1.field1 = 'value1',"
                          + " <unknown_name>.table1.field2 = 'value2' WHERE <unknown_name>.table1.field3 = 'value3' AND <unknown_name>.table1.field4 = 'value4'"
                          + " IF 'class' = 'transaction_value5';";
    testRegularStatement(inputText, expectedText, "updateFull");
  }

  @Test
  public void updateForInvalidAssignment(){
    String inputText = "UPDATE table1 SET field1 = value1 WHERE field3: value3;";
    testParserFails(inputText, "updateForInvalidAssignment");
  }

  @Test
  public void updateWrongSpelling(){
    String inputText = "UPDDATE table1 SET field1 = value1 WHERE field3: value3;";
    testParserFails(inputText, "updateWrongSpelling");
  }

  @Test
  public void updateWhereUsingAnd() {
    String inputText = "UPDATE demo.table1 USING 'TTL' = 400 AND 'TTL2' = 400 SET field1 = 'value1',"
                       + " field2 = 'value2' WHERE field3 = 'value3' AND field4 = 'value4';";
    String expectedText = "UPDATE demo.table1 USING 'TTL' = 400 AND 'TTL2' = 400 SET demo.table1.field1 = 'value1',"
                          + " demo.table1.field2 = 'value2' WHERE demo.table1.field3 = 'value3' AND demo.table1.field4 = 'value4';";
    testRegularStatement(inputText, expectedText, "updateWhereUsingAnd");
  }

  /*@Test
  public void updateWhereWithCollectionMap() {
    String inputText = "UPDATE demo.table1 SET emails[admin] = myemail@mycompany.org WHERE field3 = value3;";
    String expectedText = "UPDATE demo.table1 SET demo.table1.emails[admin] = myemail@mycompany.org WHERE demo.table1.field3 = value3;";
    testRegularStatement(inputText, expectedText, "updateWhereWithCollectionMap");
  }

  @Test
  public void updateWhereWithCollectionSet() {
    String inputText = "UPDATE table1 SET emails = emails + {myemail@mycompany.org} WHERE field3 = value3;";
    String expectedText = "UPDATE <unknown_name>.table1 SET <unknown_name>.table1.emails = emails + {myemail@mycompany.org} WHERE <unknown_name>.table1.field3 = value3;";
    testRegularStatement(inputText, expectedText, "updateWhereWithCollectionSet");
  }

  @Test
  public void updateWhereWithCollectionList() {
    String inputText = "[demo], UPDATE table1 SET emails = emails + [myemail@mycompany.org] WHERE field3 = value3;";
    String expectedText = "UPDATE demo.table1 SET demo.table1.emails = emails + [myemail@mycompany.org] WHERE demo.table1.field3 = value3;";
    testRegularStatement(inputText, expectedText, "updateWhereWithCollectionList");
  }*/

  @Test
  public void updateTablenameIfAnd1() {
    String inputText = "UPDATE myTable SET ident1 = 'term1', ident2 = 'term2'"
                       + " WHERE ident3 = 34 IF field3 = 86 AND field2 = 25;";
    String expectedText = "UPDATE demo.myTable SET demo.myTable.ident1 = 'term1', demo.myTable.ident2 = 'term2'"
                          + " WHERE demo.myTable.ident3 = 34 IF demo.myTable.field3 = 86 AND demo.myTable.field2 = 25;";
    testRegularStatementSession("demo", inputText, expectedText, "updateTablenameIfAnd1");
  }

  @Test
  public void updateTablenameIfAnd2() {
    String inputText = "UPDATE tester.myTable USING 'prop1' = 342 SET ident1 = 'term1', ident2 = 'term2'"
                       + " WHERE ident3 = 'Big Data' IF field3 = 86 AND field2 = 25;";
    String expectedText = "UPDATE tester.myTable USING 'prop1' = 342 SET tester.myTable.ident1 = 'term1', tester.myTable.ident2 = 'term2'"
                          + " WHERE tester.myTable.ident3 = 'Big Data' IF tester.myTable.field3 = 86 AND tester.myTable.field2 = 25;";
    testRegularStatementSession("demo", inputText, expectedText, "updateTablenameIfAnd2");
  }

}