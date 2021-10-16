pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract ChargeInfo{
    event CreateResult(int count);
    event InsertResult(int count);
    
    struct ChargeInfoList {
        string[] idList;
        string[] chargeNameList;
        string[] chargePhoneList;
    }

    string public table_name;
    TableFactory tf = TableFactory(0x1001); // 将TableFactory的地址固定为0x1001
    
    // 构造函数创建表
    constructor() public 
    {
        table_name = "ChargeInfoTable";
        
        int count = tf.createTable(
            table_name,
            "id", //主键，服务id
            "chargeName, chargePhone");
        
        emit CreateResult(count);
    }
    
    // 添加服务负责人信息
    function insert(string _id, string _chargeName, string _chargePhone) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
	    Entry entry = table.newEntry();
        entry.set("id", _id);
        entry.set("chargeName", _chargeName);
        entry.set("chargePhone", _chargePhone);
        
        int count = table.insert(_id, entry);
        emit InsertResult(count);
        return (count);
    }
    
    // 用id查询单条服务记录
    function selectById(string _id) public constant returns(string[], string[], string[])        
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
	    Entries entries = table.select(_id, condition);
        ChargeInfoList memory ciList;
        ciList.idList           = new string[](uint256(entries.size()));
        ciList.chargeNameList   = new string[](uint256(entries.size()));
        ciList.chargePhoneList  = new string[](uint256(entries.size()));
        
        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            ciList.idList[uint256(i)]           = entry.getString("id");
            ciList.chargeNameList[uint256(i)]   = entry.getString("chargeName");
            ciList.chargePhoneList[uint256(i)]  = entry.getString("chargePhone");
        }
        
        return (ciList.idList, ciList.chargeNameList, ciList.chargePhoneList);
    }
}