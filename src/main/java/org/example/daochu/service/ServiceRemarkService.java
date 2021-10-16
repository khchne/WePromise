package org.example.daochu.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.daochu.model.bo.ServiceRemarkApplyServiceInputBO;
import org.example.daochu.model.bo.ServiceRemarkCommentServiceInputBO;
import org.example.daochu.model.bo.ServiceRemarkConfirmServiceInputBO;
import org.example.daochu.model.bo.ServiceRemarkRejectServiceInputBO;
import org.example.daochu.model.bo.ServiceRemarkSelectByIdInputBO;
import org.example.daochu.model.bo.ServiceRemarkSelectByOrgIdInputBO;
import org.example.daochu.model.bo.ServiceRemarkSelectByStateInputBO;
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
public class ServiceRemarkService {
  public static final String ABI = org.example.daochu.utils.IOUtil.readResourceAsString("abi/ServiceRemark.abi");

  public static final String BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/ecc/ServiceRemark.bin");

  public static final String SM_BINARY = org.example.daochu.utils.IOUtil.readResourceAsString("bin/sm/ServiceRemark.bin");

  @Value("${system.contract.serviceRemarkAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse confirmService(ServiceRemarkConfirmServiceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "confirmService", input.toArgs());
  }

  public TransactionResponse applyService(ServiceRemarkApplyServiceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "applyService", input.toArgs());
  }

  public CallResponse selectById(ServiceRemarkSelectByIdInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectById", input.toArgs());
  }

  public TransactionResponse commentService(ServiceRemarkCommentServiceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "commentService", input.toArgs());
  }

  public CallResponse table_name() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "table_name", Arrays.asList());
  }

  public CallResponse selectByState(ServiceRemarkSelectByStateInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectByState", input.toArgs());
  }

  public CallResponse selectAll() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectAll", Arrays.asList());
  }

  public CallResponse selectByOrgId(ServiceRemarkSelectByOrgIdInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectByOrgId", input.toArgs());
  }

  public TransactionResponse rejectService(ServiceRemarkRejectServiceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "rejectService", input.toArgs());
  }
}
