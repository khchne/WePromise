package org.example.daochu.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.daochu.model.bo.OrgRegisterModifyPasswordInputBO;
import org.example.daochu.model.bo.OrgRegisterRegisterInputBO;
import org.example.daochu.model.bo.OrgRegisterSelectLoginInfoInputBO;
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
public class OrgRegisterService {
  public static final String ABI = org.example.daochu.utils.IOUtil.readResourceAsString("abi/OrgRegister.abi");

  public static final String BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/ecc/OrgRegister.bin");

  public static final String SM_BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/sm/OrgRegister.bin");

  @Value("${system.contract.orgRegisterAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse modifyPassword(OrgRegisterModifyPasswordInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "modifyPassword", input.toArgs());
  }

  public TransactionResponse selectLoginInfo(OrgRegisterSelectLoginInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "selectLoginInfo", input.toArgs());
  }

  public TransactionResponse register(OrgRegisterRegisterInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "register", input.toArgs());
  }

  public CallResponse table_name() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "table_name", Arrays.asList());
  }
}
