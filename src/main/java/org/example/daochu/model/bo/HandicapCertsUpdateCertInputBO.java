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
public class HandicapCertsUpdateCertInputBO {
  private String _id;

  private String _name;

  private String _certId;

  private BigInteger _hCategory;

  private BigInteger _hLevel;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    args.add(_name);
    args.add(_certId);
    args.add(_hCategory);
    args.add(_hLevel);
    return args;
  }
}
