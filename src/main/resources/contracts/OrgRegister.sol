pragma solidity ^0.4.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";

contract OrgRegister {
    event CreateResult(int256 count);
    event ModifyResult(int256 count);
    event RegisterEvent(int256 count, string account, string password);
    
    struct OrgReg{
        string[] id;        // 组织id
        string[] loginName; // 登录名
        string[] password;  // 密码
    }
    
    string public table_name;
    string internal primaryKey = "org";
    TableFactory tf = TableFactory(0x1001);  // 将TableFactory的地址固定为0x1001
        
    // 构造函数创建表
    constructor() public {
        table_name = "OrgRegTable";
        int count = tf.createTable(
            table_name,
            "sort", //主键
            "id, loginName, password");
        
        emit CreateResult(count);
    }

    // 注册
    function register(string _id, string _loginName) public returns(int256)
    {
        Table table = tf.openTable(table_name);
        
        Entry entry = table.newEntry();
        entry.set("sort", primaryKey);
        entry.set("id", _id);
        entry.set("loginName", _loginName);
        entry.set("password", "123456"); //默认密码

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
    
    // 登录认证
    function selectLoginInfo(string _loginName, string _password) public returns(string)
    {
        Table table = tf.openTable(table_name); //打开表
        
        Condition condition = table.newCondition();
        condition.EQ("sort", primaryKey);
        condition.EQ("loginName", _loginName);
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