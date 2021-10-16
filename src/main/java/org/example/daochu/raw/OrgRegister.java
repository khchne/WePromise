package org.example.daochu.raw;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class OrgRegister extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040526040805190810160405280600381526020017f6f72670000000000000000000000000000000000000000000000000000000000815250600190805190602001906200005192919062000205565b50611001600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550348015620000a257600080fd5b5060006040805190810160405280600b81526020017f4f72675265675461626c6500000000000000000000000000000000000000000081525060009080519060200190620000f292919062000205565b50600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a60006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016200016d919062000420565b602060405180830381600087803b1580156200018857600080fd5b505af11580156200019d573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250620001c39190810190620002ca565b90507fb5636cd912a73dcdb5b570dbe331dfa3e6435c93e029e642def2c8e40dacf21081604051620001f6919062000403565b60405180910390a15062000494565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200024857805160ff191683800117855562000279565b8280016001018555821562000279579182015b82811115620002785782518255916020019190600101906200025b565b5b5090506200028891906200028c565b5090565b620002b191905b80821115620002ad57600081600090555060010162000293565b5090565b90565b6000620002c282516200048a565b905092915050565b600060208284031215620002dd57600080fd5b6000620002ed84828501620002b4565b91505092915050565b620003018162000480565b82525050565b60008154600181166000811462000327576001811462000348576200038d565b607f600283041680865260ff1983166020870152604086019350506200038d565b6002820480865260208601955062000360856200046e565b60005b82811015620003845781548189015260018201915060208101905062000363565b80880195505050505b505092915050565b6000601782527f69642c206c6f67696e4e616d652c2070617373776f72640000000000000000006020830152604082019050919050565b6000600482527f736f7274000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b60006020820190506200041a6000830184620002f6565b92915050565b600060608201905081810360008301526200043c818462000307565b905081810360208301526200045181620003cc565b90508181036040830152620004668162000395565b905092915050565b60008160005260206000209050919050565b6000819050919050565b6000819050919050565b611b1b80620004a46000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633ffbd47f14610067578063706a7ca8146100a4578063cb100c04146100e1578063db015ff51461011e575b600080fd5b34801561007357600080fd5b5061008e60048036036100899190810190611373565b610149565b60405161009b91906116b7565b60405180910390f35b3480156100b057600080fd5b506100cb60048036036100c69190810190611373565b6105c5565b6040516100d89190611752565b60405180910390f35b3480156100ed57600080fd5b50610108600480360361010391908101906113df565b610b97565b60405161011591906116b7565b60405180910390f35b34801561012a57600080fd5b506101336110b7565b6040516101409190611730565b60405180910390f35b600080600080600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016101c79190611774565b602060405180830381600087803b1580156101e157600080fd5b505af11580156101f5573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061021991908101906112e0565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561027f57600080fd5b505af1158015610293573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506102b791908101906112b7565b91508173ffffffffffffffffffffffffffffffffffffffff1663e942b51660016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161030f9190611869565b600060405180830381600087803b15801561032957600080fd5b505af115801561033d573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161039691906118be565b600060405180830381600087803b1580156103b057600080fd5b505af11580156103c4573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516866040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161041d9190611834565b600060405180830381600087803b15801561043757600080fd5b505af115801561044b573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5166040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104a290611928565b600060405180830381600087803b1580156104bc57600080fd5b505af11580156104d0573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac366001846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161052c9291906117c6565b602060405180830381600087803b15801561054657600080fd5b505af115801561055a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061057e9190810190611309565b90507fe71002dee81d9ff68a8184c07ed89508062d232ea9979314fd048b99aca6f25e81876040516105b19291906116d2565b60405180910390a180935050505092915050565b6060600080600080600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960006040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016106459190611774565b602060405180830381600087803b15801561065f57600080fd5b505af1158015610673573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061069791908101906112e0565b93508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156106fd57600080fd5b505af1158015610711573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506107359190810190611265565b92508273ffffffffffffffffffffffffffffffffffffffff1663cd30a1d160016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161078d9190611869565b600060405180830381600087803b1580156107a757600080fd5b505af11580156107bb573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016108149190611834565b600060405180830381600087803b15801561082e57600080fd5b505af1158015610842573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161089b91906118f3565b600060405180830381600087803b1580156108b557600080fd5b505af11580156108c9573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663e8434e396001856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610925929190611796565b602060405180830381600087803b15801561093f57600080fd5b505af1158015610953573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610977919081019061128e565b915060008273ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156109df57600080fd5b505af11580156109f3573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610a179190810190611309565b131515610a365760206040519081016040528060008152509450610b8d565b8173ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610a8c9190611715565b602060405180830381600087803b158015610aa657600080fd5b505af1158015610aba573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610ade91908101906112b7565b90508073ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610b339061189e565b600060405180830381600087803b158015610b4d57600080fd5b505af1158015610b61573d600080","3e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250610b8a9190810190611332565b94505b5050505092915050565b6000806000806000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c960006040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610c179190611774565b602060405180830381600087803b158015610c3157600080fd5b505af1158015610c45573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610c6991908101906112e0565b93508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610ccf57600080fd5b505af1158015610ce3573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610d0791908101906112b7565b92508273ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610d5e91906118f3565b600060405180830381600087803b158015610d7857600080fd5b505af1158015610d8c573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610df457600080fd5b505af1158015610e08573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250610e2c9190810190611265565b91508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d160016040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610e849190611869565b600060405180830381600087803b158015610e9e57600080fd5b505af1158015610eb2573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610f0b91906118be565b600060405180830381600087803b158015610f2557600080fd5b505af1158015610f39573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401610f9291906118f3565b600060405180830381600087803b158015610fac57600080fd5b505af1158015610fc0573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a1600185856040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161101e939291906117f6565b602060405180830381600087803b15801561103857600080fd5b505af115801561104c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506110709190810190611309565b90507fe0a12646844336ff8c309afb06bcf2500cb0003d52dc6945a63f5b0767113938816040516110a191906116b7565b60405180910390a1809450505050509392505050565b60008054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561114d5780601f106111225761010080835404028352916020019161114d565b820191906000526020600020905b81548152906001019060200180831161113057829003601f168201915b505050505081565b60006111618251611a06565b905092915050565b60006111758251611a18565b905092915050565b60006111898251611a2a565b905092915050565b600061119d8251611a3c565b905092915050565b60006111b18251611a4e565b905092915050565b600082601f83011215156111cc57600080fd5b81356111df6111da82611988565b61195b565b915080825260208301602083018583830111156111fb57600080fd5b611206838284611a8e565b50505092915050565b600082601f830112151561122257600080fd5b815161123561123082611988565b61195b565b9150808252602083016020830185838301111561125157600080fd5b61125c838284611a9d565b50505092915050565b60006020828403121561127757600080fd5b600061128584828501611155565b91505092915050565b6000602082840312156112a057600080fd5b60006112ae84828501611169565b91505092915050565b6000602082840312156112c957600080fd5b60006112d78482850161117d565b91505092915050565b6000602082840312156112f257600080fd5b600061130084828501611191565b91505092915050565b60006020828403121561131b57600080fd5b6000611329848285016111a5565b91505092915050565b60006020828403121561134457600080fd5b600082015167ffffffffffffffff81111561135e57600080fd5b61136a8482850161120f565b91505092915050565b6000806040838503121561138657600080fd5b600083013567ffffffffffffffff8111156113a057600080fd5b6113ac858286016111b9565b925050602083013567ffffffffffffffff8111156113c957600080fd5b6113d5858286016111b9565b9150509250929050565b6000806000606084860312156113f457600080fd5b600084013567ffffffffffffffff81111561140e57600080fd5b61141a868287016111b9565b935050602084013567ffffffffffffffff81111561143757600080fd5b611443868287016111b9565b925050604084013567ffffffffffffffff81111561146057600080fd5b61146c868287016111b9565b9150509250925092565b61147f81611a58565b82525050565b61148e81611a6a565b82525050565b61149d816119fc565b82525050565b6114ac81611a7c565b82525050565b60006114bd826119d1565b8084526114d1816020860160208601611a9d565b6114da81611ad0565b602085010191505092915050565b60006114f3826119c6565b808452611507816020860160208601611a9d565b61151081611ad0565b602085010191505092915050565b60008154600181166000811461153b576001811461155b5761159c565b607f600283041680865260ff19831660208701526040860193505061159c565b60028204808652602086019550611571856119b4565b60005b8281101561159357815481890152600182019150602081019050611574565b80880195505050505b505092915050565b6000600982527f6c6f67696e4e616d6500000000000000000000000000000000000000000000006020830152604082019050919050565b6000600482527f736f7274000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600282527f69640000000000000000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600882527f70617373776f72640000000000000000000000000000000000000000000000006020830152604082019050919050565b6000600682527f31323334353600000000000000000000000000000000000000000000000000006020830152604082019050919050565b60006020820190506116cc6000830184611494565b92915050565b60006060820190506116e76000830185611494565b81810360208301526116f981846114b2565b9050818103604083015261170c81611680565b90509392505050565b600060208201905061172a60008301846114a3565b92915050565b6000602082019050818103600083015261174a81846114e8565b905092915050565b6000602082019050818103600083015261176c81846114b2565b905092915050565b6000602082019050818103600083015261178e818461151e565b905092915050565b600060408201905081810360008301526117b0818561151e565b90506117bf6020830184611476565b9392505050565b600060408201905081810360008301526117e0818561151e565b90506117ef6020830184611485565b9392505050565b60006060820190508181036000830152611810818661151e565b905061181f6020830185611485565b61182c6040830184611476565b949350505050565b6000604082019050818103600083015261184d816115a4565b9050818103602083015261186181846114b2565b905092915050565b60006040820190508181036000830152611882816115db565b90508181036020830152611896818461151e565b905092915050565b600060208201905081810360008301526118b781611612565b9050919050565b600060408201905081810360008301526118d781611612565b905081810360208301526118eb81846114b2565b905092915050565b6000604082019050818103600083015261190c81611649565b9050818103602083015261192081846114b2565b905092915050565b6000604082019050818103600083015261194181611649565b9050818103602083015261195481611680565b9050919050565b6000604051905081810181811067ffffffffffffffff8211171561197e57600080fd5b8060405250919050565b600067ffffffffffffffff82111561199f57600080fd5b601f19601f8301169050602081019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000611a11826119dc565b9050919050565b6000611a23826119dc565b9050919050565b6000611a35826119dc565b9050919050565b6000611a47826119dc565b9050919050565b6000819050919050565b6000611a63826119dc565b9050919050565b6000611a75826119dc565b9050919050565b6000611a87826119fc565b9050919050565b82818337600083830152505050565b60005b83811015611abb578082015181840152602081019050611aa0565b83811115611aca576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a723058201c09eb7690652563fc6556627d86cf3fdec26bb03ed36e12aef22c46ba366b646c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"},{\"name\":\"_loginName\",\"type\":\"string\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_loginName\",\"type\":\"string\"},{\"name\":\"_password\",\"type\":\"string\"}],\"name\":\"selectLoginInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"string\"},{\"name\":\"_oldpassword\",\"type\":\"string\"},{\"name\":\"_newpassword\",\"type\":\"string\"}],\"name\":\"modifyPassword\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"table_name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"CreateResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"ModifyResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"account\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"password\",\"type\":\"string\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_SELECTLOGININFO = "selectLoginInfo";

    public static final String FUNC_MODIFYPASSWORD = "modifyPassword";

    public static final String FUNC_TABLE_NAME = "table_name";

    public static final Event CREATERESULT_EVENT = new Event("CreateResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event MODIFYRESULT_EVENT = new Event("ModifyResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected OrgRegister(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt register(String _id, String _loginName) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(_id),
                new Utf8String(_loginName)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void register(String _id, String _loginName, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(_id),
                new Utf8String(_loginName)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForRegister(String _id, String _loginName) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(_id),
                new Utf8String(_loginName)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getRegisterInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getRegisterOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt selectLoginInfo(String _loginName, String _password) {
        final Function function = new Function(
                FUNC_SELECTLOGININFO, 
                Arrays.<Type>asList(new Utf8String(_loginName),
                new Utf8String(_password)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void selectLoginInfo(String _loginName, String _password, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SELECTLOGININFO, 
                Arrays.<Type>asList(new Utf8String(_loginName),
                new Utf8String(_password)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSelectLoginInfo(String _loginName, String _password) {
        final Function function = new Function(
                FUNC_SELECTLOGININFO, 
                Arrays.<Type>asList(new Utf8String(_loginName),
                new Utf8String(_password)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getSelectLoginInfoInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SELECTLOGININFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<String> getSelectLoginInfoOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SELECTLOGININFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt modifyPassword(String _id, String _oldpassword, String _newpassword) {
        final Function function = new Function(
                FUNC_MODIFYPASSWORD, 
                Arrays.<Type>asList(new Utf8String(_id),
                new Utf8String(_oldpassword),
                new Utf8String(_newpassword)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void modifyPassword(String _id, String _oldpassword, String _newpassword, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_MODIFYPASSWORD, 
                Arrays.<Type>asList(new Utf8String(_id),
                new Utf8String(_oldpassword),
                new Utf8String(_newpassword)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForModifyPassword(String _id, String _oldpassword, String _newpassword) {
        final Function function = new Function(
                FUNC_MODIFYPASSWORD, 
                Arrays.<Type>asList(new Utf8String(_id),
                new Utf8String(_oldpassword),
                new Utf8String(_newpassword)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<String, String, String> getModifyPasswordInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MODIFYPASSWORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getModifyPasswordOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_MODIFYPASSWORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public String table_name() throws ContractException {
        final Function function = new Function(FUNC_TABLE_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public List<CreateResultEventResponse> getCreateResultEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATERESULT_EVENT, transactionReceipt);
        ArrayList<CreateResultEventResponse> responses = new ArrayList<CreateResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateResultEventResponse typedResponse = new CreateResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeCreateResultEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(CREATERESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeCreateResultEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(CREATERESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<ModifyResultEventResponse> getModifyResultEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MODIFYRESULT_EVENT, transactionReceipt);
        ArrayList<ModifyResultEventResponse> responses = new ArrayList<ModifyResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ModifyResultEventResponse typedResponse = new ModifyResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeModifyResultEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(MODIFYRESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeModifyResultEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(MODIFYRESULT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.password = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeRegisterEventEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(REGISTEREVENT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeRegisterEventEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(REGISTEREVENT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static OrgRegister load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new OrgRegister(contractAddress, client, credential);
    }

    public static OrgRegister deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(OrgRegister.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class CreateResultEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }

    public static class ModifyResultEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;
    }

    public static class RegisterEventEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger count;

        public String account;

        public String password;
    }
}
