pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract ServiceRemark{
    event CreateResult(int count);
    event InsertResult(int count);
    event UpdateResult(int count);
    
    struct SerRmkList {
        string[] idList;
        string[] orgIdList;
        string[] endTimeList;
        string[] commentList;
        int[]    stateList;
        int[]    gradeList;
    }

    string public table_name;
    string internal primaryKey = "serrmk";
    TableFactory tf = TableFactory(0x1001); // 将TableFactory的地址固定为0x1001
    
    // 构造函数创建表
    constructor() public 
    {
        table_name = "SerRmkTable";
        
        int count = tf.createTable(
            table_name,
            "sort", //主键，无意义
            "id, orgId, endTime, comment, state, grade");
        
        emit CreateResult(count);
    }
    
    // 申请服务
    function applyService(string _id, string _orgId, int _state) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
	    Entry entry = table.newEntry();
        entry.set("sort", primaryKey);
        entry.set("id", _id);
        entry.set("orgId", _orgId);
        entry.set("state", _state); // 1表示已申请
        
        int count = table.insert(primaryKey, entry);
        emit InsertResult(count);
        return (count);
    }
    
    // 组织确认接受/完成服务
    function confirmService(string _id, int _state) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("state", _state); // 2表示已接受，3表示已完成
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
        int count = table.update(primaryKey, entry, condition);
        emit UpdateResult(count);
        return (count);
    }

    // 组织拒绝服务
    function rejectService(string _id, string _endTime, string _comment, int _state) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("endTime", _endTime); // 拒绝时间
        entry.set("comment", _comment); // 拒绝理由
        entry.set("state", _state);     // 4表示已拒绝
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
        int count = table.update(primaryKey, entry, condition);
        emit UpdateResult(count);
        return (count);
    }
    
    // 用户评价服务
    function commentService(string  _id, string _endTime, string _comment, int _grade) public returns(int) 
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("endTime", _endTime); // 评价时间
        entry.set("comment", _comment); // 用户评价
        entry.set("grade", _grade);     // 用户评分
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
        int count = table.update(primaryKey, entry, condition);
        emit UpdateResult(count);
        return (count);
    }

    // 用服务状态查询多条记录
    function selectByState(int _state) public constant 
        returns(string[], string[], string[], string[], int[], int[])        
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("state", _state);
        
	    Entries entries = table.select(primaryKey, condition);
        SerRmkList memory serRmkList;
        serRmkList.idList          = new string[](uint256(entries.size()));
        serRmkList.orgIdList       = new string[](uint256(entries.size()));
        serRmkList.endTimeList     = new string[](uint256(entries.size()));
        serRmkList.commentList     = new string[](uint256(entries.size()));
        serRmkList.stateList       = new int[](uint256(entries.size()));
        serRmkList.gradeList       = new int[](uint256(entries.size()));
        
        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            serRmkList.idList[uint256(i)]           = entry.getString("id");
            serRmkList.orgIdList[uint256(i)]        = entry.getString("orgId");
            serRmkList.endTimeList[uint256(i)]      = entry.getString("endTime");
            serRmkList.commentList[uint256(i)]      = entry.getString("comment");
            serRmkList.stateList[uint256(i)]        = entry.getInt("state");
            serRmkList.gradeList[uint256(i)]        = entry.getInt("grade");
        }
        
        return (serRmkList.idList, serRmkList.orgIdList, serRmkList.endTimeList, 
            serRmkList.commentList, serRmkList.stateList, serRmkList.gradeList);
    }
    
    // 用组织id查询多条记录
    function selectByOrgId(string _orgId) public constant 
        returns(string[], string[], string[], string[], int[], int[])        
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("orgId", _orgId);
        
	    Entries entries = table.select(primaryKey, condition);
        SerRmkList memory serRmkList;
        serRmkList.idList          = new string[](uint256(entries.size()));
        serRmkList.orgIdList       = new string[](uint256(entries.size()));
        serRmkList.endTimeList     = new string[](uint256(entries.size()));
        serRmkList.commentList     = new string[](uint256(entries.size()));
        serRmkList.stateList       = new int[](uint256(entries.size()));
        serRmkList.gradeList       = new int[](uint256(entries.size()));
        
        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            serRmkList.idList[uint256(i)]           = entry.getString("id");
            serRmkList.orgIdList[uint256(i)]        = entry.getString("orgId");
            serRmkList.endTimeList[uint256(i)]      = entry.getString("endTime");
            serRmkList.commentList[uint256(i)]      = entry.getString("comment");
            serRmkList.stateList[uint256(i)]        = entry.getInt("state");
            serRmkList.gradeList[uint256(i)]        = entry.getInt("grade");
        }
        
        return (serRmkList.idList, serRmkList.orgIdList, serRmkList.endTimeList, 
            serRmkList.commentList, serRmkList.stateList, serRmkList.gradeList);
    }
    
    // // 用空条件查询全部记录
    // function selectAll() public constant 
    //     returns(string[], string[], string[], string[], int[], int[])         
    // {
    //     Table table = tf.openTable(table_name); //打开表
        
	   // Entries entries = table.select(primaryKey, table.newCondition());
    //     SerRmkList memory serRmkList;
    //     serRmkList.idList          = new string[](uint256(entries.size()));
    //     serRmkList.orgIdList       = new string[](uint256(entries.size()));
    //     serRmkList.endTimeList     = new string[](uint256(entries.size()));
    //     serRmkList.commentList     = new string[](uint256(entries.size()));
    //     serRmkList.stateList       = new int[](uint256(entries.size()));
    //     serRmkList.gradeList       = new int[](uint256(entries.size()));
        
    //     for(int i=0; i<entries.size(); ++i) 
    //     {
    //         Entry entry = entries.get(i);
            
    //         serRmkList.idList[uint256(i)]           = entry.getString("id");
    //         serRmkList.orgIdList[uint256(i)]        = entry.getString("orgId");
    //         serRmkList.endTimeList[uint256(i)]      = entry.getString("endTime");
    //         serRmkList.commentList[uint256(i)]      = entry.getString("comment");
    //         serRmkList.stateList[uint256(i)]        = entry.getInt("state");
    //         serRmkList.gradeList[uint256(i)]        = entry.getInt("grade");
    //     }
        
    //     return (serRmkList.idList, serRmkList.orgIdList, serRmkList.endTimeList, 
    //         serRmkList.commentList, serRmkList.stateList, serRmkList.gradeList);
    // }
    
    // 用服务id查询单条记录
    function selectById(string _id) public constant 
        returns(string[], string[], string[], string[], int[], int[])         
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("id", _id);
        
	    Entries entries = table.select(primaryKey, condition);
        SerRmkList memory serRmkList;
        serRmkList.idList          = new string[](uint256(entries.size()));
        serRmkList.orgIdList       = new string[](uint256(entries.size()));
        serRmkList.endTimeList     = new string[](uint256(entries.size()));
        serRmkList.commentList     = new string[](uint256(entries.size()));
        serRmkList.stateList       = new int[](uint256(entries.size()));
        serRmkList.gradeList       = new int[](uint256(entries.size()));
        
        for(int i=0; i<entries.size(); ++i) 
        {
            Entry entry = entries.get(i);
            
            serRmkList.idList[uint256(i)]           = entry.getString("id");
            serRmkList.orgIdList[uint256(i)]        = entry.getString("orgId");
            serRmkList.endTimeList[uint256(i)]      = entry.getString("endTime");
            serRmkList.commentList[uint256(i)]      = entry.getString("comment");
            serRmkList.stateList[uint256(i)]        = entry.getInt("state");
            serRmkList.gradeList[uint256(i)]        = entry.getInt("grade");
        }
        
        return (serRmkList.idList, serRmkList.orgIdList, serRmkList.endTimeList, 
            serRmkList.commentList, serRmkList.stateList, serRmkList.gradeList);
    }
}