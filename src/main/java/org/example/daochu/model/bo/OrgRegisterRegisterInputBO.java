package org.example.daochu.model.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgRegisterRegisterInputBO {
  private String _id;

  private String _loginName;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    args.add(_loginName);
    return args;
  }
}
