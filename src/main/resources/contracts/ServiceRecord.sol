pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract ServiceRecord{
    event CreateResult(int count);
    event InsertResult(int count);
    
    struct SerRecList {
        string[] idList;
        string[] hIdList;
        string[] startTimeList;
        string[] serAddressList;
        string[] detailList;
        int[]    serviceList;
    }

    string public table_name;
    TableFactory tf = TableFactory(0x1001); // 将TableFactory的地址固定为0x1001
    
    // 构造函数创建表
    constructor() public 
    {
        table_name = "SerRecTable";
        
        int count = tf.createTable(
            table_name,
            "id", //主键，服务id
            "hId, startTime, serAddress, detail, service");
        
        emit CreateResult(count);
    }
    
    // 申请服务
    function applyService(string _id, string _hId, string _startTime, 
        string _serAddress, string _detail, int _service) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
	    Entry entry = table.newEntry();
        entry.set("id", _id);
        entry.set("hId", _hId);
        entry.set("startTime", _startTime);
        entry.set("serAddress", _serAddress);
        entry.set("detail", _detail);
        entry.set("service", _service);
        
        int count = table.insert(_id, entry);
        emit InsertResult(count);
        return (count);
    }
    
    // 用服务id查询单条记录
    function selectById(string _id) public constant 
        returns(string[], string[], string[], string[], string[], int[])        
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
	    Entries entries = table.select(_id, condition);
        SerRecList memory srList;
        srList.idList          = new string[](uint256(entries.size()));
        srList.hIdList         = new string[](uint256(entries.size()));
        srList.startTimeList   = new string[](uint256(entries.size()));
        srList.serAddressList  = new string[](uint256(entries.size()));
        srList.detailList      = new string[](uint256(entries.size()));
        srList.serviceList     = new int[](uint256(entries.size()));
        
        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            srList.idList[uint256(i)]           = entry.getString("id");
            srList.hIdList[uint256(i)]          = entry.getString("hId");
            srList.startTimeList[uint256(i)]    = entry.getString("startTime");
            srList.serAddressList[uint256(i)]   = entry.getString("serAddress");
            srList.detailList[uint256(i)]       = entry.getString("detail");
            srList.serviceList[uint256(i)]      = entry.getInt("service");
        }
        
        return (srList.idList, srList.hIdList, srList.startTimeList, 
            srList.serAddressList, srList.detailList, srList.serviceList);
    }
}