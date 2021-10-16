package org.example.daochu.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.daochu.model.bo.OrgInformationInsertOrgInfoInputBO;
import org.example.daochu.model.bo.OrgInformationRemoveOrgInfoInputBO;
import org.example.daochu.model.bo.OrgInformationSelectByidInputBO;
import org.example.daochu.model.bo.OrgInformationSelectByserviceInputBO;
import org.example.daochu.model.bo.OrgInformationUpdateOrgInfoInputBO;
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
public class OrgInformationService {
  public static final String ABI = org.example.daochu.utils.IOUtil.readResourceAsString("abi/OrgInformation.abi");

  public static final String BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/ecc/OrgInformation.bin");

  public static final String SM_BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/sm/OrgInformation.bin");

  @Value("${system.contract.orgInformationAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse updateOrgInfo(OrgInformationUpdateOrgInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateOrgInfo", input.toArgs());
  }

  public TransactionResponse insertOrgInfo(OrgInformationInsertOrgInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "insertOrgInfo", input.toArgs());
  }

  public CallResponse selectByid(OrgInformationSelectByidInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectByid", input.toArgs());
  }

  public TransactionResponse removeOrgInfo(OrgInformationRemoveOrgInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "removeOrgInfo", input.toArgs());
  }

  public CallResponse selectByservice(OrgInformationSelectByserviceInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectByservice", input.toArgs());
  }

  public CallResponse selectAll() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectAll", Arrays.asList());
  }

  public CallResponse table_name() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "table_name", Arrays.asList());
  }
}
