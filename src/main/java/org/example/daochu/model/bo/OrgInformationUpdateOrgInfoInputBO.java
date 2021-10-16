package org.example.daochu.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgInformationUpdateOrgInfoInputBO {
  private String _id;

  private BigInteger _newgrade;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    args.add(_newgrade);
    return args;
  }
}
