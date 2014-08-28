package com.stratio.meta2.core.statements;

import com.stratio.meta.core.metadata.MetadataManager;
import com.stratio.meta.core.utils.Tree;
import com.stratio.meta2.core.engine.validator.Validation;
import com.stratio.meta2.core.engine.validator.ValidationRequirements;

public class DetachConnectorStatement extends MetaStatement {

  private String name;

  public DetachConnectorStatement(String name){
    this.name = name;
  }

  @Override
  public String toString() {
    return "DETACH CONNECTOR "+name;
  }

  @Override
  public String translateToCQL() {
    return null;
  }

  @Override
  public Tree getPlan(MetadataManager metadataManager, String targetKeyspace) {
    return null;
  }

  @Override
  public ValidationRequirements getValidationRequirements() {
    return new ValidationRequirements().add(Validation.MUST_EXIST_CLUSTER)
        .add(Validation.MUST_EXIST_ATTACH_CONNECTOR_CLUSTER);
  }
}
