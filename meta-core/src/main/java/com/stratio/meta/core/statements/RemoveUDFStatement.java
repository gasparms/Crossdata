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

package com.stratio.meta.core.statements;

import com.stratio.meta.core.metadata.MetadataManager;
import com.stratio.meta.core.utils.Tree;

public class RemoveUDFStatement extends MetaStatement {

    /**
     * The target jar name.
     */
    private String jarName = null;

    /**
     * Class constructor.
     * @param jarName The name of the target jar.
     */
    public RemoveUDFStatement(String jarName){
        this.command = true;
        this.jarName = jarName;
    }

    @Override
    public String toString() {
            return "REMOVE UDF \"" + jarName + "\"";
    }

    @Override
    public String translateToCQL(MetadataManager metadataManager) {
        return this.toString();
    }

    @Override
    public Tree getPlan(MetadataManager metadataManager, String targetKeyspace) {
        return new Tree();
    }
    
}