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
public class OrgRegisterSelectLoginInfoInputBO {
  private String _loginName;

  private String _password;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_loginName);
    args.add(_password);
    return args;
  }
}
