pragma solidity ^0.4.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract HandicapRegister {
    event CreateResult(int256 count);
    event ModifyResult(int256 count);
    event RegisterEvent(int256 count, string account, string password);
    
    struct HReg{
        string[] id;
        string[] password;
        string[] hPhone;
        string[] guardianName;
        string[] guardianPhone;
    }
    
    string public table_name;
    string internal primaryKey = "cjr";
    TableFactory tf = TableFactory(0x1001);  // 将TableFactory的地址固定为0x1001
        
    // 构造函数创建表
    constructor() public {
        table_name = "handicapRegs";
        int count = tf.createTable(
            table_name,
            "sort", //主键
            "id, password, hPhone, guardianName, guardianPhone");
        
        emit CreateResult(count);
    }

    // 注册
    function register(string _id, string _hPhone, string _guardianName, string _guardianPhone) public returns(int256)
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("sort", primaryKey);
        entry.set("id", _id);
        entry.set("password", "123456"); //默认密码
        entry.set("hPhone", _hPhone);
        entry.set("guardianName", _guardianName);
        entry.set("guardianPhone", _guardianPhone);
        
        int256 count = table.insert(primaryKey, entry);
        emit RegisterEvent(count, _id, "123456");
        return count;
    }
    
    // 改密
    function modifyPassword(string _id, string _oldpassword, string _newpassword) public returns(int256)
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("password", _newpassword);
        
        Condition condition = table.newCondition();
        condition.EQ("sort", primaryKey);
        condition.EQ("id", _id);
        condition.EQ("password", _oldpassword);
        
        int256 count = table.update(primaryKey, entry, condition);
        emit ModifyResult(count);
        return count;
    }
    
    // 通过身份证号查信息
    function selectByid(string _id) public constant returns(string[], string[], string[], string[])
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("sort", primaryKey);
        condition.EQ("id", _id);
        
	    Entries entries = table.select(primaryKey, condition);
        Entry entry = entries.get(0);
    
        HReg hReg;
        hReg.id             = new string[](1);
        hReg.hPhone         = new string[](1);
        hReg.guardianName   = new string[](1);
        hReg.guardianPhone  = new string[](1);
        
        hReg.id[0]              = entry.getString("id");
        hReg.hPhone[0]          = entry.getString("hPhone");
        hReg.guardianName[0]    = entry.getString("guardianName");
        hReg.guardianPhone[0]   = entry.getString("guardianPhone");
        
        return (hReg.id, hReg.hPhone, hReg.guardianName, hReg.guardianPhone);
    }
    
    // 登录认证
    function selectLoginInfo(string _id, string _password) public returns(string)
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("sort", primaryKey);
        condition.EQ("id", _id);
        condition.EQ("password", _password);
        
	    Entries entries = table.select(primaryKey, condition);
        if(entries.size()<=0)
            return "";
        else
        {
            Entry entry = entries.get(0);
            return entry.getString("id");
        }
    }
}