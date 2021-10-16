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
public class ServiceRecordApplyServiceInputBO {
  private String _id;

  private String _hId;

  private String _startTime;

  private String _serAddress;

  private String _detail;

  private BigInteger _service;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_id);
    args.add(_hId);
    args.add(_startTime);
    args.add(_serAddress);
    args.add(_detail);
    args.add(_service);
    return args;
  }
}
