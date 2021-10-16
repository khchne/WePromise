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
public class HandicapRegisterModifyPasswordInputBO {
  private String _id;

  private String _oldpassword;

  private String _newpassword;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    args.add(_oldpassword);
    args.add(_newpassword);
    return args;
  }
}
