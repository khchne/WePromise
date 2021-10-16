package org.example.daochu.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.daochu.config.SystemConfig;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Slf4j
public class ServiceManager {
  @Autowired
  private SystemConfig config;

  @Autowired
  private Client client;

  List<String> hexPrivateKeyList;

  @PostConstruct
  public void init() {
    hexPrivateKeyList = Arrays.asList(this.config.getHexPrivateKey().split(","));
  }

  /**
   * @notice: must use @Qualifier("TableService") with @Autowired to get this Bean
   */
  @Bean("TableService")
  public Map<String, TableService> initTableServiceManager() throws Exception {
    Map<String, TableService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	TableService tableService = new TableService();
    	tableService.setAddress(this.config.getContract().getTableAddress());
    	tableService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	tableService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, tableService);
    }
    log.info("++++++++TableService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("ServiceRemarkService") with @Autowired to get this Bean
   */
  @Bean("ServiceRemarkService")
  public Map<String, ServiceRemarkService> initServiceRemarkServiceManager() throws Exception {
    Map<String, ServiceRemarkService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	ServiceRemarkService serviceRemarkService = new ServiceRemarkService();
    	serviceRemarkService.setAddress(this.config.getContract().getServiceRemarkAddress());
    	serviceRemarkService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	serviceRemarkService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, serviceRemarkService);
    }
    log.info("++++++++ServiceRemarkService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("ServiceRecordService") with @Autowired to get this Bean
   */
  @Bean("ServiceRecordService")
  public Map<String, ServiceRecordService> initServiceRecordServiceManager() throws Exception {
    Map<String, ServiceRecordService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	ServiceRecordService serviceRecordService = new ServiceRecordService();
    	serviceRecordService.setAddress(this.config.getContract().getServiceRecordAddress());
    	serviceRecordService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	serviceRecordService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, serviceRecordService);
    }
    log.info("++++++++ServiceRecordService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("OrgRegisterService") with @Autowired to get this Bean
   */
  @Bean("OrgRegisterService")
  public Map<String, OrgRegisterService> initOrgRegisterServiceManager() throws Exception {
    Map<String, OrgRegisterService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	OrgRegisterService orgRegisterService = new OrgRegisterService();
    	orgRegisterService.setAddress(this.config.getContract().getOrgRegisterAddress());
    	orgRegisterService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	orgRegisterService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, orgRegisterService);
    }
    log.info("++++++++OrgRegisterService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("OrgInformationService") with @Autowired to get this Bean
   */
  @Bean("OrgInformationService")
  public Map<String, OrgInformationService> initOrgInformationServiceManager() throws Exception {
    Map<String, OrgInformationService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	OrgInformationService orgInformationService = new OrgInformationService();
    	orgInformationService.setAddress(this.config.getContract().getOrgInformationAddress());
    	orgInformationService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	orgInformationService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, orgInformationService);
    }
    log.info("++++++++OrgInformationService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("HandicapRegisterService") with @Autowired to get this Bean
   */
  @Bean("HandicapRegisterService")
  public Map<String, HandicapRegisterService> initHandicapRegisterServiceManager() throws Exception {
    Map<String, HandicapRegisterService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	HandicapRegisterService handicapRegisterService = new HandicapRegisterService();
    	handicapRegisterService.setAddress(this.config.getContract().getHandicapRegisterAddress());
    	handicapRegisterService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	handicapRegisterService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, handicapRegisterService);
    }
    log.info("++++++++HandicapRegisterService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("HandicapCertsService") with @Autowired to get this Bean
   */
  @Bean("HandicapCertsService")
  public Map<String, HandicapCertsService> initHandicapCertsServiceManager() throws Exception {
    Map<String, HandicapCertsService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	HandicapCertsService handicapCertsService = new HandicapCertsService();
    	handicapCertsService.setAddress(this.config.getContract().getHandicapCertsAddress());
    	handicapCertsService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	handicapCertsService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, handicapCertsService);
    }
    log.info("++++++++HandicapCertsService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("ChargeInfoService") with @Autowired to get this Bean
   */
  @Bean("ChargeInfoService")
  public Map<String, ChargeInfoService> initChargeInfoServiceManager() throws Exception {
    Map<String, ChargeInfoService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	ChargeInfoService chargeInfoService = new ChargeInfoService();
    	chargeInfoService.setAddress(this.config.getContract().getChargeInfoAddress());
    	chargeInfoService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	chargeInfoService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, chargeInfoService);
    }
    log.info("++++++++ChargeInfoService map:{}", serviceMap);
    return serviceMap;
  }
}
