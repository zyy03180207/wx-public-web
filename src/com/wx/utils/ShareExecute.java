package com.wx.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.ICallback;

public class ShareExecute implements ICallback {
	public String startDate = null;
	public String endDate = null;
	
	@Override
	public Object call(Connection conn) throws SQLException {
		CallableStatement proc = null;
		try {
			String procedure = "{call WXCHECKMONEYS(?,?,?,?)}";
			proc = conn.prepareCall(procedure);
			proc.setString(1, this.startDate);
			proc.setString(2, this.endDate);
			proc.setString(3, this.startDate+"230000");
			proc.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
			proc.execute();
			return proc.getString(4);
		} finally {
			proc.close();
		}
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
