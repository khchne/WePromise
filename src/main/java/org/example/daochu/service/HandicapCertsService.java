package org.example.daochu.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.daochu.model.bo.HandicapCertsInsertCertInputBO;
import org.example.daochu.model.bo.HandicapCertsRemoveCertInputBO;
import org.example.daochu.model.bo.HandicapCertsSelectByidInputBO;
import org.example.daochu.model.bo.HandicapCertsUpdateCertInputBO;
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
public class HandicapCertsService {
  public static final String ABI = org.example.daochu.utils.IOUtil.readResourceAsString("abi/HandicapCerts.abi");

  public static final String BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/ecc/HandicapCerts.bin");

  public static final String SM_BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/sm/HandicapCerts.bin");

  @Value("${system.contract.handicapCertsAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse removeCert(HandicapCertsRemoveCertInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "removeCert", input.toArgs());
  }

  public CallResponse selectAll() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectAll", Arrays.asList());
  }

  public TransactionResponse updateCert(HandicapCertsUpdateCertInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateCert", input.toArgs());
  }

  public CallResponse table_name() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "table_name", Arrays.asList());
  }

  public TransactionResponse insertCert(HandicapCertsInsertCertInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "insertCert", input.toArgs());
  }

  public CallResponse selectByid(HandicapCertsSelectByidInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectByid", input.toArgs());
  }
}
