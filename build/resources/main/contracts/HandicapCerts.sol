pragma solidity ^0.4.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract HandicapCerts{
    event CreateResult(int count);
    event InsertResult(int count);
    event UpdateResult(int count);
    event RemoveResult(int count);

    struct HCert{
        string[]    id;         // 残疾人身份证
        string[]    name;       // 残疾人姓名
        string[]    certId;     // 残疾证id
        int[]       hCategory;  // 残疾种类
        int[]       hLevel;     // 残疾水平
    }
    string public table_name;
    string internal primaryKey = "cert";
    TableFactory tf = TableFactory(0x1001);  // 将TableFactory的地址固定为0x1001
    
    // 构造函数创建表
    constructor() public {
        table_name = "handicapCerts";
        int count = tf.createTable(
            table_name,
            "sort", //主键
            "id, name, certId, hCategory, hLevel");
        
        emit CreateResult(count);
    }
    
    // 插入数据
    function insertCert(string  _id, string  _name, string  _certId, int  _hCategory, int _hLevel) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
	    Entry entry = table.newEntry();
	    entry.set("sort", primaryKey);
        entry.set("id", _id);
        entry.set("name", _name);
        entry.set("certId", _certId);
        entry.set("hCategory", _hCategory);
        entry.set("hLevel", _hLevel);
                
        int count = table.insert(primaryKey, entry);
        emit InsertResult(count);
        return count;
    }

    // 通过身份证号查信息
    function selectByid(string _id) public constant returns(string[], string[], string[], int[], int[])
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
	    Entries entries = table.select(primaryKey, condition);
        
        HCert memory hCert;
        hCert.id        = new string[](uint256(entries.size()));
        hCert.name      = new string[](uint256(entries.size()));
        hCert.certId    = new string[](uint256(entries.size()));
        hCert.hCategory = new int[](uint256(entries.size()));
        hCert.hLevel    = new int[](uint256(entries.size()));
        
       for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
          
            hCert.id[uint256(i)]        = entry.getString("id");
            hCert.name[uint256(i)]      = entry.getString("name");
            hCert.certId[uint256(i)]    = entry.getString("certId");
            hCert.hCategory[uint256(i)] = entry.getInt("hCategory");
            hCert.hLevel[uint256(i)]    = entry.getInt("hLevel");
        }
        
        return (hCert.id, hCert.name, hCert.certId, hCert.hCategory, hCert.hLevel);
    }
    
    // 通过空条件查全部信息
    function selectAll() public constant returns(string[], string[], string[], int[], int[])
    {
        Table table = tf.openTable(table_name); //打开表
        
        Entries entries = table.select(primaryKey, table.newCondition());
        
        HCert memory hCert;
        hCert.id        = new string[](uint256(entries.size()));
        hCert.name      = new string[](uint256(entries.size()));
        hCert.certId    = new string[](uint256(entries.size()));
        hCert.hCategory = new int[](uint256(entries.size()));
        hCert.hLevel    = new int[](uint256(entries.size()));
        
       for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
          
            hCert.id[uint256(i)]        = entry.getString("id");
            hCert.name[uint256(i)]      = entry.getString("name");
            hCert.certId[uint256(i)]    = entry.getString("certId");
            hCert.hCategory[uint256(i)] = entry.getInt("hCategory");
            hCert.hLevel[uint256(i)]    = entry.getInt("hLevel");
        }
        
        return (hCert.id, hCert.name, hCert.certId, hCert.hCategory, hCert.hLevel);
    }
    
    // 更新
    function updateCert(string  _id, string  _name, string  _certId, int  _hCategory, int _hLevel) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("sort", primaryKey);
        entry.set("id", _id);
        entry.set("name", _name);
        entry.set("certId", _certId);
        entry.set("hCategory", _hCategory);
        entry.set("hLevel", _hLevel);
        
        int count = table.update(primaryKey, entry, table.newCondition());
        emit UpdateResult(count);
        return count;
    }

    // 删除数据
    function removeCert(string _id) public returns(int)
    {
        Table table = tf.openTable(table_name);
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
        int count = table.remove(primaryKey, condition);
        emit RemoveResult(count);
        return count;
    }
}