pragma solidity ^0.4.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract OrgInformation{
    event CreateResult(int count);
    event InsertResult(int count);
    event UpdateResult(int count);
    event RemoveResult(int count);

    struct OrgInfoList{
        string[]    idList;         // 组织id
        string[]    nameList;       // 组织名称
        int[]       serviceList;    // 组织服务
        int[]       gradeList;      // 组织评分
    }
    string public table_name;
    string internal primaryKey = "org";
    TableFactory tf = TableFactory(0x1001); // 将TableFactory的地址固定为0x1001
        
    // 构造函数创建表
    constructor() public {
        table_name = "OrgInfoTable";
        int count = tf.createTable(
            table_name,
            "sort", //主键
            "id, name, service, grade");
        
        emit CreateResult(count);
    }
    
    // 插入数据
    function insertOrgInfo(string  _id, string  _name, int  _service, int  _grade) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
	    Entry entry = table.newEntry(); 
        entry.set("sort", primaryKey);
        entry.set("id", _id);
        entry.set("name", _name);
        entry.set("service", _service);
        entry.set("grade", _grade);
                
        int count = table.insert(primaryKey, entry);
        emit InsertResult(count);
        return count;
    }
    
    // 更新评分
    function updateOrgInfo(string _id, int _newgrade) public returns(int) 
    {
        Table table = tf.openTable(table_name);

        Entry entry = table.newEntry();
        entry.set("grade", _newgrade);
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
        int count = table.update(primaryKey, entry, condition);
        emit UpdateResult(count);
        return count;
    }

    // 删除数据
    function removeOrgInfo(string _id) public returns(int)
    {
        Table table = tf.openTable(table_name);
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
        int count = table.remove(primaryKey, condition);
        emit RemoveResult(count);
        return count;
    }
    
    // 用组织id查询单条记录
    function selectByid(string _id) public constant returns(string[], string[], int[], int[])
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
	    Entries entries = table.select(primaryKey, condition);
        OrgInfoList memory orginfoList;
        orginfoList.idList          = new string[](uint256(entries.size()));
        orginfoList.nameList        = new string[](uint256(entries.size()));
        orginfoList.serviceList     = new int[](uint256(entries.size()));
        orginfoList.gradeList       = new int[](uint256(entries.size()));

        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            orginfoList.idList[uint256(i)]       = entry.getString("id");
            orginfoList.nameList[uint256(i)]     = entry.getString("name");
            orginfoList.serviceList[uint256(i)]  = entry.getInt("service");
            orginfoList.gradeList[uint256(i)]    = entry.getInt("grade");
        }
        
        
        return (orginfoList.idList, orginfoList.nameList, orginfoList.serviceList, orginfoList.gradeList);
    }
    
    // 用组织服务查询多条记录
    function selectByservice(int _service) public constant returns(string[], string[], int[], int[])
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("service", _service);
        
	    Entries entries = table.select(primaryKey, condition);
        OrgInfoList memory orginfoList;
        orginfoList.idList          = new string[](uint256(entries.size()));
        orginfoList.nameList        = new string[](uint256(entries.size()));
        orginfoList.serviceList     = new int[](uint256(entries.size()));
        orginfoList.gradeList       = new int[](uint256(entries.size()));

        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            orginfoList.idList[uint256(i)]       = entry.getString("id");
            orginfoList.nameList[uint256(i)]     = entry.getString("name");
            orginfoList.serviceList[uint256(i)]  = entry.getInt("service");
            orginfoList.gradeList[uint256(i)]    = entry.getInt("grade");
        }
        
        return (orginfoList.idList, orginfoList.nameList, orginfoList.serviceList, orginfoList.gradeList);
    }
    
    // 用空条件查询所有组织
    function selectAll() public constant returns(string[], string[], int[], int[])
    {
        Table table = tf.openTable(table_name); //打开表
        
        Entries entries = table.select(primaryKey, table.newCondition());
        
        OrgInfoList memory orginfoList;
        orginfoList.idList          = new string[](uint256(entries.size()));
        orginfoList.nameList        = new string[](uint256(entries.size()));
        orginfoList.serviceList     = new int[](uint256(entries.size()));
        orginfoList.gradeList       = new int[](uint256(entries.size()));

        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            orginfoList.idList[uint256(i)]       = entry.getString("id");
            orginfoList.nameList[uint256(i)]     = entry.getString("name");
            orginfoList.serviceList[uint256(i)]  = entry.getInt("service");
            orginfoList.gradeList[uint256(i)]    = entry.getInt("grade");
        }
        
        return (orginfoList.idList, orginfoList.nameList, orginfoList.serviceList, orginfoList.gradeList);
    }
}