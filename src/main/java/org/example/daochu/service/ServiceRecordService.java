package org.example.daochu.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.daochu.model.bo.ServiceRecordApplyServiceInputBO;
import org.example.daochu.model.bo.ServiceRecordSelectByIdInputBO;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class ServiceRecordService {
  public static final String ABI = org.example.daochu.utils.IOUtil.readResourceAsString("abi/ServiceRecord.abi");

  public static final String BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/ecc/ServiceRecord.bin");

  public static final String SM_BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/sm/ServiceRecord.bin");

  @Value("${system.contract.serviceRecordAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse selectById(ServiceRecordSelectByIdInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectById", input.toArgs());
  }

  public CallResponse table_name() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "table_name", Arrays.asList());
  }

  public TransactionResponse applyService(ServiceRecordApplyServiceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "applyService", input.toArgs());
  }
}
