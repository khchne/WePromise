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
public class HandicapRegisterRegisterInputBO {
  private String _id;

  private String _hPhone;

  private String _guardianName;

  private String _guardianPhone;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    args.add(_hPhone);
    args.add(_guardianName);
    args.add(_guardianPhone);
    return args;
  }
}
